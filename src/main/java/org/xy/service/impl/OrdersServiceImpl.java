package org.xy.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.xy.entity.Orders;
import org.xy.mapper.OrdersMapper;
import org.xy.service.OrdersService;

public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService
{
}
