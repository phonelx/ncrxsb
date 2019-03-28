package monitor.goodsHouse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monitor.common.dao.BaseDAO;
import monitor.common.util.UpdateCalculate;
import monitor.goodsHouse.bean.entity.Cpmxb;
import monitor.goodsHouse.bean.entity.Zhbj;
import monitor.goodsHouse.bean.vo.CpmxbVo;
import monitor.goodsHouse.bean.vo.ZhbjVo;
import monitor.goodsHouse.dao.IStentsDao;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName
 * @dataTime 2017-10-14-下午2:41:06
 * @version
 * @author:唐青
 * @since
 */
public class StentsDaoImpl extends BaseDAO implements IStentsDao {

	private void closeCon(Connection conn, PreparedStatement pstmt, ResultSet rs)
			throws SQLException {
		// 关闭连接
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 加载部件列表 getStentsMenu:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:12:50
	 * @param squ
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Map<String, String>> getStentsMenu(String squ, String type)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 获取连接
		try {
			conn = datasource.getConnection();
			StringBuffer sql = new StringBuffer();
			

			// 菜单查询条件判断
			if (squ == null || squ.equals("")) {
				squ = "-1";
			}
			sql.append("SELECT * FROM PROJECT_WZZDB T WHERE PARENTSQU = '" + squ
					+ "' AND DMLB='" + type + "' ORDER BY T.DMLB ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("SQU", rs.getString("SQU"));
				map.put("DMLB", rs.getString("DMLB"));
				map.put("LBMC", rs.getString("LBMC"));
				map.put("PARENTSQU", rs.getString("PARENTSQU"));
				map.put("JD", rs.getString("JD"));
				map.put("ZHDM", rs.getString("ZHDM"));
				// map.put("SSMC", rs.getString("SSMC"));
				list.add(map);
			}
		} finally{
			this.closeCon(conn, pstmt, rs);
		}
		
		
		return list;
	}

	/**
	 * 获取产品部件 selectChildCp:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:13:17
	 * @param page
	 * @param squ
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	@Override
	public PageInfoVo selectChildCp(PageInfoVo page, String squ, String key)
			throws SQLException {
		List<CpmxbVo> list = new ArrayList<CpmxbVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			String query = "";
			if (key.equals("1")) {
				query = "";
			} else {
				query = " AND ( CPBM LIKE '%" + key + "%' OR CYMC LIKE '%" + key
						+ "%' OR BJXH  LIKE '%" + key + "%')";
			}
			sql.append(" SELECT COUNT(*) FROM (");
			sql.append(" SELECT X.* FROM PROJCRT_CPMXB X WHERE 1=1 AND ");
			sql.append(" ( X.CPXH='" + squ + "' OR X.CPXL='" + squ
					+ "' OR X.CPZM='" + squ + "' OR X.CPTZ='" + squ + "')" + query
					+ ")");
			/* System.out.println(sql.toString()); */
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			// 获取总数
			if (rs.next()) {
				page.setTotal(rs.getInt(1));
			}
			this.closeCon(null, pstmt, rs);
			// 开始数
			int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
			StringBuffer sbsql = new StringBuffer();

