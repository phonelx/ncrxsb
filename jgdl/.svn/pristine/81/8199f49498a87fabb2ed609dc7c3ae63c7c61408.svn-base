/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-12-18 上午11:42:56
 */
package monitor.common.action;

import monitor.common.BaseAction;
import monitor.common.bean.dto.ResourceTypeDto;
import monitor.common.service.IResourceTypeService;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 操作数据资源类型的action
 * 
 * @author cl
 * @datetime 2011-12-18 上午11:42:56
 */
public class ResourceTypeAction extends BaseAction implements ModelDriven<ResourceTypeDto> {
	private ResourceTypeDto resourceTypeDto = new ResourceTypeDto();
	private IResourceTypeService resourceTypeService = null;
	public ResourceTypeDto getModel() {
		return resourceTypeDto;
	}
	
	public ResourceTypeDto getResourceTypeDto() {
		return resourceTypeDto;
	}

	public void setResourceTypeDto(ResourceTypeDto resourceTypeDto) {
		this.resourceTypeDto = resourceTypeDto;
	}

	public IResourceTypeService getResourceTypeService() {
		return resourceTypeService;
	}

	public void setResourceTypeService(IResourceTypeService resourceTypeService) {
		this.resourceTypeService = resourceTypeService;
	}
	/** 
	 * @field serialVersionUID
	*/
	private static final long serialVersionUID = -1392215664106777061L;
	

	/**
	 * 根据父资源类型查询所有子资源dtos
	 * @author:  cl 
	 * @param 
	 * @return void
	 */
	public void fetchResourceTypesByParentSQU() {
		String parentSqu = getRequest().getParameter("psqu");
		if(parentSqu!=null&&!"".equals(parentSqu.trim())){
			long lParentSqu = Long.parseLong(parentSqu);
			String jsonStr = resourceTypeService.fetchResourceTypesByParentSQU(lParentSqu);
			super.writeStringBack(jsonStr);
		}
	}

	/**
	 * 根据子资源类型查询父资源类型squ
	 * @author:  cl 
	 * @param 
	 * @return void
	 */
	public void fetchParentResourceTypesSqu(){
		String squ = getRequest().getParameter("squ");
		if(squ!=null&&!"".equals(squ.trim())){
			long lSqu = Long.parseLong(squ);
			long lParentSqu = resourceTypeService.fetchParentResourceTypesSqu(lSqu);
			super.writeStringBack(""+lParentSqu);
		}
	}
	/**
	 * 生成资源树
	 */
	public void genResourceTypeTree(){
	    writeStringBack(resourceTypeService.genResourceTypeTree());
	}
}
