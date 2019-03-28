/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-12-18 上午11:43:48
*/
package monitor.common.bean.dto;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-12-18 上午11:43:48
 */
public class ResourceTypeDto {
	private long squ = 0;
	private String typeName = "";
	private String descb = "";
	private String abbr = "";
	private String addDatetime = "";
	private int orderno = 0;
	private long parentSqu = 0;
	public long getSqu() {
		return squ;
	}
	public void setSqu(long squ) {
		this.squ = squ;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getDescb() {
		return descb;
	}
	public void setDescb(String descb) {
		this.descb = descb;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getAddDatetime() {
		return addDatetime;
	}
	public void setAddDatetime(String addDatetime) {
		this.addDatetime = addDatetime;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public long getParentSqu() {
		return parentSqu;
	}
	public void setParentSqu(long parentSqu) {
		this.parentSqu = parentSqu;
	}
	
}
