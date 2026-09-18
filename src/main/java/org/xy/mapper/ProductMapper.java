package org.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xy.entity.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
