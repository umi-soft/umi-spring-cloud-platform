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
 * 角色-分组中间表
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("R_ROLE_GROUP")
@Entity
@Table(name = "R_ROLE_GROUP")
@Where(clause = "deleted = 0")
public class RRoleGroup extends UmiEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 分组ID
     */
    @TableField("GROUP_ID")
    @Column(name = "GROUP_ID")
    private String groupId;

}
