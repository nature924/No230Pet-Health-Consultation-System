package com.entity.vo;

import com.entity.GuwenYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 顾问预约
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("guwen_yuyue")
public class GuwenYuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 报名编号
     */

    @TableField(value = "guwen_yuyue_uuid_number")
    private String guwenYuyueUuidNumber;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 顾问
     */

    @TableField(value = "guwen_id")
    private Integer guwenId;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 预约缘由
     */

    @TableField(value = "guwen_yuyue_text")
    private String guwenYuyueText;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "guwen_yuyue_time")
    private Date guwenYuyueTime;


    /**
     * 申请状态
     */

    @TableField(value = "guwen_yuyue_yesno_types")
    private Integer guwenYuyueYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "guwen_yuyue_yesno_text")
    private String guwenYuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "guwen_yuyue_shenhe_time")
    private Date guwenYuyueShenheTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：报名编号
	 */
    public String getGuwenYuyueUuidNumber() {
        return guwenYuyueUuidNumber;
    }


    /**
	 * 获取：报名编号
	 */

    public void setGuwenYuyueUuidNumber(String guwenYuyueUuidNumber) {
        this.guwenYuyueUuidNumber = guwenYuyueUuidNumber;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：顾问
	 */
    public Integer getGuwenId() {
        return guwenId;
    }


    /**
	 * 获取：顾问
	 */

    public void setGuwenId(Integer guwenId) {
        this.guwenId = guwenId;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：预约缘由
	 */
    public String getGuwenYuyueText() {
        return guwenYuyueText;
    }


    /**
	 * 获取：预约缘由
	 */

    public void setGuwenYuyueText(String guwenYuyueText) {
        this.guwenYuyueText = guwenYuyueText;
    }
    /**
	 * 设置：预约日期
	 */
    public Date getGuwenYuyueTime() {
        return guwenYuyueTime;
    }


    /**
	 * 获取：预约日期
	 */

    public void setGuwenYuyueTime(Date guwenYuyueTime) {
        this.guwenYuyueTime = guwenYuyueTime;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getGuwenYuyueYesnoTypes() {
        return guwenYuyueYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setGuwenYuyueYesnoTypes(Integer guwenYuyueYesnoTypes) {
        this.guwenYuyueYesnoTypes = guwenYuyueYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getGuwenYuyueYesnoText() {
        return guwenYuyueYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setGuwenYuyueYesnoText(String guwenYuyueYesnoText) {
        this.guwenYuyueYesnoText = guwenYuyueYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getGuwenYuyueShenheTime() {
        return guwenYuyueShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setGuwenYuyueShenheTime(Date guwenYuyueShenheTime) {
        this.guwenYuyueShenheTime = guwenYuyueShenheTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
