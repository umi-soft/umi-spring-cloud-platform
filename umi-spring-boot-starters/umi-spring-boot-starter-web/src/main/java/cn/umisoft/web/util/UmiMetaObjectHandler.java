package cn.umisoft.web.util;

import cn.umisoft.util.context.UmiUserContextHolder;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @description: <p>自动填充相关字段值，需要被构建为bean组件，mybatisplus会自动获取该bean</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/25 10:15 AM
 */
public class UmiMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmiMetaObjectHandler.class);

    private static String DELETED = "deleted";
    private static String CREATED_BY = "createdBy";
    private static String CREATED_DATE = "createdDate";
    private static String MODIFIED_BY = "modifiedBy";
    private static String MODIFIED_DATE = "modifiedDate";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 请注意字段填充时，数据类型必须高度保持一致
        LOGGER.info("start insert fill " + DELETED);
        this.setInsertFieldValByName(DELETED, 0, metaObject);
        LOGGER.info("start insert fill " + CREATED_BY);
        this.setInsertFieldValByName(CREATED_BY, UmiUserContextHolder.getContext(), metaObject);
        LOGGER.info("start insert fill " + CREATED_DATE);
        this.setInsertFieldValByName(CREATED_DATE, new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 请注意字段填充时，数据类型必须高度保持一致
        LOGGER.info("start update fill " + MODIFIED_BY);
        this.setUpdateFieldValByName(MODIFIED_BY, UmiUserContextHolder.getContext(), metaObject);
        LOGGER.info("start update fill " + MODIFIED_DATE);
        this.setUpdateFieldValByName(MODIFIED_DATE, new Date(), metaObject);
    }
}