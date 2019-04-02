package cn.umisoft.ribbon;

import cn.umisoft.util.context.UmiUserContextHolder;
import cn.umisoft.util.jwt.JwtUtils;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: <p>Feign配置</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/26 4:49 PM
 */
@Configuration
public class FeignAutoConfigure {

    /**
     * @description: <p>Feign 日志级别</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/26 5:00 PM
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * @description: <p>创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/31 11:34 AM
     */
    @Bean
    public FeignTokenRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignTokenRequestInterceptor();
    }

    public class FeignTokenRequestInterceptor  implements RequestInterceptor {

        public FeignTokenRequestInterceptor() { }

        @Override
        public void apply(RequestTemplate template) {
            template.header(JwtUtils.HEAD_TOKEN_KEY, UmiUserContextHolder.getJwtToken());
        }
    }
}
