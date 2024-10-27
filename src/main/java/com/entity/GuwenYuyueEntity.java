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
 * 顾问预约
 *
 * @author 
 * @email
 */
@TableName("guwen_yuyue")
public class GuwenYuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GuwenYuyueEntity() {

	}

	public GuwenYuyueEntity(T t) {
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
     * 报名编号
     */
    @ColumnInfo(comment="报名编号",type="varchar(200)")
    @TableField(value = "guwen_yuyue_uuid_number")

    private String guwenYuyueUuidNumber;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 顾问
     */
    @ColumnInfo(comment="顾问",type="int(11)")
    @TableField(value = "guwen_id")

    private Integer guwenId;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 预约缘由
     */
    @ColumnInfo(comment="预约缘由",type="longtext")
    @TableField(value = "guwen_yuyue_text")

    private String guwenYuyueText;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @ColumnInfo(comment="预约日期",type="date")
    @TableField(value = "guwen_yuyue_time")

    private Date guwenYuyueTime;


    /**
     * 申请状态
     */
    @ColumnInfo(comment="申请状态",type="int(11)")
    @TableField(value = "guwen_yuyue_yesno_types")

    private Integer guwenYuyueYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "guwen_yuyue_yesno_text")

    private String guwenYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "guwen_yuyue_shenhe_time")

    private Date guwenYuyueShenheTime;


    /**
     * 创建时间  listShow
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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "GuwenYuyue{" +
            ", id=" + id +
            ", guwenYuyueUuidNumber=" + guwenYuyueUuidNumber +
            ", yonghuId=" + yonghuId +
            ", guwenId=" + guwenId +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", guwenYuyueText=" + guwenYuyueText +
            ", guwenYuyueTime=" + DateUtil.convertString(guwenYuyueTime,"yyyy-MM-dd") +
            ", guwenYuyueYesnoTypes=" + guwenYuyueYesnoTypes +
            ", guwenYuyueYesnoText=" + guwenYuyueYesnoText +
            ", guwenYuyueShenheTime=" + DateUtil.convertString(guwenYuyueShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
