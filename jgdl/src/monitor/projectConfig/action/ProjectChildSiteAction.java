package monitor.projectConfig.action;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import monitor.common.BaseAction;
import monitor.common.util.Constant;
import monitor.projectConfig.bean.entity.Project_Child_Site;
import monitor.projectConfig.bean.entity.T_Project_Zj;
import monitor.projectConfig.service.impl.ProjectChildSiteServiceImpl;
import monitor.user.bean.vo.PageInfoVo;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName:ProjectChildAction   子项目部位
 * @dateTime: 2018-2-6 上午9:52:25
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class ProjectChildSiteAction  extends BaseAction implements 
		ModelDriven<Project_Child_Site>{
	/**
	 * @Fields serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since JDK1.7
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "pro_child_site")
	private Project_Child_Site pro_child_site;
	
	@Resource(name = "proZj")
	private T_Project_Zj proZj;
	
	@Resource(name = "pageVo")
	private PageInfoVo pageVo;
	
	@Resource(name = "projectChildSiteService")
	private ProjectChildSiteServiceImpl projectChildSiteService;
	
	private File file1;
	private File file2;
	private File file3;
	
	
	
	
	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public File getFile3() {
		return file3;
	}

	public void setFile3(File file3) {
		this.file3 = file3;
	}

	@Override
	public Project_Child_Site getModel() {
		return this.pro_child_site;
	}
	
	public void getT_Project_ZjById(){
		JSONObject json = new JSONObject();
		try {
			T_Project_Zj t = projectChildSiteService.getT_Project_ZjById(proZj.getSqu());
			json.accumulate("status", "success");
			json.accumulate("result", t);
		} catch (SQLException e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
			e.printStackTrace();
		}
		writeJsonBack(json.toString());
	}
	
	/**
	  * numberTheHolder:(支架生成流水号).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-6-2 下午3:55:02 void
	  * @since JDK 1.7
	*/
	public void numberTheHolder(){
		String siteSqu = getRequest().getParameter("siteSqu");
		try {
			projectChildSiteService.numberTheHolder(siteSqu);
			writeJsonBack("success");
		} catch (SQLException e) {
			e.printStackTrace();
			writeJsonBack("error");
		}
	}
	
	/**
	  * addChildSite:(新增部位).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午5:27:20 void
	  * @since JDK 1.7
	 */
	public void insertChildSite(){
		JSONObject json = new JSONObject();
		try {
			projectChildSiteService.addChildSite(pro_child_site);
			json.accumulate("status", "success");
		} catch (Exception e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	/**
	 * 
	  * delChildSite:(删除部位).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午5:27:33 void
	  * @since JDK 1.7
	 */
	public void delChildSite(){
		//部位squ
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		try {
			json.accumulate("status", projectChildSiteService.delChildSite(squ));
		} catch (SQLException e) {
			e.printStackTrace();
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	/**
	 * 
	  * updateChildSite:(修改部位).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午5:30:49 void
	  * @since JDK 1.7
	 */
	public void updateChildSite(){
		JSONObject json = new JSONObject();
		try {
			projectChildSiteService.updateChildSite(pro_child_site);
			json.accumulate("status", "success");
		} catch (SQLException e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	/**
	 * 
	  * selectlistChildSite:(查询部位列表).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午5:33:47 void
	  * @since JDK 1.7
	 */
	public void selectlistChildSite(){
		//子单位工程的squ
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		try {
			List<Project_Child_Site> list = projectChildSiteService.listChildSite(squ);
			json.accumulate("status", "success");
			json.accumulate("result", list);
		} catch (Exception e) {
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	/**
	 * 
	  * addDxInfo:(添加支架).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午6:04:56 void
	  * @since JDK 1.7
	 */
	public void insertDxInfo(){
		JSONObject json = new JSONObject();
		try {
			projectChildSiteService.addDxInfo(proZj);
			json.accumulate("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	/**
	  * delDxInfo:(删除支架信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午5:58:42 void
	  * @since JDK 1.7
	 */
	public void delDxInfo(){
		//支架squ
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		try {
			projectChildSiteService.delDxInfo(squ);
			json.accumulate("status", "success");				
		} catch (SQLException e) {
			e.printStackTrace();
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
			writeJsonBack(json.toString());
	}
	/**
	 * 
	  * updateDxInfo:(修改支架信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-6 下午6:04:10 void
	  * @since JDK 1.7
	 */
	public void updateDxInfo(){
		JSONObject json = new JSONObject();
		try {
			projectChildSiteService.updateDxInfo(proZj);
			json.accumulate("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	/**
	  * updateReportParam:(修改计算报告参数).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-6-21 下午7:20:53 void
	  * @since JDK 1.7
	*/
	public void updateReportParam(){
		JSONObject json = new JSONObject();
		try {
			projectChildSiteService.updateReportParam(proZj, file1, file2, file3);
			json.accumulate("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	/**
	  * selectlistDxInfo:(获取该部位下的支架列表).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-7 上午9:59:49 void
	  * @since JDK 1.7
	 */
	public void selectlistDxInfo(){
		//部位的squ
		String squ = getRequest().getParameter("squ");
		//专业类别
		String type = getRequest().getParameter("type");
		JSONObject json = new JSONObject();
		try {
			json.accumulate("status", "success");
			json.accumulate("result", projectChildSiteService.listDxInfo(squ, type));
		} catch (SQLException e) {
			e.printStackTrace();
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	/**
	 * getHolderNotInSite:(获取该部位下没有的支架).<br/>
	 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	 * @author: 黄月
	 * @dateTime: 2017-10-24 下午7:17:17 void
	 * @since JDK 1.7
	 */
	public void getHolderNotInSite() {
		int pageSize = Integer.parseInt(getRequest().getParameter(
				Constant.STRROWS));
		int pageNo = Integer.parseInt(getRequest().getParameter(
				Constant.STRPAGE));
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		String site_squ = getRequest().getParameter("site_squ");
		try {
			pageVo = projectChildSiteService.getHolderNotInSite(pageVo, site_squ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(pageVo);
		writeJsonBack(json.toString());
	}
	
	/**
	 * 
	  * getLbxx:(查询类别系数).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-20 上午9:43:20 void
	  * @since JDK 1.7
	 */
	public void getLbxx(){
		JSONObject json = new JSONObject();
		try {
			json.accumulate("status", "success");
			json.accumulate("result", projectChildSiteService.selectLbxx());
		} catch (Exception e) {
			e.printStackTrace();
			json.accumulate("status", "error");
			json.accumulate("result", e.getMessage());
		}
		writeJsonBack(json.toString());
	}
	
	public T_Project_Zj getProZj() {
		return proZj;
	}

	public void setProZj(T_Project_Zj proZj) {
		this.proZj = proZj;
	}
	
	
}
