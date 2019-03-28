/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-28 下午03:38:24
*/
package monitor.common.service.impl;


import monitor.common.bean.entity.ErrorDataReportEntity;
import monitor.common.bean.entity.OperationEntity;
import monitor.common.bean.vo.DataSizeVo;
import monitor.common.dao.ICommonDAO;
import monitor.common.service.ICommonService;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-28 下午03:38:24
 */
public class CommonServiceImpl implements ICommonService {
	private ICommonDAO commonDAO = null;
	
	public ICommonDAO getCommonDAO() {
		return commonDAO;
	}
	public void setCommonDAO(ICommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	/**
	 * 记录用户操作信息，存入数据库
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String recordOperationType(OperationEntity opEntity) {
		return commonDAO.recordOperationType(opEntity);
	}
	
	

	/**
	 * 统计数据库中所有资源的记录总数 
	 * @author: cl 
	 * @param 
	 * @return String
	 */
	public String calculateAllFactourCounts(){
		return commonDAO.calculateAllFactourCounts();
	}
	
	/**
	 * 统计资源库中所有资源占用的数据空间情况 
	 * @author: cl 
	 * @param 
	 * @return DataSizeVo
	 */
	public DataSizeVo calculateAllDataSize() {
		return commonDAO.calculateAllDataSize();
	}
	
	/**
	 * 首页保存错误数据信息 
	 * @author: yl 
	 * @param rer
	 * @return null
	 */
	public void saveErrorData(ErrorDataReportEntity rer) {
		 commonDAO.saveErrorData(rer);
	}
	
	/**
	 * 进行同名验证
	 * @param tableName 表名
	 * @param fileName 字段名
	 * @param filedName 需要验证的名字
	 * @return boolean(true|false)
	 */
	public boolean sameNameJudge(String tableName,String filedName,String nowUseName,String dbid,String tabid){
		return commonDAO.sameNameJudge(tableName,filedName,nowUseName,dbid,tabid);
	}
    /* (non-Javadoc)
     * @see ssp.common.service.ICommonService#checkSysStatus()
     */
    @Override
    public String checkSysStatus() {
        return commonDAO.checkSysStatus();
    }
	
}
