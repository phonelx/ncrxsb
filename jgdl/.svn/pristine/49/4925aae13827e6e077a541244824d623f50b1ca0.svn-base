/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-18 下午05:04:49
*/
package monitor.user.action;

import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import monitor.common.BaseAction;
import monitor.user.bean.dto.RoleDto;
import monitor.user.bean.vo.ComboOptionVo;
import monitor.user.bean.vo.PageInfoVo;
import monitor.user.bean.vo.RoleVo;
import monitor.user.service.IRoleService;
import com.opensymphony.xwork2.ModelDriven;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-18 下午05:04:49
 */
@SuppressWarnings("static-access")
public class RoleAction extends BaseAction implements ModelDriven<RoleVo>{
	private RoleVo roleVo = new RoleVo();
	private PageInfoVo pageVo = new PageInfoVo();
	private IRoleService roleService = null;
	
	private static final long serialVersionUID = 5884757157416309902L;
	
	public PageInfoVo getPageVo() {
		return pageVo;
	}
	public void setPageVo(PageInfoVo pageVo) {
		this.pageVo = pageVo;
	}
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public RoleVo getModel() {
		return this.roleVo;
	}
	public RoleVo getRoleVo() {
		return roleVo;
	}
	public void setRoleVo(RoleVo roleVo) {
		this.roleVo = roleVo;
	}
	/**
	 * 今入roleIndex.jsp页面
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String showRoleIndex(){
		return this.SUCCESS;
	}
	
	/**
	 * 列出所有角色信息，不支持分页显示
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String listRoles(){
		List<RoleDto> roleList = roleService.listRoles();
		
		getRequest().setAttribute("roleList", roleList);
		return this.SUCCESS;
	}
	
	/**
	 * 列出easyUi使用的comboBox需要的所有角色信息，不支持分页显示，json格式返回
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void listRolesInCombo(){
		List<ComboOptionVo> comboOptionVoList = roleService.listRolesInCombo();
		
		JSONArray jsonArray = JSONArray.fromObject(comboOptionVoList);
		writeJsonBack(jsonArray.toString());
	}
	
	/**
	 * 列出指定页面的角色信息，支持分页显示
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void listRolesInPage(){
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");
		if(pageStr!=null){
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if(rowsStr!=null){
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
		
		this.pageVo = roleService.listRolesInPage(pageVo);
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
		jsonObj.put("pageSize", rowsStr);
        jsonObj.put("pageNumber", pageStr);
		writeJsonBack(jsonObj.toString());
	}
	
	/**
	 * 获取权限树
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void fetchAuthTree(){
		String treeJsonStr = roleService.fetchAuthTree();
		writeJsonBack(treeJsonStr);
	}
	
	/**
	 * 获取指定角色的权限树
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void fetchGrantedAuthTree(){
		int roleSqu = Integer.parseInt(getRequest().getParameter("squ").trim());
		String treeJsonStr = roleService.fetchGrantedAuthTree(roleSqu);
		writeJsonBack(treeJsonStr);
	}
	
	public void addRole(){
		String result = roleService.addRole(roleVo);
		writeJsonBack(getText(result));
		this.setOperationDescb("角色名称:"+roleVo.getRoleName());
	}
	
	public void editRole(){
		//修改角色信息
		String result = roleService.editRole(roleVo);
		writeJsonBack(getText(result));	
		this.setOperationDescb("角色名称:"+roleVo.getRoleName());
	}
	
	public void deleteRole(){
		String result = roleService.deleteRole(roleVo);
		
		/*if(!"deleteSuccess".equals(result)){
			getRequest().setAttribute("info", getText(result));
			return "notice";
		}
		return this.SUCCESS;*/
		writeJsonBack(getText(result));
		
		this.setOperationDescb("角色名称:"+roleVo.getRoleName());
	}
	
	
	
	
	
	/****************************************************/
	
	/**
	 * 生成所有实体树
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	/*public String fetchEntityTree(){
		String treeJsonStr = roleService.fetchEntityTree();
		writeJsonBack(treeJsonStr);
		return this.SUCCESS;
	}*/
	
	/**
	 * 获取指定角色的实体树
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void fetchGrantedEntityTree(){
		int roleSqu = Integer.parseInt(getRequest().getParameter("squ").trim());
		String treeJsonStr = roleService.fetchGrantedEntityTree(roleSqu);
		writeJsonBack(treeJsonStr);
	}
	
	/**
	 * 保存实体的授权信息
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void saveGrantedEntities(){
		String result = roleService.saveGrantedEntities(roleVo);
		writeJsonBack(getText(result));
	}
	
	/**
	 * 生成角色可授权查询树（根据角色信息查询已授权实体信息，生成完整可授权查询树）
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	/*public String fetchQueryTreeByRole(){
		int roleSqu = Integer.parseInt(getRequest().getParameter("squ").trim());
		String treeJsonStr = roleService.fetchQueryTreeByRole(roleSqu);
		writeJsonBack(treeJsonStr);
		return this.SUCCESS;
	}*/
	
	/**
	 * 读取角色已授权查询树（根据角色信息查询已授权查询信息，生成已授权查询树）
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void fetchGrantedQueryTreeByRole(){
		int roleSqu = Integer.parseInt(getRequest().getParameter("squ").trim());
		String treeJsonStr = roleService.fetchGrantedQueryTreeByRole(roleSqu);
		writeJsonBack(treeJsonStr);
	}
	
	/**
	 * 保存查询的授权信息
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public void saveGrantedQueries(){
		String result = roleService.saveGrantedQueries(roleVo);
		writeJsonBack(getText(result));
	}
	
	/**
	 * 根据角色squ查询出所有注册并已授权实体信息,返回Json格式
	 * @author:  cl 
	 * @param 
	 * @return void
	 */
	public void listGrantedEntityesInJson() {
		String data = roleService.listGrantedEntityesInJson(roleVo.getSqu());
//		data = data.replace("\"", "'");
		super.writeStringBack(data);
	}
}
