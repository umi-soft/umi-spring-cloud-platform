package cn.umisoft.ribbon;

import cn.umisoft.ribbon.properties.RestTemplateProperties;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(RestTemplateProperties.class)
public class RestTemplateAutoConfigure {

    @Autowired
    protected RestTemplateProperties properties;

    private static String HTTP = "http";
    private static String HTTPS = "https";

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(httpRequestFactory());
        return restTemplate;
    }

    /**
     * @description: <p>httpRequestFactory工厂</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/26 4:59 PM
     */
    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    /**
     * @description: <p>使用连接池的 httpclient</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/3/26 4:59 PM
     */
    @Bean
    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register(HTTP, PlainConnectionSocketFactory.getSocketFactory())
                .register(HTTPS, SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        // 最大链接数
        connectionManager.setMaxTotal(properties.getMaxTotal());
        // 同路由并发数20
        connectionManager.setDefaultMaxPerRoute(properties.getMaxPerRoute());

        RequestConfig requestConfig = RequestConfig.custom()
                // 读超时
                .setSocketTimeout(properties.getReadTimeout())
                // 链接超时
                .setConnectTimeout(properties.getConnectTimeout())
                // 链接不够用的等待时间
                .setConnectionRequestTimeout(properties.getConnectionRequestTimeout())
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(properties.getRetryCount(), true))
                .build();
    }
}
