package cn.umisoft.sentinel;

import cn.umisoft.util.api.ApiResultWrapper;
import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: <p>自定义限流、熔断处理</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/27 3:41 PM
 */
public class UmiSentinelUrlBlockHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().print(ApiResultWrapper.sentinelBlockException().toJSONString());
    }

}
