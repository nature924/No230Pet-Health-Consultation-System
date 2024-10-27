package com.entity.model;

import com.entity.GuwenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 顾问
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GuwenModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 顾问姓名
     */
    private String guwenName;


    /**
     * 顾问手机号
     */
    private String guwenPhone;


    /**
     * 顾问身份证号
     */
    private String guwenIdNumber;


    /**
     * 顾问头像
     */
    private String guwenPhoto;


    /**
     * 擅长
     */
    private String guwenShanchang;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 顾问邮箱
     */
    private String guwenEmail;


    /**
     * 顾问介绍
     */
    private String guwenContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
