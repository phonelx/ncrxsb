/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.7
	* @version：1.0
	* File Name:IProjectChildSiteService.java
	* Date:2018-2-6上午11:05:56   
	***/
package monitor.projectConfig.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import monitor.projectConfig.bean.entity.Project_Child_Site;
import monitor.projectConfig.bean.entity.Project_DXInfo;
import monitor.projectConfig.bean.entity.T_Project_Zj;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:IProjectChildSiteService
 * @dateTime: 2018-2-6 上午11:05:56
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public interface IProjectChildSiteService {
	
	Project_Child_Site getProject_Child_SiteBySqu(String squ) throws SQLException;
	
	/**
	  * updateReportParam:(上传，修改计算报告参数和文件).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-6-21 下午7:23:44
	  * @param proZj 部位_支架计算报告手动填写信息
	  * @param file1  支架形式简图
	  * @param file2 侧向受力分析图
	  * @param file3 纵向受力分析图
	  * @return String
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void updateReportParam(T_Project_Zj proZj, File file1, File file2, File file3) throws SQLException;
	
	T_Project_Zj getT_Project_ZjById(String squ) throws SQLException;
	
	/**
	  * getHolderNotInSite:(获取该部位下没有的支架).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-24 下午7:18:41
	  * @param pageVo
	  * @return
	  * @throws SQLException PageInfoVo
	  * @since JDK 1.7
	*/
	PageInfoVo getHolderNotInSite(PageInfoVo pageVo, String site_squ) throws SQLException;
	
	/**
	 * 
	  * addChildSite:(新增部位).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午2:14:58
	  * @param site
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	void addChildSite(Project_Child_Site site) throws Exception;;
	
	/**
	 * 
	  * delChildSite:(根据部位squ删除部位).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	 * @return 
	  * @dateTime: 2018-2-6 下午2:15:34
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	String delChildSite(String siteSqu) throws Exception;;
	
	/**
	 * 
	  * updateChildSite:(修改部位).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午2:15:47
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	void updateChildSite(Project_Child_Site site) throws SQLException;;
	
	/**
	 * 
	  * listChildSite:(查询部位列表).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午2:16:07
	  * @param conn
	  * @param chlidSqu
	  * @return
	  * @throws SQLException List<Project_Child_Site>
	  * @since JDK 1.7
	 */
	List<Project_Child_Site> listChildSite(String childSqu) throws Exception;;
	
	/**
	 * 
	  * addChildSite:(部位下面添加支架).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午2:14:58
	  * @param site
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	void addDxInfo(T_Project_Zj proZj) throws Exception;;
	
	/**
	 * 
	  * delChildSite:(根据squ删除部位下面的支架).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午2:15:34
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	void delDxInfo(String proZjSqu) throws Exception;;
	
	/**
	 * 
	  * updateChildSite:(修改部位下面的支架信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午2:15:47
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	void updateDxInfo(T_Project_Zj proZj) throws Exception;;
	
	/**
	  * listDxInfo:(查询该部位下支架列表).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午2:16:07
	  * @param conn
	  * @param chlidSqu
	  * @return
	  * @throws SQLException List<Project_Child_Site>
	  * @since JDK 1.7
	 */
	List<Project_DXInfo> listDxInfo(String siteSqu, String type) throws Exception;
	
	/**
	 * 
	  * countChilSite:(统计部位个数).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-7 下午4:15:06
	  * @param childSqu
	  * @return
	  * @throws Exception List<Map<String,String>>
	  * @since JDK 1.7
	 */
	List<Map<String,String>> countChilSite(String childSqu)throws Exception;
	
	/**
	 * 
	  * countSiteDxInfo:(统计支架个数).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-8 上午9:46:42
	  * @param siteSqu
	  * @return
	  * @throws Exception List<Map<String,String>>
	  * @since JDK 1.7
	 */
	List<Map<String,String>>  countSiteDxInfo(String siteSqu)throws Exception;
	
	/**
	 * 
	  * countParts:(统计部件个数).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-8 上午9:47:47
	  * @param siteSqu
	  * @return
	  * @throws Exception String
	  * @since JDK 1.7
	 */
	String countParts(String siteSqu)throws Exception;
	
	/**
	 * 
	  * selectLbxx:(查询类别系数).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-20 上午9:35:56
	  * @param conn
	  * @return
	  * @throws Exception List<Map<String,String>>
	  * @since JDK 1.7
	 */
	List<Map<String,String>> selectLbxx()throws Exception;
	
	/**
	  * numberTheHolder:(给之间编号).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	 * @throws SQLException 
	  * @dateTime: 2018-6-2 上午11:27:27 void
	  * @since JDK 1.7
	*/
	void numberTheHolder(String siteSqu) throws SQLException;
}
