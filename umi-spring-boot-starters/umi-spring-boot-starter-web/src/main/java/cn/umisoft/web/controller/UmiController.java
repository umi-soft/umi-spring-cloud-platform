package cn.umisoft.web.controller;

import cn.umisoft.web.entity.IEntityHelper;
import cn.umisoft.web.entity.UmiEntity;
import cn.umisoft.web.service.IUmiService;
import cn.umisoft.web.util.UmiQueryCondition;
import cn.umisoft.web.util.UmiQueryConditionTypeEnum;
import cn.umisoft.util.exceptions.ControllerFieldCheckException;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: <p>Umi Controller公共组件，依赖基础Service构建</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/26 11:57 PM
 */
public class UmiController<S extends IUmiService<T>, T extends UmiEntity> implements IEntityHelper<T> {

    /**
     * @description: <p>请务必在子类中填充完整的映射字段</p>
     *               <p>如：用户部门中间表，则需要填充上'userId'和'deptId'</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/24 8:49 AM
     */
    public Set<String> mappingFields = new HashSet<String>();

    /**
     * @description: <p>根据反射获取指定字段值，依赖于get方法</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/24 9:17 AM
     * @param: entity 中间表实体
     * @param: entityFieldName 中间表实体属性名称
     * @return: 返回entityFieldName字段值
     */
    protected Object getEntityFieldValue(T entity, String entityFieldName) {
        Object value = null;
        try {
            Method getMethod = entity.getClass().getDeclaredMethod("get" + entityFieldName.substring(0, 1).toUpperCase() + entityFieldName.substring(1));
            value = getMethod.invoke(entity);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            value = null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            value = null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            value = null;
        }
        if (value == null) {
            throw new ControllerFieldCheckException("无效的mapping映射属性或其值为null");
        }
        return value;
    }

    /**
     * @description: <p>中间表关联字段检查是否缺失</p>
     *               <p>检查mappingFields是否在子类被初始化，已经对应中间表实体中是否已经有值，不允许为null</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/24 9:31 AM
     * @param: entity中间表实体
     * @return: void
     */
    protected void checkMappingFields(T entity) {
        if (this.mappingFields.size() == 0) {
            throw new ControllerFieldCheckException("请配置中间表字段映射");
        }
        for (String mappingField: mappingFields) {
            getEntityFieldValue(entity, mappingField);
        }
    }

