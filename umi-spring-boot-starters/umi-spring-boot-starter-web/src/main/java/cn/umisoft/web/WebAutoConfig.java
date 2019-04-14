package cn.umisoft.web;

import cn.umisoft.web.util.UmiQueryConditionTypeEnum;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @description: <p>通用bean组件配置</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/26 10:52 PM
 */
@Import({ MyBatisPlusAutoConfig.class, GlobalExceptionHandler.class, RequestMappingApplicationRunner.class})
@Configuration
public class WebAutoConfig {
    /**
     * @description: <p>FastJson整合</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/12 9:44 PM
     * @return: org.springframework.boot.autoconfigure.http.HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();

        config.setSerializerFeatures(SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);

        // 便于动态SQL 枚举映射
        SerializeConfig serializeConfig = new SerializeConfig();
        for (UmiQueryConditionTypeEnum item : UmiQueryConditionTypeEnum.values()) {
            serializeConfig.configEnumAsJavaBean(item.getClass());
        }
        config.setSerializeConfig(serializeConfig);

        fastConverter.setFastJsonConfig(config);
        return new HttpMessageConverters(fastConverter);
    }
    /**
     * @description: <p>防止feign客户端与spring mvc RequestMappingHandlerMapping冲突</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/4/13 11:44 PM
     */
    @Bean
    @ConditionalOnClass({Feign.class})
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    private class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }
}
