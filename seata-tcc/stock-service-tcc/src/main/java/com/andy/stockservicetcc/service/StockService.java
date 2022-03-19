package com.andy.stockservicetcc.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @Author Andy
 * @Date: 2022/03/19/ 15:24
 * @Description
 */
@LocalTCC
public interface StockService {


    /**
     * Prepare boolean.
     *
     * @param actionContext the action context
     * @param commodityCode
     * @param count
     * @return the boolean
     */
    @TwoPhaseBusinessAction(name = "stockTccService", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext,
                           @BusinessActionContextParameter(paramName = "commodityCode")String commodityCode,
                           @BusinessActionContextParameter(paramName = "count")Integer count
    );

    /**
     * Commit boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
     boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
     boolean rollback(BusinessActionContext actionContext);
}
