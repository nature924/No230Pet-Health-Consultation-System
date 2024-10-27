package com.entity.model;

import com.entity.GuwenYuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 顾问预约
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GuwenYuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 报名编号
     */
    private String guwenYuyueUuidNumber;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 顾问
     */
    private Integer guwenId;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 预约缘由
     */
    private String guwenYuyueText;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date guwenYuyueTime;


    /**
     * 申请状态
     */
    private Integer guwenYuyueYesnoTypes;


    /**
     * 审核回复
     */
    private String guwenYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date guwenYuyueShenheTime;


    /**
     * 创建时间 show3 listShow
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
	 * 获取：报名编号
	 */
    public String getGuwenYuyueUuidNumber() {
        return guwenYuyueUuidNumber;
    }


    /**
	 * 设置：报名编号
	 */
    public void setGuwenYuyueUuidNumber(String guwenYuyueUuidNumber) {
        this.guwenYuyueUuidNumber = guwenYuyueUuidNumber;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：顾问
	 */
    public Integer getGuwenId() {
        return guwenId;
    }


    /**
	 * 设置：顾问
	 */
    public void setGuwenId(Integer guwenId) {
        this.guwenId = guwenId;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：预约缘由
	 */
    public String getGuwenYuyueText() {
        return guwenYuyueText;
    }


    /**
	 * 设置：预约缘由
	 */
    public void setGuwenYuyueText(String guwenYuyueText) {
        this.guwenYuyueText = guwenYuyueText;
    }
    /**
	 * 获取：预约日期
	 */
    public Date getGuwenYuyueTime() {
        return guwenYuyueTime;
    }


    /**
	 * 设置：预约日期
	 */
    public void setGuwenYuyueTime(Date guwenYuyueTime) {
        this.guwenYuyueTime = guwenYuyueTime;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getGuwenYuyueYesnoTypes() {
        return guwenYuyueYesnoTypes;
    }


    /**
	 * 设置：申请状态
	 */
    public void setGuwenYuyueYesnoTypes(Integer guwenYuyueYesnoTypes) {
        this.guwenYuyueYesnoTypes = guwenYuyueYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getGuwenYuyueYesnoText() {
        return guwenYuyueYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setGuwenYuyueYesnoText(String guwenYuyueYesnoText) {
        this.guwenYuyueYesnoText = guwenYuyueYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getGuwenYuyueShenheTime() {
        return guwenYuyueShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setGuwenYuyueShenheTime(Date guwenYuyueShenheTime) {
        this.guwenYuyueShenheTime = guwenYuyueShenheTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
