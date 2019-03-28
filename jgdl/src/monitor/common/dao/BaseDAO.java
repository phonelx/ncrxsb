/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-5-17 下午03:26:57
*/
package monitor.common.dao;

import javax.sql.DataSource;

/** 
 * DAO类的基类
 * @author  cl
 * @datetime  2011-5-17 下午03:26:57
 */
public class BaseDAO {
	protected DataSource datasource = null;

	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	
}	
