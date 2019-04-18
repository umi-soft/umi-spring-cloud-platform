package cn.umisoft.gateway.config;

import cn.umisoft.gateway.security.RedisMemorySecurityMetadataSource;
import cn.umisoft.util.enums.RedisKeyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * TODO 系统管理微服务中，对角色权限变更时，主动下发redis消息，网关服务订阅后，强制刷新authorityMap convertAndSend方法
 * @description: <p>订阅redis消息，主动刷新系统全局权限集合信息</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/4/17 7:49 PM
 */
@Configuration
@AutoConfigureAfter(SecurityWebFluxConfigration.class)
@Slf4j
public class RedisMessageConfigration {
    private static final String RECEIVE_METHOD = "receiveMessage";

    @Autowired
    private RedisMemorySecurityMetadataSource securityMetadataSource;

    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(securityMetadataSourceMessageListener(), ChannelTopic.of(RedisKeyEnum.FORCE_REFRESH_PLATFORM_PERMISSIONS_KEY.toString()));
        return container;
    }

    // RedisMemorySecurityMetadataSource

    @Bean
    MessageListenerAdapter securityMetadataSourceMessageListener() {
        return new MessageListenerAdapter(new RefreshSecurityMetadataSourceMessage(), RECEIVE_METHOD);
    }

    public class RefreshSecurityMetadataSourceMessage {
        public void receiveMessage(String message) {
            log.info(String.format("接受到redis消息：%s", message));
            try {
                securityMetadataSource.initResourceMap(true);
                log.info(String.format("处理完成redis消息：%s", message));
            } catch (Exception e) {
                e.printStackTrace();
                log.info(String.format("处理redis消息：%s\n发生如下异常：\n", e.getLocalizedMessage()));
            }
        }
    }
}
