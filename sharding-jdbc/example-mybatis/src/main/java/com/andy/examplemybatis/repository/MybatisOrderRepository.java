package com.andy.examplemybatis.repository;

import com.andy.exampleapi.repository.OrderRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisOrderRepository extends OrderRepository {
}
