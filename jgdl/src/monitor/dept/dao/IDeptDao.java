package monitor.dept.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import monitor.dept.bean.entity.DeptBean;
import monitor.user.bean.vo.PageInfoVo;

public interface IDeptDao {

	PageInfoVo listDept(PageInfoVo pageVo, String searchKey) throws SQLException;

	int addDept(DeptBean dept) throws SQLException;

	int delDept(String bmdm) throws SQLException;

	void editDept(DeptBean dept) throws SQLException;

	List<Map<String, Object>> getAlluser() throws SQLException;

	PageInfoVo getUserBySqu(String bmdm, PageInfoVo pageVo) throws SQLException;

	void clearDept();

	void upDataDept(List<DeptBean> list);

	void otherDataDept(List<DeptBean> list);

	List<DeptBean> listDeptByBmdm(String pd) throws SQLException;

	public int countParentNotes(String pd);
	
	/**
	  * searchUser:(为添加用户到部门加载用户列表).<br/>
	  * @author: 张家俊
	  * @dateTime: 2016-12-19 下午3:30:07
	  * @param: @param param
	  * @param: @return
	  * @return: List<Map<String,Object>>
	  * @throws
	  * @since JDK 1.6
	*/
	public List<Map<String,Object>> searchUser(String param);
	
	/**
	  * searchUserCount:(查询满足条件的用户数量).<br/>
	  * @author: 张家俊
	  * @dateTime: 2016-12-19 下午3:42:33
	  * @param: @param param
	  * @param: @return
	  * @return: int
	  * @throws
	  * @since JDK 1.6
	*/
	public int searchUserCount(String param);
	
	/**
	  * addUserToDept:(添加\编辑\删除部门下的用户).<br/>
	  * @author: 张家俊
	  * @dateTime: 2016-12-19 下午3:30:12
	  * @param: @param bmdm
	  * @param: @param array
	  * @param: @throws SQLException
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void addUserToDept(String bmdm, String[] array) throws SQLException;
}
