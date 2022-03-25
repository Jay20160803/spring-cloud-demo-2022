package com.andy.examplemybatis.repository;

import com.andy.exampleapi.repository.OrderItemRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisOrderItemRepository extends OrderItemRepository {
}
