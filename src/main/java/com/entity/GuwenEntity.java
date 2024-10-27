package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 顾问
 *
 * @author 
 * @email
 */
@TableName("guwen")
public class GuwenEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GuwenEntity() {

	}

	public GuwenEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 顾问姓名
     */
    @ColumnInfo(comment="顾问姓名",type="varchar(200)")
    @TableField(value = "guwen_name")

    private String guwenName;


    /**
     * 顾问手机号
     */
    @ColumnInfo(comment="顾问手机号",type="varchar(200)")
    @TableField(value = "guwen_phone")

    private String guwenPhone;


    /**
     * 顾问身份证号
     */
    @ColumnInfo(comment="顾问身份证号",type="varchar(200)")
    @TableField(value = "guwen_id_number")

    private String guwenIdNumber;


    /**
     * 顾问头像
     */
    @ColumnInfo(comment="顾问头像",type="varchar(200)")
    @TableField(value = "guwen_photo")

    private String guwenPhoto;


    /**
     * 擅长
     */
    @ColumnInfo(comment="擅长",type="varchar(200)")
    @TableField(value = "guwen_shanchang")

    private String guwenShanchang;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 顾问邮箱
     */
    @ColumnInfo(comment="顾问邮箱",type="varchar(200)")
    @TableField(value = "guwen_email")

    private String guwenEmail;


    /**
     * 顾问介绍
     */
    @ColumnInfo(comment="顾问介绍",type="longtext")
    @TableField(value = "guwen_content")

    private String guwenContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }
    /**
	 * 设置：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }
    /**
	 * 设置：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：顾问姓名
	 */
    public String getGuwenName() {
        return guwenName;
    }
    /**
	 * 设置：顾问姓名
	 */

    public void setGuwenName(String guwenName) {
        this.guwenName = guwenName;
    }
    /**
	 * 获取：顾问手机号
	 */
    public String getGuwenPhone() {
        return guwenPhone;
    }
    /**
	 * 设置：顾问手机号
	 */

    public void setGuwenPhone(String guwenPhone) {
        this.guwenPhone = guwenPhone;
    }
    /**
	 * 获取：顾问身份证号
	 */
    public String getGuwenIdNumber() {
        return guwenIdNumber;
    }
    /**
	 * 设置：顾问身份证号
	 */

    public void setGuwenIdNumber(String guwenIdNumber) {
        this.guwenIdNumber = guwenIdNumber;
    }
    /**
	 * 获取：顾问头像
	 */
    public String getGuwenPhoto() {
        return guwenPhoto;
    }
    /**
	 * 设置：顾问头像
	 */

    public void setGuwenPhoto(String guwenPhoto) {
        this.guwenPhoto = guwenPhoto;
    }
    /**
	 * 获取：擅长
	 */
    public String getGuwenShanchang() {
        return guwenShanchang;
    }
    /**
	 * 设置：擅长
	 */

    public void setGuwenShanchang(String guwenShanchang) {
        this.guwenShanchang = guwenShanchang;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }
    /**
	 * 设置：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：顾问邮箱
	 */
    public String getGuwenEmail() {
        return guwenEmail;
    }
    /**
	 * 设置：顾问邮箱
	 */

    public void setGuwenEmail(String guwenEmail) {
        this.guwenEmail = guwenEmail;
    }
    /**
	 * 获取：顾问介绍
	 */
    public String getGuwenContent() {
        return guwenContent;
    }
    /**
	 * 设置：顾问介绍
	 */

    public void setGuwenContent(String guwenContent) {
        this.guwenContent = guwenContent;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Guwen{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", guwenName=" + guwenName +
            ", guwenPhone=" + guwenPhone +
            ", guwenIdNumber=" + guwenIdNumber +
            ", guwenPhoto=" + guwenPhoto +
            ", guwenShanchang=" + guwenShanchang +
            ", sexTypes=" + sexTypes +
            ", guwenEmail=" + guwenEmail +
            ", guwenContent=" + guwenContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
