package org.xy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.xy.Bo.ProductPublishBo;
import org.xy.common.Result;
import org.xy.common.UserContext;
import org.xy.service.ProductService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/my/product")
public class MyProductController {
    private final ProductService productService;
    @PostMapping
    public Result<Void> publish (@RequestBody ProductPublishBo bo){
        return productService.publish(bo, UserContext.getUserId());
    }
    @PutMapping("/{id}/off")
    public Result<Void>offShelf(@PathVariable Long id){
        return productService.offShelf(id,UserContext.getUserId());
    }
}
