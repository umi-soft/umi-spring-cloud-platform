package cn.umisoft.admin;

import cn.umisoft.admin.properties.SystemAdminProperties;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(SystemAdminProperties.class)
public class SystemAdminStarter implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SystemAdminStarter.class, args);
	}
	
	/**
	 * @description: <p>验证码生成配置bean</p>
	 * @author: hujie@umisoft.cn
	 * @date: 2019/2/20 12:58 AM
	 */
	@Bean
	public Producer producer() {
		Properties props = new Properties();
		props.put("kaptcha.border", "no");
		props.put("kaptcha.textproducer.char.length","6");
		props.put("kaptcha.image.height","40");
		props.put("kaptcha.image.width","200");
		props.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
		props.put("kaptcha.textproducer.font.color","black");
		props.put("kaptcha.textproducer.font.size","30");
//		props.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
		props.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
		props.put("kaptcha.textproducer.char.string","acdefhkmnprtwxy2345678");
		Config config = new Config(props);
		return config.getProducerImpl();
	}


}

