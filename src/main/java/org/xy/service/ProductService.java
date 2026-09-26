package org.xy.service;

import com.baomidou.mybatisplus.spring.service.IService;
import org.xy.Bo.ProductPublishBo;
import org.xy.Bo.ProductQueryBo;
import org.xy.Vo.ProductDetailVO;
import org.xy.common.PageVO;
import org.xy.common.Result;
import org.xy.entity.Product;

public interface ProductService extends IService<Product> {
    // 发布：入参是 BO + 卖家 id（从 UserContext 来）
    Result<Void>publish(ProductPublishBo bo,Long sellerid);
    // 首页列表：入参 BO，出参分页包实体
    Result<PageVO<Product>> pageList(ProductQueryBo bo);
    // 详情：只有 id 入参，出参是加工过的 VO
    Result<ProductDetailVO>getDetail(Long id);
    // 下架：id + 当前登录人（用来判越权）
    Result<Void>offShelf(Long id,Long sellerId);
}

