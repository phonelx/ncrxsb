/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-12-18 上午11:47:05
*/
package monitor.common.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import monitor.common.bean.dto.ResourceTypeDto;
import monitor.common.dao.IResourceTypeDAO;
import monitor.common.service.IResourceTypeService;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-12-18 上午11:47:05
 */
public class ResourceTypeServiceImpl implements IResourceTypeService {
	private IResourceTypeDAO resourceTypeDAO = null;
	public IResourceTypeDAO getResourceTypeDAO() {
		return resourceTypeDAO;
	}
	public void setResourceTypeDAO(IResourceTypeDAO resourceTypeDAO) {
		this.resourceTypeDAO = resourceTypeDAO;
	}



	/**
	 * 根据父资源类型squ查询所有子资源类型dtos
	 * @author:  cl 
	 * @param long parentSqu 父资源squ
	 * @return String 所有子资源类型dtos的json字符串
	 */
	public String fetchResourceTypesByParentSQU(long parentSqu) {
		List<ResourceTypeDto> resourceTypeList = resourceTypeDAO.fetchResourceTypesByParentSQU(parentSqu);
		JSONArray jsonArray = JSONArray.fromObject(resourceTypeList);
		return jsonArray.toString();
	}
	
	/**
	 * 根据子资源类型查询父资源类型squ
	 * @author:  cl 
	 * @param 
	 * @return long 父资源类型squ
	 */
	public long fetchParentResourceTypesSqu(long squ) {
		return resourceTypeDAO.fetchParentResourceTypesSqu(squ);
	}
    /* (non-Javadoc)
     * @see ssp.common.service.IResourceTypeService#genResourceTypeSqu()
     */
    @Override
    public String genResourceTypeTree() {
        return resourceTypeDAO.genResourceTypeTree();
    }

}
