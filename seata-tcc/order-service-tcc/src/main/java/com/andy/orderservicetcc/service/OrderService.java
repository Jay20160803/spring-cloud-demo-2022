package com.andy.orderservicetcc.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @Author Andy
 * @Date: 2022/03/19/ 14:43
 * @Description
 */
@LocalTCC
public interface OrderService {


    /**
     * Prepare boolean.
     *
     * @param actionContext the action context
     * @param userId
     * @param commodityCode
     * @param count
     * @return the boolean
     */
    @TwoPhaseBusinessAction(name = "orderTccService", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean prepare(BusinessActionContext actionContext,
                           @BusinessActionContextParameter(paramName = "userId")String userId,
                           @BusinessActionContextParameter(paramName = "commodityCode")String commodityCode,
                           @BusinessActionContextParameter(paramName = "count")Integer count
                           );

    /**
     * Commit boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    public boolean commit(BusinessActionContext actionContext);

    /**
     * Rollback boolean.
     *
     * @param actionContext the action context
     * @return the boolean
     */
    public boolean rollback(BusinessActionContext actionContext);

}
