/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-12-18 上午11:47:18
*/
package monitor.common.dao;

import java.util.List;

import monitor.common.bean.dto.ResourceTypeDto;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-12-18 上午11:47:18
 */
public interface IResourceTypeDAO {

	/**
	 * 根据父资源类型squ查询所有子资源类型dtos
	 * @author:  cl 
	 * @param long parentSqu 父资源squ
	 * @return List<ResourceTypeDto> 所有子资源类型dtos的list
	 */
	List<ResourceTypeDto> fetchResourceTypesByParentSQU(long parentSqu);

	/**
	 * 根据子资源类型查询父资源类型squ
	 * @author:  cl 
	 * @param 
	 * @return long 父资源类型squ
	 */
	long fetchParentResourceTypesSqu(long squ);
	public String genResourceTypeTree() ;
}
