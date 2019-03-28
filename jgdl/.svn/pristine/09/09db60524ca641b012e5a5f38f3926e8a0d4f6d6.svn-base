/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-18 下午05:02:44
*/
package monitor.user.service;

import java.util.List;

import monitor.user.bean.dto.EntityDto;
import monitor.user.bean.dto.RoleDto;
import monitor.user.bean.vo.ComboOptionVo;
import monitor.user.bean.vo.PageInfoVo;
import monitor.user.bean.vo.RoleVo;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-18 下午05:02:44
 */
public interface IRoleService {
	/**
	 * 列出所有角色信息，不支持分页显示
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public List<RoleDto> listRoles();
	
	/**
	 * 列出easyUi使用的comboBox需要的所有角色信息，不支持分页显示
	 * @author:  cl 
	 * @param 
	 * @return List<ComboOptionVo>
	 */
	public List<ComboOptionVo> listRolesInCombo();
	
	/**
	 * 列出指定页面的角色信息，支持分页显示
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public PageInfoVo listRolesInPage(PageInfoVo pageVo);
	
	/**
	 * 以json字符串方式返回权限树，
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String fetchAuthTree();

	/**
	 * 查询指定角色的权限树，
	 * @author:  cl 
	 * @param roleSqu
	 * @return String
	 */
	public String fetchGrantedAuthTree(int roleSqu);

	public String addRole(RoleVo roleVo);
	
	public String editRole(RoleVo roleVo);

	public String deleteRole(RoleVo roleVo);

	/**
	 * 生成所有实体树
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
//	public String fetchEntityTree();

	/**
	 * 获取指定角色的实体树
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String fetchGrantedEntityTree(int roleSqu);

	/**
	 * 保存实体的授权信息
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String saveGrantedEntities(RoleVo roleVo);
	
	
	/**
	 * 生成角色可授权查询树（根据角色信息查询已授权实体信息，生成完整可授权查询树）
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
//	public String fetchQueryTreeByRole(int roleSqu);

	
	/**
	 * 读取角色已授权查询树（根据角色信息查询已授权查询信息，生成已授权查询树）
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String fetchGrantedQueryTreeByRole(int roleSqu);

	/**
	 * 保存查询的授权信息
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String saveGrantedQueries(RoleVo roleVo);

	/**
	 * 根据角色squ查询出所有注册并已授权实体信息,返回Json格式
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String listGrantedEntityesInJson(int roleSqu);
}
