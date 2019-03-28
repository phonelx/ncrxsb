package monitor.ality.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import monitor.ality.bean.entity.AlityBean;
import monitor.ality.service.IAlitySetService;

import monitor.common.BaseAction;
import monitor.common.exception.DaoException;
import monitor.common.util.Constant;
import monitor.user.bean.vo.PageInfoVo;
import net.sf.json.JSONObject;

/**
 *@ClassName
 *@dataTime 2017-10-13-下午1:49:30
 *@version
 *@author:唐青
 *@since
 */
/**
 * @ClassName:AlitySetAction
 * @datetime: 2015-12-4 下午3:35:32
 * @Description: 系统功能配置
 * @author: 张敏
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public class AlitySetAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    /**.
     * service注入
     */
	private IAlitySetService alityService;
	/**
	 * @Fields pageVo:分页工具
	 * @since JDK1.6
	 */
	private PageInfoVo pageVo = new PageInfoVo();
	/**
	 * bean 
	 */
	private AlityBean ality;
	/**
	  * @Fields  pSqu:功能模块编号
	  * @since JDK1.6
	*/
	private String squ;
	/**
	  * @Fields  urlName:url地址
	  * @since JDK1.6
	*/
	private String urlName;
	private String query;
	private String doSqu;
	/**
	  * @Fields  mainName:模块名称
	  * @since JDK1.6
	*/
	private String mainName;
	/**.
	  * gotoSystemSetIndex:跳转系统功能配置主页
	  * @author: 张敏
	  * @datetime: 2015-12-4 下午3:40:01
	  * @since JDK 1.6
	*/
	public String gotoSystemSetIndex(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = alityService.fethParentMenu(squ);//初始squ为空
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getRequest().setAttribute("list", list);
		return SUCCESS;
	}
	/**
	  * gotoSetting:跳转系统配置主页
	  * @author: 张敏
	  * @datetime: 2015-12-4 下午5:19:23
	  * @since JDK 1.6
	*/
	public String gotoSetting(){
		return SUCCESS;
	}
	/**
	  * addAlityUrl:新增功能请求
	  * @author: 张敏
	  * @datetime: 2015-12-9 下午3:49:01
	  * @since JDK 1.6
	*/
	public void addAlityUrl(){
		String pSqu = getRequest().getParameter("pSqu");
		int count = alityService.addAlityUrl(ality,pSqu);
		JSONObject json = new JSONObject();
		json.accumulate("state", count);
		writeJsonBack(json.toString());
	}
	/**
	  * delAlityUrl:删除功能请求地址
	  * @author: 张敏
	  * @datetime: 2015-12-9 下午3:41:47
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void delAlityUrl(){
		alityService.delAlityUrl(squ,doSqu);
	}
	
	
	/**
	  * editAlityUrl:编辑功能请求地址
	  * @author: 张敏
	  * @datetime: 2015-12-9 下午3:42:34
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void editAlityUrl(){
		alityService.editAlityUrl(ality,squ);
		
		writeJsonBack("s");
	}
	
	/**
	  * fetchAlityUrl:加载功能菜单下请求列表
	  * @author: 张敏
	  * @datetime: 2015-12-9 下午3:30:07
	  * @since JDK 1.6
	*/
	public void fetchAlityUrl(){//加载三级功能菜单
		int pageSize = Integer.parseInt(getRequest().getParameter(Constant.STRROWS));// 每页显示条数
		int pageNo = Integer.parseInt(getRequest().getParameter(Constant.STRPAGE));// 当前页
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		
		try {
			pageVo = alityService.fetchAlityUrl(squ,pageVo);//squ传入上级功能菜单编号
		} catch (SQLException e) {
			throw new DaoException();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
		writeJsonBack(jsonObj.toString());
	}
	/**
	  * addAlityMenu:新增功能菜单
	  * @author: 张敏
	  * @datetime: 2015-12-8 上午11:32:51
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void addAlityMenu(){
		JSONObject json = new JSONObject();
		int count = alityService.addAlityMenu(ality);
		json.accumulate("state", count);
		writeStringBack(json.toString());
	}
	
	/**
	  * editAlityMenu:编辑功能菜单
	  * @author: 张敏
	  * @datetime: 2015-12-8 上午11:56:15
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void editAlityMenu(){
		alityService.editAlityMenu(ality);
	}
	
	/**
	  * delAlityMenu:删除功能菜单
	  * @author: 张敏
	  * @datetime: 2015-12-8 下午12:06:16
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void delAlityMenu(){
		int count = alityService.delAlityMenu(squ);
		JSONObject json = new JSONObject();
		json.accumulate("state", count);
		writeJsonBack(json.toString());
	}
	/**
	  * urlHendiadys:url 重名验证
	  * @author: 张敏
	  * @datetime: 2015-12-7 上午10:15:51
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void urlHendiadys(){
		int count = alityService.urlHendiadys(urlName);
		JSONObject json  = new JSONObject();
		json.accumulate("state", count);
		writeJsonBack(json.toString());
	}
	
	/**
	  * fetchParentMenu:加载功能模块
	  * @author: 张敏
	  * @datetime: 2015-12-4 下午3:41:41
	  * @since JDK 1.6
	*/
	public void fetchParentMenu(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = alityService.fethParentMenu(squ);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.accumulate("row", list);
		writeStringBack(json.toString());
	}
	
	/**
	  * delParentMenu:删除功能模块
	  * @author: 张敏
	  * @datetime: 2015-12-7 下午2:36:35
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void delParentMenu(){
		int count = alityService.delParentMenu(squ);
		JSONObject json = new JSONObject();
		json.accumulate("state", count);
		writeJsonBack(json.toString());
	}
	
	/**
	  * addParentMenu:新增功能模块
	  * @author: 张敏
	  * @datetime: 2015-12-7 下午2:54:16
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void addParentMenu(){
		int count = alityService.addParentMenu(mainName);
		JSONObject json = new JSONObject();
		json.accumulate("state", count);
		writeJsonBack(json.toString());
	}
	
	public void editParentMenu(){
		alityService.editParentMenu(mainName,squ);
	}
	/**
	  * fetchAlityMenu:加载系统功能菜单
	  * @author: 张敏
	  * @datetime: 2015-12-4 下午3:57:32
	  * @since JDK 1.6
	*/
	public void fetchAlityMenu(){
		// 每页显示条数
		int pageSize = Integer.parseInt(getRequest().getParameter(Constant.STRROWS));
		// 当前页
		int pageNo = Integer.parseInt(getRequest().getParameter(Constant.STRPAGE));
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		try {
			pageVo = alityService.fethAlityMenu(squ,pageVo,query);
		} catch (SQLException e) {
			throw new DaoException();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
		writeJsonBack(jsonObj.toString());
	}
	
	/**
	  * fetchMaxOrder:加载菜单最大排序编号
	  * @author: 张敏
	  * @datetime: 2015-12-4 下午4:45:42
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	*/
	public void fetchMaxOrder(){
		int num = alityService.fetchMaxOrder(squ);
		JSONObject json = new JSONObject();
		json.accumulate("row", num);
		writeJsonBack(json.toString());
	
	}
	
	/**获取当前系统时间
	 * @return
	 */
	public String getDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}
	/**
	 * checkUploadFileType:检查文件格式
	 * @author: 张敏
	 * @datetime: 2015-6-18 上午9:50:24
	 * @param: @param file
	 * @param: @return
	 * @return: boolean
	 * @throws
	 * @since JDK 1.6
	 */
	public boolean checkUploadFileType(String file) {
		String[] imgArry = new String[] { "bmp", "jpg", "jpeg", "png", "tiff",
				"gif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd",
				"dxf", "ufo", "eps", "ai", "raw" };
		boolean isflag = false;
		for (int i = 0; i < imgArry.length; i++) {
			if (file.contains(imgArry[i])) {
				isflag = true;
			}
		}
		return isflag;

	}

	public void setAlityService(IAlitySetService alityService) {
		this.alityService = alityService;
	}
	public String getSqu() {
		return squ;
	}
	public void setSqu(String squ) {
		this.squ = squ;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getMainName() {
		return mainName;
	}
	public void setMainName(String mainName) {
		this.mainName = mainName;
	}
	public AlityBean getAlity() {
		return ality;
	}
	public void setAlity(AlityBean ality) {
		this.ality = ality;
	}

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getDoSqu() {
		return doSqu;
	}
	public void setDoSqu(String doSqu) {
		this.doSqu = doSqu;
	}
	
	
	
	
}

