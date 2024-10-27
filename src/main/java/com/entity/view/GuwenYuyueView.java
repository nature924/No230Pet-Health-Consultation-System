package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.GuwenYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 顾问预约
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("guwen_yuyue")
public class GuwenYuyueView extends GuwenYuyueEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 申请状态的值
	*/
	@ColumnInfo(comment="申请状态的字典表值",type="varchar(200)")
	private String guwenYuyueYesnoValue;

	//级联表 顾问
		/**
		* 顾问姓名
		*/

		@ColumnInfo(comment="顾问姓名",type="varchar(200)")
		private String guwenName;
		/**
		* 顾问手机号
		*/

		@ColumnInfo(comment="顾问手机号",type="varchar(200)")
		private String guwenPhone;
		/**
		* 顾问身份证号
		*/

		@ColumnInfo(comment="顾问身份证号",type="varchar(200)")
		private String guwenIdNumber;
		/**
		* 顾问头像
		*/

		@ColumnInfo(comment="顾问头像",type="varchar(200)")
		private String guwenPhoto;
		/**
		* 擅长
		*/

		@ColumnInfo(comment="擅长",type="varchar(200)")
		private String guwenShanchang;
		/**
		* 顾问邮箱
		*/

		@ColumnInfo(comment="顾问邮箱",type="varchar(200)")
		private String guwenEmail;
		/**
		* 顾问介绍
		*/

		@ColumnInfo(comment="顾问介绍",type="longtext")
		private String guwenContent;
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

	//重复字段
			/**
			* 重复字段 的types
			*/
			@ColumnInfo(comment="重复字段 的types",type="Integer")
			private Integer sexTypes;
				@ColumnInfo(comment="重复字段 的值",type="varchar(200)")
				private String sexValue;


	public GuwenYuyueView() {

	}

	public GuwenYuyueView(GuwenYuyueEntity guwenYuyueEntity) {
		try {
			BeanUtils.copyProperties(this, guwenYuyueEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 申请状态的值
	*/
	public String getGuwenYuyueYesnoValue() {
		return guwenYuyueYesnoValue;
	}
	/**
	* 设置： 申请状态的值
	*/
	public void setGuwenYuyueYesnoValue(String guwenYuyueYesnoValue) {
		this.guwenYuyueYesnoValue = guwenYuyueYesnoValue;
	}


	//级联表的get和set 顾问

		/**
		* 获取： 顾问姓名
		*/
		public String getGuwenName() {
			return guwenName;
		}
		/**
		* 设置： 顾问姓名
		*/
		public void setGuwenName(String guwenName) {
			this.guwenName = guwenName;
		}

		/**
		* 获取： 顾问手机号
		*/
		public String getGuwenPhone() {
			return guwenPhone;
		}
		/**
		* 设置： 顾问手机号
		*/
		public void setGuwenPhone(String guwenPhone) {
			this.guwenPhone = guwenPhone;
		}

		/**
		* 获取： 顾问身份证号
		*/
		public String getGuwenIdNumber() {
			return guwenIdNumber;
		}
		/**
		* 设置： 顾问身份证号
		*/
		public void setGuwenIdNumber(String guwenIdNumber) {
			this.guwenIdNumber = guwenIdNumber;
		}

		/**
		* 获取： 顾问头像
		*/
		public String getGuwenPhoto() {
			return guwenPhoto;
		}
		/**
		* 设置： 顾问头像
		*/
		public void setGuwenPhoto(String guwenPhoto) {
			this.guwenPhoto = guwenPhoto;
		}

		/**
		* 获取： 擅长
		*/
		public String getGuwenShanchang() {
			return guwenShanchang;
		}
		/**
		* 设置： 擅长
		*/
		public void setGuwenShanchang(String guwenShanchang) {
			this.guwenShanchang = guwenShanchang;
		}

		/**
		* 获取： 顾问邮箱
		*/
		public String getGuwenEmail() {
			return guwenEmail;
		}
		/**
		* 设置： 顾问邮箱
		*/
		public void setGuwenEmail(String guwenEmail) {
			this.guwenEmail = guwenEmail;
		}

		/**
		* 获取： 顾问介绍
		*/
		public String getGuwenContent() {
			return guwenContent;
		}
		/**
		* 设置： 顾问介绍
		*/
		public void setGuwenContent(String guwenContent) {
			this.guwenContent = guwenContent;
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

	//重复字段
			/**
			* 获取： 重复字段 的types
			*/
			public Integer getSexTypes() {
			return sexTypes;
			}
			/**
			* 设置： 重复字段 的types
			*/
			public void setSexTypes(Integer sexTypes) {
			this.sexTypes = sexTypes;
			}
				public String getSexValue() {
				return sexValue;
				}
				public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
				}

	@Override
	public String toString() {
		return "GuwenYuyueView{" +
			", guwenYuyueYesnoValue=" + guwenYuyueYesnoValue +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", guwenName=" + guwenName +
			", guwenPhone=" + guwenPhone +
			", guwenIdNumber=" + guwenIdNumber +
			", guwenPhoto=" + guwenPhoto +
			", guwenShanchang=" + guwenShanchang +
			", guwenEmail=" + guwenEmail +
			", guwenContent=" + guwenContent +
			"} " + super.toString();
	}
}
