/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-20 上午09:53:41
*/
package monitor.user.bean.vo;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-20 上午09:53:41
 */
public class ModuleSubVo {
	private int squ = -1;
	private int mainSqu = -1;
	private String name = null;
	private String entryUrl = null;
	public int getSqu() {
		return squ;
	}
	public void setSqu(int squ) {
		this.squ = squ;
	}
	public int getMainSqu() {
		return mainSqu;
	}
	public void setMainSqu(int mainSqu) {
		this.mainSqu = mainSqu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEntryUrl() {
		return entryUrl;
	}
	public void setEntryUrl(String entryUrl) {
		this.entryUrl = entryUrl;
	}
	
}	
