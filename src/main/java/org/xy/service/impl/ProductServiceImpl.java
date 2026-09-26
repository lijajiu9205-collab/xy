package org.xy.service.impl;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xy.Bo.ProductPublishBo;
import org.xy.Bo.ProductQueryBo;
import org.xy.Vo.ProductDetailVO;
import org.xy.common.PageVO;
import org.xy.common.Result;
import org.xy.entity.Category;
import org.xy.entity.Product;
import org.xy.entity.ProductImage;
import org.xy.entity.User;
import org.xy.mapper.ProductMapper;
import org.xy.service.CategoryService;
import org.xy.service.ProductImageService;
import org.xy.service.ProductService;
import org.xy.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    private final CategoryService categoryService;
    private final ProductImageService productImageService;
    private final UserService userService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> publish(ProductPublishBo bo, Long sellerId) {
        //必填校验：1.验证title/price/categoryId为空
        if(StrUtil.isBlank(bo.getTitle()))
            return Result.error(400,"标题不为空");
        if (bo.getPrice()==null)
            return Result.error(400,"售价不为空");
        if(bo.getCategoryId()==null)
            return Result.error(400,"分类不为空");
        //价格校验：2、compareTo(BigDecimal.ZERO)
        if(bo.getPrice().compareTo(BigDecimal.ZERO)<=0)
            return Result.error(400,"售价必须大于零");
        //分类校验：3.categoryService.getById(bo.getCategoryId())
        if(categoryService.getById(bo.getCategoryId())==null)
            return Result.error(400,"类型不正确");
        if (bo.getConditionLv() == null)
            return Result.error(400, "请选择成色");
        //4.Product product =new Product();
        Product product =new Product();
        //5.补sellerId/status=1/viewCount=0/isDeleted=0
        product.setTitle(bo.getTitle());
        product.setDescription(bo.getDescription());
        product.setPrice(bo.getPrice());
        product.setConditionLv(bo.getConditionLv());
        product.setCategoryId(bo.getCategoryId());
        product.setCoverImg(bo.getCoverImg());
        product.setTradePlace(bo.getTradePlace());
        product.setSellerId(sellerId);
        product.setStatus(1);
        product.setViewCount(0);
        product.setIsDeleted(0);
        //6.save(Product);
        save(product);
        //7.图片:bo.getImages()非空时，遍历组装list<ProduptImage>
        List<ProductImage> imageList = new ArrayList<>();
        if(!(bo.getImages()==null)) {
            for (int i = 0; i < bo.getImages().size(); i++) {
                ProductImage pi = new ProductImage();
                pi.setProductId(product.getId());
                pi.setImgUrl(bo.getImages().get(i));
                pi.setSort(i+1);
                imageList.add(pi);
            }
            //8，productImageService.saveBatch(list);
            productImageService.saveBatch(imageList);
        }
        return Result.success(null);
    }

    @Override
    public Result<PageVO<Product>>pageList(ProductQueryBo bo) {
        long pageNum=(bo.getPageNum()==null||bo.getPageNum()<1)?1:bo.getPageNum();
        long pageSize=(bo.getPageSize()==null||bo.getPageSize()<1)?10:bo.getPageSize();
        //封装page页
        Page<Product> page= lambdaQuery()//定义page封装查询
                .eq(Product::getStatus,1)//查找状态为在售的
                .eq(Product::getIsDeleted,0)//查找未被删除的
                .eq(bo.getCategoryId()!=null,Product::getCategoryId,bo.getCategoryId())//查找分类不为空的
                .like(StrUtil.isNotBlank(bo.getKeyword()), Product::getTitle, bo.getKeyword())//如果按关键词查找提供出标题
                .orderByDesc(Product::getCreateTime)//按创建时间倒叙排序
                .page(new Page<>(pageNum,pageSize));//封装为page页，从第pageNum页开始，每页pagesize条数据
        return Result.success(PageVO.of(page));//封装成功，将page封装入参传回
    }

    @Override
    public Result<ProductDetailVO> getDetail(Long id) {
        //1.查商品
        Product product=lambdaQuery()
                .eq(Product::getId,id)
                .eq(Product::getIsDeleted,0)
                .one();
        if (product==null){
            return Result.error(404,"商品不存在");
        }
        //2.增加浏览量
        lambdaUpdate()
                .setSql("view_count=view_count+1")
                .eq(Product::getId,id)
                .update();
        //3.分类名
        Category category=categoryService.getById(product.getCategoryId());
        //4.卖家昵称
        User seller=userService.getById(product.getSellerId());
        //5.图片数组
        List<String>images=productImageService.lambdaQuery()
                .eq(ProductImage::getProductId,id)
                .orderByAsc(ProductImage::getSort)
                .list()
                .stream()
                .map(ProductImage::getImgUrl)
                .toList();

        //6.组装vo
        ProductDetailVO vo=new ProductDetailVO();
        BeanUtil.copyProperties(product,vo);
        vo.setCategoryName(category==null?null:category.getName());
        vo.setSellerNickname(seller==null?null:seller.getNickname());
        vo.setImages(images);
        return Result.success(vo);
    }

    @Override
    public Result<Void> offShelf(Long id, Long sellerId) {
        boolean shelf=lambdaUpdate()
                .set(Product::getStatus,4)
                .eq(Product::getId,id)
                .eq(Product::getSellerId,sellerId)
                .eq(Product::getStatus,1)
                .update();
        if(shelf)
            return Result.success(null);
        else
            return Result.error(403,"商品不存在或当前状态不可下架");
    }


}
