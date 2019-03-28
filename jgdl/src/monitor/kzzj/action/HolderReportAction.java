/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.7
	* @version：1.0
	* File Name:HolderReportAction.java
	* Date:2017-10-31上午11:57:24   
	***/
package monitor.kzzj.action;

import javax.annotation.Resource;

import monitor.common.BaseAction;
import monitor.common.util.ExcleUtil;
import monitor.kzzj.service.impl.HolderReportServiceImpl;
import monitor.projectConfig.bean.entity.Project;
import monitor.projectConfig.bean.entity.Project_Child;
import monitor.projectConfig.service.impl.ProjectChildServiceImpl;
import monitor.projectConfig.service.impl.ProjectConfigServiceImpl;

/**
 * @ClassName:HolderReportAction
 * @dateTime: 2017-10-31 上午11:57:24
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class HolderReportAction extends BaseAction {

	/**
	  * @Fields  serialVersionUID:TODO(用一句话描述这个变量表示什么)
	  * @since JDK1.7
	*/
	private static final long serialVersionUID = 1L;
	
	/**
	  * @Fields  holderReportService:TODO(service注入)
	  * @since JDK1.7
	*/
	@Resource(name = "holderReportService")
	private HolderReportServiceImpl holderReportService;
	
	
	@Resource(name = "projectService")
	private ProjectConfigServiceImpl projectService;
	
	@Resource(name = "projectChildService")
	private ProjectChildServiceImpl projectChildService;
	
	/**
	  * toHolderReportIndex:(跳转抗震支架检验计算报告页面).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-31 下午12:08:41
	  * @return String
	  * @since JDK 1.7
	*/
	public String toHolderReportIndex() {
		return SUCCESS;
	}
	

	/**
	 * 
	  * downLoadReport:(计算报告导出excle表).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-6-27 下午8:15:58 void
	  * @since JDK 1.7
	 */
	public void downLoadReport(){
		try {
			Project project = projectService.getProjectInfoBySqu("BA116D000C9B40B991008C2286E2F3D9");
			Project_Child child = projectChildService.getChildProjectBySqu("77006594FA0E448D8F445D8518757949");
			ExcleUtil.drowExcle(project,child);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
