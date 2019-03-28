package monitor.projectConfig.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;



/**
 *@ClassName
 *@dataTime 2018-4-9-下午2:39:13
 *@version
 *@author:唐青
 *@since
 */
public interface ICalculationReportDao {

	/**
	 * 
	  * selectProjectInfo:(根据项目SQU查询项目信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-9 下午3:05:44
	  * @param prosqu 项目squ
	  * @return
	  * @throws SQLException List<Map<String,String>>
	  * @since JDK 1.7
	 */
	public Map<String,String> selectProjectInfo(Connection conn , String prosqu) throws SQLException;
	
	/**
	 * 
	  * selectChildInfo:(查询子单位工程信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-13 上午9:36:10
	  * @param conn
	  * @param childsqu 子单位工程squ
	  * @return
	  * @throws SQLException Map<String,String>
	  * @since JDK 1.7
	 */
	public Map<String,String> selectChildInfo(Connection conn , String childsqu)throws SQLException;

	/**
	 * 
	  * selectSiteInfo:(查询部位信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-13 上午10:11:03
	  * @param conn
	  * @param sitesqu
	  * @return
	  * @throws SQLException Map<String,String>
	  * @since JDK 1.7
	 */
	public Map<String,String> selectSiteInfo(Connection conn , String sitesqu)throws SQLException;
	
	/**
	 * 
	  * selectZjInfo:(查询支架信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-13 上午10:12:02
	  * @param conn
	  * @param zjsqu
	  * @return
	  * @throws SQLException Map<String,String>
	  * @since JDK 1.7
	 */
	public Map<String,String> selectZjInfo(Connection conn , String zjsqu)throws SQLException;
}
