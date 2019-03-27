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
 * 用户信息表
 * </p>
 *
 * @author hujie@umisoft.cn
 * @since 2019-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_USER")
@Entity
@Table(name = "T_USER")
@Where(clause = "deleted = 0")
public class TUser extends UmiEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 是否禁用
     */
    @TableField("DISABLED")
    @Column(name = "DISABLED")
    private Integer disabled;

    /**
     * 序号，可调整该值，用于排序
     */
    @TableField("SORT_NUM")
    @Column(name = "SORT_NUM")
    private String sortNum;

    /**
     * 登陆用户名
     */
    @TableField("LOGIN_NAME")
    @Column(name = "LOGIN_NAME")
    private String loginName;

    /**
     * 登陆密码
     */
    @TableField("PASSWORD")
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 名称
     */
    @TableField("NAME")
    @Column(name = "NAME")
    private String name;

    /**
     * 昵称
     */
    @TableField("NICK_NAME")
    @Column(name = "NICK_NAME")
    private String nickName;

    /**
     * 肖像地址
     */
    @TableField("AVATAR")
    @Column(name = "AVATAR")
    private String avatar;

    /**
     * 证件号码
     */
    @TableField("ID_NUMBER")
    @Column(name = "ID_NUMBER")
    private String idNumber;

    /**
     * 性别，枚举值，字典值
     */
    @TableField("GENDER")
    @Column(name = "GENDER")
    private String gender;

    /**
     * 出生日期YYYY-MM-DD
     */
    @TableField("BIRTHDAY")
    @Column(name = "BIRTHDAY")
    private String birthday;

    /**
     * 联系电话
     */
    @TableField("PHONE")
    @Column(name = "PHONE")
    private String phone;

    /**
     * 联系邮箱
     */
    @TableField("EMAIL")
    @Column(name = "EMAIL")
    private String email;

    /**
     * 联系地址
     */
    @TableField("ADDRESS")
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 用户标签
     */
    @TableField("TAG")
    @Column(name = "TAG")
    private String tag;

    /**
     * 描述
     */
    @TableField("REMARK")
    @Column(name = "REMARK")
    private String remark;

}
