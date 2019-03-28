/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-18 下午05:03:37
*/
package monitor.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import monitor.user.bean.dto.DatasourceDto;
import monitor.user.bean.dto.EntityDto;
import monitor.user.bean.dto.ModuleMainDto;
import monitor.user.bean.dto.ModuleSubDto;
import monitor.user.bean.dto.QueryDto;
import monitor.user.bean.dto.RoleDto;
import monitor.user.bean.vo.ComboOptionVo;
import monitor.user.bean.vo.PageInfoVo;
import monitor.user.bean.vo.RoleVo;
import monitor.user.bean.vo.ZTreeNodeVo;
import monitor.user.dao.IRoleDAO;
import monitor.user.service.IRoleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-18 下午05:03:37
 */
public class RoleServiceImpl implements IRoleService {
	private IRoleDAO roleDAO = null;
	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	/** 列出所有角色
	 * @return List<RoleDto>
	 */
	public List<RoleDto> listRoles() {
		return roleDAO.listRoles();
	}
	
	/**
	 * 列出easyUi使用的comboBox需要的所有角色信息，不支持分页显示
	 * @author:  cl 
	 * @param 
	 * @return List<ComboOptionVo>
	 */
	public List<ComboOptionVo> listRolesInCombo() {
		List<RoleDto> roleList = listRoles();
		
		//将roleList转换成easyUi的combobox需要的json数据
		List<ComboOptionVo> comboOptionVoList = new ArrayList<ComboOptionVo>();
		for(RoleDto role : roleList){
			ComboOptionVo optionVo = new ComboOptionVo();
			optionVo.setId(role.getSqu());
			optionVo.setText(role.getRoleName());
			comboOptionVoList.add(optionVo);
		}
		
		if(comboOptionVoList.size()>0){
			comboOptionVoList.get(0).setSelected(true);
		}
		
		return comboOptionVoList;
	}
	
	/**
	 * 返回通过PageInfoVo封装后的角色列表，已通过分页处理
	 * @author:  cl 
	 * @param PageInfoVo
	 * @return PageInfoVo
	 */
	public PageInfoVo listRolesInPage(PageInfoVo pageVo) {
		return roleDAO.listRolesInPage(pageVo);
	}
	
	/**
	 * 以json字符串方式返回权限树，
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String fetchAuthTree() {
		//从数据库查询功能模块信息
		List<ModuleMainDto> moduleMainList = roleDAO.queryModules();
		
		/*easyUi tree method
		//将功能模块信息封装成List<TreeNodeVo>并转换成json字符串
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		TreeNodeVo treeNodeMainVo = null;
		TreeNodeVo treeNodeSubVo = null;
		List<TreeNodeVo> nodeVoList = null;
		for(ModuleMainDto mainDto:moduleMainList){
			treeNodeMainVo = new TreeNodeVo(mainDto);
			nodeVoList = new ArrayList<TreeNodeVo>();
			List<ModuleSubDto> subList = mainDto.getSubModuleList();
			for(ModuleSubDto subDto:subList){
				treeNodeSubVo = new TreeNodeVo(subDto);
				nodeVoList.add(treeNodeSubVo);
			}
			treeNodeMainVo.setChildren(nodeVoList);
			treeNodeVoList.add(treeNodeMainVo);
		}*/
		
		//zTree 2.6 method
		//将功能模块信息封装成List<TreeNodeVo>并转换成json字符串
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();
		ZTreeNodeVo treeNodeMainVo = null;
		ZTreeNodeVo treeNodeSubVo = null;
		List<ZTreeNodeVo> nodeVoList = null;
		for(ModuleMainDto mainDto:moduleMainList){
			treeNodeMainVo = new ZTreeNodeVo(mainDto);
			nodeVoList = new ArrayList<ZTreeNodeVo>();
			List<ModuleSubDto> subList = mainDto.getSubModuleList();
			for(ModuleSubDto subDto:subList){
				treeNodeSubVo = new ZTreeNodeVo(subDto);
				nodeVoList.add(treeNodeSubVo);
			}
			treeNodeMainVo.setNodes(nodeVoList);
			treeNodeVoList.add(treeNodeMainVo);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(treeNodeVoList);
		return jsonArray.toString();
	}
	
