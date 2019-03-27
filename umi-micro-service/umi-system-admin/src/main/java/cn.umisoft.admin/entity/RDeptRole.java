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
 * 部门-角色，中间表
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("R_DEPT_ROLE")
@Entity
@Table(name = "R_DEPT_ROLE")
@Where(clause = "deleted = 0")
public class RDeptRole extends UmiEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableField("DEPT_ID")
    @Column(name = "DEPT_ID")
    private String deptId;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    @Column(name = "ROLE_ID")
    private String roleId;

}
