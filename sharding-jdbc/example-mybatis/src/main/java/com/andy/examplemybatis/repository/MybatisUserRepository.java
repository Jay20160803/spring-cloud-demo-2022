package com.andy.examplemybatis.repository;

import com.andy.exampleapi.repository.UserRepository;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisUserRepository extends UserRepository {
}
