package com.entity.vo;

import com.entity.JiankangzhishiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 健康知识
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiankangzhishi")
public class JiankangzhishiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 顾问
     */

    @TableField(value = "guwen_id")
    private Integer guwenId;


    /**
     * 健康知识名称
     */

    @TableField(value = "jiankangzhishi_name")
    private String jiankangzhishiName;


    /**
     * 健康知识编号
     */

    @TableField(value = "jiankangzhishi_uuid_number")
    private String jiankangzhishiUuidNumber;


    /**
     * 健康知识照片
     */

    @TableField(value = "jiankangzhishi_photo")
    private String jiankangzhishiPhoto;


    /**
     * 附件
     */

    @TableField(value = "jiankangzhishi_file")
    private String jiankangzhishiFile;


    /**
     * 视频
     */

    @TableField(value = "jiankangzhishi_video")
    private String jiankangzhishiVideo;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 健康知识类型
     */

    @TableField(value = "jiankangzhishi_types")
    private Integer jiankangzhishiTypes;


    /**
     * 健康知识热度
     */

    @TableField(value = "jiankangzhishi_clicknum")
    private Integer jiankangzhishiClicknum;


    /**
     * 健康知识内容
     */

    @TableField(value = "jiankangzhishi_content")
    private String jiankangzhishiContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "jiankangzhishi_delete")
    private Integer jiankangzhishiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


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
	 * 设置：健康知识名称
	 */
    public String getJiankangzhishiName() {
        return jiankangzhishiName;
    }


    /**
	 * 获取：健康知识名称
	 */

    public void setJiankangzhishiName(String jiankangzhishiName) {
        this.jiankangzhishiName = jiankangzhishiName;
    }
    /**
	 * 设置：健康知识编号
	 */
    public String getJiankangzhishiUuidNumber() {
        return jiankangzhishiUuidNumber;
    }


    /**
	 * 获取：健康知识编号
	 */

    public void setJiankangzhishiUuidNumber(String jiankangzhishiUuidNumber) {
        this.jiankangzhishiUuidNumber = jiankangzhishiUuidNumber;
    }
    /**
	 * 设置：健康知识照片
	 */
    public String getJiankangzhishiPhoto() {
        return jiankangzhishiPhoto;
    }


    /**
	 * 获取：健康知识照片
	 */

    public void setJiankangzhishiPhoto(String jiankangzhishiPhoto) {
        this.jiankangzhishiPhoto = jiankangzhishiPhoto;
    }
    /**
	 * 设置：附件
	 */
    public String getJiankangzhishiFile() {
        return jiankangzhishiFile;
    }


    /**
	 * 获取：附件
	 */

    public void setJiankangzhishiFile(String jiankangzhishiFile) {
        this.jiankangzhishiFile = jiankangzhishiFile;
    }
    /**
	 * 设置：视频
	 */
    public String getJiankangzhishiVideo() {
        return jiankangzhishiVideo;
    }


    /**
	 * 获取：视频
	 */

    public void setJiankangzhishiVideo(String jiankangzhishiVideo) {
        this.jiankangzhishiVideo = jiankangzhishiVideo;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：健康知识类型
	 */
    public Integer getJiankangzhishiTypes() {
        return jiankangzhishiTypes;
    }


    /**
	 * 获取：健康知识类型
	 */

    public void setJiankangzhishiTypes(Integer jiankangzhishiTypes) {
        this.jiankangzhishiTypes = jiankangzhishiTypes;
    }
    /**
	 * 设置：健康知识热度
	 */
    public Integer getJiankangzhishiClicknum() {
        return jiankangzhishiClicknum;
    }


    /**
	 * 获取：健康知识热度
	 */

    public void setJiankangzhishiClicknum(Integer jiankangzhishiClicknum) {
        this.jiankangzhishiClicknum = jiankangzhishiClicknum;
    }
    /**
	 * 设置：健康知识内容
	 */
    public String getJiankangzhishiContent() {
        return jiankangzhishiContent;
    }


    /**
	 * 获取：健康知识内容
	 */

    public void setJiankangzhishiContent(String jiankangzhishiContent) {
        this.jiankangzhishiContent = jiankangzhishiContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getJiankangzhishiDelete() {
        return jiankangzhishiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setJiankangzhishiDelete(Integer jiankangzhishiDelete) {
        this.jiankangzhishiDelete = jiankangzhishiDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
