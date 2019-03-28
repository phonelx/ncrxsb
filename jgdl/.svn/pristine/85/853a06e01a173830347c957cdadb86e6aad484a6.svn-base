/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-18 上午10:54:16
 */
package monitor.user.service.impl;

import java.util.List;

import monitor.user.bean.dto.ModuleMainDto;
import monitor.user.bean.dto.UserDto;
import monitor.user.bean.dto.UserLoginInfoDto;
import monitor.user.bean.vo.PageInfoVo;
import monitor.user.dao.IUserDAO;
import monitor.user.service.IUserService;

/**
 * <description>
 * 
 * @author cl
 * @datetime 2011-4-18 上午10:54:16
 */
public class UserServiceImpl implements IUserService {
	private IUserDAO userDAO = null;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 查询所有用户信息
	 * 
	 * @author: cl
	 * @param
	 * @return List<UserDto>
	 * @deprecated
	 */
	public List<UserDto> listUsers() {
		return userDAO.listUsers();
	}

	/**
	 * 返回通过PageInfoVo封装后的用户列表，已通过分页处理
	 * @author: cl
	 * @param
	 * @return PageInfoVo
	 */
	public PageInfoVo listUsersInPage(PageInfoVo page) {
		return userDAO.listUsersInPage(page);
	}

	/**
	  * addUser:新增用户
	  * @date  2017-11-10 下午3:16:54
	  * @param userDto
	  * @return 
	*/
	public String addUser(UserDto userDto) {
		String pwd = userDto.getPwd().trim();
		String repeatPwd = userDto.getRepeatPwd().trim();
		if (!pwd.equals(repeatPwd)) {
			return "pwdNotEqual";
		}
		return userDAO.addUser(userDto);
	}

	/**
	  * editUser:修改用户
	  * @date  2017-11-10 下午3:26:17
	  * @param userDto
	  * @return 
	*/
	public String editUser(UserDto userDto) {
		return userDAO.editUser(userDto);
	}

	public String deleteUser(UserDto userDto) {
		return userDAO.deleteUser(userDto);
	}

	/**
	 * 登录验证方法
	 * @param UserDto
	 * @return UserDto
	 */
	public UserDto login(UserDto user) {
		return userDAO.login(user);
	}

	/**
	 * 列出指定用户的功能菜单
	 * 
	 * @author: cl
	 * @param UserDto
	 * @return List<ModuleMainVo>
	 */
	public List<ModuleMainDto> listModules(UserDto dto) {
		return userDAO.listModules(dto);
	}

	/**
	 * 记录用户登录系统信息
	 * 
	 * @author: cl
	 * @param
	 * @return int 新增记录信息主键squ
	 */
	public int recordUserLogin(UserLoginInfoDto userLoginDto) {
//		return 1;
		return userDAO.recordUserLogin(userLoginDto);
	}

	/**
	 * 记录用户退出系统信息(更新数据，加入退出时间)
	 * 
	 * @author: cl
	 * @param
	 * @return void
	 */
	public void recordUserLogout(UserLoginInfoDto userLoginInfoDto) {
		userDAO.recordUserLogout(userLoginInfoDto);
	}

	/**
	 * 取得权限信息
	 * 
	 * @author: yl
	 * @param
	 * @return void
	 */
	public String getMain() {
		return userDAO.getMain();
	}

	public UserDto getUser() {
		return userDAO.getUser();
	}
}
