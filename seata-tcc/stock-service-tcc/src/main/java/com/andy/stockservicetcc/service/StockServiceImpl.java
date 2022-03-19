/*
 *  Copyright 1999-2021 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.andy.stockservicetcc.service;

import com.andy.stockservicetcc.entity.Stock;
import com.andy.stockservicetcc.repository.StockDAO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Program Name: springcloud-nacos-seata
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/8/28 4:05 PM
 */
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Resource
    private StockDAO stockDAO;


    /**
     * Prepare boolean.
     *
     * @param actionContext the action context
     * @param commodityCode
     * @param count
     * @return the boolean
     */
    @Override
    public boolean prepare(BusinessActionContext actionContext, String commodityCode, Integer count) {

        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }

        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Stock().setCommodityCode(commodityCode));
        Stock stock = stockDAO.selectOne(wrapper);
        stock.setCount(stock.getCount() - count);

        stockDAO.updateById(stock);

        log.info("StockServiceImpl prepare, commodityCode:{}, count:{}",commodityCode,count);
        return true;
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
        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        Integer count = (Integer) actionContext.getActionContext("count");

        log.info("StockServiceImpl commit, xid:" + xid
                + ", commodityCode:" + commodityCode
                + ", count:" + count);
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


        String commodityCode = (String) actionContext.getActionContext("commodityCode");
        Integer count = (Integer) actionContext.getActionContext("count");
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Stock().setCommodityCode(commodityCode));
        Stock stock = stockDAO.selectOne(wrapper);
        stock.setCount(stock.getCount() + count);

        stockDAO.updateById(stock);

        log.info("StockServiceImpl rollback, xid:" + actionContext.getXid() +
                ", commodityCode:" + commodityCode
                + "count:" + count);
        return true;
    }
}
