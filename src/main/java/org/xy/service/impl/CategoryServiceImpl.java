package org.xy.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xy.entity.Category;
import org.xy.mapper.CategoryMapper;
import org.xy.service.CategoryService;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService
{
}
