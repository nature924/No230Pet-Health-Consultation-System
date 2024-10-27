package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JiankangzhishiCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 健康知识收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jiankangzhishi_collection")
public class JiankangzhishiCollectionView extends JiankangzhishiCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String jiankangzhishiCollectionValue;

	//级联表 健康知识
					 
		/**
		* 健康知识 的 顾问
		*/
		@ColumnInfo(comment="顾问",type="int(11)")
		private Integer jiankangzhishiGuwenId;
		/**
		* 健康知识名称
		*/

		@ColumnInfo(comment="健康知识名称",type="varchar(200)")
		private String jiankangzhishiName;
		/**
		* 健康知识编号
		*/

		@ColumnInfo(comment="健康知识编号",type="varchar(200)")
		private String jiankangzhishiUuidNumber;
		/**
		* 健康知识照片
		*/

		@ColumnInfo(comment="健康知识照片",type="varchar(200)")
		private String jiankangzhishiPhoto;
		/**
		* 附件
		*/

		@ColumnInfo(comment="附件",type="varchar(200)")
		private String jiankangzhishiFile;
		/**
		* 视频
		*/

		@ColumnInfo(comment="视频",type="varchar(200)")
		private String jiankangzhishiVideo;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 健康知识类型
		*/
		@ColumnInfo(comment="健康知识类型",type="int(11)")
		private Integer jiankangzhishiTypes;
			/**
			* 健康知识类型的值
			*/
			@ColumnInfo(comment="健康知识类型的字典表值",type="varchar(200)")
			private String jiankangzhishiValue;
		/**
		* 健康知识热度
		*/

		@ColumnInfo(comment="健康知识热度",type="int(11)")
		private Integer jiankangzhishiClicknum;
		/**
		* 健康知识内容
		*/

		@ColumnInfo(comment="健康知识内容",type="longtext")
		private String jiankangzhishiContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer jiankangzhishiDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;



	public JiankangzhishiCollectionView() {

	}

	public JiankangzhishiCollectionView(JiankangzhishiCollectionEntity jiankangzhishiCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, jiankangzhishiCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getJiankangzhishiCollectionValue() {
		return jiankangzhishiCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setJiankangzhishiCollectionValue(String jiankangzhishiCollectionValue) {
		this.jiankangzhishiCollectionValue = jiankangzhishiCollectionValue;
	}


	//级联表的get和set 健康知识
		/**
		* 获取：健康知识 的 顾问
		*/
		public Integer getJiankangzhishiGuwenId() {
			return jiankangzhishiGuwenId;
		}
		/**
		* 设置：健康知识 的 顾问
		*/
		public void setJiankangzhishiGuwenId(Integer jiankangzhishiGuwenId) {
			this.jiankangzhishiGuwenId = jiankangzhishiGuwenId;
		}

		/**
		* 获取： 健康知识名称
		*/
		public String getJiankangzhishiName() {
			return jiankangzhishiName;
		}
		/**
		* 设置： 健康知识名称
		*/
		public void setJiankangzhishiName(String jiankangzhishiName) {
			this.jiankangzhishiName = jiankangzhishiName;
		}

		/**
		* 获取： 健康知识编号
		*/
		public String getJiankangzhishiUuidNumber() {
			return jiankangzhishiUuidNumber;
		}
		/**
		* 设置： 健康知识编号
		*/
		public void setJiankangzhishiUuidNumber(String jiankangzhishiUuidNumber) {
			this.jiankangzhishiUuidNumber = jiankangzhishiUuidNumber;
		}

		/**
		* 获取： 健康知识照片
		*/
		public String getJiankangzhishiPhoto() {
			return jiankangzhishiPhoto;
		}
		/**
		* 设置： 健康知识照片
		*/
		public void setJiankangzhishiPhoto(String jiankangzhishiPhoto) {
			this.jiankangzhishiPhoto = jiankangzhishiPhoto;
		}

		/**
		* 获取： 附件
		*/
		public String getJiankangzhishiFile() {
			return jiankangzhishiFile;
		}
		/**
		* 设置： 附件
		*/
		public void setJiankangzhishiFile(String jiankangzhishiFile) {
			this.jiankangzhishiFile = jiankangzhishiFile;
		}

		/**
		* 获取： 视频
		*/
		public String getJiankangzhishiVideo() {
			return jiankangzhishiVideo;
		}
		/**
		* 设置： 视频
		*/
		public void setJiankangzhishiVideo(String jiankangzhishiVideo) {
			this.jiankangzhishiVideo = jiankangzhishiVideo;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 健康知识类型
		*/
		public Integer getJiankangzhishiTypes() {
			return jiankangzhishiTypes;
		}
		/**
		* 设置： 健康知识类型
		*/
		public void setJiankangzhishiTypes(Integer jiankangzhishiTypes) {
			this.jiankangzhishiTypes = jiankangzhishiTypes;
		}


			/**
			* 获取： 健康知识类型的值
			*/
			public String getJiankangzhishiValue() {
				return jiankangzhishiValue;
			}
			/**
			* 设置： 健康知识类型的值
			*/
			public void setJiankangzhishiValue(String jiankangzhishiValue) {
				this.jiankangzhishiValue = jiankangzhishiValue;
			}

		/**
		* 获取： 健康知识热度
		*/
		public Integer getJiankangzhishiClicknum() {
			return jiankangzhishiClicknum;
		}
		/**
		* 设置： 健康知识热度
		*/
		public void setJiankangzhishiClicknum(Integer jiankangzhishiClicknum) {
			this.jiankangzhishiClicknum = jiankangzhishiClicknum;
		}

		/**
		* 获取： 健康知识内容
		*/
		public String getJiankangzhishiContent() {
			return jiankangzhishiContent;
		}
		/**
		* 设置： 健康知识内容
		*/
		public void setJiankangzhishiContent(String jiankangzhishiContent) {
			this.jiankangzhishiContent = jiankangzhishiContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getJiankangzhishiDelete() {
			return jiankangzhishiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setJiankangzhishiDelete(Integer jiankangzhishiDelete) {
			this.jiankangzhishiDelete = jiankangzhishiDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "JiankangzhishiCollectionView{" +
			", jiankangzhishiCollectionValue=" + jiankangzhishiCollectionValue +
			", jiankangzhishiName=" + jiankangzhishiName +
			", jiankangzhishiUuidNumber=" + jiankangzhishiUuidNumber +
			", jiankangzhishiPhoto=" + jiankangzhishiPhoto +
			", jiankangzhishiFile=" + jiankangzhishiFile +
			", jiankangzhishiVideo=" + jiankangzhishiVideo +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", jiankangzhishiClicknum=" + jiankangzhishiClicknum +
			", jiankangzhishiContent=" + jiankangzhishiContent +
			", jiankangzhishiDelete=" + jiankangzhishiDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