			sbsql.append(" SELECT * FROM ( SELECT A.* ,ROWNUM RN FROM ( ");
			sbsql.append(" SELECT X.*");
			sbsql.append(" FROM PROJCRT_CPMXB X WHERE 1=1 AND ");
			sbsql.append(" ( X.CPXH='" + squ + "' OR X.CPXL='" + squ
					+ "' OR X.CPZM='" + squ + "' OR X.CPTZ='" + squ + "')" + query
					+ ")A)  ");
			sbsql.append("WHERE RN<" + (startIndex + page.getPageSize())
					+ " AND RN>=" + startIndex);
			// System.out.println();
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			CpmxbVo cp = null;
			while (rs.next()) {
				cp = new CpmxbVo();
				cp.setCpSQU(rs.getString("SQU"));
				cp.setCPBM(rs.getString("CPBM"));
				cp.setCYMC(rs.getString("CYMC"));
				cp.setBJXH(rs.getString("BJXH"));
				cp.setJLDW(rs.getString("JLDW"));
				cp.setCBDJ(rs.getString("CBDJ"));
				cp.setCpPARENTSQU(rs.getString("PARENTSQU"));
				cp.setEDHL(rs.getString("EDHL"));
				cp.setCPXH(rs.getString("CPXH"));
				cp.setCPXL(rs.getString("CPXL"));
				cp.setCPZM(rs.getString("CPZM"));
				cp.setCPTZ(rs.getString("CPTZ"));
				cp.setLXCS(rs.getString("LXCS"));
				cp.setZP(rs.getString("ZP"));
				list.add(cp);
			}
			page.setRows(list);
		} finally{
			this.closeCon(conn, pstmt, rs);
		}
		
		
		return page;
	}

	/**
	 * 添加产品部件 addCp:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:14:43
	 * @param cpmxb
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int addCp(Cpmxb cpmxb) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		int i = 0;
		String cpxh = cpmxb.getCPXH();
	
		cpmxb.setCPXH(cpxh);
		cpmxb.setCPTZ(cpxh);
		cpmxb.setCPZM(cpxh);
		cpmxb.setCPXL(cpxh);
		try {
			conn = datasource.getConnection();
		if (cpmxb.getZP().equals("1")) {
				
				String sql = "INSERT INTO  PROJCRT_CPMXB (CPBM,CYMC,BJXH,JLDW,PARENTSQU,EDHL,CPXH,CPXL,CPZM,CPTZ,ZP,CBDJ,AZFSDM,LXCS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cpmxb.getCPBM());
				pstmt.setString(2, cpmxb.getCYMC());
				pstmt.setString(3, cpmxb.getBJXH());
				pstmt.setString(4, cpmxb.getJLDW());
				pstmt.setString(5, null);
				pstmt.setString(6, cpmxb.getEDHL());
				pstmt.setString(7, cpmxb.getCPXH());
				pstmt.setString(8, cpmxb.getCPXL());
				pstmt.setString(9, cpmxb.getCPZM());
				pstmt.setString(10, cpmxb.getCPTZ());
				pstmt.setString(11, null);
				pstmt.setString(12, cpmxb.getCBDJ());
				pstmt.setString(13, cpmxb.getAZFSDM());
				pstmt.setString(14, cpmxb.getLXCS());
				i = pstmt.executeUpdate();
			} else {
			
				String sql = "INSERT INTO  PROJCRT_CPMXB (CPBM,CYMC,BJXH,JLDW,PARENTSQU,EDHL,CPXH,CPXL,CPZM,CPTZ,ZP,CBDJ,AZFSDM,LXCS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cpmxb.getCPBM());
				pstmt.setString(2, cpmxb.getCYMC());
				pstmt.setString(3, cpmxb.getBJXH());
				pstmt.setString(4, cpmxb.getJLDW());
				pstmt.setString(5, null);
				pstmt.setString(6, cpmxb.getEDHL());
				pstmt.setString(7, cpmxb.getCPXH());
				pstmt.setString(8, cpmxb.getCPXL());
				pstmt.setString(9, cpmxb.getCPZM());
				pstmt.setString(10, cpmxb.getCPTZ());
				pstmt.setString(11, cpmxb.getZP());
				pstmt.setString(12, cpmxb.getCBDJ());
				pstmt.setString(13, cpmxb.getAZFSDM());
				pstmt.setString(14, cpmxb.getLXCS());
				i = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		} finally {
			this.closeCon(conn, pstmt, null);
		}
		return i;
	}

	public Map<String, String> getCpxl(String squ) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String cpxl = null;
		String sql1 = "SELECT PARENTSQU FROM PROJECT_WZZDB WHERE SQU='" + squ
				+ "'";
	
		Map<String, String> map = null;
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("PARENTSQU", rs.getString("PARENTSQU"));

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeCon(conn, pstmt, rs);
		}

		return map;
	}

	/**
	 * 修改产品部件 editCp:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:22:21
	 * @param cpmxb
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int editCp(Cpmxb cpmxb) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		// System.out.println();
		String sql = "";
		int i = 0;
		try {
			if (cpmxb.getZP().equals("1")) {
				sql = "UPDATE PROJCRT_CPMXB SET CPBM=?,CYMC=?,BJXH=?,JLDW=?,EDHL=?,CBDJ=? ,LXCS=? WHERE SQU=?";
				conn = datasource.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cpmxb.getCPBM());
				pstmt.setString(2, cpmxb.getCYMC());
				pstmt.setString(3, cpmxb.getBJXH());
				pstmt.setString(4, cpmxb.getJLDW());
				pstmt.setString(5, cpmxb.getEDHL());
				pstmt.setString(6, cpmxb.getCBDJ());
				pstmt.setString(7, cpmxb.getLXCS());
				pstmt.setString(8, cpmxb.getSQU());

				i = pstmt.executeUpdate();
			} else {
				sql = "UPDATE PROJCRT_CPMXB SET CPBM=?,CYMC=?,BJXH=?,JLDW=?,EDHL=?,CBDJ=?,ZP=?,LXCS=?WHERE SQU=?";
				conn = datasource.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cpmxb.getCPBM());
				pstmt.setString(2, cpmxb.getCYMC());
				pstmt.setString(3, cpmxb.getBJXH());
				pstmt.setString(4, cpmxb.getJLDW());
				pstmt.setString(5, cpmxb.getEDHL());
				pstmt.setString(6, cpmxb.getCBDJ());
				pstmt.setString(7, cpmxb.getZP());
				pstmt.setString(8, cpmxb.getLXCS());
				pstmt.setString(9, cpmxb.getSQU());

				i = pstmt.executeUpdate();
			}
			//修改零部件
			new UpdateCalculate().updateInfo(6, cpmxb.getSQU(),conn);
		} finally {
			this.closeCon(conn, pstmt, null);
		}
		return i;
	}

	/**
	 * 修改产品部件 deleteCp:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:22:35
	 * @param squ
	 * @throws SQLException
	 */
	@Override
	public int deleteCp(String squ) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = datasource.getConnection();
			String sq = "SELECT COUNT(*) FROM PROJECT_DXZHMXB_ZB WHERE BJSQU= '"
					+ squ + "'";
			pstmt = conn.prepareStatement(sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if(count == 0){
				String sqq = "SELECT COUNT(*) FROM PROJECT_DXMXB WHERE BJSQU= '"
						+ squ + "'";
				pstmt = conn.prepareStatement(sqq);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
			}
			this.closeCon(null, pstmt, rs);
			if (count == 0) {
				String sql = "DELETE PROJCRT_CPMXB  WHERE SQU=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, squ);

				pstmt.executeUpdate();
			}

		} finally {
			this.closeCon(conn, pstmt, rs);
		}
		return count;
	}

	/**
	 * 查询部件详情 selectStentsBySqu:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:22:45
	 * @param squ
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("null")
	@Override
	public List<Cpmxb> selectStentsBySqu(String squ) throws SQLException {
		List<Cpmxb> list = new ArrayList<Cpmxb>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			StringBuffer sbsql = new StringBuffer();

			sbsql.append("  SELECT B.SQU,B.CPBM,B.CYMC,B.BJXH,B.JLDW,B.CBDJ,B.EDHL,B.AZFSDM,B.LXCS ");
			sbsql.append("  FROM PROJCRT_CPMXB B ");
			sbsql.append("  WHERE 1=1 AND B.SQU='" + squ + "'");

			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			Cpmxb cp = new Cpmxb();
			String azfs = "";
			while (rs.next()) {
				cp.setSQU(rs.getString("SQU"));
				cp.setCPBM(rs.getString("CPBM"));
				cp.setCYMC(rs.getString("CYMC"));
				cp.setBJXH(rs.getString("BJXH"));
				cp.setJLDW(rs.getString("JLDW"));
				cp.setCBDJ(rs.getString("CBDJ"));
				cp.setEDHL(rs.getString("EDHL"));
				// cp.setAZFSDM(rs.getString("AZFSDM"));
				cp.setLXCS(rs.getString("LXCS"));
				// azfs = rs.getString("AZFSDM");
			}
			

			list.add(cp);
		}finally{
			this.closeCon(conn, pstmt, rs);
		}
		
		// page.setRows(list);
		
		return list;

	}

	/**
	 * 批量添加部件 addBatchStents:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:22:55
	 * @param list
	 * @throws SQLException
	 */
	@Override
	public void addBatchStents(List<Cpmxb> list) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO  PROJCRT_CPMXB (CPBM,CYMC,BJXH,JLDW,PARENTSQU,EDHL,CPXH,CPXL,CPZM,CPTZ,ZP,CBDJ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = datasource.getConnection();
			// 关闭事务自动提交
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				pstmt.setString(1, list.get(i).getCPBM());
				pstmt.setString(2, list.get(i).getCYMC());
				pstmt.setString(3, list.get(i).getBJXH());
				pstmt.setString(4, list.get(i).getJLDW());
				pstmt.setString(5, null);
				pstmt.setString(6, list.get(i).getEDHL());
				pstmt.setString(7, list.get(i).getCPXH());
				pstmt.setString(8, list.get(i).getCPXL());
				pstmt.setString(9, list.get(i).getCPZM());
				pstmt.setString(10, list.get(i).getCPTZ());
				pstmt.setString(11, null);
				pstmt.setString(12, list.get(i).getCBDJ());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
			// 语句执行完毕，提交本事务
			conn.commit();
		} catch (SQLException e) {
			if (conn != null)
				conn.rollback();
			throw new SQLException("批量导入失败！" + e.getMessage());
		} finally {
			this.closeCon(conn, pstmt, null);
		}

	}

	/**
	 * 查询组合部件 selectZhbj:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:23:07
	 * @param squ
	 * @return
	 * @throws SQLException
	 */
	@Override
	public PageInfoVo selectZhbj(PageInfoVo page, String squ, String key)
			throws SQLException {
		// System.out.println(11);
		List<Zhbj> list = new ArrayList<Zhbj>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			StringBuffer sql = new StringBuffer();
			String query = "";
			if (key.equals("1")) {
				query = " ";
			} else {
				query = " AND ( DXZHMC LIKE '%" + key + "%')";
			}
			sql.append("SELECT COUNT(*) FROM (");
			sql.append("  SELECT ZHSQU,GDLX,AZFS,ZJXS,DXZHMC,ZP FROM PROJECT_DXZHMXB WHERE 1=1 "
					+ query + ")");

			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			// 获取总数
			if (rs.next()) {
				page.setTotal(rs.getInt(1));
			}
			this.closeCon(null, pstmt, rs);
			// 开始数
			int startIndex = (page.getPageNumber() - 1) * page.getPageSize()
					+ 1;

			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT * FROM ( ");
			sbsql.append(" SELECT A.* ,ROWNUM RN FROM ( ");
			sbsql.append(" SELECT ZHSQU,GDLX,AZFS,ZJXS,DXZHMC,ZP FROM ");
			sbsql.append(" PROJECT_DXZHMXB WHERE 1=1 " + query + ") A) ");
			sbsql.append("WHERE RN<" + (startIndex + page.getPageSize())
					+ " AND RN>=" + startIndex);

			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			Zhbj zhbj = null;
			while (rs.next()) {
				zhbj = new Zhbj();
				zhbj.setZHSQU(rs.getString("ZHSQU"));
				//zhbj.setGDLX(rs.getString("GDLX"));
				zhbj.setAZFS(rs.getString("AZFS"));
				zhbj.setZJXS(rs.getString("ZJXS"));
				zhbj.setDXZHMC(rs.getString("DXZHMC"));
				zhbj.setZP(rs.getString("ZP"));
				
				String[] gdlx = rs.getString("GDLX").split(",");

				StringBuffer stgdlx = new StringBuffer();

				for (int j = 0, len = gdlx.length; j < len; j++) {
					PreparedStatement pstmt1 = null;
					ResultSet rs1 = null;
					String sqlgl = " SELECT XH FROM PROJECT_GDLX WHERE SQU='"
							+ gdlx[j] + "' AND FL='0'";

					pstmt1 = conn.prepareStatement(sqlgl);
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						stgdlx.append(rs1.getString("XH") + ",");
					}
					this.closeCon(null, pstmt1, rs1);
				}
				zhbj.setGDLX(stgdlx.toString());
				list.add(zhbj);
			}
			this.closeCon(null, pstmt, rs);

			/*for (int i = 0; i < list.size(); i++) {
				
				String[] gdlx = list.get(i).getGDLX().split(",");

				StringBuffer stgdlx = new StringBuffer();

				for (int j = 0, len = gdlx.length; j < len; j++) {
					PreparedStatement pstmt1 = null;
					ResultSet rs1 = null;
					String sqlgl = " SELECT XH FROM PROJECT_GDLX WHERE SQU='"
							+ gdlx[j] + "' AND FL='0'";

					pstmt1 = conn.prepareStatement(sqlgl);
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						stgdlx.append(rs.getString("XH") + ",");
					}
					this.closeCon(null, pstmt1, rs1);
				}
				list.get(i).setGDLXDM(stgdlx.toString());
			}*/

			page.setRows(list);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeCon(conn, pstmt, rs);
		}

		return page;
	}

	/**
	 * 查询组合部件菜单 selectZhbjInfo:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-26 下午4:51:29
	 * @param squ
	 * @return
	 * @throws SQLException
	 */
	public List<Zhbj> selectZhbjInfo(String squ) throws SQLException {
		// System.out.println(11);
		List<Zhbj> list = new ArrayList<Zhbj>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();

			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT ZHSQU,GDLX,AZFS,ZJXS,DXZHMC,ZP,LXCS FROM ");
			sbsql.append(" PROJECT_DXZHMXB ");
			//System.out.println(sbsql.toString());
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			Zhbj zhbj = null;
			while (rs.next()) {
				zhbj = new Zhbj();
				zhbj.setZHSQU(rs.getString("ZHSQU"));
				zhbj.setGDLX(rs.getString("GDLX"));
				zhbj.setAZFS(rs.getString("AZFS"));
				zhbj.setZJXS(rs.getString("ZJXS"));
				zhbj.setDXZHMC(rs.getString("DXZHMC"));
				zhbj.setZP(rs.getString("ZP"));
				zhbj.setLXCS(rs.getString("LXCS"));

				list.add(zhbj);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeCon(conn, pstmt, rs);
		}

		return list;
	}

	/**
	 * 添加组合部件 addZhbj:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:23:25
	 * @param zhbjVo
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	@Override
	public void addZhbj(ZhbjVo zhbjVo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			// conn.setAutoCommit(false);
			/*
			 * String sql = "select sys_guid() squ from dual";
			 * 
			 * pstmt = conn.prepareStatement(sql); rs = pstmt.executeQuery();
			 * String squ = ""; while(rs.next()){ squ = rs.getString(1); }
			 */

			this.closeCon(null, pstmt, rs);
			if (zhbjVo.getZP().equals("1")) {
				StringBuffer sbsql = new StringBuffer();
				sbsql.append(" INSERT INTO PROJECT_DXZHMXB (");
				sbsql.append(" GDLX,AZFS,ZJXS,DXZHMC,ZP,LXCS) VALUES(");
				sbsql.append(" ?,?,?,?,?,?) ");
				pstmt = conn.prepareStatement(sbsql.toString());
				pstmt.setString(1, zhbjVo.getGDLX());
				pstmt.setString(2, zhbjVo.getAZFS());
				pstmt.setString(3, zhbjVo.getZJXS());
				pstmt.setString(4, zhbjVo.getDXZHMC());
				pstmt.setString(5, null);
				pstmt.setString(6, zhbjVo.getLXCS());
				pstmt.executeUpdate();
				this.closeCon(null, pstmt, null);
			} else {
				StringBuffer sbsql = new StringBuffer();
				sbsql.append(" INSERT INTO PROJECT_DXZHMXB (");
				sbsql.append(" GDLX,AZFS,ZJXS,DXZHMC,ZP,LXCS) VALUES(");
				sbsql.append(" ?,?,?,?,?,?) ");
				pstmt = conn.prepareStatement(sbsql.toString());
				pstmt.setString(1, zhbjVo.getGDLX());
				pstmt.setString(2, zhbjVo.getAZFS());
				pstmt.setString(3, zhbjVo.getZJXS());
				pstmt.setString(4, zhbjVo.getDXZHMC());
				pstmt.setString(5, zhbjVo.getZP());
				pstmt.setString(6, zhbjVo.getLXCS());
				pstmt.executeUpdate();
				this.closeCon(null, pstmt, null);
			}

			/*
			 * StringBuffer sbsql1 = new StringBuffer();
			 * sbsql1.append(" INSERT INTO PROJECT_DXZHMXB_ZB (");
			 * sbsql1.append(" ZHSQU,BJSQU,SJ) VALUES(?,?,?)"); pstmt =
			 * conn.prepareStatement(sbsql1.toString()); pstmt.setString(1,
			 * squ); pstmt.setString(2, zhbjVo.getZBJSQU()); pstmt.setString(3,
			 * zhbjVo.getSJ()); pstmt.executeUpdate(); conn.commit();
			 */
		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			throw new SQLException("注册失败！" + e.getMessage());

		} finally {
			this.closeCon(conn, pstmt, null);
		}

	}

	/**
	 * 获取组合部件详情 selectZhbjXq:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:23:43
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	@Override
	public PageInfoVo selectZhbjXq(PageInfoVo page, String squ, String key)
			throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			StringBuffer sql = new StringBuffer();
			String query = "";
			if (key.equals("1")) {
				query = " ";
			} else {
				query = " AND (C.CPBM LIKE '%" + key + "%' OR C.CYMC LIKE '%"
						+ key + "%')";
			}
			sql.append("SELECT COUNT(*) FROM (");
			sql.append("  SELECT *  FROM PROJECT_DXZHMXB_ZB A,PROJECT_DXZHMXB B,PROJCRT_CPMXB C ");
			sql.append(" WHERE A.ZHSQU = B.ZHSQU AND C.SQU=A.BJSQU AND B.ZHSQU='"
					+ squ + "' " + query + ")");
			//
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			// 获取总数
			if (rs.next()) {
				page.setTotal(rs.getInt(1));
			}
			this.closeCon(null, pstmt, rs);

			// 开始数
			int startIndex = (page.getPageNumber() - 1) * page.getPageSize()
					+ 1;
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT * FROM ( ");
			sbsql.append(" SELECT H.* ,ROWNUM RN FROM ( ");
			sbsql.append(" SELECT A.ZBSQU,A.ZHSQU,A.BJSQU,A.SJ,B.GDLX,B.AZFS,B.ZJXS,B.DXZHMC,");
			sbsql.append(" B.ZP,C.CPBM,C.CYMC,C.BJXH,C.JLDW,C.CBDJ,C.EDHL,C.ZP BZP");
			sbsql.append(" FROM PROJECT_DXZHMXB_ZB A,PROJECT_DXZHMXB B,");
			sbsql.append(" PROJCRT_CPMXB C WHERE A.ZHSQU = B.ZHSQU AND C.SQU=A.BJSQU AND B.ZHSQU='"
					+ squ + "' " + query + ") H)");
			sbsql.append("WHERE RN<" + (startIndex + page.getPageSize())
					+ " AND RN>=" + startIndex);
			// System.out.println(sbsql.toString());
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("ZBSQU", rs.getString("ZBSQU"));
				map.put("ZHSQU", rs.getString("ZHSQU"));
				map.put("BJSQU", rs.getString("BJSQU"));
				//map.put("GDLX", rs.getString("GDLX"));
				map.put("AZFS", rs.getString("AZFS"));
				map.put("ZJXS", rs.getString("ZJXS"));
				map.put("DXZHMC", rs.getString("DXZHMC"));
				map.put("ZP", rs.getString("ZP"));
				map.put("CPBM", rs.getString("CPBM"));
				map.put("CYMC", rs.getString("CYMC"));
				map.put("BJXH", rs.getString("BJXH"));
				map.put("JLDW", rs.getString("JLDW"));
				map.put("CBDJ", rs.getString("CBDJ"));
				map.put("EDHL", rs.getString("EDHL"));
				map.put("BZP", rs.getString("BZP"));
				map.put("SJ", rs.getString("SJ"));
				map.put("FLA", "2");
				String[] gdlx = rs.getString("GDLX").split(",");

				StringBuffer stgdlx = new StringBuffer();

				for (int j = 0, len = gdlx.length; j < len; j++) {
					PreparedStatement pstmt1 = null;
					ResultSet rs1 = null;
					String sqlgl = " SELECT XH FROM PROJECT_GDLX WHERE SQU='"
							+ gdlx[j] + "' AND FL='0'";

					pstmt1 = conn.prepareStatement(sqlgl);
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						stgdlx.append(rs1.getString("XH") + ",");
					}
					this.closeCon(null, pstmt1, rs1);
				}
				map.put("GDLX", stgdlx.toString());
				list.add(map);
			}
			this.closeCon(null, pstmt, rs);
			/*for (int i = 0; i < list.size(); i++) {
				String[] gdlx = list.get(i).get("GDLX").split(",");

				StringBuffer stgdlx = new StringBuffer();

				for (int j = 0, len = gdlx.length; j < len; j++) {
					PreparedStatement pstmt1 = null;
					ResultSet rs1 = null;
					String sqlgl = " SELECT XH FROM PROJECT_GDLX WHERE SQU='"
							+ gdlx[j] + "' AND FL='0'";

					pstmt1 = conn.prepareStatement(sqlgl);
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						stgdlx.append(rs1.getString("XH") + ",");
					}
					this.closeCon(null, pstmt1, rs1);
				}

				//this.closeCon(null, pstmt, rs);

				list.get(i).put("GDLX", stgdlx.toString());
			}*/
			page.setRows(list);

		} catch (Exception e) {

		} finally {
			this.closeCon(conn, pstmt, rs);
		}
		return page;
	}

	public List<Map<String, String>> selectSingleZhbj(String squ)
			throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql1 = "SELECT DXZHMC,GDLX,AZFS,ZJXS,ZP,LXCS FROM PROJECT_DXZHMXB WHERE ZHSQU='"
					+ squ + "'";

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("DXZHMC", rs.getString("DXZHMC"));
				map.put("GDLX", rs.getString("GDLX"));
				map.put("AZFS", rs.getString("AZFS"));
				map.put("ZJXS", rs.getString("ZJXS"));
				map.put("ZP", rs.getString("ZP"));
				map.put("LXCS", rs.getString("LXCS"));
				map.put("FLA", "1");
				list.add(map);
			}
			for (int i = 0; i < list.size(); i++) {
				String[] gdlx = list.get(i).get("GDLX").split(",");

				StringBuffer stgdlx = new StringBuffer();

				for (int j = 0, len = gdlx.length; j < len; j++) {
					String sqlgl = " SELECT XH FROM PROJECT_GDLX WHERE SQU='"
							+ gdlx[j] + "' AND FL='0'";
					pstmt = conn.prepareStatement(sqlgl);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						stgdlx.append(rs.getString("XH") + ",");
					}

				}

				this.closeCon(null, pstmt, rs);

				list.get(i).put("GDLX", stgdlx.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			closeCon(null, pstmt, rs);
		}
		return list;
	}

	/**
	 * 修改组合部件 updateZhbj:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:23:58
	 * @param zhbjVo
	 * @throws SQLException
	 */
	@Override
	public void updateZhbj(ZhbjVo zhbjVo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = datasource.getConnection();
			StringBuffer sbsql = new StringBuffer();
			if (zhbjVo.getZP().equals("1")) {
				sbsql.append(" UPDATE PROJECT_DXZHMXB SET GDLX=?,AZFS=?,ZJXS=?,DXZHMC=?,LXCS=? WHERE ZHSQU=?");
				pstmt = conn.prepareStatement(sbsql.toString());
				pstmt.setString(1, zhbjVo.getGDLX());
				pstmt.setString(2, zhbjVo.getAZFS());
				pstmt.setString(3, zhbjVo.getZJXS());
				pstmt.setString(4, zhbjVo.getDXZHMC());
				pstmt.setString(5, zhbjVo.getLXCS());
				pstmt.setString(6, zhbjVo.getZHSQU());
				pstmt.executeUpdate();
			} else {
				sbsql.append(" UPDATE PROJECT_DXZHMXB SET GDLX=?,AZFS=?,ZJXS=?,DXZHMC=?,ZP=? ,LXCS=?  WHERE ZHSQU=?");
				pstmt = conn.prepareStatement(sbsql.toString());
				pstmt.setString(1, zhbjVo.getGDLX());
				pstmt.setString(2, zhbjVo.getAZFS());
				pstmt.setString(3, zhbjVo.getZJXS());
				pstmt.setString(4, zhbjVo.getDXZHMC());
				pstmt.setString(5, zhbjVo.getZP());
				pstmt.setString(6, zhbjVo.getLXCS());
				pstmt.setString(7, zhbjVo.getZHSQU());
				pstmt.executeUpdate();
			}
			//修改组合部件
			new UpdateCalculate().updateInfo(5, zhbjVo.getZHSQU(),conn);
		} finally {
			this.closeCon(conn, pstmt, null);
		}

	}

	/**
	 * 删除组合部件 deleteZhbj:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:24:25
	 * @param squ
	 * @throws SQLException
	 */
	@Override
	public String deleteZhbj(String squ) throws SQLException {
		String str = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;//组合部件中是否已有部件
		int j = 0;//组合部件是否已被支架使用
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) FROM PROJECT_DXZHMXB_ZB WHERE ZHSQU='"
					+ squ + "'";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				i = rs.getInt(1);
				
			}
			this.closeCon(null, pstmt, rs);
			
			
				String sqq = "SELECT COUNT(*) FROM PROJECT_DXMXB WHERE BJSQU= '"
						+ squ + "'";
				pstmt = conn.prepareStatement(sqq);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					j = rs.getInt(1);
				}
				
			this.closeCon(null, pstmt, rs);
			if(i>0){
				str = "bjExit";
			}else if(j>0){
				str =  "zjExit";
			}else{
				if (i == 0) {
					String sql1 = "DELETE PROJECT_DXZHMXB WHERE ZHSQU=?";
					pstmt = conn.prepareStatement(sql1);
					pstmt.setString(1, squ);
					pstmt.executeUpdate();
				}
				str =  "success";
			}
			
			
		} finally {
			this.closeCon(conn, pstmt, rs);
		}

		return str;

	}

	/**
	 * 添加组合部件是选着的部件下拉框 selectBj:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-24 上午11:24:36
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Map<String, String>> selectBj() throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT CYMC,SQU FROM PROJCRT_CPMXB");
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("CYMC", rs.getString("CYMC"));
				map.put("SQU", rs.getString("SQU"));
				list.add(map);
			}
		} finally {
			this.closeCon(conn, pstmt, rs);
		}
		return list;
	}

	/**
	 * 添加组合部件是获取部件系列信息 selectLbInfo:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-25 下午7:00:28
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Map<String, String>> selectLbInfo(String id)
			throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 获取连接
		conn = datasource.getConnection();
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		// 菜单查询条件判断
		String squ = "";
		if (id.equals("-1")) {
			pstmt = conn
					.prepareStatement(" SELECT SQU FROM PROJECT_WZZDB T WHERE PARENTSQU = '-1'AND DMLB='CPXL' ");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				squ = rs.getString("SQU");
			}
		} else {
			squ = id;
		}
		this.closeCon(null, pstmt, rs);
		sql.append("SELECT * FROM PROJECT_WZZDB T WHERE PARENTSQU = '" + squ
				+ "'  ORDER BY T.DMLB ");

		pstmt = conn.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();
		Map<String, String> map = null;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SQU", rs.getString("SQU"));
			map.put("DMLB", rs.getString("DMLB"));
			map.put("LBMC", rs.getString("LBMC"));
			map.put("PARENTSQU", rs.getString("PARENTSQU"));
			map.put("JD", rs.getString("JD"));
			map.put("ZHDM", rs.getString("ZHDM"));
			// map.put("SSMC", rs.getString("SSMC"));
			list.add(map);
		}
		this.closeCon(conn, pstmt, rs);
		return list;

	}

	/**
	 * 添加组合部件是获取部件信息 queryBjInfo:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-26 下午4:15:57
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public PageInfoVo queryBjInfo(PageInfoVo page,String id, String type)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			// 获取连接
			conn = datasource.getConnection();
			// 查询条件判断
			String qusql = "";
			if (type.equals("5")) {
				qusql = " AND ( CPXH='" + id + "' OR CPXL='" + id + "' OR CPZM='"
						+ id + "' OR CPTZ='" + id + "')";

			} else if (type.equals("6")) {
				qusql = " AND SQU = '" + id + "'";
			}
			
		
			String sq = "SELECT COUNT(*) FROM PROJCRT_CPMXB WHERE 1=1 "+qusql+"";
			pstmt = conn.prepareStatement(sq);
			rs = pstmt.executeQuery();
			// 获取总数
			if (rs.next()) {
				page.setTotal(rs.getInt(1));
			}
			this.closeCon(null, pstmt, rs);
			// 开始数
			int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
			StringBuffer sql = new StringBuffer();
			
			
			sql.append("SELECT * FROM ( SELECT A.* ,ROWNUM RN FROM (");
			sql.append("SELECT SQU,CPBM,CYMC,BJXH,JLDW,CBDJ,EDHL,ZP,LXCS FROM PROJCRT_CPMXB  WHERE 1=1 "
					+ qusql + "  ORDER BY SQU)A) ");
			sql.append("WHERE RN<" + (startIndex + page.getPageSize())
					+ " AND RN>=" + startIndex);
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("SQU", rs.getString("SQU"));
				map.put("CPBM", rs.getString("CPBM"));
				map.put("CYMC", rs.getString("CYMC"));
				map.put("BJXH", rs.getString("BJXH"));
				map.put("JLDW", rs.getString("JLDW"));
				map.put("CBDJ", rs.getString("CBDJ"));
				map.put("EDHL", rs.getString("EDHL"));
				map.put("ZP", rs.getString("ZP"));
				map.put("RN", rs.getString("RN"));
				map.put("LXCS", rs.getString("LXCS"));
				list.add(map);
			}
			page.setRows(list);
		}finally{
			this.closeCon(conn, pstmt, rs);
		}
		
		
		return page;

	}

	@Override
	public List<Map<String, String>> getBjDetails(String squ)
			throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 获取连接
			conn = datasource.getConnection();
			StringBuffer sql = new StringBuffer();
			// sql.append("SELECT SQU,CPBM,CYMC FROM PROJCRT_CPMXB  WHERE CPXH = '"+id+"'  ORDER BY SQU ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("SQU", rs.getString("SQU"));
				map.put("CPBM", rs.getString("CPBM"));
				map.put("CYMC", rs.getString("CYMC"));

				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeCon(conn, pstmt, rs);
		}

		return list;

	}

	@Override
	public void updateBuJian(ZhbjVo zhbjVo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" UPDATE PROJECT_DXZHMXB_ZB SET SJ=? WHERE ZBSQU=?");

			pstmt = conn.prepareStatement(sbsql.toString());
			pstmt.setString(1, zhbjVo.getSJ());
			pstmt.setString(2, zhbjVo.getZZBSQU());
			pstmt.executeUpdate();
			
			pstmt.close();
			
			String sql  = "SELECT ZHSQU FROM PROJECT_DXZHMXB_ZB WHRERE WHERE ZBSQU='"+zhbjVo.getZZBSQU()+"'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()){
				str = rs.getString(1);
			}
			//修改组合部件
			new UpdateCalculate().updateInfo(5, str,conn);
		} finally {
			this.closeCon(conn, pstmt, null);
		}
		
	}

	/**
	 * 删除组合部件中的部件 deleteBuJian:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-30 下午7:24:34
	 * @param squ
	 * @throws SQLException
	 */
	@Override
	public void deleteBuJian(String squ) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = datasource.getConnection();
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" DELETE  PROJECT_DXZHMXB_ZB WHERE ZBSQU=?");

			pstmt = conn.prepareStatement(sbsql.toString());
			pstmt.setString(1, squ);

			pstmt.executeUpdate();
		} finally {
			this.closeCon(conn, pstmt, null);
		}

	}

	/**
	 * 添加组合部件中的部件 addBuJian:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-30 下午7:53:44
	 * @param zhbjVo
	 * @throws SQLException
	 */
	@Override
	public void addBuJian(String[] arry,String squ) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sbsql1 = new StringBuffer();
			sbsql1.append(" INSERT INTO PROJECT_DXZHMXB_ZB (");
			sbsql1.append(" ZHSQU,BJSQU,SJ) VALUES(?,?,?)");
			pstmt = conn.prepareStatement(sbsql1.toString());
			for(int i = 0,len = arry.length;i<len;i++){
			
				String[] bj = arry[i].split("_");
			
				pstmt.setString(1, squ);
				pstmt.setString(2, bj[0]);
				pstmt.setString(3, bj[1]);
				pstmt.addBatch();  
			}
			
			// System.out.println(sbsql1.toString());
			
			pstmt.executeBatch();
			conn.commit();
		} catch(Exception ex){
			 try {  
		            conn.rollback(); //回滚  
		        } catch (SQLException e1) {  
		            e1.printStackTrace();  
		        }  
			 ex.printStackTrace();  
		     
		}finally {
			this.closeCon(conn, pstmt, null);
		}
	}

	@Override
	public List<Zhbj> getZhbjBysqu(String squ) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Zhbj> list = new ArrayList<Zhbj>();
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM PROJECT_DXZHMXB WHERE ZHSQU = '" + squ
					+ "'";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Zhbj zhbj = null;
			while (rs.next()) {
				zhbj = new Zhbj();
				zhbj.setZHSQU(rs.getString("ZHSQU"));
				zhbj.setGDLX(rs.getString("GDLX"));
				zhbj.setAZFS(rs.getString("AZFS"));
				zhbj.setZJXS(rs.getString("ZJXS"));
				zhbj.setDXZHMC(rs.getString("DXZHMC"));
				zhbj.setZP(rs.getString("ZP"));
				zhbj.setCJSJ(rs.getString("CJSJ"));
				zhbj.setLXCS(rs.getString("LXCS"));

			}

			list.add(zhbj);

		} finally {
			this.closeCon(conn, pstmt, rs);
		}

		return list;
	}

}
