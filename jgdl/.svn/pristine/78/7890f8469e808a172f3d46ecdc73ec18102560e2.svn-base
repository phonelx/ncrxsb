/***
 * copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * Project Name:monitor
 * @since：JDK1.6
 * @version：1.0
 * File Name:MonitorLogServiceImpl.java
 * Date:2016-3-22下午12:51:04
 ***/
package monitor.log.service.impl;

import monitor.log.bean.entity.InterLogEntity;
import monitor.log.dao.IMonitorLogDAO;
import monitor.log.service.IMonitorLogService;

/**
 * @ClassName:MonitorLogServiceImpl
 * @dateTime: 2016-3-22 下午12:51:04
 * @version
 * @author: 张家俊
 * @since JDK 1.6 History： Editor version Time Operation
 */
public class MonitorLogServiceImpl implements IMonitorLogService {
	private IMonitorLogDAO monitorLogDAO;

	public IMonitorLogDAO getMonitorLogDAO() {
		return monitorLogDAO;
	}

	public void setMonitorLogDAO(IMonitorLogDAO monitorLogDAO) {
		this.monitorLogDAO = monitorLogDAO;
	}

	/**
	 * @see monitor.log.service.IMonitorLogService#inter_log(monitor.log.bean.entity.InterLogEntity,
	 *      java.lang.String)
	 */
	@Override
	public void executeInter_log(InterLogEntity ilEntity, String sql) {
		monitorLogDAO.executeInter_log(ilEntity, sql);
	}
}
