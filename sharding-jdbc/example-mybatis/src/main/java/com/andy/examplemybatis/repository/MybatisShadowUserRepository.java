package com.andy.examplemybatis.repository;

import com.andy.exampleapi.repository.ShadowUserRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisShadowUserRepository extends ShadowUserRepository {
}
