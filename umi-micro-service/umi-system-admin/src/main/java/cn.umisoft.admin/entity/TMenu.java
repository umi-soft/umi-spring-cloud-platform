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
 * 前端路由菜单信息表
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_MENU")
@Entity
@Table(name = "T_MENU")
@Where(clause = "deleted = 0")
public class TMenu extends UmiEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    @TableField("PARENT_ID")
    @Column(name = "PARENT_ID")
    private String parentId;

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
     * 图标名称
     */
    @TableField("ICON")
    @Column(name = "ICON")
    private String icon;

    /**
     * 描述
     */
    @TableField("REMARK")
    @Column(name = "REMARK")
    private String remark;

}
