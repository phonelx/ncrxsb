package monitor.common.service;

import javax.sql.DataSource;

/**
 * @ClassName:BaseServicce
 * @dateTime: 2017-10-14 下午3:46:40
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class BaseServicce {
	protected DataSource datasource = null;

	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
}
