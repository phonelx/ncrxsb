package monitor.projectConfig.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import monitor.common.service.BaseServicce;
import monitor.goodsHouse.bean.entity.Wzzdb;
import monitor.goodsHouse.dao.impl.PartsDaoImpl;
import monitor.projectConfig.bean.entity.Project;
import monitor.projectConfig.dao.impl.ProjectChildDaoImpl;
import monitor.projectConfig.dao.impl.ProjectChildSiteDaoImpl;
import monitor.projectConfig.dao.impl.ProjectConfigDaoImpl;
import monitor.projectConfig.service.IProjectConfigService;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:ProjectConfigImpl
 * @dateTime: 2017-10-14 下午3:22:52
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class ProjectConfigServiceImpl extends BaseServicce implements IProjectConfigService {
	
	private Connection conn = null;
	
	/**
	  * @Fields  projectDao:TODO(项目dao层注入)
	  * @since JDK1.7
	*/
	@Resource(name = "projectDao")
	private ProjectConfigDaoImpl projectDao;
	
	@Resource(name = "partsDao")
	private PartsDaoImpl partsDao;
	/**
	  * @Fields  projectChildDao:TODO(子项目dao层注入)
	  * @since JDK1.7
	*/
	@Resource(name = "projectChildDao")
	private ProjectChildDaoImpl projectChildDao;
	
	@Resource(name = "projectChildSiteDao")
	private ProjectChildSiteDaoImpl projectChildSiteDao;
	
	/**
	  * selectProjectPageInfo:分页查询项目列表
	  * @author 黄月
	  * @date  2017-10-14 下午3:55:54
	  * @param pageVo
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public PageInfoVo selectProjectPageInfo(PageInfoVo pageVo, String searchWord) throws SQLException {
		
		pageVo.setStartIndex((pageVo.getPageNumber() - 1) * pageVo.getPageSize() + 1);
		pageVo.setEndIndex(pageVo.getStartIndex() + pageVo.getPageSize());
		
		try {
			conn = datasource.getConnection();
			if ("".equals(searchWord) || searchWord == null) {
				//项目总数
				pageVo.setTotal(projectDao.selectCountProject(conn));
				//项目列表
				pageVo.setRows(projectDao.selectProjectPageInfo(pageVo, conn));
			} else {
				searchWord = searchWord.trim();
				//项目总数
				pageVo.setTotal(projectDao.selectCountProjectBySearch(conn, searchWord));
				//项目列表
				pageVo.setRows(projectDao.selectProjectPageInfoBySearch(pageVo, conn, searchWord));
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(conn);
		}
		return pageVo;
	}

	/**
	  * selectProjectBySqu:查询单个项目
	  * @author 黄月
	  * @date  2017-10-14 下午3:55:54
	  * @param squ
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public Project selectProjectBySqu(String squ) throws SQLException {
		Project pro = new Project();
		try {
			conn = datasource.getConnection();
			pro = projectDao.selectProjectBySqu(squ, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
		return pro;
	}

	/**
	  * insertProject:新增项目
	  * @author 黄月
	  * @date  2017-10-14 下午3:55:54
	  * @param project 
	 * @throws SQLException 
	*/
	@Override
	public void insertProject(Project project) throws SQLException {
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			projectDao.insertProject(project, conn);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
	}

	/**
	  * updateProjectBySqu:修改项目基本信息
	  * @author 黄月
	  * @date  2017-10-14 下午3:55:54
	  * @param squ 
	 * @throws SQLException 
	*/
	@Override
	public void updateProjectBySqu(Project project) throws SQLException {
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			projectDao.updateProjectBySqu(project, conn);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
	}

	/**
	  * deleteProjectBySqu:接口实现
	  * @author 黄月
	  * @date  2017-10-14 下午3:55:54
	  * @param squ 
	 * @throws SQLException 
	*/
	@Override
	public String deleteProjectBySqu(String squ) throws SQLException {
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			int count = projectChildDao.countChildProjectByProjectSqu(conn, squ);
			if (count > 0) {
				return "hasChild";
			}
			projectDao.deleteProjectBySqu(squ, conn);
			conn.commit();
			return "success";
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
	}
	
	
	public ProjectConfigDaoImpl getProjectDao() {
		return projectDao;
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
	  * selectProjectAll:接口实现
	  * @author 黄月
	  * @date  2017-10-23 下午6:39:42
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public List<Project> selectProjectAll() throws SQLException {
		return null;
	/*	List<T_Project> list = null;
		try {
			conn = datasource.getConnection();
			list = projectDao.selectProjectAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
		return list;*/
	}

	/**
	  * selectHolderOfProjectPageInfo:接口实现
	  * @author 黄月
	  * @date  2017-10-23 下午8:05:13
	  * @param pageVo
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public PageInfoVo selectHolderOfProjectPageInfo(PageInfoVo pageVo, String squ)
			throws SQLException {
		try {
			conn = datasource.getConnection();
			//项目总数
			pageVo.setTotal(projectDao.selectCountHolderOfProject(conn, squ));
			
			pageVo.setStartIndex((pageVo.getPageNumber() - 1) * pageVo.getPageSize() + 1);
			pageVo.setEndIndex(pageVo.getStartIndex() + pageVo.getPageSize());
			//项目列表
			pageVo.setRows(projectDao.selectHolderOfProjectPageInfo(pageVo, squ, conn));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
		return pageVo;
	}

	/**
	  * getHolderList:接口实现
	  * @author 黄月
	  * @date  2017-10-24 下午7:18:58
	  * @param pageVo
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public PageInfoVo getHolderList(PageInfoVo pageVo, String gdlx, String azfs, String search) throws SQLException {
		try {
			conn = datasource.getConnection();
			String sql = "";
			if (!"".equals(gdlx)) {
				sql += " AND GDLX LIKE '%" + gdlx + "%' ";
			}
			if (!"".equals(azfs)) {
				sql += " AND AZFS LIKE '%" + azfs + "%' ";
			}
			if (!"".equals(search)) {
				sql += " AND DXMC LIKE '%" + search + "%' ";
			}
			
			pageVo.setTotal(projectDao.selectCountHolder(conn, sql));
			
			pageVo.setStartIndex((pageVo.getPageNumber() - 1) * pageVo.getPageSize() + 1);
			pageVo.setEndIndex(pageVo.getStartIndex() + pageVo.getPageSize());
			//项目列表
			pageVo.setRows(projectDao.selectHolderPageInfo(pageVo, conn, sql));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
		return pageVo;
	}

	/**
	  * addHolderToProject:新增支架
	  * @author 黄月
	  * @date  2017-10-24 下午9:03:20
	  * @param holderSqu
	  * @param projectSqu 
	 * @throws SQLException 
	*/
	@Override
	public void addHolderToProject(String holderSqu, String projectSqu) throws SQLException {
		//int num = 0;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			/*//查询项目是否已存在支架，并返回数量
			num = projectDao.selectHavingHolderOfProject(conn, holderSqu, projectSqu);
			if (num == 0) {*/
				//不存在则添加支架，默认为1
				projectDao.insertHolderToProject(conn, holderSqu, projectSqu);
			//}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(conn);
		}
	}
	
	/**
	  * addHolderToProject:(修改项目支架数量).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 黄月
	  * @dateTime: 2017-11-20 上午11:25:31
	  * @param num void
	 * @throws SQLException 
	  * @since JDK 1.7
	*/
	@Override
	public void updateHolderNumOfProject(Integer num, String holderSqu, String projectSqu) throws SQLException{
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			projectDao.updateHolderNumOfProject(conn, num, holderSqu, projectSqu);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new SQLException();
		} finally {
			closed(conn);
		}
	}

	/**
	  * deleteHolderOfProjectBySqu:根据id删除项目支架
	  * @author 黄月
	  * @date  2017-11-20 上午10:57:21
	  * @param squ
	  * @param conn 
	 * @throws SQLException 
	*/
	@Override
	public void deleteHolderOfProjectBySqu(String squ) throws SQLException {
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			projectDao.deleteHolderOfProjectBySqu(squ, conn);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new SQLException();
		} finally {
			closed(conn);
		}
	}

	/**
	  * getSelectOfObject:获取类别下拉列表
	  * @author 黄月
	  * @date  2017-11-22 下午5:02:16
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public List<String> getSelectOfObject(String selectType) throws SQLException {
		List<String> result = null;
		String squ = "";
		try {
			conn = datasource.getConnection();
			if ("jzlb".equals(selectType)) {
				squ = "6C67EF9E919842A4AD16A26514DBB8F0";
			} else if ("dqsfld".equals(selectType)) {
				squ = "4CEA6ECDF4474EE6AD9E9F9BE3B44B0D";
			} else if ("dzlx".equals(selectType)) {		//地震类型
				squ = "5B3D4CE66DD44A46A61FC466C5E1DFE4";
			} else if ("gdlx".equals(selectType)) {
				squ = "05B1B0C9A39446BC80DAAE8DD6C3A129";
			}
			result = projectDao.getBuildingCategory(conn, squ);
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(conn);
		}
		return result;
	}

	/**
	  * getSelectOfGDLX_AZFS:接口实现
	  * @author 黄月
	  * @date  2017-12-4 下午3:12:18
	  * @param selectType
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public List<Map<String, String>> getSelectOfGDLX_AZFS(String selectType)
			throws SQLException {
		List<Map<String, String>> result = null;
		try {
			conn = datasource.getConnection();
			if ("GDLX".equals(selectType)) {
				result = projectDao.getSelectOfGDLX(conn);
			} else if ("AZFS".equals(selectType)) {
				result = projectDao.getSelectOfAZFS(conn);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(conn);
		}
		return result;
	}

	/**
	  * listCountMsg:获取各级数据统计
	  * @author 黄月
	  * @date  2018-2-8 下午6:52:36
	  * @param squ
	  * @param level
	  * @return 
	*/
	@Override
	public List<Integer> listCountMsg(String squ, String level, String type) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			conn = datasource.getConnection();
			if ("1".equals(level)) {
				//子项目
				list.add(projectChildDao.countChildProjectByProjectSqu(conn, squ));
				//部位
				list.add(projectChildSiteDao.countChildSiteByProjectSqu(conn, squ));
				//支架
				list.add(projectChildSiteDao.countHolderByProjectSqu(conn, squ));
				//部件
				list.add(projectChildSiteDao.countPartsByProjectSqu(conn, squ));
			} else if ("2".equals(level)) {
				list.add(0);
				list.add(projectChildSiteDao.countChildSiteByChildSqu(conn, squ));
				list.add(projectChildSiteDao.countHolderByChildSqu(conn, squ));
				list.add(projectChildSiteDao.countPartsByChildSqu(conn, squ));
			} else if ("3".equals(level)) {
				//统计所有支架
				list.add(0);
				list.add(0);
				list.add(projectChildSiteDao.countHolderBySiteSqu(conn, squ, ""));
				list.add(projectChildSiteDao.countPartsBySiteSqu(conn, squ, ""));
			} else if ("4".equals(level)) {
				//统计专业下支架
				list.add(0);
				list.add(0);
				list.add(projectChildSiteDao.countHolderBySiteSqu(conn, squ,type));
				list.add(projectChildSiteDao.countPartsBySiteSqu(conn, squ,type));
			} else if ("5".equals(level)) {
				list.add(0);
				list.add(0);
				list.add(0);
				list.add(projectChildSiteDao.countPartsByHolderSqu(conn, squ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closed(conn);
		}
		return list;
	}

	@Override
	public Project getProjectInfoBySqu(String projectSqu) {
		Project project = null;
		try {
			conn = datasource.getConnection();
			project = projectDao.getProjectInfoBySqu(projectSqu, conn) ;
			//地震设防烈度
			Wzzdb dzsfld = partsDao.selectWzzdb(project.getDzsfld());
			//地震加速度
			Wzzdb dzjsd = partsDao.selectWzzdb(project.getDzjsd());
			//地震类型
			Wzzdb dzlx = partsDao.selectWzzdb(project.getDzlx());
			
			project.setDzsfld(dzsfld.getLBMC());
			project.setDzjsd(dzjsd.getLBMC());
			project.setDzlx(dzlx.getLBMC());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	}

}
