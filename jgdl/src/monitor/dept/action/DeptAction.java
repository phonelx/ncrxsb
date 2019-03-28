package monitor.dept.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import monitor.common.BaseAction;
import monitor.common.exception.DaoException;
import monitor.common.util.Constant;
import monitor.dept.bean.entity.DeptBean;
import monitor.dept.bean.entity.ServiceUpdate;
import monitor.dept.service.IDeptService;
import monitor.user.bean.vo.PageInfoVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName:DeptAction
 * @dateTime: 2015-8-13 下午8:13:57
 * @version
 * @author: 张敏
 * @since JDK 1.6 History： Editor version Time Operation
 */
public class DeptAction extends BaseAction {

	/**
	 * @Fields serialVersionUID:唯一标识
	 * @since JDK1.6
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Fields pageVo:分页工具
	 * @since JDK1.6
	 */
	private PageInfoVo pageVo = new PageInfoVo();

	private IDeptService deptService;

	private DeptBean dept;

	private ServiceUpdate sync;

	/**
	 * showDeptIndex:跳转到部门管理
	 * 
	 * @author: 张敏
	 * @dateTime: 2015-8-13 下午8:59:01
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since JDK 1.6
	 */
	public String showDeptIndex() {
		return "success";
	}

	/**
	 * 
	 * listDeptByBmdm:(组织机构树).<br/>
	 * 
	 * @author: 黄月
	 * @dateTime: 2016-10-14 下午5:07:34
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.7
	 */
	public void listDeptByBmdm() {
		int count = 0;
		String pd = getRequest().getParameter("id");
		List<DeptBean> deptList = null;
		try {
			deptList = deptService.listDeptByBmdm(pd);
			String pd1 = "";
			if (!deptList.isEmpty()) {
				JSONArray json = new JSONArray();
				for (DeptBean de : deptList) {
					JSONObject jo = new JSONObject();
					jo.put("id", de.getBmdm());
					pd1 = de.getBmdm();
					jo.put("name", de.getBmmc());
					jo.put("pId", pd);
					// 如果bean的bmdm不是另外任意一个bean的parentbmdm，那么设置‘isParent属性为false’
					count = deptService.countParentNotes(pd1);
					if (count == 0) {
						jo.put("isParent", false);
					}
					if (count > 0) {
						jo.put("isParent", true);
					}
					json.add(jo);
				}
				writeJsonBack(json.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * loadCategoryMenu:部门列表
	 * @author: 张敏
	 * @dateTime: 2015-6-23 下午7:42:50
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void listDept() {
		// 每页显示条数
		int pageSize = Integer.parseInt(getRequest().getParameter(
				Constant.STRROWS));
		// 当前页
		int pageNo = Integer.parseInt(getRequest().getParameter(
				Constant.STRPAGE));
		String searchKey = getRequest().getParameter("searchKey");
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		try {
			pageVo = deptService.listDept(pageVo, searchKey);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
		writeJsonBack(jsonObj.toString());
	}

	/**
	 * addDept:新增部门
	 * 
	 * @author: 张敏
	 * @dateTime: 2015-8-14 上午9:47:40
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void addDept() {
		int count = 0;
		try {
			count = deptService.addDept(dept);
		} catch (SQLException e) {
			count = -1;
			throw new DaoException();
		} finally {
			String result = "部门新增成功";
			if (count > 0) {
				result = "部门已存在";
			}
			if (count < 0) {
				result = "新增失败";
			}
			writeJsonBack(result);
		}
	}

	/**
	 * delDept:删除部门信息
	 * 
	 * @author: 张敏
	 * @dateTime: 2015-8-14 上午10:02:15
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void delDept() {
		String result = "部门删除成功";
		int count = 0;
		try {
			count = deptService.delDept(dept.getBmdm());
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			if (count > 0) {
				result = "该部门下还有子部门，无法删除";
			}
			writeJsonBack(result);
		}
	}

	/**
	 * delDept:删除部门信息
	 * @author: 张敏
	 * @dateTime: 2015-8-14 上午10:02:15
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void editDept() {
		String result = "部门编辑成功";
		try {
			deptService.editDept(dept);
		} catch (SQLException e) {
			result = "部门编辑失败";
			throw new DaoException();
		} finally {
			writeJsonBack(result);
		}
	}

	/**
	 * findAllUser:加载用户列表
	 * 
	 * @author: 张敏
	 * @dateTime: 2015-8-14 下午4:26:08
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void findAllUser() {
		List<Map<String, Object>> list;
		try {
			list = deptService.getAllUser();
		} catch (SQLException e) {
			throw new DaoException();
		}
		JSONObject json = new JSONObject();
		json.accumulate("rows", list);

		writeJsonBack(json.toString());
	}

	/**
	 * findUserBySqu:加载部门用户
	 * 
	 * @author: 张敏
	 * @dateTime: 2015-8-17 上午9:42:08
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void findUserBySqu() {
		// 每页显示条数
		int pageSize = Integer.parseInt(getRequest().getParameter(Constant.STRROWS));
		// 当前页
		int pageNo = Integer.parseInt(getRequest().getParameter(Constant.STRPAGE));
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		try {
			pageVo = deptService.getUserBySqu(dept.getBmdm(), pageVo);
		} catch (SQLException e) {
			throw new DaoException();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);

		writeJsonBack(jsonObj.toString());
	}

	/**
	 * loadUserGrld:(为部门批量添加用户加载显示用户列表).<br/>
	 * 
	 * @author: 张家俊
	 * @dateTime: 2016-12-19 下午3:17:46
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void loadUserGrld() {
		String param=getRequest().getParameter("param");
		
		JSONObject json=deptService.searchUser(param);

		writeJsonBack(json.toString());
	}

	/**
	 * addUserToDept:新增用户到部门
	 * 
	 * @author: 张家俊
	 * @dateTime: 2016-12-19 下午3:17:46
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void addUserToDept() {
		String result = "部门添加用户成功";
		try {
			String arr = getRequest().getParameter("array");
			String[] array = arr.split(",");
			deptService.addUserToDept(dept.getBmdm(), array);
		} catch (SQLException e) {
			result = "部门添加用户失败";
			throw new DaoException();
		} finally {
			writeJsonBack(result);
		}
	}

	public void deptDataUpdate() {
		this.clearDept();
		int count = deptService.deptDataUpdate(sync);
		JSONObject json = new JSONObject();
		json.accumulate("state", count);

		writeJsonBack(json.toString());
	}

	/**
	 * 清空表数据
	 */
	public void clearDept() {
		deptService.clearDept();
	}

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}

	public DeptBean getDept() {
		return dept;
	}

	public void setDept(DeptBean dept) {
		this.dept = dept;
	}

	public ServiceUpdate getSync() {
		return sync;
	}

	public void setSync(ServiceUpdate sync) {
		this.sync = sync;
	}
}
