/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-24 上午10:18:33
*/
package monitor.user.bean.dto;

import java.util.List;

import monitor.user.bean.vo.TreeNodeVo;
import monitor.user.bean.vo.ZTreeNodeVo;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-24 上午10:18:33
 */
public class QueryDto {
	private int squ = -1;
	private int dsSqu = -1;//cl add,11-06-21
	private String name = null;
	private List<EntityDto> entityDtoList = null;
	
	public QueryDto() {
	
	}
	
	public QueryDto(TreeNodeVo nodeVo) {
		this.squ = nodeVo.getId();
		this.name = nodeVo.getText();
	}
	
	
	public QueryDto(ZTreeNodeVo nodeVo) {
		this.squ = new Long(nodeVo.getId()).intValue();
		this.name = nodeVo.getColumnName();
	}

	public int getSqu() {
		return squ;
	}
	public void setSqu(int squ) {
		this.squ = squ;
	}
	public int getDsSqu() {
		return dsSqu;
	}

	public void setDsSqu(int dsSqu) {
		this.dsSqu = dsSqu;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EntityDto> getEntityDtoList() {
		return entityDtoList;
	}
	public void setEntityDtoList(List<EntityDto> entityDtoList) {
		this.entityDtoList = entityDtoList;
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
		QueryDto other = (QueryDto) obj;
		if (squ != other.squ)
			return false;
		return true;
	}
	
}
