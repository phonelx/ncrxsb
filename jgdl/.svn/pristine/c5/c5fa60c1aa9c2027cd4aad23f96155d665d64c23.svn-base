package monitor.dept.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import monitor.dept.bean.entity.DeptBean;
import monitor.dept.bean.entity.ServiceUpdate;
import monitor.user.bean.vo.PageInfoVo;

public interface IDeptService {
	PageInfoVo listDept(PageInfoVo pageVo, String searchKey) throws SQLException;

	int addDept(DeptBean dept) throws SQLException;

	int delDept(String bmdm) throws SQLException;

	void editDept(DeptBean dept) throws SQLException;

	List<Map<String, Object>> getAllUser() throws SQLException;

	PageInfoVo getUserBySqu(String bmdm, PageInfoVo pageVo) throws SQLException;

	void clearDept();

	int deptDataUpdate(ServiceUpdate sync);

	String getDeptData(String SendID);

	public int countParentNotes(String pd);

	/**
	 * 
	 * listDeptByBmdm:(根据部门代码查询相关部门).<br/>
	 * 
	 * @author: 黄月
	 * @dateTime: 2016-8-26 下午2:12:15
	 * @param: @return
	 * @param: @throws SQLException
	 * @return: List<DeptBean>
	 * @throws SQLException 
	 * @throws
	 * @since JDK 1.7
	 */
	List<DeptBean> listDeptByBmdm(String pd) throws SQLException;
	
	/**
	  * searchUser:(为添加用户到部门加载用户列表).<br/>
	  * @author: 张家俊
	  * @dateTime: 2016-12-19 下午3:25:02
	  * @param: @param param
	  * @param: @return
	  * @return: JSONObject
	  * @throws
	  * @since JDK 1.6
	*/
	public JSONObject searchUser(String param);

	/**
	  * addUserToDept:(添加\编辑\删除部门下的用户).<br/>
	  * @author: 张家俊
	  * @dateTime: 2016-12-19 下午3:25:06
	  * @param: @param bmdm
	  * @param: @param array
	  * @param: @throws SQLException
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void addUserToDept(String bmdm, String[] array) throws SQLException;
}
