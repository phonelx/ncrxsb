/*
 * @copyright:  Chengdu Heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-11-29 下午03:37:11
*/
package monitor.user.bean.vo;

import java.util.ArrayList;
import java.util.List;

import monitor.user.bean.dto.DatasourceDto;
import monitor.user.bean.dto.EntityDto;
import monitor.user.bean.dto.ModuleMainDto;
import monitor.user.bean.dto.ModuleSubDto;
import monitor.user.bean.dto.QueryDto;


/** 
 * <description> 
 * @author  cl
 * @datetime  2011-11-29 下午03:37:11
 */
public class ZTreeNodeVo {
	private String name = "";//节点显示文本
	private String icon = "";//树节点图标
	private boolean open = false;//是否展开
	private boolean checked = false;//是否选中
	private List<ZTreeNodeVo> nodes = new ArrayList<ZTreeNodeVo>();
	
	private long id = 0;//子模块id(自定义，非ztree默认)
//	private long mainId = 0;//主模块id(自定义，非ztree默认)
	private String columnName = "";//实体字段名(自定义，非ztree默认)
	
	public ZTreeNodeVo(){
		
	}
	
	public ZTreeNodeVo(DatasourceDto dsDto) {
		this.id = dsDto.getSqu();
		this.name = dsDto.getTitle();
		this.open = true;
	}
	public ZTreeNodeVo(EntityDto entityDto) {
		this.id = entityDto.getSqu();
		this.name = entityDto.getTitle();
		this.columnName = entityDto.getName();
	}
	
	public ZTreeNodeVo(QueryDto queryDto) {
		this.id = queryDto.getSqu();
		this.name = queryDto.getName();
	}

	public ZTreeNodeVo(ModuleMainDto moduleMainDto){
		this.id = moduleMainDto.getSqu();
		this.name = moduleMainDto.getName();
	}

	public ZTreeNodeVo(ModuleSubDto moduleSubDto){
		this.id = moduleSubDto.getSqu();
		this.name = moduleSubDto.getName();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
//	public long getMainId() {
//		return mainId;
//	}
//	public void setMainId(long mainId) {
//		this.mainId = mainId;
//	}
	public List<ZTreeNodeVo> getNodes() {
		return nodes;
	}
	public void setNodes(List<ZTreeNodeVo> nodes) {
		this.nodes = nodes;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
}
