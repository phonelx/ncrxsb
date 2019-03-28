/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-6-4 下午03:57:51
*/
package monitor.user.dao;

import java.util.Set;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-6-4 下午03:57:51
 */
public interface IAuthorityDAO {
	public Set<String> getPublicActionSet();
	
	public Set<String> getPublicJspSet();
	
	/**
	 * 查看当前action地址是否已被授权
	 * @author:  cl 
	 * @param 
	 * @return boolean
	 */
	public boolean checkRight(String currentURI,int roleSqu);
}