    /**
     * @description: <p>UmiQueryCondition动态QueryWrapper封装</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/25 9:46 AM
     * @param: List<UmiQueryCondition> conditions
     * @return: QueryWrapper<T> queryWrapper
     */
    protected QueryWrapper<T> queryWrapperBuilder( List<UmiQueryCondition> conditions) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        if (conditions == null) {
            return queryWrapper;
        }
        for(UmiQueryCondition condition : conditions) {
            umiQueryConditionBuilder(queryWrapper, condition);
        }
        return queryWrapper;
    }

    /**
     * @description: <p>UmiQueryCondition动态QueryWrapper封装</p>
     * @author: hujie@umisoft.cn
     * @date: 2019/1/25 9:46 AM
     * @param: QueryWrapper<T> queryWrapper
     * @param: UmiQueryCondition condition
     * @return: void
     */
    protected void umiQueryConditionBuilder(QueryWrapper<T> queryWrapper, UmiQueryCondition condition) {
        // 默认条件为 "等值"
        UmiQueryConditionTypeEnum type =  (condition.getType() == null) ? UmiQueryConditionTypeEnum.EQ : condition.getType();
        switch (type) {
            case ALL_EQ:
                throw new ControllerFieldCheckException("请使用EQ逐个替代");
            case EQ:
                queryWrapper.eq(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case NE:
                queryWrapper.eq(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case GT:
                queryWrapper.gt(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case GE:
                queryWrapper.ge(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case LT:
                queryWrapper.lt(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case LE:
                queryWrapper.le(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case BETWEEN:
                // 要求value是数组，长度为2,格式参考："[\"20\",\"30\"]"
                JSONArray array = JSONArray.parseArray(condition.getValue());
                queryWrapper.between(getEntityTableFieldValue(condition.getField()), array.get(0), array.get(0));
                break;
            case NOT_BETWEEN:
                // 要求value同BETWEEN
                array = JSONArray.parseArray(condition.getValue());
                queryWrapper.between(getEntityTableFieldValue(condition.getField()), array.get(0), array.get(0));
                break;
            case LIKE:
                queryWrapper.like(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case NOT_LIKE:
                queryWrapper.notLike(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case LIKE_LEFT:
                queryWrapper.likeLeft(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case LIKE_RIGHT:
                queryWrapper.likeRight(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case IS_NULL:
                queryWrapper.isNull(getEntityTableFieldValue(condition.getField()));
                break;
            case IS_NOT_NULL:
                queryWrapper.isNotNull(getEntityTableFieldValue(condition.getField()));
                break;
            case IN:
                array = JSONArray.parseArray(condition.getValue());
                queryWrapper.in(getEntityTableFieldValue(condition.getField()), array);
                break;
            case NOT_IN:
                array = JSONArray.parseArray(condition.getValue());
                queryWrapper.notIn(getEntityTableFieldValue(condition.getField()), array);
                break;
            case IN_SQL:
                // 要求value是字符串，格式有两种，实际的逗号分开的值，或可执行的sql语句，如："1，2，3，4，5" 或 "SELECT ID FROM T_DEPT WHERE NAME IS NOT NULL"
                queryWrapper.notIn(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case NOT_IN_SQL:
                // 要求value同IN_SQL
                queryWrapper.notIn(getEntityTableFieldValue(condition.getField()), condition.getValue());
                break;
            case GROUP_BY:
                // 要求field为JSON数组，如实参考如："[\"age\",\"name\"]"
                array = JSONArray.parseArray(condition.getField());
                String[] dbFields = new String[array.size()];
                int i = 0;
                for (Object o : array) {
                    dbFields[i] = getEntityTableFieldValue((String)o);
                    i ++;
                }
                queryWrapper.groupBy(dbFields);
                break;
            case ORDER_BY_ASC:
                // 要求field为JSON数组，如实参考如："[\"age\",\"name\"]"
                array = JSONArray.parseArray(condition.getField());
                dbFields = new String[array.size()];
                i = 0;
                for (Object o : array) {
                    dbFields[i] = getEntityTableFieldValue((String)o);
                    i ++;
                }
                queryWrapper.orderByAsc(dbFields);
                break;
            case ORDER_BY_DESC:
                // 要求field同ORDER_BY_ASC
                array = JSONArray.parseArray(condition.getField());
                dbFields = new String[array.size()];
                i = 0;
                for (Object o : array) {
                    dbFields[i] = getEntityTableFieldValue((String)o);
                    i ++;
                }
                queryWrapper.orderByDesc(dbFields);
                break;
//            case ORDER_BY:
//                throw new ControllerFieldCheckException("请使用ORDER_BY_DESC或ORDER_BY_ASC替代");
            case HAVING:
                // 要求value为符合语法的having SQL语句，如："sum(age) > 10"
                queryWrapper.having(condition.getValue());
                break;
            case OR:
                queryWrapper.or();
                break;
            case AND:
                queryWrapper.and(wrapper -> {
                    for(UmiQueryCondition filter : condition.getFilters()) {
                        umiQueryConditionBuilder(wrapper, filter);
                    }
                    return wrapper;
                });
                break;
            case APPLY:
                throw new ControllerFieldCheckException("暂时无法支持APPLY");
            case LAST:
                throw new ControllerFieldCheckException("暂时无法支持LAST，存在SQL注入风险");
            case EXISTS:
                // value要求为可执行的exists语句，如："SELECT ID FROM T_USER WHERE AGE = 25"
                queryWrapper.exists(condition.getValue());
                break;
            case NOT_EXISTS:
                // value要求同EXISTS
                queryWrapper.notExists(condition.getValue());
                break;
            case NESTED:
                queryWrapper.nested(wrapper -> {
                    for(UmiQueryCondition filter : condition.getFilters()) {
                        umiQueryConditionBuilder(wrapper, filter);
                    }
                    return wrapper;
                });
                break;
            default:
                throw new ControllerFieldCheckException("未知的条件类型");
        }
    }
}
