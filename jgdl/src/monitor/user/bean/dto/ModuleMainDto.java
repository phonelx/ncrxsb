/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-20 上午09:53:01
*/
package monitor.user.bean.dto;

import java.io.Serializable;
import java.util.List;

import monitor.user.bean.vo.TreeNodeVo;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-20 上午09:53:01
 */
public class ModuleMainDto implements Serializable{
	private int squ = -1;
	private String name = null;
	private List<ModuleSubDto> subModuleList = null;
	
	public ModuleMainDto() {
	}
	
	public ModuleMainDto(TreeNodeVo nodeVo) {
		this.squ = nodeVo.getId();
		this.name = nodeVo.getText();
	}
	
	public int getSqu() {
		return squ;
	}
	public void setSqu(int squ) {
		this.squ = squ;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ModuleSubDto> getSubModuleList() {
		return subModuleList;
	}
	public void setSubModuleList(List<ModuleSubDto> subModuleList) {
		this.subModuleList = subModuleList;
	}
	
	
}
