package org.xy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.xy.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
