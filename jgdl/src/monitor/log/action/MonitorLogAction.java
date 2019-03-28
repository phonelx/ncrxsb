/***
 * copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * Project Name:monitor
 * @since：JDK1.6
 * @version：1.0
 * File Name:MonitorLogAction.java
 * Date:2016-3-22下午12:44:40
 ***/
package monitor.log.action;

import org.aspectj.lang.JoinPoint;

import monitor.common.BaseAction;
import monitor.log.bean.entity.InterLogEntity;
import monitor.log.service.IMonitorLogService;

/**
 * @ClassName:MonitorLogAction
 * @dateTime: 2016-3-22 下午12:44:40
 * @version
 * @author: 张家俊
 * @since JDK 1.6 History： Editor version Time Operation
 */
public class MonitorLogAction extends BaseAction {
	private IMonitorLogService monitorLogService;

	public IMonitorLogService getMonitorLogService() {
		return monitorLogService;
	}

	public void setMonitorLogService(IMonitorLogService monitorLogService) {
		this.monitorLogService = monitorLogService;
	}

	/**
	 * @since JDK1.6
	 */
	private static final long serialVersionUID = 1L;
	String sql = "";

	/**
	 * inter_log:("为实体对像和SQL语句赋初值").<br/>
	 * 
	 * @author: 张家俊
	 * @dateTime: 2016-3-22 下午12:37:18
	 * @param: @param jp
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void inter_log(JoinPoint jp) {
		InterLogEntity ilEntity = new InterLogEntity();
		String methodName = jp.getSignature().getName();

		sql = "insert into inter_log(num_id,interface_time,requester,terminal_id,interface_condition,interface_result)values(INTERLOG.NEXTVAL,TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS'),?,?,?,?)";
		ilEntity.setRequester("");// 请求方的应用系统或客户端名称
		ilEntity.setTerminal_id(getIpAddr());// 赋值IP
		ilEntity.setInterface_condition(methodName);// 记录接口服务的接口名称和参数值。
		ilEntity.setInterface_result("1");// 接口服务的结果记录.1:成功；0：失败。

		monitorLogService.executeInter_log(ilEntity, sql);
	}

	/**
	 * getIpAddr:(获取当前请求的IP).<br/>
	 * 
	 * @author: 张家俊
	 * @dateTime: 2016-3-22 下午1:06:54
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since JDK 1.6
	 */
	private String getIpAddr() {
		String ip = getRequest().getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		return ip;
	}
}
