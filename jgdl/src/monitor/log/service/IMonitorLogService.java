/***
 * copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * Project Name:monitor
 * @since：JDK1.6
 * @version：1.0
 * File Name:IMonitorLogService.java
 * Date:2016-3-22下午12:47:43
 ***/
package monitor.log.service;

import monitor.log.bean.entity.InterLogEntity;

/**
 * @ClassName:IMonitorLogService
 * @dateTime: 2016-3-22 下午12:47:43
 * @version
 * @author: 张家俊
 * @since JDK 1.6 History： Editor version Time Operation
 */
public interface IMonitorLogService {
	public void executeInter_log(InterLogEntity ilEntity, String sql);
}
