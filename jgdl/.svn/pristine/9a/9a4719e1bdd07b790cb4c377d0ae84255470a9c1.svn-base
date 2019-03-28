/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-12-18 上午11:46:46
*/
package monitor.common.service;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-12-18 上午11:46:46
 */
public interface IResourceTypeService {

	/**
	 * 根据父资源类型squ查询所有子资源类型dtos
	 * @author:  cl 
	 * @param long parentSqu 父资源squ
	 * @return String 所有子资源类型dtos的json字符串
	 */
	String fetchResourceTypesByParentSQU(long parentSqu);

	/**
	 * 根据子资源类型查询父资源类型squ
	 * @author:  cl 
	 * @param 
	 * @return long 父资源类型squ
	 */
	long fetchParentResourceTypesSqu(long squ);
	/**
	 * 生成资源分类树
	 * @return
	 */
	public String genResourceTypeTree() ;
}
