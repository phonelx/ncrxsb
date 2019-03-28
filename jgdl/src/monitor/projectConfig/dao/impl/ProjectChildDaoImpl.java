/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.7
	* @version：1.0
	* File Name:ProjectChildDao.java
	* Date:2018-2-6上午9:55:12   
	***/
package monitor.projectConfig.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import monitor.projectConfig.bean.entity.Project;
import monitor.projectConfig.bean.entity.Project_Child;
import monitor.projectConfig.dao.IProjectChildDao;

/**
 * @ClassName:ProjectChildDao
 * @dateTime: 2018-2-6 上午9:55:12
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class ProjectChildDaoImpl implements IProjectChildDao {

	/**
	  * @Fields  ps:TODO(用一句话描述这个变量表示什么)
	  * @since JDK1.7
	*/
	private PreparedStatement ps = null;
	
	/**
	  * listChildProjectByProjectSqu:根据项目id获取子项目列表
	  * @author 黄月
	  * @date  2018-2-6 上午10:21:21
	  * @param conn
	  * @param projectSqu
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public List<Project_Child> listChildProjectByProjectSqu(Connection conn,
			String projectSqu) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT CHILD_SQU,JZGD,PROJECT_SQU,ZDWXMMC, JZLB,CHILDNUM,");
		sql.append(" TO_CHAR(CREATEDATE,'YYYY/MM/DD HH24:mi:ss') AS CREATEDATE, ");
		sql.append(" TO_CHAR(UPDATEDATE,'YYYY/MM/DD HH24:mi:ss') AS UPDATEDATE ");
		sql.append(" FROM PROJECT_CHILD WHERE PROJECT_SQU = ? ORDER BY UPDATEDATE DESC ");
		ResultSet rs = null;
		List<Project_Child> list = new LinkedList<Project_Child>();
		Project_Child childEntity = null;
		
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, projectSqu);
			rs = ps.executeQuery();
			while (rs.next()) {
				childEntity = new Project_Child();
				childEntity.setChild_squ(rs.getString("CHILD_SQU"));
				childEntity.setChildNum(rs.getString("CHILDNUM"));
				childEntity.setJzgd(rs.getDouble("JZGD"));
				childEntity.setProject_squ(rs.getString("PROJECT_SQU"));
				childEntity.setZdwxmmc(rs.getString("ZDWXMMC"));
				childEntity.setCreateDate(rs.getString("CREATEDATE"));
				childEntity.setUpdateDate(rs.getString("UPDATEDATE"));
				childEntity.setJzlb(rs.getString("JZLB"));
				list.add(childEntity);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			closed(rs, ps);
		}
		return list;
	}

	/**
	  * countChildProjectByProjectSqu:更具项目id获取子项目统计
	  * @author 黄月
	  * @date  2018-2-6 上午10:21:21
	  * @param conn
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public Integer countChildProjectByProjectSqu(Connection conn, String squ) throws SQLException {
		int count = 0;
		ResultSet rs = null;
		String sql = " SELECT COUNT(*) FROM PROJECT_CHILD WHERE PROJECT_SQU = '" + squ + "'";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return count;
	}

	/**
	  * delChildProjectBySqu:更具子项目id删除子项目
	  * @author 黄月
	  * @date  2018-2-6 上午10:21:21
	  * @param conn
	  * @param childSqu 
	 * @throws SQLException 
	*/
	@Override
	public void delChildProjectBySqu(Connection conn, String childSqu) throws SQLException {

		String sql = " DELETE FROM PROJECT_CHILD WHERE CHILD_SQU = '" + childSqu + "'";
		//System.out.println("delChildProjectBySqu:" + sql);
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(null, ps);
		}
	}

	/**
	  * updateChildProject:修改子项目信息
	  * @author 黄月
	  * @date  2018-2-6 上午10:21:21
	  * @param conn
	  * @param childEntity 
	 * @throws SQLException 
	*/
	@Override
	public void updateChildProject(Connection conn, Project_Child childEntity) throws SQLException {
		String sql = " UPDATE PROJECT_CHILD SET JZGD = ?,ZDWXMMC = ?,UPDATEDATE=SYSDATE,JZLB = ?,CHILDNUM=? WHERE CHILD_SQU = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, childEntity.getJzgd());
			ps.setString(2, childEntity.getZdwxmmc());
			ps.setString(3, childEntity.getJzlb());
			ps.setString(4, childEntity.getChildNum());
			ps.setString(5, childEntity.getChild_squ());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new  SQLException();
		} finally {
			closed(null, ps);
		}
	}

	/**
	  * addChildProject:新增子项目
	  * @author 黄月
	  * @date  2018-2-6 上午10:21:21
	  * @param conn
	  * @param childEntity 
	 * @throws SQLException 
	*/
	@Override
	public void addChildProject(Connection conn, Project_Child childEntity) throws SQLException {
		String sql = " INSERT INTO PROJECT_CHILD(CHILD_SQU, JZGD, ZDWXMMC, PROJECT_SQU,JZLB,CHILDNUM) VALUES(SYS_GUID(),?,?,?,?,?) ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, childEntity.getJzgd());
			ps.setString(2, childEntity.getZdwxmmc());
			ps.setString(3, childEntity.getProject_squ());
			ps.setString(4, childEntity.getJzlb());
			ps.setString(5, childEntity.getChildNum());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(null, ps);
		}
	}

	
	private void closed(ResultSet rs, PreparedStatement ps){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	  * getChildProjectBySqu:接口实现
	  * @author 黄月
	  * @date  2018-6-23 下午1:01:16
	  * @param conn
	  * @param squ
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public Project_Child getChildProjectBySqu(Connection conn, String squ) throws SQLException {
		ResultSet rs = null;
		String sql = " SELECT * FROM PROJECT_CHILD WHERE CHILD_SQU=? ";
		Project_Child child = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, squ);
			rs = ps.executeQuery();
			if (rs.next()) {
				child = new Project_Child();
				child.setChild_squ(rs.getString("CHILD_SQU"));
				child.setChildNum(rs.getString("CHILDNUM"));
				child.setJzgd(rs.getDouble("JZGD"));
				child.setProject_squ(rs.getString("PROJECT_SQU"));
				child.setJzlb(rs.getString("JZLB"));
				child.setZdwxmmc(rs.getString("ZDWXMMC"));
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(null, ps);
		}
		return child;
	}

	
}
