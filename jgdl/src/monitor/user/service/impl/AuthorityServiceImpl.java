/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-6-4 下午03:54:13
*/
package monitor.user.service.impl;

import monitor.user.dao.IAuthorityDAO;
import monitor.user.service.IAuthorityService;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-6-4 下午03:54:13
 */
public class AuthorityServiceImpl implements IAuthorityService {
	private IAuthorityDAO authDAO = null;
	public IAuthorityDAO getAuthDAO() {
		return authDAO;
	}
	public void setAuthDAO(IAuthorityDAO authDAO) {
		this.authDAO = authDAO;
	}
	/**
	 * 检查当前action访问url是否是公共actionUrl
	 * @author:  cl
	 * @param 
	 * @return boolean
	 */
	public boolean checkPublicAction(String currentURI){
		return this.authDAO.getPublicActionSet().contains(currentURI);
	}
	
	/**
	 * 检查当前jsp访问url是否是公共jspUrl
	 * @author:  cl
	 * @param 
	 * @return boolean
	 */
	public boolean checkPublicJsp(String currentURI){
		return this.authDAO.getPublicJspSet().contains(currentURI);
	}
	
	
	/**
	 * 查看当前action地址是否已被授权
	 * @author:  cl
	 * @param 
	 * @return boolean
	 */
	public boolean checkRight(String currentURI,int roleSqu){
		return authDAO.checkRight(currentURI, roleSqu);
	}
	
	
}
