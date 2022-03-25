package com.andy.examplemybatis.service;

import com.andy.exampleapi.entity.Address;
import com.andy.exampleapi.entity.Order;
import com.andy.exampleapi.entity.OrderItem;
import com.andy.exampleapi.repository.AddressRepository;
import com.andy.exampleapi.repository.OrderItemRepository;
import com.andy.exampleapi.repository.OrderRepository;
import com.andy.exampleapi.service.ExampleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class OrderServiceImpl implements ExampleService {

    @Resource
    private OrderRepository orderRepository;
    @Resource
    private OrderItemRepository orderItemRepository;
    @Resource
    private AddressRepository addressRepository;

    @Override
    public void initEnvironment() throws SQLException {

        orderRepository.createTableIfNotExists();
        orderItemRepository.createTableIfNotExists();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        initAddressTable();
    }

    private void initAddressTable() throws SQLException {
        addressRepository.createTableIfNotExists();
        addressRepository.truncateTable();

        for (int i = 1; i <= 10; i++) {

            Address address = new Address();
            address.setAddressId((long) i);
            address.setAddressName("addressName" + i);
            addressRepository.insert(address);
        }
    }

    @Override
    public void cleanEnvironment() throws SQLException {
        orderRepository.dropTable();
        orderItemRepository.dropTable();
        addressRepository.dropTable();
    }

    @Override
    @Transactional
    public void processSuccess() throws SQLException {
        System.out.println("-------------- Process Success Begin ---------------");
        List<Long> orderIds = insertData();
        printData();
        //deleteData(orderIds);
       // printData();
       // System.out.println("-------------- Process Success Finish --------------");
    }

    @Transactional
    @Override
    public void processFailure() throws SQLException {
        System.out.println("-------------- Process Failure Begin ---------------");
        insertData();
        System.out.println("-------------- Process Failure Finish --------------");
        throw new RuntimeException("Exception occur for transaction test.");
    }

    @Override
    public void printData() throws SQLException {
        System.out.println("---------------------------- Print Order Data -----------------------");
        for (Object each : orderRepository.selectAll()) {
            System.out.println(each);
        }
        System.out.println("---------------------------- Print OrderItem Data -------------------");
        for (Object each : orderItemRepository.selectAll()) {
            System.out.println(each);
        }
    }



    private List<Long> insertData() throws SQLException {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            orderRepository.insert(order);
            OrderItem item = new OrderItem();
            item.setOrderId(order.getOrderId());
            item.setUserId(i);
            item.setStatus("INSERT_TEST");
            orderItemRepository.insert(item);
            result.add(order.getOrderId());
        }
        return result;
    }

    private void deleteData(final List<Long> orderIds) throws SQLException {
        System.out.println("---------------------------- Delete Data ----------------------------");
        for (Long each : orderIds) {
            orderRepository.delete(each);
            orderItemRepository.delete(each);
        }
    }

}
