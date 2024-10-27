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
 * 健康知识
 *
 * @author 
 * @email
 */
@TableName("jiankangzhishi")
public class JiankangzhishiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiankangzhishiEntity() {

	}

	public JiankangzhishiEntity(T t) {
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
     * 顾问
     */
    @ColumnInfo(comment="顾问",type="int(11)")
    @TableField(value = "guwen_id")

    private Integer guwenId;


    /**
     * 健康知识名称
     */
    @ColumnInfo(comment="健康知识名称",type="varchar(200)")
    @TableField(value = "jiankangzhishi_name")

    private String jiankangzhishiName;


    /**
     * 健康知识编号
     */
    @ColumnInfo(comment="健康知识编号",type="varchar(200)")
    @TableField(value = "jiankangzhishi_uuid_number")

    private String jiankangzhishiUuidNumber;


    /**
     * 健康知识照片
     */
    @ColumnInfo(comment="健康知识照片",type="varchar(200)")
    @TableField(value = "jiankangzhishi_photo")

    private String jiankangzhishiPhoto;


    /**
     * 附件
     */
    @ColumnInfo(comment="附件",type="varchar(200)")
    @TableField(value = "jiankangzhishi_file")

    private String jiankangzhishiFile;


    /**
     * 视频
     */
    @ColumnInfo(comment="视频",type="varchar(200)")
    @TableField(value = "jiankangzhishi_video")

    private String jiankangzhishiVideo;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 健康知识类型
     */
    @ColumnInfo(comment="健康知识类型",type="int(11)")
    @TableField(value = "jiankangzhishi_types")

    private Integer jiankangzhishiTypes;


    /**
     * 健康知识热度
     */
    @ColumnInfo(comment="健康知识热度",type="int(11)")
    @TableField(value = "jiankangzhishi_clicknum")

    private Integer jiankangzhishiClicknum;


    /**
     * 健康知识内容
     */
    @ColumnInfo(comment="健康知识内容",type="longtext")
    @TableField(value = "jiankangzhishi_content")

    private String jiankangzhishiContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "jiankangzhishi_delete")

    private Integer jiankangzhishiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：健康知识名称
	 */
    public String getJiankangzhishiName() {
        return jiankangzhishiName;
    }
    /**
	 * 设置：健康知识名称
	 */

    public void setJiankangzhishiName(String jiankangzhishiName) {
        this.jiankangzhishiName = jiankangzhishiName;
    }
    /**
	 * 获取：健康知识编号
	 */
    public String getJiankangzhishiUuidNumber() {
        return jiankangzhishiUuidNumber;
    }
    /**
	 * 设置：健康知识编号
	 */

    public void setJiankangzhishiUuidNumber(String jiankangzhishiUuidNumber) {
        this.jiankangzhishiUuidNumber = jiankangzhishiUuidNumber;
    }
    /**
	 * 获取：健康知识照片
	 */
    public String getJiankangzhishiPhoto() {
        return jiankangzhishiPhoto;
    }
    /**
	 * 设置：健康知识照片
	 */

    public void setJiankangzhishiPhoto(String jiankangzhishiPhoto) {
        this.jiankangzhishiPhoto = jiankangzhishiPhoto;
    }
    /**
	 * 获取：附件
	 */
    public String getJiankangzhishiFile() {
        return jiankangzhishiFile;
    }
    /**
	 * 设置：附件
	 */

    public void setJiankangzhishiFile(String jiankangzhishiFile) {
        this.jiankangzhishiFile = jiankangzhishiFile;
    }
    /**
	 * 获取：视频
	 */
    public String getJiankangzhishiVideo() {
        return jiankangzhishiVideo;
    }
    /**
	 * 设置：视频
	 */

    public void setJiankangzhishiVideo(String jiankangzhishiVideo) {
        this.jiankangzhishiVideo = jiankangzhishiVideo;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：健康知识类型
	 */
    public Integer getJiankangzhishiTypes() {
        return jiankangzhishiTypes;
    }
    /**
	 * 设置：健康知识类型
	 */

    public void setJiankangzhishiTypes(Integer jiankangzhishiTypes) {
        this.jiankangzhishiTypes = jiankangzhishiTypes;
    }
    /**
	 * 获取：健康知识热度
	 */
    public Integer getJiankangzhishiClicknum() {
        return jiankangzhishiClicknum;
    }
    /**
	 * 设置：健康知识热度
	 */

    public void setJiankangzhishiClicknum(Integer jiankangzhishiClicknum) {
        this.jiankangzhishiClicknum = jiankangzhishiClicknum;
    }
    /**
	 * 获取：健康知识内容
	 */
    public String getJiankangzhishiContent() {
        return jiankangzhishiContent;
    }
    /**
	 * 设置：健康知识内容
	 */

    public void setJiankangzhishiContent(String jiankangzhishiContent) {
        this.jiankangzhishiContent = jiankangzhishiContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJiankangzhishiDelete() {
        return jiankangzhishiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setJiankangzhishiDelete(Integer jiankangzhishiDelete) {
        this.jiankangzhishiDelete = jiankangzhishiDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Jiankangzhishi{" +
            ", id=" + id +
            ", guwenId=" + guwenId +
            ", jiankangzhishiName=" + jiankangzhishiName +
            ", jiankangzhishiUuidNumber=" + jiankangzhishiUuidNumber +
            ", jiankangzhishiPhoto=" + jiankangzhishiPhoto +
            ", jiankangzhishiFile=" + jiankangzhishiFile +
            ", jiankangzhishiVideo=" + jiankangzhishiVideo +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", jiankangzhishiTypes=" + jiankangzhishiTypes +
            ", jiankangzhishiClicknum=" + jiankangzhishiClicknum +
            ", jiankangzhishiContent=" + jiankangzhishiContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", jiankangzhishiDelete=" + jiankangzhishiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
