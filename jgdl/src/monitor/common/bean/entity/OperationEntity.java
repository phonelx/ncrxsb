/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-28 上午10:19:13
*/
package monitor.common.bean.entity;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-28 上午10:19:13
 */
public class OperationEntity {
	private int squ = -1;//序列号
//	private int userSqu = -1;//登录用户squ
	private String userInfo = "";
	
	/**
	 *  0：	添加操作
	 *	1：	删除操作
	 *	2：	修改操作
	 *	3：	查询操作
	 *	4：	同步操作
	 */
	private int operateType = -1;//操作类型
	private String operateKeyWords = "";//操作关键字
	private String operateDescb = "";//操作备注
	private String operateDatetime = "";//操作执行时间
	public int getSqu() {
		return squ;
	}
	public void setSqu(int squ) {
		this.squ = squ;
	}
//	public int getUserSqu() {
//		return userSqu;
//	}
//	public void setUserSqu(int userSqu) {
//		this.userSqu = userSqu;
//	}
	
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public String getOperateKeyWords() {
		return operateKeyWords;
	}
	public void setOperateKeyWords(String operateKeyWords) {
		this.operateKeyWords = operateKeyWords;
	}
	public String getOperateDatetime() {
		return operateDatetime;
	}
	public void setOperateDatetime(String operateDatetime) {
		this.operateDatetime = operateDatetime;
	}
	public String getOperateDescb() {
		return operateDescb;
	}
	public void setOperateDescb(String operateDescb) {
		this.operateDescb = operateDescb;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

}