	public String fetchGrantedAuthTree(int roleSqu) {
		//从数据库查询功能模块信息
		List<ModuleMainDto> moduleMainList = roleDAO.queryModules();
		
		//查询指定角色权限
		List<ModuleSubDto> moduleSubList = roleDAO.queryModulesByRoleSqu(roleSqu);
		
		/*easyUi tree method
		//比对所有模块和角色授权模块，设置已授权模块checkbox为true
		//将功能模块信息封装成List<TreeNodeVo>并转换成json字符串
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		TreeNodeVo treeNodeMainVo = null;
		TreeNodeVo treeNodeSubVo = null;
		List<TreeNodeVo> nodeVoList = null;
		for(ModuleMainDto mainDto:moduleMainList){
			treeNodeMainVo = new TreeNodeVo(mainDto);
			nodeVoList = new ArrayList<TreeNodeVo>();
			List<ModuleSubDto> subList = mainDto.getSubModuleList();
			for(ModuleSubDto subDto:subList){
				treeNodeSubVo = new TreeNodeVo(subDto);
				
				
				//循环查找moduleSubList
				for(ModuleSubDto grantedSubDto:moduleSubList){
					if(subDto.getSqu() == grantedSubDto.getSqu()){
						treeNodeSubVo.setChecked(true);
					}
				}
				nodeVoList.add(treeNodeSubVo);
			}
			treeNodeMainVo.setChildren(nodeVoList);
			treeNodeVoList.add(treeNodeMainVo);
		}*/
		
		//zTree 2.6 method
		//比对所有模块和角色授权模块，设置已授权模块checkbox为true
		//将功能模块信息封装成List<TreeNodeVo>并转换成json字符串
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();
		ZTreeNodeVo treeNodeMainVo = null;
		ZTreeNodeVo treeNodeSubVo = null;
		List<ZTreeNodeVo> nodeVoList = null;
		for(ModuleMainDto mainDto:moduleMainList){
			treeNodeMainVo = new ZTreeNodeVo(mainDto);
			nodeVoList = new ArrayList<ZTreeNodeVo>();
			List<ModuleSubDto> subList = mainDto.getSubModuleList();
			for(ModuleSubDto subDto:subList){
				treeNodeSubVo = new ZTreeNodeVo(subDto);
				
				
				//循环查找moduleSubList
				for(ModuleSubDto grantedSubDto:moduleSubList){
					if(subDto.getSqu() == grantedSubDto.getSqu()){
						treeNodeSubVo.setChecked(true);
					}
				}
				nodeVoList.add(treeNodeSubVo);
			}
			treeNodeMainVo.setNodes(nodeVoList);
			treeNodeVoList.add(treeNodeMainVo);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(treeNodeVoList);
		return jsonArray.toString();
	}
	
	public String addRole(RoleVo roleVo) {
		JSONArray moduleJsonArray = JSONArray.fromObject(roleVo.getJsonStr());
		
		/*easyUi tree method
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		TreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<moduleJsonArray.size(); i++){
        	JSONObject jsonObject = moduleJsonArray.getJSONObject(i);
        	treeNodeVo = (TreeNodeVo)JSONObject.toBean(jsonObject, TreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
        //转换成moduleMainList
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleVo.getRoleName());
        roleDto.setDescb(roleVo.getDescb());
        roleDto.setIsAdmin(roleVo.getIsAdmin());
        
        List<ModuleSubDto> moduleSubList = new ArrayList<ModuleSubDto>();
        for(TreeNodeVo nodeVo:treeNodeVoList){
        	moduleSubList.add(new ModuleSubDto(nodeVo));
        }*/
		
		//zTree 2.6 method
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();
		ZTreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<moduleJsonArray.size(); i++){
        	JSONObject jsonObject = moduleJsonArray.getJSONObject(i);
        	treeNodeVo = (ZTreeNodeVo)JSONObject.toBean(jsonObject, ZTreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
        //转换成moduleMainList
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleVo.getRoleName());
        roleDto.setDescb(roleVo.getDescb());
        roleDto.setIsAdmin(roleVo.getIsAdmin());
        roleDto.setIsSpr(roleVo.getIsSpr());
        
        List<ModuleSubDto> moduleSubList = new ArrayList<ModuleSubDto>();
        for(ZTreeNodeVo nodeVo:treeNodeVoList){
        	moduleSubList.add(new ModuleSubDto(nodeVo));
        }
		
		return roleDAO.addRole(roleDto,moduleSubList);
	}
	
	public String editRole(RoleVo roleVo) {
		JSONArray moduleJsonArray = JSONArray.fromObject(roleVo.getJsonStr());
		
		/*easyUi tree method
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		TreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<moduleJsonArray.size(); i++){
        	JSONObject jsonObject = moduleJsonArray.getJSONObject(i);
        	treeNodeVo = (TreeNodeVo)JSONObject.toBean(jsonObject, TreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
        //转换成moduleMainList
        RoleDto roleDto = new RoleDto();
        roleDto.setSqu(roleVo.getSqu());
        roleDto.setRoleName(roleVo.getRoleName());
        roleDto.setDescb(roleVo.getDescb());
        roleDto.setIsAdmin(roleVo.getIsAdmin());
        
        List<ModuleSubDto> moduleSubList = new ArrayList<ModuleSubDto>();
        for(TreeNodeVo nodeVo:treeNodeVoList){
        	moduleSubList.add(new ModuleSubDto(nodeVo));
        }*/
		
		//zTree 2.6 method
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();
		ZTreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<moduleJsonArray.size(); i++){
        	JSONObject jsonObject = moduleJsonArray.getJSONObject(i);
        	treeNodeVo = (ZTreeNodeVo)JSONObject.toBean(jsonObject, ZTreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
        //转换成moduleMainList
        RoleDto roleDto = new RoleDto();
        roleDto.setSqu(roleVo.getSqu());
        roleDto.setRoleName(roleVo.getRoleName());
        roleDto.setDescb(roleVo.getDescb());
        roleDto.setIsAdmin(roleVo.getIsAdmin());
        roleDto.setIsSpr(roleVo.getIsSpr());
        
        List<ModuleSubDto> moduleSubList = new ArrayList<ModuleSubDto>();
        for(ZTreeNodeVo nodeVo:treeNodeVoList){
        	moduleSubList.add(new ModuleSubDto(nodeVo));
        }
		
		return roleDAO.editRole(roleDto,moduleSubList);
	}
	public String deleteRole(RoleVo roleVo) {
		return roleDAO.deleteRoleBySqu(roleVo.getSqu());
	}

	
	/**
	 * 取得所有实体树,返回json数据包括数据源和实体
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	/*public String fetchEntityTree() {
		//从数据库查询功能模块信息
		List<EntityDto> entityDtoList = roleDAO.queryAllEntities();
		List<DatasourceDto> dsDtoList = roleDAO.queryAllDatasources();
		
		//将实体信息(包含父节点datasource)封装成List<TreeNodeVo>并转换成json字符串
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		
		TreeNodeVo treeNodeDatasourceVo = null;
		TreeNodeVo treeNodeEntityVo = null;
		List<TreeNodeVo> nodeVoList = null;
		for(DatasourceDto dsDto:dsDtoList){
			treeNodeDatasourceVo = new TreeNodeVo(dsDto);
			nodeVoList = new ArrayList<TreeNodeVo>();
			for(EntityDto entityDto:entityDtoList){
				if(entityDto.getDatasourceSqu() == dsDto.getSqu()){
					treeNodeEntityVo = new TreeNodeVo(entityDto);
					nodeVoList.add(treeNodeEntityVo);
				}
			}
			treeNodeDatasourceVo.setChildren(nodeVoList);
			treeNodeVoList.add(treeNodeDatasourceVo);
		}
		
		JSONArray jsonArray = JSONArray.fromObject(treeNodeVoList);
		
		return jsonArray.toString();
	}*/
	
	
	/**
	 * 获取指定角色的实体树
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String fetchGrantedEntityTree(int roleSqu) {
		//从数据库查询功能模块信息
		List<EntityDto> allEntitiesDtoList = roleDAO.queryAllEntities();
		List<EntityDto> grantedEntitiesDtoList = roleDAO.queryGrantedEntitiesByRoleSqu(roleSqu);
		List<DatasourceDto> dsDtoList = roleDAO.queryAllDatasources();
		
		/*easyUi tree method
		//将实体信息(包含父节点datasource)封装成List<TreeNodeVo>并转换成json字符串
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		
		TreeNodeVo treeNodeDatasourceVo = null;
		TreeNodeVo treeNodeEntityVo = null;
		List<TreeNodeVo> nodeVoList = null;
		for(DatasourceDto dsDto:dsDtoList){
			treeNodeDatasourceVo = new TreeNodeVo(dsDto);
			nodeVoList = new ArrayList<TreeNodeVo>();
			for(EntityDto entityDto:allEntitiesDtoList){
				if(entityDto.getDatasourceSqu() == dsDto.getSqu()){
					treeNodeEntityVo = new TreeNodeVo(entityDto);
					
					//检查该实体是否已授权，是则checkbox=true
					for(EntityDto _EntityDto:grantedEntitiesDtoList){
						if(_EntityDto.getSqu() == entityDto.getSqu()){
							treeNodeEntityVo.setChecked(true);
							grantedEntitiesDtoList.remove(_EntityDto);
							break;
						}
					}
					nodeVoList.add(treeNodeEntityVo);
				}
			}
			treeNodeDatasourceVo.setChildren(nodeVoList);
			treeNodeVoList.add(treeNodeDatasourceVo);
		}*/
		
		//zTree 2.6 method
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();
		ZTreeNodeVo treeNodeDatasourceVo = null;
		ZTreeNodeVo treeNodeEntityVo = null;
		List<ZTreeNodeVo> nodeVoList = null;
		for(DatasourceDto dsDto:dsDtoList){
			treeNodeDatasourceVo = new ZTreeNodeVo(dsDto);
			nodeVoList = new ArrayList<ZTreeNodeVo>();
			for(EntityDto entityDto:allEntitiesDtoList){
				if(entityDto.getDatasourceSqu() == dsDto.getSqu()){
					treeNodeEntityVo = new ZTreeNodeVo(entityDto);
					
					//检查该实体是否已授权，是则checkbox=true
					for(EntityDto _EntityDto:grantedEntitiesDtoList){
						if(_EntityDto.getSqu() == entityDto.getSqu()){
							treeNodeEntityVo.setChecked(true);
							grantedEntitiesDtoList.remove(_EntityDto);
							break;
						}
					}
					nodeVoList.add(treeNodeEntityVo);
				}
			}
			treeNodeDatasourceVo.setNodes(nodeVoList);
			treeNodeVoList.add(treeNodeDatasourceVo);
		}
		
		
		JSONArray jsonArray = JSONArray.fromObject(treeNodeVoList);
		return jsonArray.toString();
	}
	
	/**
	 * 保存实体的授权信息
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String saveGrantedEntities(RoleVo roleVo) {
		JSONArray entityJsonArray = JSONArray.fromObject(roleVo.getJsonStr());
		
		/* easyUi tree method
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		TreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<entityJsonArray.size(); i++){
        	JSONObject jsonObject = entityJsonArray.getJSONObject(i);
        	treeNodeVo = (TreeNodeVo)JSONObject.toBean(jsonObject, TreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
		
        //RoleVo转换成RoleDto
        RoleDto roleDto = new RoleDto(roleVo);
        
        //转换成EntityDtoList
        List<EntityDto> EntityDtoList = new ArrayList<EntityDto>();
        for(TreeNodeVo nodeVo:treeNodeVoList){
        	EntityDtoList.add(new EntityDto(nodeVo));
        }*/
		
		//zTree 2.6 method
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();
		ZTreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<entityJsonArray.size(); i++){
        	JSONObject jsonObject = entityJsonArray.getJSONObject(i);
        	treeNodeVo = (ZTreeNodeVo)JSONObject.toBean(jsonObject, ZTreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
		
        //RoleVo转换成RoleDto
        RoleDto roleDto = new RoleDto(roleVo);
        
        //转换成EntityDtoList
        List<EntityDto> EntityDtoList = new ArrayList<EntityDto>();
        for(ZTreeNodeVo nodeVo:treeNodeVoList){
        	EntityDtoList.add(new EntityDto(nodeVo));
        }
		
		return roleDAO.saveGrantedEntities(roleDto,EntityDtoList);
	}
	
	
	/**
	 * 生成角色可授权查询树（根据角色信息查询已授权实体信息，生成完整可授权查询树）
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	/*public String fetchQueryTreeByRole(int roleSqu){
		
		//从数据库查询功能模块信息
		List<EntityDto> grantedEntityDtoList = roleDAO.queryGrantedEntitiesByRoleSqu(roleSqu);
		List<QueryDto> queryDtoList = roleDAO.queryAllQueries();
		
		//将实体信息(包含父节点datasource)封装成List<TreeNodeVo>并转换成json字符串
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		
		for(QueryDto queryDto:queryDtoList){
			boolean flag = true;//true=可用查询，false=不可用查询
			for(EntityDto entityDto:queryDto.getEntityDtoList()){
				if(!grantedEntityDtoList.contains(entityDto)){
					flag = false;
					break;
				}
			}
			if(flag == true){
				treeNodeVoList.add(new TreeNodeVo(queryDto));
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(treeNodeVoList);
		
		return jsonArray.toString();
	}*/
	
	/**
	 * 读取角色已授权查询树（根据角色信息查询已授权查询信息，生成已授权查询树）
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String fetchGrantedQueryTreeByRole(int roleSqu){
		//从数据库查询功能模块信息
		List<QueryDto> allQueryList = roleDAO.queryAllQueries();
		List<EntityDto> grantedEntityDtoList = roleDAO.queryGrantedEntitiesByRoleSqu(roleSqu);
		List<QueryDto> grantedQueryDtoList = roleDAO.queryGrantedQueriesByRoleSqu(roleSqu);
		List<DatasourceDto> dsDtoList = roleDAO.queryAllDatasources();
		
		List<QueryDto> allEnabledQueryList = new ArrayList<QueryDto>();//可以被授权的q	ueryList
		//将所有query中没有权限的部分过滤掉
		for(QueryDto queryDto:allQueryList){
			boolean flag = true;//true=可用查询，false=不可用查询
			for(EntityDto entityDto:queryDto.getEntityDtoList()){
				//检查已授权实体中是否存在该实体
				if(!isEntityGranted(entityDto,grantedEntityDtoList)){
					flag = false;
				}
			}
			if(flag == true){
				allEnabledQueryList.add(queryDto);
			}
		}
		
		/* easyUi tree method
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();//最终返回的treeNodeList
		TreeNodeVo treeNodeDatasourceVo = null;
		TreeNodeVo treeNodeQueryVo = null;
		List<TreeNodeVo> nodeVoList = null;
		for(DatasourceDto dsDto:dsDtoList){
			treeNodeDatasourceVo = new TreeNodeVo(dsDto);
			nodeVoList = new ArrayList<TreeNodeVo>();
			for(QueryDto queryDto:allEnabledQueryList){
				if(queryDto.getDsSqu() == dsDto.getSqu()){
					treeNodeQueryVo = new TreeNodeVo(queryDto);
					
					//检查该查询是否已授权，是则checkbox=true
					if(isQueryGranted(queryDto,grantedQueryDtoList)){
						treeNodeQueryVo.setChecked(true);
					}
					nodeVoList.add(treeNodeQueryVo);
				}
			}
			treeNodeDatasourceVo.setChildren(nodeVoList);
			treeNodeVoList.add(treeNodeDatasourceVo);
		}*/
		
		//zTree 2.6 method
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();//最终返回的treeNodeList
		ZTreeNodeVo treeNodeDatasourceVo = null;
		ZTreeNodeVo treeNodeQueryVo = null;
		List<ZTreeNodeVo> nodeVoList = null;
		for(DatasourceDto dsDto:dsDtoList){
			treeNodeDatasourceVo = new ZTreeNodeVo(dsDto);
			nodeVoList = new ArrayList<ZTreeNodeVo>();
			for(QueryDto queryDto:allEnabledQueryList){
				if(queryDto.getDsSqu() == dsDto.getSqu()){
					treeNodeQueryVo = new ZTreeNodeVo(queryDto);
					
					//检查该查询是否已授权，是则checkbox=true
					if(isQueryGranted(queryDto,grantedQueryDtoList)){
						treeNodeQueryVo.setChecked(true);
					}
					nodeVoList.add(treeNodeQueryVo);
				}
			}
			treeNodeDatasourceVo.setNodes(nodeVoList);
			treeNodeVoList.add(treeNodeDatasourceVo);
		}
		
		
		JSONArray jsonArray = JSONArray.fromObject(treeNodeVoList);
		return jsonArray.toString();
	}
	
	
	
	
	/**
	 * 保存查询的授权信息
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String saveGrantedQueries(RoleVo roleVo) {
		JSONArray queryJsonArray = JSONArray.fromObject(roleVo.getJsonStr());
		
		/* easyUi tree method
		List<TreeNodeVo> treeNodeVoList = new ArrayList<TreeNodeVo>();
		TreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<queryJsonArray.size(); i++){
        	JSONObject jsonObject = queryJsonArray.getJSONObject(i);
        	treeNodeVo = (TreeNodeVo)JSONObject.toBean(jsonObject, TreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
		
        //RoleVo转换成RoleDto
        RoleDto roleDto = new RoleDto(roleVo);
        //转换成queryDtoList
        List<QueryDto> queryDtoList = new ArrayList<QueryDto>();
        for(TreeNodeVo nodeVo:treeNodeVoList){
        	queryDtoList.add(new QueryDto(nodeVo));
        }*/
		
