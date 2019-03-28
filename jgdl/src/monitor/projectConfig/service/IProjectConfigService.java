package monitor.projectConfig.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import monitor.projectConfig.bean.entity.Project;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:IProjectConfigService
 * @dateTime: 2017-10-14 下午3:22:31
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public interface IProjectConfigService {
	
	/**
	  * listCountMsg:(查询各级数据统计).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-2-8 下午6:52:11
	  * @param squ
	  * @param level
	  * @return List<Integer>
	  * @since JDK 1.7
	*/
	List<Integer> listCountMsg(String squ, String level, String type);
	
	/**
	  * updateHolderNumOfProject:(修改项目支架数量).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-20 上午11:23:40
	  * @param num void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void updateHolderNumOfProject(Integer num, String holderSqu, String projectSqu) throws SQLException;
	
	/**
	  * addHolderToProject:(新增支架).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-24 下午9:03:02
	  * @param holderSqu
	  * @param projectSqu void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void addHolderToProject(String holderSqu, String projectSqu) throws SQLException;
	
	/**
	  * getHolderList:(获取支架分页列表).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-24 下午7:18:41
	  * @param pageVo
	  * @return
	  * @throws SQLException PageInfoVo
	  * @since JDK 1.7
	*/
	PageInfoVo getHolderList(PageInfoVo pageVo, String gdlx, String azfs, String search) throws SQLException;

	/**
	  * selectProjectPageInfo:(分页查询项目列表).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-14 下午3:10:39
	  * @return List<T_Project>
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	PageInfoVo selectProjectPageInfo(PageInfoVo pageVo, String searchWord) throws SQLException;
	
	
	/**
	  * selectHolderOfProjectPageInfo:(分页查询项目支架信息列表).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-23 下午8:04:55
	  * @param pageVo
	  * @return
	  * @throws SQLException PageInfoVo
	  * @since JDK 1.7
	*/
	PageInfoVo selectHolderOfProjectPageInfo(PageInfoVo pageVo, String squ) throws SQLException;
	
	/**
	  * selectProjectAll:(查询所有项目).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-23 下午6:39:29
	  * @return List<T_Project>
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	List<Project> selectProjectAll() throws SQLException;
	
	/**
	  * selectProjectBySqu:(查询单个项目).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-14 下午3:26:43
	  * @return T_Project
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	Project selectProjectBySqu(String squ) throws SQLException;
	
	/**
	  * insertProject:(新增项目).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-14 下午3:24:34
	  * @param project void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void insertProject(Project project) throws SQLException;
	
	/**
	  * updateProject:(修改项目).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-14 下午3:24:57
	  * @param project void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void updateProjectBySqu(Project project) throws SQLException;
	
	/**
	  * deleteProjectBySqu:(根据id删除项目).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-14 下午3:25:23
	  * @param project void
	 * @return 
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	String deleteProjectBySqu(String squ) throws SQLException;
	
	/**
	  * deleteHolderOfProjectBySqu:(根据squ删除项目支架).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-20 上午10:55:39
	  * @param squ
	  * @param conn void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void deleteHolderOfProjectBySqu(String squ) throws SQLException;
	
	/**
	  * getSelectOfObject:(获取下拉列表).<br/>
	  * TODO(项目基本配置).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-22 下午4:57:44
	  * @return List<String>
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	List<String> getSelectOfObject(String selectType) throws SQLException;
	
	List<Map<String, String>> getSelectOfGDLX_AZFS(String selectType) throws SQLException;
	
	/**
	 * 
	  * getProjectInfo:(根据项目squ查询该项目信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-6-25 下午6:19:56
	  * @param projectSqu
	  * @return Project
	  * @since JDK 1.7
	 */
	public Project getProjectInfoBySqu(String projectSqu);
}
