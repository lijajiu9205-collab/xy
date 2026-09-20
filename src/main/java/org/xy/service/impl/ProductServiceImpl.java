package org.xy.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xy.entity.Product;
import org.xy.mapper.ProductMapper;
import org.xy.service.ProductService;
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService
{
}
