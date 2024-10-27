package com.entity.vo;

import com.entity.GuwenEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 顾问
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("guwen")
public class GuwenVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 顾问姓名
     */

    @TableField(value = "guwen_name")
    private String guwenName;


    /**
     * 顾问手机号
     */

    @TableField(value = "guwen_phone")
    private String guwenPhone;


    /**
     * 顾问身份证号
     */

    @TableField(value = "guwen_id_number")
    private String guwenIdNumber;


    /**
     * 顾问头像
     */

    @TableField(value = "guwen_photo")
    private String guwenPhoto;


    /**
     * 擅长
     */

    @TableField(value = "guwen_shanchang")
    private String guwenShanchang;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 顾问邮箱
     */

    @TableField(value = "guwen_email")
    private String guwenEmail;


    /**
     * 顾问介绍
     */

    @TableField(value = "guwen_content")
    private String guwenContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：顾问姓名
	 */
    public String getGuwenName() {
        return guwenName;
    }


    /**
	 * 获取：顾问姓名
	 */

    public void setGuwenName(String guwenName) {
        this.guwenName = guwenName;
    }
    /**
	 * 设置：顾问手机号
	 */
    public String getGuwenPhone() {
        return guwenPhone;
    }


    /**
	 * 获取：顾问手机号
	 */

    public void setGuwenPhone(String guwenPhone) {
        this.guwenPhone = guwenPhone;
    }
    /**
	 * 设置：顾问身份证号
	 */
    public String getGuwenIdNumber() {
        return guwenIdNumber;
    }


    /**
	 * 获取：顾问身份证号
	 */

    public void setGuwenIdNumber(String guwenIdNumber) {
        this.guwenIdNumber = guwenIdNumber;
    }
    /**
	 * 设置：顾问头像
	 */
    public String getGuwenPhoto() {
        return guwenPhoto;
    }


    /**
	 * 获取：顾问头像
	 */

    public void setGuwenPhoto(String guwenPhoto) {
        this.guwenPhoto = guwenPhoto;
    }
    /**
	 * 设置：擅长
	 */
    public String getGuwenShanchang() {
        return guwenShanchang;
    }


    /**
	 * 获取：擅长
	 */

    public void setGuwenShanchang(String guwenShanchang) {
        this.guwenShanchang = guwenShanchang;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：顾问邮箱
	 */
    public String getGuwenEmail() {
        return guwenEmail;
    }


    /**
	 * 获取：顾问邮箱
	 */

    public void setGuwenEmail(String guwenEmail) {
        this.guwenEmail = guwenEmail;
    }
    /**
	 * 设置：顾问介绍
	 */
    public String getGuwenContent() {
        return guwenContent;
    }


    /**
	 * 获取：顾问介绍
	 */

    public void setGuwenContent(String guwenContent) {
        this.guwenContent = guwenContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
