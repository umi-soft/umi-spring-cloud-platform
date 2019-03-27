package cn.umisoft.web.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description: <p>面向mybaties-plus的QueryWrapper查询条件</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/1/24 5:04 PM
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UmiQueryCondition {
    /**
     * @description <p>条件类型，默认为等值条件</p>
     */
    private UmiQueryConditionTypeEnum type;

    /**
     * @description <p>entity实体字段名称，利用反射实现自动转换为对应的数据库字段名称</p>
     */
    private String field;

    /**
     * @description <p>field取值，依据type类型，实际的格式存在差异，前端需要处理成字符串统一交给后端处理</p>
     */
    private String value;

    /**
     * @description  <p>嵌套条件,需要注意的是，嵌套使用是有局限的，请按照SQL标准使用嵌套</p>
     */
    private List<UmiQueryCondition> filters;
}
