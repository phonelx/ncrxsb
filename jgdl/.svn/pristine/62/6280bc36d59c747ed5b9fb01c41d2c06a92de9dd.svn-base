package monitor.projectConfig.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import monitor.common.BaseAction;
import monitor.projectConfig.service.impl.CalculationReportServiceImpl;

/**
 *@ClassName
 *@dataTime 2018-4-9-下午3:11:30
 *@version
 *@author:唐青
 *@since
 */
public class CalculationReportAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "calculationReportService")
	private CalculationReportServiceImpl calculationReportService;
	
	public String geToCalculationIndex(){
		String prosqu = getRequest().getParameter("prosqu");
		String childsqu = getRequest().getParameter("childsqu");
		String sitesqu = getRequest().getParameter("sitesqu");
		String zjsqu = getRequest().getParameter("zjsqu");
		if ("".equals(prosqu) ||"".equals(childsqu)||"".equals(sitesqu)||"".equals(zjsqu) ||
				"undefined".equals(prosqu) ||"undefined".equals(childsqu)||"undefined".equals(sitesqu)||"undefined".equals(zjsqu)) {
			getRequest().setAttribute("errorMsg","参数id获取失败！");
			getRequest().setAttribute("errorClass",this.getClass().toString());
			return "error";
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("prosqu", prosqu);
		map.put("childsqu", childsqu);
		map.put("sitesqu", sitesqu);
		map.put("zjsqu", zjsqu);
		JSONObject jsonObject = JSONObject.fromObject(map);
		getRequest().setAttribute("map", jsonObject.toString());
		return "success";
		
	}
	/**&
	 * 
	  * getProjectInfo:(根据项目squ获取项目基本信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-11 下午2:18:19 void
	  * @since JDK 1.7
	 */
	public void getProjectInfo(){
		String  prosqu = getRequest().getParameter("prosqu");
		JSONObject json = new JSONObject();
		try {
			Map<String,String> map = calculationReportService.selectProjectInfo(prosqu);
			json.accumulate("map", map);
		} catch (Exception e) {
			json.accumulate("wrong", e.getMessage());
			e.printStackTrace();
		}
		//System.out.println(json.toString());
		writeStringBack(json.toString());
	}
	
	/**
	 * 
	  * getChildInfo:(查询子单位工程信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-13 上午10:36:34 void
	  * @since JDK 1.7
	 */
	public void getChildInfo(){
		String  childsqu = getRequest().getParameter("childsqu");
		JSONObject json = new JSONObject();
		try {
			Map<String,String> map = calculationReportService.selectChildInfo(childsqu);
			json.accumulate("map", map);
		} catch (Exception e) {
			json.accumulate("wrong", e.getMessage());
			e.printStackTrace();
		}
		//System.out.println(json.toString());
		writeStringBack(json.toString());
	}
	
	/**
	 * 
	  * getSiteInfo:(查询部位信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-13 上午10:36:59 void
	  * @since JDK 1.7
	 */
	public void getSiteInfo(){
		String  sitesqu = getRequest().getParameter("sitesqu");
		JSONObject json = new JSONObject();
		try {
			Map<String,String> map = calculationReportService.selectSiteInfo(sitesqu);
			json.accumulate("map", map);
		} catch (Exception e) {
			json.accumulate("wrong", e.getMessage());
			e.printStackTrace();
		}
		//System.out.println(json.toString());
		writeStringBack(json.toString());
	}
	
	/**
	 * 
	  * getZjInfo:(查询支架信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-4-13 上午10:46:05 void
	  * @since JDK 1.7
	 */
	public void getZjInfo(){
		String  zjsqu = getRequest().getParameter("zjsqu");
		JSONObject json = new JSONObject();
		try {
			Map<String,String> map = calculationReportService.selectZjInfo(zjsqu);
			json.accumulate("map", map);
		} catch (Exception e) {
			json.accumulate("wrong", e.getMessage());
			e.printStackTrace();
		}
		//System.out.println(json.toString());
		writeStringBack(json.toString());
	}
}
