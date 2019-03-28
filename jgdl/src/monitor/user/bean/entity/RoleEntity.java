/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-18 上午10:57:58
*/
package monitor.user.bean.entity;

import java.io.Serializable;



/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-18 上午10:57:58
 */
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 7993562774966425162L;
	private int squ = -1;
	private String roleName = null;
	private String descb = null;
	private int isAdmin = 0;//是否是管理角色;1:管理角色 0：查询角色
	private int roleLevel; // 角色等级
	public int getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}
	/**
	 * 是否是审批人1是，0不是
	 */
	private int isSpr = 0;
	private int isDefault = 0;
	public int getSqu() {
		return squ;
	}
	public void setSqu(int squ) {
		this.squ = squ;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescb() {
		return descb;
	}
	public void setDescb(String descb) {
		this.descb = descb;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getIsSpr() {
		return isSpr;
	}
	public void setIsSpr(int isSpr) {
		this.isSpr = isSpr;
	}
	
}
