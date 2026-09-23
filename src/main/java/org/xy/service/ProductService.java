package org.xy.service;

import com.baomidou.mybatisplus.spring.service.IService;
import org.xy.Bo.ProductPublishBo;
import org.xy.Bo.ProductQueryBo;
import org.xy.Vo.ProductDetailVO;
import org.xy.common.PageVO;
import org.xy.common.Result;
import org.xy.entity.Product;

public class ProductService implements IService<Product> {
    // 发布：入参是 BO + 卖家 id（从 UserContext 来）
    // 首页列表：入参 BO，出参分页包实体
    // 详情：只有 id 入参，出参是加工过的 VO
    // 下架：id + 当前登录人（用来判越权）
}
