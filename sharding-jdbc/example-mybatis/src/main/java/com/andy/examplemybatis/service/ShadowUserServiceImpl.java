package com.andy.examplemybatis.service;

import com.andy.exampleapi.entity.ShadowUser;
import com.andy.exampleapi.repository.ShadowUserRepository;
import com.andy.exampleapi.service.ExampleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShadowUserServiceImpl implements ExampleService {

    @Resource
    private ShadowUserRepository shadowUserRepository;

    @Override
    public void initEnvironment() throws SQLException {
        shadowUserRepository.createTableIfNotExists();
        shadowUserRepository.truncateTable();
    }

    @Override
    public void cleanEnvironment() throws SQLException {
        shadowUserRepository.dropTable();
    }

    @Override
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> userIds = insertData();
        printData();
        deleteData(userIds);
        printData();
        System.out.println("-------------- Process Success Finish --------------");
    }

    @Override
    public void processFailure() throws SQLException {
        System.out.println("-------------- Process Failure Begin ---------------");
        insertData();
        System.out.println("-------------- Process Failure Finish --------------");
        throw new RuntimeException("Exception occur for transaction test.");
    }

    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print User Data -----------------------");
        for (Object each : shadowUserRepository.selectAll()) {
            System.out.println(each);
        }
    }


    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            ShadowUser user = new ShadowUser();
            user.setUserId(i);
            user.setUserType(i % 2);
            user.setUsername("test_mybatis_" + i);
            user.setPwd("pwd_mybatis_" + i);
            shadowUserRepository.insert(user);
            result.add((long) user.getUserId());
        }
        return result;
    }

    private void deleteData(final List<Long> userIds) throws SQLException {
        System.out.println("---------------------------- Delete Data ----------------------------");
        for (Long each : userIds) {
            shadowUserRepository.delete(each);
        }
    }
}
