package org.xy.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.xy.entity.Orders;
import org.xy.mapper.OrdersMapper;
import org.xy.service.OrdersService;
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService
{
}
