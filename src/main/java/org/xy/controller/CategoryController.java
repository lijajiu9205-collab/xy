package org.xy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xy.common.Result;
import org.xy.entity.Category;
import org.xy.service.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public Result<List<Category>> list(){
        return Result.success(categoryService.list());
    }
}
