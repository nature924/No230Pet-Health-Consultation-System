package com.entity.model;

import com.entity.JiankangzhishiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 健康知识
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiankangzhishiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 顾问
     */
    private Integer guwenId;


    /**
     * 健康知识名称
     */
    private String jiankangzhishiName;


    /**
     * 健康知识编号
     */
    private String jiankangzhishiUuidNumber;


    /**
     * 健康知识照片
     */
    private String jiankangzhishiPhoto;


    /**
     * 附件
     */
    private String jiankangzhishiFile;


    /**
     * 视频
     */
    private String jiankangzhishiVideo;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 健康知识类型
     */
    private Integer jiankangzhishiTypes;


    /**
     * 健康知识热度
     */
    private Integer jiankangzhishiClicknum;


    /**
     * 健康知识内容
     */
    private String jiankangzhishiContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer jiankangzhishiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
