package com.andy.eurekaconsumer.config.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Andy
 * Created time on 2022/3/10 15:11
 * @description 自定义URL限流触发后处理逻辑
 */
@Component
public class CustomUrlBlockHandler implements BlockExceptionHandler {
    /**
     * Handle the request when blocked.
     *
     * @param request  Servlet request
     * @param response Servlet response
     * @param e        the block exception
     * @throws Exception users may throw out the BlockException or other error occurs
     */
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setStatus(500);

        PrintWriter out = response.getWriter();
        out.print("Blocked by Sentinel (flow limiting)111");
        out.flush();
        out.close();
    }
}
