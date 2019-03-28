/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-6-4 下午03:53:34
*/
package monitor.user.service;


/** 
 * <description> 
 * @author  cl
 * @datetime  2011-6-4 下午03:53:34
 */
public interface IAuthorityService {
	/**
	 * 检查当前action访问url是否是公共actionUrl
	 * @author:  cl
	 * @param 
	 * @return boolean
	 */
	public boolean checkPublicAction(String currentURI);
	
	/**
	 * 检查当前jsp访问url是否是公共jspUrl
	 * @author:  cl
	 * @param 
	 * @return boolean
	 */
	public boolean checkPublicJsp(String currentURI);
	
	/**
	 * 查看当前action地址是否已被授权
	 * @author:  cl
	 * @param 
	 * @return boolean
	 */
	public boolean checkRight(String currentURI,int roleSqu);

}
