package org.xy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xy.Bo.ProductQueryBo;
import org.xy.Vo.ProductDetailVO;
import org.xy.common.PageVO;
import org.xy.common.Result;
import org.xy.entity.Product;
import org.xy.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public  Result<PageVO<Product>> pageList(ProductQueryBo bo){
        return  productService.pageList(bo);
    }
    @GetMapping("/{id}")
    public Result<ProductDetailVO> getDetail(@PathVariable Long id){
        return productService.getDetail(id);
    }
}
