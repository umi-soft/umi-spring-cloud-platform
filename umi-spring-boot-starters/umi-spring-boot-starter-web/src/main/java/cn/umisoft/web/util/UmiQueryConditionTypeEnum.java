package cn.umisoft.web.util;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @description: 面向mybaties-plus的QueryWrapper查询条件类型
 * @author: hujie@umisoft.cn
 * @date: 2019/1/14 11:30 AM
 */
@JSONType(serializeEnumAsJavaBean = true)
public enum UmiQueryConditionTypeEnum {
    /**
     * @description:
     */
    ALL_EQ,

    /**
     * @description:
     */
    EQ,

    /**
     * @description:
     */
    NE,

    /**
     * @description:
     */
    GT,

    /**
     * @description:
     */
    GE,

    /**
     * @description:
     */
    LT,

    /**
     * @description:
     */
    LE,

    /**
     * @description:
     */
    BETWEEN,

    /**
     * @description:
     */
    NOT_BETWEEN,

    /**
     * @description:
     */
    LIKE,

    /**
     * @description:
     */
    NOT_LIKE,

    /**
     * @description:
     */
    LIKE_LEFT,

    /**
     * @description:
     */
    LIKE_RIGHT,

    /**
     * @description:
     */
    IS_NULL,

    /**
     * @description:
     */
    IS_NOT_NULL,

    /**
     * @description:
     */
    IN,

    /**
     * @description:
     */
    NOT_IN,

    /**
     * @description:
     */
    IN_SQL,

    /**
     * @description:
     */
    NOT_IN_SQL,

    /**
     * @description:
     */
    GROUP_BY,

    /**
     * @description:
     */
//    ORDER_BY,

    /**
     * @description:
     */
    ORDER_BY_ASC,

    /**
     * @description:
     */
    ORDER_BY_DESC,

    /**
     * @description:
     */
    HAVING,

    /**
     * @description:
     */
    OR,

    /**
     * @description:
     */
    AND,

    /**
     * @description:
     */
    APPLY,

    /**
     * @description:
     */
    LAST,

    /**
     * @description:
     */
    EXISTS,

    /**
     * @description:
     */
    NOT_EXISTS,

    /**
     * @description:
     */
    NESTED;

}
