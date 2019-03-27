package cn.umisoft.web;

import cn.umisoft.web.util.UmiMetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * @description: <p>MyBatisPlus配置，详见：https://github.com/baomidou/mybatis-plus</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/12 9:53 PM
 */
public class MyBatisPlusAutoConfig {

    /**
     * @description: <p>MyBatisPlus 扫描配置,也可以使用@MapperScan("cn.umisoft.*.mapper")替代</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/12 10:48 PM
     * @return: org.mybatis.spring.mapper.MapperScannerConfigurer
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("cn.umisoft.*.*.mapper");
        return scannerConfigurer;
    }
    /**
     * @description: <p>分页配置</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/12 9:45 PM
     * @return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * @description: <p>实现数据逻辑删除</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/12 9:46 PM
     * @return: com.baomidou.mybatisplus.core.injector.ISqlInjector
     */
    @Bean
    public ISqlInjector logicDeleteByIdWithFill() {
        return new LogicSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList() {
                List<AbstractMethod> methodList = super.getMethodList();
                methodList.add(new LogicDeleteByIdWithFill());
                return methodList;
            }
        };
    }

    /**
     * @description: <p>设置 dev test 环境开启,SQL执行效率插件</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/12 10:13 PM
     * @return: com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * @description: <p>通用表结构字段值注入，如创建人、创建时间，最后修改人，最后修改时间</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/25 10:17 AM
     * @return: cn.umisoft.admin.util.mybatisplus.UmiMetaObjectHandler
     */
    @Bean
    public UmiMetaObjectHandler umiMetaObjectHandler() {
        return new UmiMetaObjectHandler();
    }
}