		//zTree 2.6 method
		List<ZTreeNodeVo> treeNodeVoList = new ArrayList<ZTreeNodeVo>();
		ZTreeNodeVo treeNodeVo = null;
        for ( int i = 0 ; i<queryJsonArray.size(); i++){
        	JSONObject jsonObject = queryJsonArray.getJSONObject(i);
        	treeNodeVo = (ZTreeNodeVo)JSONObject.toBean(jsonObject, ZTreeNodeVo.class);
        	treeNodeVoList.add(treeNodeVo);
        }
		
        //RoleVo转换成RoleDto
        RoleDto roleDto = new RoleDto(roleVo);
        //转换成queryDtoList
        List<QueryDto> queryDtoList = new ArrayList<QueryDto>();
        for(ZTreeNodeVo nodeVo:treeNodeVoList){
        	queryDtoList.add(new QueryDto(nodeVo));
        }
		
		return roleDAO.saveGrantedQueries(roleDto,queryDtoList);
	}
	
	
	
	
	
	
	/**
	 * 检查指定EntityDto是否已授权
	 * @author:  cl 
	 * @param EntityDto checkEntityDto 待检测实体
	 * @param List<EntityDto> grantedEntityDtoList 所有已授权实体list
	 * @return boolean
	 */
	private boolean isEntityGranted(EntityDto checkEntityDto,List<EntityDto> grantedEntityDtoList){
		for(EntityDto entityDto:grantedEntityDtoList){
			if(entityDto.getSqu() == checkEntityDto.getSqu()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查指定EntityDto是否已授权
	 * @author:  cl 
	 * @param QueryDto checkQueryDto 待检测查询
	 * @param List<QueryDto> grantedQueryDtoList 所有已授权查询list
	 * @return boolean
	 */
	private boolean isQueryGranted(QueryDto checkQueryDto,List<QueryDto> grantedQueryDtoList){
		for(QueryDto queryDto:grantedQueryDtoList){
			if(queryDto.getSqu() == checkQueryDto.getSqu()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据角色squ查询出所有注册并已授权实体信息,返回Json格式
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String listGrantedEntityesInJson(int roleSqu) {
		List<EntityDto> entityList = roleDAO.queryGrantedEntitiesByRoleSqu(roleSqu);
		
		PageInfoVo page = new PageInfoVo();
		page.setRows(entityList);
		page.setTotal(entityList.size());
		
		JSONObject jsonObj = JSONObject.fromObject(page);
		String jsonStr = jsonObj.toString();
		return jsonStr;
	}
	

}
