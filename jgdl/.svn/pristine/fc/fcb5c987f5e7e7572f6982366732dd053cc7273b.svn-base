package monitor.projectConfig.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monitor.projectConfig.bean.entity.Project_Child_Site;
import monitor.projectConfig.bean.entity.Project_DXInfo;
import monitor.projectConfig.bean.entity.T_Project_Zj;
import monitor.projectConfig.dao.IProjectChildSiteDao;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:ProjectChildSiteDaoImpl
 * @dateTime: 2018-2-6 上午11:06:50
 * @Description: TODO  部位信息操作dao层
 * @version
 * @author: 黄月
 * @since JDK 1.7 History： Editor version Time Operation
 */
public class ProjectChildSiteDaoImpl implements IProjectChildSiteDao {

	/**
	 * closed:(关闭连接). TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	 * @author: 唐青
	 * @dateTime: 2018-2-6 下午2:54:35
	 * @param rs
	 * @param ps
	 *            void
	 * @since JDK 1.7
	 */
	private void closed(ResultSet rs, PreparedStatement ps) {
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

	private PreparedStatement ps = null;

	/**
	 * addChildSite:新增部位
	 * @author 黄月
	 * @date 2018-2-6 上午11:40:18
	 * @param site
	 */
	@Override
	public void addChildSite(Connection conn, Project_Child_Site site)
			 {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PROJECT_CHILD_SITE(SITE_SQU,BWMC,AZDG,BWBG,GJLX,CHILD_SQU,SITENUM) ");
		sql.append(" VALUES(SYS_GUID(),?,?,?,?,?,?)");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, site.getBwmc());
			ps.setDouble(2, site.getAzdg());
			ps.setDouble(3, site.getBwbg());
			ps.setInt(4, site.getGjlx());
			ps.setString(5, site.getChild_squ());
			ps.setString(6, site.getSiteNum());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closed(null, ps);
		}
	}

	/**
	 * delChildSite:接口实现
	 * @author 唐青
	 * @date 2018-2-6 下午2:38:13
	 * @param siteSqu
	 * @throws SQLException
	 */
	@Override
	public void delChildSite(Connection conn, String siteSqu)
			throws SQLException {
		String sql = "DELETE FROM PROJECT_CHILD_SITE WHERE SITE_SQU = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, siteSqu);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			closed(null, ps);
		}
	}

	/**
	 * updateChildSite:修改部位信息
	 * @author 唐青
	 * @date 2018-2-6 下午2:38:13
	 * @param siteSqu
	 * @throws SQLException
	 */
	@Override
	public void updateChildSite(Connection conn, Project_Child_Site site)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PROJECT_CHILD_SITE SET BWMC=?,AZDG=?,BWBG=?,GJLX=?,SITENUM=? ");
		sql.append(" ,UPDATEDATE=SYSDATE WHERE SITE_SQU=?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, site.getBwmc());
			ps.setDouble(2, site.getAzdg());
			ps.setDouble(3, site.getBwbg());
			ps.setInt(4, site.getGjlx());
			ps.setString(5, site.getSiteNum());
		/*	ps.setString(5, site.getGdcz());
			ps.setString(6, site.getGdlx());
			ps.setString(7, site.getGdgg());*/
			ps.setString(6, site.getSite_squ());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("修改部位信息出错！"+ e.getMessage());
		} finally {
			closed(null, ps);
		}
	}

	/**
	 * listChildSite:加载部位列表
	 * @author 唐青
	 * @date 2018-2-6 下午2:38:13
	 * @param siteSqu
	 * @throws SQLException
	 */
	@Override
	public List<Project_Child_Site> listChildSite(Connection conn,
			String childSqu) throws SQLException {
		List<Project_Child_Site> list = new ArrayList<Project_Child_Site>();
		;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SITE_SQU,SITENUM, BWMC,AZDG,BWBG,GJLX,TO_CHAR(CREATEDATE,'YYYY/MM/DD HH24:mi:ss') AS CREATEDATE,TO_CHAR(UPDATEDATE,'YYYY/MM/DD HH24:mi:ss') AS UPDATEDATE,");
		sql.append(" CHILD_SQU FROM PROJECT_CHILD_SITE WHERE CHILD_SQU = '").append(childSqu).append("' ORDER BY UPDATEDATE DESC ");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			Project_Child_Site site = null;
			while (rs.next()) {
				site = new Project_Child_Site();
				site.setSite_squ(rs.getString("SITE_SQU"));
				site.setSiteNum(rs.getString("SITENUM"));
				site.setBwmc(rs.getString("BWMC"));
				site.setAzdg(rs.getDouble("AZDG"));
				site.setBwbg(rs.getDouble("BWBG"));
				site.setGjlx(rs.getInt("GJLX"));
				site.setCreateDate(rs.getString("CREATEDATE"));
				site.setUpdateDate(rs.getString("UPDATEDATE"));
				site.setChild_squ(rs.getString("CHILD_SQU"));
				list.add(site);
			}
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			closed(rs, ps);
		}

		return list;
	}

	/**
	 * addDxInfo:添加支架
	 * @author 唐青
	 * @date 2018-2-6 下午2:38:13
	 * @param siteSqu
	 * @throws SQLException
	 */
	@Override
	public void addDxInfo(Connection conn, T_Project_Zj proZj) throws SQLException
			{
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO T_PROJECT_ZJ (SQU,PSQU,ZJSQU, SL,GXLXSQU,GXCZSQU,GXGGSQU)");
		sql.append(" VALUES(SYS_GUID(),?,?,1,?,?,?)");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, proZj.getPsqu());
			ps.setString(2, proZj.getZjsqu());
			ps.setString(3, proZj.getGxlxsqu());
			ps.setString(4, proZj.getGxczsqu());
			ps.setString(5, proZj.getGxggsqu());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			closed(null, ps);
		}
	}

	/**
	 * delChildSite:根据id删除部位支架中间表支架
	 * @author 唐青
	 * @date 2018-2-6 下午2:38:13
	 * @param siteSqu
	 * @throws SQLException
	 */
	@Override
	public void delDxInfo(Connection conn, String proZjSqu) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM T_PROJECT_ZJ WHERE SQU = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, proZjSqu);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			closed(null, ps);
		}
	}

	/**
	 * updateDxInfo:接口实现
	 * @author 唐青
	 * @date 2018-2-6 下午2:38:13
	 * @param siteSqu
	 * @throws SQLException
	 */
	@Override
	public void updateDxInfo(Connection conn, T_Project_Zj proZj)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE T_PROJECT_ZJ SET SL = ? WHERE SQU = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, proZj.getSl());
			ps.setString(2, proZj.getSqu());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			closed(null, ps);
		}

	}

	/**
	 * listDxInfo:查询该部位下支架列表
	 * @author 唐青
	 * @date 2018-2-6 下午2:38:13
	 * @param siteSqu
	 * @throws SQLException
	 */
	@Override
	public List<Project_DXInfo> listDxInfo(Connection conn, String siteSqu, String type)
			throws SQLException {
		List<Project_DXInfo> list = new ArrayList<Project_DXInfo>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT Z.SQU,Z.PSQU,D.DXSQU,D.GDLX,D.AZFS,D.ZJXS,D.DXMC,D.ZP,D.CJSJ,D.LXCS,Z.SL,D.ZYM,Z.SERIALNUMBER ");
		sql.append(" FROM PROJECT_DXINFO D,T_PROJECT_ZJ Z WHERE D.DXSQU=Z.ZJSQU AND Z.PSQU = '"+ siteSqu + "'");
		
		if (!"".equals(type)) {
			sql.append(" AND D.ZYM = '" + type + "'");
		}
		ResultSet rs = null;
		try {
			Project_DXInfo dxInfo = null;
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				//查询支架信息
				dxInfo = new Project_DXInfo();
				dxInfo.setSqu(rs.getString("SQU"));
				dxInfo.setSite_squ(rs.getString("PSQU"));
				dxInfo.setDxsqu(rs.getString("DXSQU"));
				dxInfo.setZjxs(rs.getString("ZJXS"));
				dxInfo.setDxmc(rs.getString("DXMC"));
				dxInfo.setZp(rs.getString("ZP"));
				dxInfo.setLxcs(rs.getString("LXCS"));
				dxInfo.setSl(rs.getInt("SL"));
				dxInfo.setZym(rs.getString("ZYM"));
				dxInfo.setSerialNumber(rs.getString("SERIALNUMBER"));
				
				list.add(dxInfo);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return list;
	}

	/**
	  * selectCountChilSite:根据部位id查询该部位下支架总数
	  * @author 黄月
	  * @date  2018-2-8 下午12:11:55
	  * @param conn
	  * @param siteSqu
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public Integer selectCountChilSite(Connection conn,String siteSqu) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(*) FROM PROJECT_DXINFO D,T_PROJECT_ZJ Z WHERE D.DXSQU = Z.ZJSQU AND Z.PSQU = '"+ siteSqu + "'");
		int sum = 0;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(rs, ps);
		}
		return sum;
	}
	
	/**
	  * countChilSite:根据子项目id统计部位总数
	  * @author 黄月
	  * @date  2018-2-8 上午10:57:23
	  * @param conn
	  * @param childSqu
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public Integer countChildSiteByChildSqu(Connection conn, String childSqu)
			throws SQLException {
		int siteCount = 0;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT COUNT(*) FROM PROJECT_CHILD_SITE WHERE CHILD_SQU='"
							+ childSqu + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	
	/**
	  * countChildSiteByProjectSqu:接口实现
	  * @author 黄月
	  * @date  2018-2-8 下午7:00:24
	  * @param conn
	  * @param childSqu
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public Integer countChildSiteByProjectSqu(Connection conn, String projectSqu)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(*) FROM PROJECT_CHILD_SITE WHERE CHILD_SQU IN ( ");
		sql.append(" SELECT CHILD_SQU FROM PROJECT_CHILD WHERE PROJECT_SQU = '" + projectSqu + "') ");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	
	@Override
	public Integer countHolderByProjectSqu(Connection conn, String projectSqu)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SUM(SL) FROM T_PROJECT_ZJ WHERE PSQU IN ( ");
		sql.append(" SELECT SITE_SQU FROM PROJECT_CHILD_SITE WHERE CHILD_SQU IN ( ");
		sql.append(" SELECT CHILD_SQU FROM PROJECT_CHILD WHERE PROJECT_SQU = '" + projectSqu + "') )");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	
	@Override
	public Integer countHolderByChildSqu(Connection conn, String childSqu)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SUM(SL) FROM T_PROJECT_ZJ WHERE PSQU IN ( ");
		sql.append(" SELECT SITE_SQU FROM PROJECT_CHILD_SITE WHERE CHILD_SQU IN ('" + childSqu + "') )");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	
	/**
	  * countHolderBySiteSqu:接口实现
	  * @date  2018-5-29 下午6:01:55
	  * @param conn
	  * @param siteSqu
	  * @param type
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public Integer countHolderBySiteSqu(Connection conn, String siteSqu, String type)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT SUM(SL) FROM T_PROJECT_ZJ WHERE PSQU IN ('" + siteSqu + "') ");
		if (!"".equals(type)) {
			sql.append(" AND ZJSQU IN ( SELECT DXSQU FROM PROJECT_DXINFO WHERE ZYM = '" + type + "' )");
		}
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	
	@Override
	public Integer countPartsByProjectSqu(Connection conn, String projectSqu)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT SUM(SL*ZJSL) COU FROM PROJECT_DXMXB D,( ");
		sql.append(" SELECT Z.ZJSQU,Z.SL ZJSL FROM T_PROJECT_ZJ Z WHERE  Z.PSQU IN ( ");
		sql.append(" SELECT SITE_SQU FROM PROJECT_CHILD_SITE WHERE CHILD_SQU IN ( ");
		sql.append(" SELECT CHILD_SQU FROM PROJECT_CHILD WHERE PROJECT_SQU = '" + projectSqu + "') ");
		sql.append(" ) ) Z1 ");
		sql.append(" WHERE D.DXSQU = Z1.ZJSQU ");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	@Override
	public Integer countPartsByChildSqu(Connection conn, String childSqu)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT SUM(SL*ZJSL) COU FROM PROJECT_DXMXB D,( ");
		sql.append(" SELECT Z.ZJSQU,Z.SL ZJSL FROM T_PROJECT_ZJ Z WHERE  Z.PSQU IN ( ");
		sql.append(" SELECT SITE_SQU FROM PROJECT_CHILD_SITE WHERE CHILD_SQU IN ('" + childSqu + "') ");
		sql.append(" ) ) Z1 WHERE D.DXSQU = Z1.ZJSQU ");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	
	/**
	  * countPartsBySiteSqu:接口实现
	  * @date  2018-5-29 下午6:07:37
	  * @param conn
	  * @param siteSqu
	  * @param type
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public Integer countPartsBySiteSqu(Connection conn, String siteSqu, String type)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT SUM(SL*ZJSL) COU FROM PROJECT_DXMXB D,( ");
		sql.append(" SELECT Z.ZJSQU,Z.SL ZJSL FROM T_PROJECT_ZJ Z WHERE Z.PSQU IN ('" + siteSqu + "')  ");
		if (!"".equals(type)) {
			sql.append(" AND ZJSQU IN ( SELECT DXSQU FROM PROJECT_DXINFO WHERE ZYM = '" + type + "') ");
		}
		sql.append(" ) Z1 WHERE D.DXSQU = Z1.ZJSQU ");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}
	
	@Override
	public Integer countPartsByHolderSqu(Connection conn, String holderSqu)
			throws SQLException {
		int siteCount = 0;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT SUM(SL) COU FROM PROJECT_DXMXB D WHERE D.DXSQU = '" + holderSqu + "' ");
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				siteCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			closed(rs, ps);
		}
		return siteCount;
	}

	@Override
	public String selectChilSiteSqu(Connection conn, String childSqu)
			throws SQLException {
		StringBuilder siteSqu = new StringBuilder();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT SITE_SQU FROM PROJECT_CHILD_SITE WHERE  CHILD_SQU='"+childSqu+"'");
			rs = ps.executeQuery();
			while(rs.next()){
				siteSqu.append("'"+rs.getString(1)+"',");
			}
			
			closed(rs, ps);
		}finally{
			closed(rs, ps);
		}
		return siteSqu.toString();
	}

	@Override
	public String countDxInfo(Connection conn, String siteSqu)
			throws SQLException {
		String dxInfoCount = new String();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(" SELECT SUM(Z.SL)  CON FROM  T_PROJECT_ZJ Z WHERE Z.PSQU IN ("+siteSqu+")");
			rs = ps.executeQuery();
			while(rs.next()){
				dxInfoCount = rs.getString(1);
			}			
		} finally{
			closed(rs, ps);
		}
		return dxInfoCount;
	}

	@Override
	public String selectDxInfoSqu(Connection conn, String siteSqu)
			throws SQLException {
		StringBuilder dxInfoSqu = new StringBuilder();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT ZJSQU FROM T_PROJECT_ZJ Z WHERE Z.PSQU IN ("+siteSqu+") GROUP BY ZJSQU");
			rs = ps.executeQuery();
			while(rs.next()){
				dxInfoSqu.append("'"+rs.getString(1)+"',");
			}			
		} finally{
			closed(rs, ps);
		}
		return dxInfoSqu.toString();
	}

	@Override
	public String countParts(Connection conn, String siteSqu)
			throws SQLException {
		String partsCount = new String();
		StringBuilder sql = new StringBuilder();
		ResultSet rs = null;
				
		try {
			sql.append("SELECT SUM(SL*ZJSL) COU  FROM  PROJECT_DXMXB D,(");
			sql.append(" SELECT Z.ZJSQU,Z.SL ZJSL FROM  T_PROJECT_ZJ Z WHERE  Z.PSQU IN("+siteSqu+")) Z1");
			sql.append("WHERE D.DXSQU = Z1.ZJSQU ");
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				partsCount = rs.getString(1);
			}			
		} finally{
			closed(rs, ps);
		}
		return partsCount;
	}

	/**
	  * listHolderNotInSitePageInfo:获取该部位下不存在的支架分页列表
	  * @author 黄月
	  * @date  2018-2-8 下午2:19:25
	  * @param pageVo
	  * @param conn
	  * @param site_squ
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public List<Project_DXInfo> listHolderNotInSitePageInfo(PageInfoVo pageVo,
			Connection conn, String site_squ) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM ( SELECT ROWNUM RN,B.* FROM PROJECT_DXINFO B WHERE B.DXSQU NOT IN (SELECT ZJSQU FROM T_PROJECT_ZJ WHERE PSQU = '" + site_squ + "')");
		sql.append(" ) WHERE RN >= ? AND RN < ? ");
		List<Project_DXInfo> list = new ArrayList<Project_DXInfo>();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, pageVo.getStartIndex());
			ps.setInt(2, pageVo.getEndIndex());
			rs = ps.executeQuery();
			
			Project_DXInfo holder = null;
			while (rs.next()) {
				holder = new Project_DXInfo();
				holder.setDxmc(rs.getString("DXMC"));
				holder.setDxsqu(rs.getString("DXSQU"));
				holder.setAzfs(rs.getString("AZFS"));
				holder.setGdlx(rs.getString("GDLX"));
				holder.setZjxs(rs.getString("ZJXS"));
				holder.setZp(rs.getString("ZP"));
				
				ResultSet rs1 = null;
				PreparedStatement ps1 = null;
				String result = "";
				if (!"".equals(rs.getString("GDLX")) && rs.getString("GDLX") != null) {
					String[] arr = rs.getString("GDLX").split(",");
					String lxStr = "";
					if (arr.length > 0) {
						for (String string : arr) {
							lxStr += "'" + string + "',";
						}
					}
					if (lxStr.length() > 0 ) {
						lxStr = lxStr.substring(0, lxStr.length() - 1);
					}
					ps1 = conn.prepareStatement(" SELECT XH FROM PROJECT_GDLX WHERE SQU IN (" + lxStr + ") ");
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						result += rs1.getString("XH") + ",";
					}
					if (result.length() > 0) {
						result = result.substring(0, result.length() - 1);
					}
					
				}
				holder.setGdlx_name(result);
				result = "";
					
				if (!"".equals(rs.getString("AZFS")) && rs.getString("AZFS") != null) {
					String[] arr2 = rs.getString("AZFS").split(",");
					String azStr = "";
					if (arr2.length > 0) {
						for (String string : arr2) {
							azStr += "'" + string + "',";
						}
					}
					if (azStr.length() > 0 ) {
						azStr = azStr.substring(0, azStr.length() - 1);
					}
					ps1.close();
					ps1 = conn.prepareStatement(" SELECT MS FROM PROJECT_GDLX WHERE SQU IN (" + azStr + ") ");
					rs1 = ps1.executeQuery();
					while (rs1.next()) {
						result += rs1.getString("MS") + ",";
					}
					if (result.length() > 0) {
						result = result.substring(0, result.length() - 1);
					}
				}
				holder.setAzfs_name(result);
				
				list.add(holder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(rs, ps);
		}
		return list;
	}

	/**
	  * countHolderNotInSite:获取该部位下没有的支架类型总数
	  * @author 黄月
	  * @date  2018-2-8 下午2:19:25
	  * @param conn
	  * @param site_squ
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public Integer countHolderNotInSite(Connection conn, String site_squ) throws SQLException {
		int sum = 0;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(" SELECT COUNT(DXSQU) FROM PROJECT_DXINFO WHERE DXSQU NOT IN (SELECT ZJSQU FROM T_PROJECT_ZJ WHERE PSQU='" + site_squ + "') ");
			rs = ps.executeQuery();
			if (rs.next()) {
				sum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(rs, ps);
		}
		return sum;
	}

	@Override
	public List<Map<String, String>> selectLbxx(Connection conn)
			throws SQLException {
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		try {
			ps = conn.prepareStatement(" SELECT LBMC FROM PROJECT_WZZDB WHERE DMLB='LBXS' ");
			rs = ps.executeQuery();
			
			if (rs.next()) {
				map = new HashMap<String, String>();
				map.put("LBMC", rs.getNString("LBMC"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e.getMessage());
		} finally {
			closed(rs, ps);
		}
		return null;
	}

	/**
	  * updateNumberBySqu:接口实现
	  * @author 黄月
	  * @date  2018-6-2 下午3:38:04
	  * @param squ
	  * @param number
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public void updateNumberBySqu(Connection conn, T_Project_Zj proZj) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE T_PROJECT_ZJ SET SERIALNUMBER = ? WHERE SQU = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, proZj.getSerialNumber());
			ps.setString(2, proZj.getSqu());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(null, ps);
		}
	}

	/**
	  * getT_Project_ZjById:根据id查询支架部位关联表
	  * @author 黄月
	  * @date  2018-6-10 下午2:56:55
	  * @param conn
	  * @param id
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public T_Project_Zj getT_Project_ZjById(Connection conn, String id) throws SQLException {
		ResultSet rs = null;
		T_Project_Zj t = new T_Project_Zj();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM T_PROJECT_ZJ WHERE SQU = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				t.setLb_num(rs.getInt("LB_NUM"));
				t.setNum(rs.getInt("NUM"));
				t.setPsqu(rs.getString("PSQU"));
				t.setSerialNumber(rs.getString("SERIALNUMBER"));
				t.setService_system(rs.getString("SERVICE_SYSTEM"));
				t.setSl(rs.getInt("SL"));
				t.setSqu(rs.getString("SQU"));
				t.setVb_num(rs.getInt("VB_NUM"));
				t.setZjsqu(rs.getString("ZJSQU"));
				t.setGxczsqu(rs.getString("GXCZSQU"));
				t.setGxggsqu(rs.getString("GXGGSQU"));
				t.setGxlxsqu(rs.getString("GXLXSQU"));
				t.setHolderImg(rs.getString("HOLDERIMG"));
				t.setLbImg(rs.getString("LBIMG"));
				t.setVbImg(rs.getString("VBIMG"));
			}
			return t;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(null, ps);
		}
	}

	/**
	  * updateReportOfProject_Zj:接口实现
	  * @author 黄月
	  * @date  2018-6-21 下午8:24:14
	  * @param conn
	  * @param proZj 
	 * @throws SQLException 
	*/
	@Override
	public void updateReportOfProject_Zj(Connection conn, T_Project_Zj proZj) throws SQLException {
		StringBuilder params = new StringBuilder();
		if (!"".equals(proZj.getVbImg())) {
			params.append(",VBIMG = '" + proZj.getVbImg() + "'");
		}
		if (!"".equals(proZj.getLbImg())) {
			params.append(",LBIMG = '" + proZj.getLbImg() + "'");
		}
		if (!"".equals(proZj.getHolderImg())) {
			params.append(",HOLDERIMG = '" + proZj.getHolderImg() + "'");
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE T_PROJECT_ZJ SET NUM = ?,SERVICE_SYSTEM = ?,VB_NUM = ?,LB_NUM = ?").append(params).append(" WHERE SQU = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, proZj.getNum());
			ps.setString(2, proZj.getService_system());
			ps.setInt(3, proZj.getVb_num());
			ps.setInt(4, proZj.getLb_num());
			ps.setString(5, proZj.getSqu());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(null, ps);
		}
	}

	/**
	  * getProject_Child_SiteBySqu:接口实现
	  * @author 黄月
	  * @date  2018-6-23 下午3:00:38
	  * @param conn
	  * @param squ
	  * @return 
	 * @throws SQLException 
	*/
	@Override
	public Project_Child_Site getProject_Child_SiteBySqu(Connection conn,
			String squ) throws SQLException {
		ResultSet rs = null;
		Project_Child_Site t = new Project_Child_Site();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM PROJECT_CHILD_SITE WHERE SITE_SQU = ?");
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, squ);
			rs = ps.executeQuery();
			if (rs.next()) {
				t.setAzdg(rs.getDouble("Azdg"));
				t.setBwbg(rs.getDouble("Bwbg"));
				t.setBwmc(rs.getString("Bwmc"));
				t.setChild_squ(rs.getString("Child_squ"));
				t.setCreateDate(rs.getString("CreateDate"));
				t.setGjlx(rs.getInt("Gjlx"));
				t.setSite_squ(rs.getString("Site_squ"));
				t.setSiteNum(rs.getString("SiteNum"));
				t.setUpdateDate(rs.getString("UpdateDate"));
			}
			return t;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			closed(null, ps);
		}
	}
	
	

}
