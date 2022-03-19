package com.andy.orderservicetcc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

/**
 * @Author Andy
 * @Date: 2022/03/17/ 16:32
 * @Description
 */
@Component
public class TransactionUtils {


    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;


    /**
     * 开启事务
     * @return
     */
    public TransactionStatus begin(){
       return transactionManager.getTransaction(transactionDefinition);
    }

    /**
     * 提交事务
     * @param transactionStatus
     */
    public void commit(TransactionStatus transactionStatus){
        transactionManager.commit(transactionStatus);
    }

    /**
     * 回滚事务
     * @param transactionStatus
     */
    public void rollback(TransactionStatus transactionStatus){
        transactionManager.rollback(transactionStatus);
    }


}
