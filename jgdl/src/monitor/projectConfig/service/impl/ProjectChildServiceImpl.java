/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.7
	* @version：1.0
	* File Name:ProjectChildServiceImpl.java
	* Date:2018-2-6上午9:54:04   
	***/
package monitor.projectConfig.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import monitor.common.service.BaseServicce;
import monitor.goodsHouse.dao.impl.PartsDaoImpl;
import monitor.projectConfig.bean.entity.Project_Child;
import monitor.projectConfig.dao.impl.ProjectChildDaoImpl;
import monitor.projectConfig.dao.impl.ProjectChildSiteDaoImpl;
import monitor.projectConfig.service.IProjectChildService;

/**
 * @ClassName:ProjectChildServiceImpl
 * @dateTime: 2018-2-6 上午9:54:04
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class ProjectChildServiceImpl extends BaseServicce implements IProjectChildService {

	private Connection conn = null;
	
	@Resource(name = "projectChildDao")
	private ProjectChildDaoImpl projectChildDao;

	/**
	  * @Fields  projectChildSiteDao:TODO(部位dao层注入)
	  * @since JDK1.7
	*/
	@Resource(name = "projectChildSiteDao")
	private ProjectChildSiteDaoImpl projectChildSiteDao;
	
	/**
	  * listChildProjectByProjectSqu:根据项目id获取子项目
	  * @author 黄月
	  * @date  2018-2-6 下午12:07:26
	  * @param conn
	  * @param projectSqu
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public List<Project_Child> listChildProjectByProjectSqu(
			String projectSqu) throws SQLException {
		List<Project_Child> list = null;
		try {
			conn = datasource.getConnection();
			list = projectChildDao.listChildProjectByProjectSqu(conn, projectSqu);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			closed(conn);
		}
		return list;
	}

	/**
	  * countChildProjectByProjectSqu:接口实现
	  * @author 黄月
	  * @date  2018-2-6 下午12:07:26
	  * @param conn
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public Integer countChildProjectByProjectSqu(String projectSqu) throws SQLException {
		try {
			conn = datasource.getConnection();
			return projectChildDao.countChildProjectByProjectSqu(conn, projectSqu);
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(conn);
		}
	}

	/**
	  * delChildProjectBySqu:根据项目id删除子项目
	  * @author 黄月
	  * @date  2018-2-6 下午12:07:26
	  * @param conn
	  * @param childSqu
	  * @throws SQLException 
	*/
	@Override
	public String delChildProjectBySqu(String childSqu)
			throws SQLException {
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			//查询当前子项目下是否存在部位
			int count = 0;
			if (count > 0) {
				return "hasSite";
			}
			projectChildDao.delChildProjectBySqu(conn, childSqu);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException();
		} finally {
			closed(conn);
		}
		return "success";
	}

	/**
	  * updateChildProject:修改子项目
	  * @author 黄月
	  * @date  2018-2-6 下午12:07:26
	  * @param conn
	  * @param childEntity
	  * @throws SQLException 
	*/
	@Override
	public void updateChildProject(Project_Child childEntity)
			throws SQLException {
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			projectChildDao.updateChildProject(conn, childEntity);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException();
		} finally {
			closed(conn);
		}
	}

	/**
	  * addChildProject:新增子项目
	  * @author 黄月
	  * @date  2018-2-6 下午12:07:26
	  * @param conn
	  * @param childEntity
	  * @throws SQLException 
	*/
	@Override
	public void addChildProject(Project_Child childEntity)
			throws SQLException {
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			projectChildDao.addChildProject(conn, childEntity);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException();
		} finally {
			closed(conn);
		}
	}

	/**
	  * closed:(关闭数据库连接).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-10-14 下午3:29:43
	  * @param rs
	  * @param ps
	  * @param conn void
	  * @since JDK 1.7
	*/
	private void closed(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	  * getChildProjectBySqu:接口实现
	  * @author 黄月
	  * @date  2018-6-23 下午1:07:54
	  * @param squ
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public Project_Child getChildProjectBySqu(String squ) throws SQLException {
		Project_Child child = new Project_Child();
		try {
			conn = datasource.getConnection();
			child = projectChildDao.getChildProjectBySqu(conn, squ);
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(conn);
		}
		return child;
	}

	@Override
	public Project_Child getChildInfoBySqu(String childsqu, Connection conn)
			throws SQLException {
		
		return null;
	}
}
