package com.andy.eurekaconsumer.config.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andy
 * Created time on 2022/3/10 14:39
 * @description
 */
@Component
@Order(1)
public class SentinelRuleApplicationRunner implements ApplicationRunner {




    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    public void run(ApplicationArguments args) throws Exception {

        //流控规则
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource("/hello");
        // set limit qps to 10
        rule.setCount(10);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

        //熔断降级规则
        List<DegradeRule> degradeRules = new ArrayList();
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("helloDegrade");
        // set threshold RT, 10 ms
        degradeRule.setCount(50);
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        degradeRule.setTimeWindow(10);
        degradeRule.setSlowRatioThreshold(0.1);
        degradeRule.setMinRequestAmount(2);
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);
    }
}
