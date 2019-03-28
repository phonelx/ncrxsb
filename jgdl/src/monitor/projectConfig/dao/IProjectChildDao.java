/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.7
	* @version：1.0
	* File Name:IProjectChildDao.java
	* Date:2018-2-6上午9:54:23   
	***/
package monitor.projectConfig.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import monitor.projectConfig.bean.entity.Project_Child;

/**
 * @ClassName:IProjectChildDao
 * @dateTime: 2018-2-6 上午9:54:23
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public interface IProjectChildDao {
	
	/**
	  * getChildProjectBySqu:(根据id查询子项目信息).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-6-23 下午12:56:48
	  * @param conn
	  * @param squ
	  * @return Project_Child
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	Project_Child getChildProjectBySqu(Connection conn,
			String squ) throws SQLException;

	/**
	  * listChildProject:(获取子项目对象列表).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-2-6 上午10:14:48
	  * @param conn
	  * @param projectSqu  项目id
	  * @return List<Project_Child>
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	List<Project_Child> listChildProjectByProjectSqu(Connection conn,
			String projectSqu) throws SQLException;
	
	/**
	  * countChildProject:(统计子项目).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-1-31 下午4:39:04
	  * @return Integer
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	Integer countChildProjectByProjectSqu(Connection conn, String squ) throws SQLException;
	
	/**
	  * delChildProjectBySqu:(根据子项目id删除).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-2-6 上午10:18:00
	  * @param conn
	  * @param squ void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void delChildProjectBySqu(Connection conn, String squ) throws SQLException;
	
	/**
	  * updateChildProject:(修改子项目信息).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-2-6 上午10:19:04
	  * @param conn
	  * @param childEntity void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void updateChildProject(Connection conn, Project_Child childEntity) throws SQLException;
	
	/**
	  * addChildProject:(新增子项目信息).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2018-2-6 上午10:20:33
	  * @param conn
	  * @param childEntity void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	void addChildProject(Connection conn, Project_Child childEntity) throws SQLException;
	

	
}
