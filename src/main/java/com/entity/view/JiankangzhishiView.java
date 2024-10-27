package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JiankangzhishiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 健康知识
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jiankangzhishi")
public class JiankangzhishiView extends JiankangzhishiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 健康知识类型的值
	*/
	@ColumnInfo(comment="健康知识类型的字典表值",type="varchar(200)")
	private String jiankangzhishiValue;
	/**
	* 是否上架的值
	*/
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;

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



	public JiankangzhishiView() {

	}

	public JiankangzhishiView(JiankangzhishiEntity jiankangzhishiEntity) {
		try {
			BeanUtils.copyProperties(this, jiankangzhishiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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
	//当前表的
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


	@Override
	public String toString() {
		return "JiankangzhishiView{" +
			", jiankangzhishiValue=" + jiankangzhishiValue +
			", shangxiaValue=" + shangxiaValue +
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
