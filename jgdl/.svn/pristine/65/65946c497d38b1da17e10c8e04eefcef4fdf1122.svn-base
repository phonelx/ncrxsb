/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-23 下午04:14:45
*/
package monitor.user.bean.dto;

import monitor.user.bean.vo.ZTreeNodeVo;

/**
 * 
 * <description> 
 * @author  cl
 * @datetime  2011-4-25 下午01:15:37
 */
public class EntityDto {
	private int squ = -1;
	private int datasourceSqu = -1;
	private String name = null;
	private String title = null;
	private int typeSqu = -1 ;
	private int isView = 0 ;
	public EntityDto(){
		
	}
	
	/*public EntityDto(TreeNodeVo nodeVo) {
		this.squ = nodeVo.getId();
		this.name = nodeVo.getText();
	}*/
	public EntityDto(ZTreeNodeVo nodeVo) {
		this.squ = new Long(nodeVo.getId()).intValue();
		this.name = nodeVo.getColumnName();
	}

	public int getSqu() {
		return squ;
	}
	public void setSqu(int squ) {
		this.squ = squ;
	}
	public int getDatasourceSqu() {
		return datasourceSqu;
	}
	public void setDatasourceSqu(int datasourceSqu) {
		this.datasourceSqu = datasourceSqu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	/** 
	 * @return typeSqu 
	 */
	public int getTypeSqu() {
		return typeSqu;
	}

	/** 
	 */
	
	public void setTypeSqu(int typeSqu) {
		this.typeSqu = typeSqu;
	}

	/** 
	 * @return isView 
	 */
	public int getIsView() {
		return isView;
	}

	/** 
	 */
	
	public void setIsView(int isView) {
		this.isView = isView;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + squ;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityDto other = (EntityDto) obj;
		if (squ != other.squ)
			return false;
		return true;
	}
	
	
}
