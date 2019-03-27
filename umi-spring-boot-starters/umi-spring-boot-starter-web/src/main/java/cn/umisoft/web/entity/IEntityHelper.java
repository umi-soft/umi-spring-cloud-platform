package cn.umisoft.web.entity;

import cn.umisoft.util.exceptions.ServiceHandlerGlobalException;
import com.baomidou.mybatisplus.annotation.TableField;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description:
 * @Author: hujie@umisoft.cn
 * @Date: 2019/2/14 2:06 PM
 */
public interface IEntityHelper<T> {
    /**
     * @description: <p>获取当前Service绑定的的Entity Class对象</p>
     *               <p>缺确保Entity必须是最后一项</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/23 10:30 PM
     * @return: Class<T>
     */
    default Class<T> getEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        return (Class <T>) actualTypeArguments[actualTypeArguments.length - 1];
    }

    /**
     * @description: <p>反射获取信息的Entity实例</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/2/15 5:11 PM
     * @param:
     * @return:
     */
    default T getEntityInstance() {
        try {
            return getEntityClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    /**
     * @description: <p>获取Entity字段上@TableField 注解描述的数据库字段名称（支持一级父类）</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/25 12:10 AM
     * @param:
     * @return:
     */
    default String getEntityTableFieldValue(String entityFieldName) {
        return getEntityTableFieldValue(getEntityClass(), entityFieldName);
    }
    /**
     * <p>获取Entity字段上@TableField 注解描述的数据库字段名称（支持一级父类）</p>
     * @param entityClass
     * @param entityFieldName
     * @return 数据库字段名称
     */
    default String getEntityTableFieldValue(Class<T> entityClass, String entityFieldName) {
        String dbFieldName = null;
        try {
            dbFieldName = entityClass.getDeclaredField(entityFieldName).getAnnotation(TableField.class).value();
        } catch (NoSuchFieldException e1) {
            dbFieldName = null;
            try {
                dbFieldName = entityClass.getSuperclass().getDeclaredField(entityFieldName).getAnnotation(TableField.class).value();
            } catch (NoSuchFieldException e2) {
                dbFieldName = null;
                throw new ServiceHandlerGlobalException("非法的参数");
            }
        }
        return dbFieldName;
    }
}
