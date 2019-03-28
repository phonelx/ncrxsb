/*
 * @copyright:  heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  Apr 18, 2011 5:21:10 PM
*/
package monitor.log.service;

import java.util.List;

/** 
 * <description> 
 * @author  jk
 * @datetime  Apr 18, 2011 5:21:10 PM
 */
public interface ISearchLogService {

//	public List<UserLogInfoVO> searchLogS(UserLogInfoVO userloginfo);
	public List<String> searchLogS(String table,String []columns,String []checkcols,String []input,int pageSize,int pageNo);
	public String exportUserLgIAsExcel(String table, String[] xszd,String[] cxzd, String[] input,String userName);
	public String exportUserOprAsExcel(String table, String[] xszd,String[] cxzd, String[] input,String userName);
	public String exportSysRunAsExcel(String table, String[] xszd,String[] cxzd, String[] input,String userName);
	public void deleteLogs(String table,String []checkcols,String []input);
	public  List doQueryAgain(String sql,int dbsSqu,int maxRows,int pageSize,int pageNo);
}
