package cn.umisoft.admin.entity;

import cn.umisoft.web.entity.UmiEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>
 * 业务字典（含业务字典分类）表
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_DICTIONARY")
@Entity
@Table(name = "T_DICTIONARY")
@Where(clause = "deleted = 0")
public class TDictionary extends UmiEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    @TableField("PARENT_ID")
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 类型，枚举值： 1：字典分类, 2：单级业务字典, 3：多级业务字典
     */
    @TableField("CATEGORY")
    @Column(name = "CATEGORY")
    private String category;

    /**
     * 字典分类ID
     */
    @TableField("TYPE")
    @Column(name = "TYPE")
    private String type;

    /**
     * 序号，可调整该值，用于排序
     */
    @TableField("SORT_NUM")
    @Column(name = "SORT_NUM")
    private String sortNum;

    /**
     * 名称
     */
    @TableField("NAME")
    @Column(name = "NAME")
    private String name;

    /**
     * 规则码
     */
    @TableField("CODE")
    @Column(name = "CODE")
    private String code;

    /**
     * 描述
     */
    @TableField("REMARK")
    @Column(name = "REMARK")
    private String remark;

}
