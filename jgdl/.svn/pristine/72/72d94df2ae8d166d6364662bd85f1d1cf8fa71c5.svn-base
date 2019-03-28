/*
 * @copyright:  heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  Apr 18, 2011 1:31:13 PM
*/
package monitor.log.dao;

import java.util.List;
import java.util.Map;

/** 
 * <description> 
 * @author  jk
 * @datetime  Apr 18, 2011 1:31:13 PM
 */
public interface ISearchLogDAO {
	public List<String>searchLogS(String table,String []xszd,String []cxzd,String []input,int pageSize,int pageNo);
	public List<String> searchLogNoPage(String table, String[] xszd,String[] cxzd, String[] input);
	public void deleteLog(String table, String[] cxzd, String[] input);
	public  List doQueryAgain(String sql,int dbsSqu,int maxRows,int pageSize,int pageNo);
}
