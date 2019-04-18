package cn.umisoft.gateway;

import cn.umisoft.util.api.ApiResultCode;
import cn.umisoft.util.jwt.JwtProperties;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({ JwtProperties.class })
public class SpringCloudGatewayStarter {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayStarter.class, args);
    }
    // TODO 完善Gateway全局异常处理
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();

        config.setSerializerFeatures(SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);

        // 便于动态SQL 枚举映射
        SerializeConfig serializeConfig = new SerializeConfig();
        for (ApiResultCode item : ApiResultCode.values()) {
            serializeConfig.configEnumAsJavaBean(item.getClass());
        }
        config.setSerializeConfig(serializeConfig);

        fastConverter.setFastJsonConfig(config);
        return new HttpMessageConverters(fastConverter);
    }
}
