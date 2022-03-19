package com.andy.orderservicetcc.service;

import com.andy.orderservicetcc.model.Order;
import com.andy.orderservicetcc.repository.OrderDAO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Andy
 * @Date: 2022/03/19/ 14:46
 * @Description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDAO orderDAO;
    /**
     * Prepare boolean.
     *
     * @param actionContext the action context
     * @param userId
     * @param commodityCode
     * @param count
     * @return the boolean
     */
    @Override
    public boolean prepare(BusinessActionContext actionContext,
                           @BusinessActionContextParameter(paramName = "userId")String userId,
                           @BusinessActionContextParameter(paramName = "commodityCode")String commodityCode,
                           @BusinessActionContextParameter(paramName = "count")Integer count) {


        try{

            BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
            Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(
                    orderMoney);
            orderDAO.insert(order);

            return true;
        }catch (Exception e){
            log.error("OrderServiceImpl.prepare,userId:{},commodityCode:{},count:{},exception:{}",userId,commodityCode,count,e);
            return false;
        }



    }

    /**
     * Commit boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    @Override
    public boolean commit(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        log.info("OrderServiceImpl commit, xid:" + xid + ", userId:" + actionContext.getActionContext("userId"));
        return true;
    }

    /**
     * Rollback boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        String xid = actionContext.getXid();
        String userId = (String) actionContext.getActionContext("userId");
        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        Integer count = (Integer) actionContext.getActionContext("count");


        Map<String,Object> param = new HashMap<>();
        param.put("user_id",userId);
        param.put("commodity_code",commodityCode);
        param.put("count",count);

        try{
            orderDAO.deleteByMap(param);
        }catch (Exception e){
            log.error("OrderServiceImpl rollback, xid:" + xid + ", userId:" + userId + ", commodityCode:" + commodityCode
                    + ", count:" + count
                    + "exception: " + e);

        }
        log.info("OrderServiceImpl rollback, xid:" + xid + ", userId:" + userId + ", commodityCode:" + commodityCode + ", count:" + count);
        return true;
    }
}
