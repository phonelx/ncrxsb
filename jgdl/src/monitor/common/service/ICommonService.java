/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-28 下午03:37:45
*/
package monitor.common.service;

import monitor.common.bean.entity.ErrorDataReportEntity;
import monitor.common.bean.entity.OperationEntity;
import monitor.common.bean.vo.DataSizeVo;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-28 下午03:37:45
 */
public interface ICommonService {
	/**
	 * 记录用户操作信息，存入数据库
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String recordOperationType(OperationEntity opEntity);
	
	/**
	 * 统计数据库中所有资源的记录总数 
	 * @author: cl 
	 * @param 
	 * @return String
	 */
	public String calculateAllFactourCounts();

	/**
	 * 统计资源库中所有资源占用的数据空间情况 
	 * @author: cl 
	 * @param 
	 * @return DataSizeVo
	 */
	public DataSizeVo calculateAllDataSize();
	
	/**
	 * 首页保存错误数据信息 
	 * @author: yl 
	 * @param  rer
	 * @return null
	 */
	public void saveErrorData(ErrorDataReportEntity rer);
	
	/**
	 * 进行同名验证
	 * @param tableName 表名
	 * @param filedName 字段名
	 * @param filedName 需要验证的名字
	 * @return boolean(true|false)
	 */
	public boolean sameNameJudge(String tableName,String filedName,
			String nowUseName,String dbid,String tabid);
	/**
	 * 检测系统运行状态
	 * @return
	 */
	public String checkSysStatus();
}
