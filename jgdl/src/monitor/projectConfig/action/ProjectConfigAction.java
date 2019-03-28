package monitor.projectConfig.action;

import java.sql.SQLException;
import java.util.List;


import javax.annotation.Resource;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import monitor.common.BaseAction;
import monitor.common.util.Constant;
import monitor.projectConfig.bean.entity.Project;
import monitor.projectConfig.service.impl.ProjectChildServiceImpl;
import monitor.projectConfig.service.impl.ProjectConfigServiceImpl;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:ProjectConfigAction 项目基本配置
 * @dateTime: 2017-10-14 下午3:03:51
 * @Description: TODO
 * @version
 * @author: 黄月
 * @since JDK 1.7 History： Editor version Time Operation
 */
public class ProjectConfigAction extends BaseAction implements
		ModelDriven<Project> {

	/**
	 * @Fields serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since JDK1.7
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "pro")
	private Project pro;

	@Resource(name = "projectService")
	private ProjectConfigServiceImpl projectService;
	
	@Resource(name = "projectChildService")
	private ProjectChildServiceImpl projectChildService;
	
	@Resource(name = "pageVo")
	private PageInfoVo pageVo;
	
	/**
	 * toProjectBasicIndex:(跳转项目基本配置页面).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-14 下午3:05:27
	 * @return String
	 * @since JDK 1.7
	 */
	public String toProjectBasicIndex() {
		
		return SUCCESS;
	}

	/**
	 * toProjectBasicIndex:(跳转项目明细配置页面).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-14 下午3:05:27
	 * @return String
	 * @since JDK 1.7
	 */
	public String toProjectIndex() {
		return SUCCESS;
	}

	/**
	 * selectProjectPageInfo:(分页查询项目列表).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-14 下午3:58:13 void
	 * @since JDK 1.7
	 */
	public void selectProjectPageInfo() {
		int pageSize = Integer.parseInt(getRequest().getParameter(
				Constant.STRROWS));
		int pageNo = Integer.parseInt(getRequest().getParameter(
				Constant.STRPAGE));
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		
		String searchWord = getRequest().getParameter("searchWord");
		try {
			pageVo = projectService.selectProjectPageInfo(pageVo, searchWord);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("加载项目列表异常：" + e);
		}
		JSONObject json = JSONObject.fromObject(pageVo);
		writeJsonBack(json.toString());
	}

	/**
	 * selectProjectBySqu:(查询单个项目).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * 
	 * @author: 黄月
	 * @dateTime: 2017-10-14 下午3:59:21 void
	 * @since JDK 1.7
	 */
	public void selectProjectBySqu() {
		// 项目id
		String squ = getRequest().getParameter("squ");
		Project pro = new Project();
		JSONObject json = new JSONObject();
		try {
			pro = projectService.selectProjectBySqu(squ);
			json.accumulate("status", "success");
			json.accumulate("result", pro);
		} catch (SQLException e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
			e.printStackTrace();
		}
		writeJsonBack(json.toString());
	}

	/**
	 * insertProject:(新增项目).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-14 下午4:00:01 void
	 * @since JDK 1.7
	 */
	public void insertProject() {
		JSONObject json = new JSONObject();
		try {
			projectService.insertProject(pro);
			json.accumulate("status", "success");
		} catch (SQLException e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
			e.printStackTrace();
		}
		writeJsonBack(json.toString());
	}

	/**
	 * updateProjectBySqu:(修改项目).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * 
	 * @author: 黄月
	 * @dateTime: 2017-10-14 下午4:00:07
	 * @since JDK 1.7
	 */
	public void updateProjectBySqu() {
		JSONObject json = new JSONObject();
		try {
			projectService.updateProjectBySqu(pro);
			json.accumulate("status", "success");
			json.accumulate("result", pro);
		} catch (SQLException e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
			e.printStackTrace();
		}
		writeJsonBack(json.toString());
	}

	/**
	 * deleteProjectBySqu:(根据id删除项目).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-14 下午3:25:23
	 * @since JDK 1.7
	 */
	public void deleteProjectBySqu() {
		JSONObject json = new JSONObject();
		String squ = getRequest().getParameter("squ");
		try {
			json.accumulate("status", projectService.deleteProjectBySqu(squ));
		} catch (SQLException e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
			e.printStackTrace();
		}
		writeJsonBack(json.toString());
	}

	/**
	 * loadHolderOfProjectPageInfo:(分页查询项目支架信息列表).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-23 下午8:07:13 void
	 * @since JDK 1.7
	 */
	/*public void loadHolderOfProjectPageInfo() {
		int pageSize = Integer.parseInt(getRequest().getParameter(
				Constant.STRROWS));
		int pageNo = Integer.parseInt(getRequest().getParameter(
				Constant.STRPAGE));
		String squ = getRequest().getParameter("squ");
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		try {
			pageVo = projectService.selectHolderOfProjectPageInfo(pageVo, squ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(pageVo);
		writeJsonBack(json.toString());
	}*/

	/**
	 * @author 黄月
	 * @date 2017-10-14 下午5:21:06
	 * @return
	 */
	@Override
	public Project getModel() {
		return this.pro;
	}

	/**
	 * getHolderList:(获取支架信息).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-24 下午7:17:17 void
	 * @since JDK 1.7
	 */
/*	public void getHolderList() {
		int pageSize = Integer.parseInt(getRequest().getParameter(
				Constant.STRROWS));
		int pageNo = Integer.parseInt(getRequest().getParameter(
				Constant.STRPAGE));
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		
		String gdlx = getRequest().getParameter("gdlx");
		String azfs = getRequest().getParameter("azfs");
		String search = getRequest().getParameter("search");
		try {
			pageVo = projectService.getHolderList(pageVo, gdlx, azfs, search);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(pageVo);
		writeJsonBack(json.toString());
	}*/

	/**
	 * addHolderToProject:(新增支架).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-24 下午9:08:02 void
	 * @since JDK 1.7
	 */
/*	public void addHolderToProject() {
		String holderSqu = getRequest().getParameter("holderSqu");
		String projectSqu = getRequest().getParameter("projectSqu");
		try {
			projectService.addHolderToProject(holderSqu, projectSqu);
			writeJsonBack("success");
		} catch (SQLException e) {
			e.printStackTrace();
			writeJsonBack("error");
		}
	}*/
	
	/**
	  * updateHolderNumOfProject:(修改支架数量).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-20 上午11:36:37 void
	  * @since JDK 1.7
	*/
/*	public void updateHolderNumOfProject(){
		Integer num = Integer.parseInt(getRequest().getParameter("num"));
		String holderSqu = getRequest().getParameter("holderSqu");
		String projectSqu = getRequest().getParameter("projectSqu");
		try {
			projectService.updateHolderNumOfProject(num, holderSqu, projectSqu);
			writeJsonBack("success");
		} catch (SQLException e) {
			e.printStackTrace();
			writeJsonBack("error");
		}
	}
	*/
	/**
	  * deleteHolderOfProject:(删除项目支架).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-20 上午10:41:31 void
	  * @since JDK 1.7
	*/
/*	public void deleteHolderOfProjectBySqu(){
		String squ = getRequest().getParameter("squ");
		try {
			projectService.deleteHolderOfProjectBySqu(squ);
			writeJsonBack("success");
		} catch (SQLException e) {
			e.printStackTrace();
			writeJsonBack("error");
		}
	}*/
	
	/**
	  * getBuildingCategory:(获取类别下拉列表).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-22 下午5:04:28 void
	  * @since JDK 1.7
	*/
	public void getSelectOfObject() {
		String selectType = getRequest().getParameter("type");
		List<String> result = null;
		try {
			result = projectService.getSelectOfObject(selectType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray json = JSONArray.fromObject(result);
		writeJsonBack(json.toString());
	}
	
/*	public void getSelectOfGDLX_AZFS(){
		String selectType = getRequest().getParameter("type");
		List<Map<String, String>> result = null;
		try {
			result = projectService.getSelectOfGDLX_AZFS(selectType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONArray json = JSONArray.fromObject(result);
		writeJsonBack(json.toString());
	}*/
	
	/**
	  * listCountMsg:(获取所有统计).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-2-8 下午6:49:31 void
	  * @since JDK 1.7
	*/
	public void listCountMsg(){
		//id
		String squ = getRequest().getParameter("squ");
		//查询等级标识
		String level = getRequest().getParameter("level"); 
		String type = getRequest().getParameter("type"); 
		List<Integer> list = projectService.listCountMsg(squ, level, type);
		JSONArray json = JSONArray.fromObject(list);
		writeJsonBack(json.toString());
	}
}
