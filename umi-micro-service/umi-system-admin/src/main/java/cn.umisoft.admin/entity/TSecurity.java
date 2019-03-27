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
 * 安全资源定义信息表
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_SECURITY")
@Entity
@Table(name = "T_SECURITY")
@Where(clause = "deleted = 0")
public class TSecurity extends UmiEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 安全资源定义，如url
     */
    @TableField("SECURITY_DEF")
    @Column(name = "SECURITY_DEF")
    private String securityDef;

    /**
     * 名称
     */
    @TableField("NAME")
    @Column(name = "NAME")
    private String name;

    /**
     * 描述
     */
    @TableField("REMARK")
    @Column(name = "REMARK")
    private String remark;

}
