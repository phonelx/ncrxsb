package monitor.projectConfig.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import monitor.projectConfig.dao.ICalculationReportDao;

/**
 *@ClassName 支架报告计算
 *@dataTime 2018-4-9-下午2:39:54
 *@version
 *@author:唐青
 *@since
 */
public class CalculationReportDaoImpl implements ICalculationReportDao{

	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@SuppressWarnings("unused")
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
	 * 根据项目SQU查询项目信息
	  * selectProjectInfo:接口实现
	  * @author 唐青
	  * @date  2018-4-9 下午3:07:02
	  * @param prosqu 项目squ
	  * @return
	  * @throws SQLException
	 */
	@Override
	public Map<String, String> selectProjectInfo(Connection conn , String prosqu)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT XMBH,XMDZ,XMLX,XMMC,XMMJ,P1.LBMC DZSFLD,P2.LBMC DZJSD,P3.LBMC DZLX, ");
		sql.append(" ALPHAMAX FROM PROJECT ,PROJECT_WZZDB P1,PROJECT_WZZDB P2,PROJECT_WZZDB P3 ");
		sql.append(" WHERE P1.SQU = DZSFLD AND P2.SQU = DZJSD AND P3.SQU= DZLX AND PROJECT_SQU='"+prosqu+"'");
		Map<String, String> map = new HashMap<String, String>();
		//map = ;
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
		
			while(rs.next()){
				map.put("XMBH", rs.getString("XMBH"));
				map.put("XMDZ", rs.getString("XMDZ"));
				map.put("XMLX", rs.getString("XMLX"));
				map.put("XMMC", rs.getString("XMMC"));
				map.put("XMMJ", rs.getString("XMMJ"));
				map.put("DZSFLD", rs.getString("DZSFLD"));
				map.put("DZJSD", rs.getString("DZJSD"));
				map.put("DZLX", rs.getString("DZLX"));
				map.put("ALPHAMAX", rs.getString("ALPHAMAX"));			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			closed(rs, ps);
		}
		
		return map;
	}

	

	/**
	 * 查询子单位工程信息
	  * selectChildInfo:接口实现
	  * @author 唐青
	  * @date  2018-4-13 上午9:45:43
	  * @param conn
	  * @param childsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public Map<String, String> selectChildInfo(Connection conn, String childsqu)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT JZGD FROM PROJECT_CHILD WHERE CHILD_SQU='"+childsqu+"' ");
		Map<String, String> map = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
		
			while(rs.next()){
				map.put("JZGD", rs.getString("JZGD"));
						
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			closed(rs, ps);
		}
		
		return map;
	}

	/**
	 * 查询部位信息
	  * selectSiteInfo:接口实现
	  * @author 唐青
	  * @date  2018-4-13 上午10:19:06
	  * @param conn
	  * @param sitesqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public Map<String, String> selectSiteInfo(Connection conn, String sitesqu)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT BWMC,AZDG,BWBG,GJLX  FROM PROJECT_CHILD_SITE WHERE SITE_SQU='"+sitesqu+"' ");
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
		
			while(rs.next()){
				map.put("BWMC", rs.getString("BWMC"));
				map.put("AZDG", rs.getString("AZDG"));
				map.put("BWBG", rs.getString("BWBG"));
				map.put("GJLX", rs.getString("GJLX"));
						
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			closed(rs, ps);
		}
		
		return map;
	}

	/**
	 * 查询支架信息
	  * selectZjInfo:接口实现
	  * @author 唐青
	  * @date  2018-4-13 上午10:19:16
	  * @param conn
	  * @param zjsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public Map<String, String> selectZjInfo(Connection conn, String zjsqu)
			throws SQLException {
		return null;
	}
}
