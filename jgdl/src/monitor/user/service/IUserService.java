/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-18 上午10:54:01
*/
package monitor.user.service;

import java.util.List;

import monitor.user.bean.dto.ModuleMainDto;
import monitor.user.bean.dto.UserDto;
import monitor.user.bean.dto.UserLoginInfoDto;
import monitor.user.bean.vo.PageInfoVo;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-4-18 上午10:54:01
 */
public interface IUserService {
	public UserDto login(UserDto user);
	
	/**
	 * 查询所有用户信息
	 * @author:  cl 
	 * @param 
	 * @return List<UserDto>
	 * @deprecated
	 */
	public List<UserDto> listUsers();
	
	
	/**
	 * 返回通过PageInfoVo封装后的用户列表，已通过分页处理
	 * @author:  cl 
	 * @param 
	 * @return PageInfoVo
	 */
	public PageInfoVo listUsersInPage(PageInfoVo page);

	public String addUser(UserDto userDto);

	public String editUser(UserDto userDto);

	public String deleteUser(UserDto userDto);

	/**
	 * 列出指定用户的功能菜单
	 * @author:  cl 
	 * @param UserDto
	 * @return List<ModuleMainVo>
	 */
	public List<ModuleMainDto> listModules(UserDto dto);
	

	/**
	 * 记录用户登录系统信息
	 * @author:  cl 
	 * @param 
	 * @return int 新增记录信息主键squ
	 */
	public int recordUserLogin(UserLoginInfoDto userLoginDto);

	/**
	 * 记录用户退出系统信息(更新数据，加入退出时间)
	 * @author:  cl 
	 * @param 
	 * @return void
	 */
	public void recordUserLogout(UserLoginInfoDto userLoginInfoDto);
	
	/**
	 * 取得权限信息
	 * @author: yl 
	 * @param 
	 * @return void
	 */
	public String getMain(); 
	public UserDto getUser();
}
