/***
 * copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * Project Name:jgdl
 * @since：JDK1.6
 * @version：1.0
 * File Name:KzzjMxpzDaoImpl.java
 * Date:2017-10-26下午3:36:27   
 ***/
package monitor.kzzjMxpz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.expr.NewArray;

import monitor.common.dao.BaseDAO;
import monitor.common.exception.DaoException;
import monitor.common.util.UpdateCalculate;
import monitor.kzzj.bean.entity.KzzjBean;
import monitor.kzzjMxpz.bean.entity.Dxinfo;
import monitor.kzzjMxpz.bean.entity.Dxmxb;
import monitor.kzzjMxpz.bean.vo.CpmxbDto;
import monitor.kzzjMxpz.bean.vo.DxzhMxbDto;
import monitor.kzzjMxpz.dao.IKzzjMxpzDao;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:KzzjMxpzDaoImpl
 * @dateTime: 2017-10-26 下午3:36:27
 * @Description: TODO
 * @version
 * @author: 康正秋
 * @since JDK 1.6 History： Editor version Time Operation
 */
public class KzzjMxpzDaoImpl extends BaseDAO implements IKzzjMxpzDao {

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
	 * TODO 查询支架目录(通过分页处理）
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#getPartsMenu(java.lang.String)
	 */
	@Override
	public PageInfoVo getPartsMenu(PageInfoVo page, String squ)
			throws SQLException {

		List<Dxinfo> list = new ArrayList<Dxinfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM PROJECT_DXINFO WHERE ZJLX = '" + squ
				+ "'";
		conn = datasource.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			page.setTotal(rs.getInt(1));
		}

		int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
		StringBuffer sbsql = new StringBuffer();

		int rn = startIndex + page.getPageSize();

		sbsql.append(" SELECT * FROM ( SELECT A.* ,ROWNUM RN FROM ( ");
		sbsql.append(" SELECT * FROM PROJECT_DXINFO WHERE ZJLX = '" + squ
				+ "' ORDER BY CJSJ DESC)A) ");
		sbsql.append("WHERE RN<" + rn + " AND RN>=" + startIndex);

		pstmt = conn.prepareStatement(sbsql.toString());
		rs = pstmt.executeQuery();

		Dxinfo dxinfo = null;

		while (rs.next()) {
			dxinfo = new Dxinfo();

			dxinfo.setDxSqu(rs.getString("DXSQU"));
			dxinfo.setDxmc(rs.getString("DXMC"));
			dxinfo.setGdlx(rs.getString("GDLX"));
			dxinfo.setAzfs(rs.getString("AZFS"));
			dxinfo.setZjxs(rs.getString("ZJXS"));
			dxinfo.setZp(rs.getString("ZP"));

			list.add(dxinfo);
		}

		page.setRows(list);
		this.closeCon(conn, pstmt, rs);

		return page;
	}

	/**
	 * TODO 添加抗震支架部件明细
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#addKzzjMxpz()
	 */
	@Override
	public String addKzzjMxpz(Dxmxb dxmxb) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 查看部件是否存在
		int count = 0;
		String searchSql = "select * from PROJECT_DXMXB t where t.DXSQU = ? AND t.BJSQU = ? AND t.BJLX = ? AND t.BJFL = ?";
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(searchSql);
			pstmt.setString(1, dxmxb.getDXSQU());
			pstmt.setString(2, dxmxb.getBJSQU());
			pstmt.setInt(3, dxmxb.getBJLX());
			pstmt.setInt(4, dxmxb.getBJFL());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// 已经存在
		if (count > 0) {
			return "hasExit";
		} else {

			try {
				conn = datasource.getConnection();
				conn.setAutoCommit(false);
				String sql = "";
				if (dxmxb.getBJLX() == 0) {

					sql = "INSERT INTO PROJECT_DXMXB(BJSQU,SL,DXSQU,BJLX,BJFL)"
							+ "VALUES(?,?,?,?,?)";
				} else {
					sql = "INSERT INTO PROJECT_DXMXB(BJSQU,SL,DXSQU,BJLX,BJFL)"
							+ "VALUES(?,?,?,?,?)";
				}

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dxmxb.getBJSQU());
				pstmt.setInt(2, dxmxb.getSL());
				pstmt.setString(3, dxmxb.getDXSQU());
				pstmt.setInt(4, dxmxb.getBJLX());
				pstmt.setInt(5, dxmxb.getBJFL());

				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					throw new DaoException(e);
				}
				throw new DaoException(e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			}
			return "addSuccess";

		}
	}

	/**
	 * TODO 删除支架部件
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#delKzzjBj(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public String delKzzjBj(String dxsqu, String bjsqu) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);

			String sql = "delete from PROJECT_DXMXB where SQU = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bjsqu);
			row = pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		if (row == 0) {
			return "delError";
		} else {
			return "delSuccess";
		}
	}

	/**
	 * TODO 编辑抗震支架部件明细
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#editKzzjBj(monitor.kzzjMxpz.bean.entity.Dxmxb)
	 */
	@Override
	public String editKzzjBj(Dxmxb dxmxb) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);

			String sql = "update PROJECT_DXMXB SET SL=? where SQU = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dxmxb.getSL());
			pstmt.setString(2, dxmxb.getSQU());

			pstmt.executeUpdate();
			conn.commit();
			
			pstmt.close();
			
			String sq  = "SELECT DXSQU FROM PROJECT_DXMXB WHRERE WHERE SQU='"+dxmxb.getSQU()+"'";
			pstmt = conn.prepareStatement(sq);
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()){
				str = rs.getString(1);
			}
			
			//修改支架
			new UpdateCalculate().updateInfo(4, str,conn);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return "editSuccess";

	}

	/**
	 * TODO 查询满足条件的组合部件
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#selectZhbjInfo()
	 */
	@Override
	public List<Map<String, String>> selectZhbjInfo(String gdlx, String azfs) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 获取连接
		try {
			conn = datasource.getConnection();
			sql.append("SELECT * FROM PROJECT_DXZHMXB T WHERE 1=1  ORDER BY T.CJSJ DESC");
System.out.println(sql.toString());
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			Map<String, String> map = null;

			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("ZHSQU", rs.getString("ZHSQU"));
				map.put("GDLX", rs.getString("GDLX"));
				map.put("AZFS", rs.getString("AZFS"));
				map.put("ZJXS", rs.getString("ZJXS"));
				map.put("DXZHMC", rs.getString("DXZHMC"));
				map.put("ZP", rs.getString("ZP"));

				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.closeCon(conn, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * TODO 简单描述该方法的实现功能
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#selectZhbjInfoById(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> selectZhbjInfoById(String ZHSQU) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 获取连接
		try {
			conn = datasource.getConnection();

			sql.append("SELECT * FROM PROJECT_DXZHMXB T WHERE T.ZHSQU = '"
					+ ZHSQU + "'");

			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("ZHSQU", rs.getString("ZHSQU"));

				map.put("ZJXS", rs.getString("ZJXS"));
				map.put("DXZHMC", rs.getString("DXZHMC"));
				map.put("ZP", rs.getString("ZP"));

				map.put("GDLX", rs.getString("GDLX"));
				map.put("AZFS", rs.getString("AZFS"));

				// 管道类型处理
				if (rs.getString("GDLX") != null) {
					String gdlxString = "";
					String gdlx = rs.getString("GDLX");

					String string = "^[A-Za-z0-9]+$";// 字母数字正则表达式
					boolean rrs = false;

					String[] strings = gdlx.split(",");

					for (int i = 0; i < strings.length; i++) {

						// 编译正则表达式
						Pattern pattern = Pattern.compile(string);
						Matcher matcher = pattern.matcher(strings[i]);
						// 查找字符串中是否有匹配正则表达式的字符/字符串
						rrs = matcher.find();
						if (rrs) {
							String sq = "select * from PROJECT_GDLX t where t.SQU = ('"
									+ strings[i] + "')";
							pstmt2 = conn.prepareStatement(sq);
							rs2 = pstmt2.executeQuery();
							while (rs2.next()) {
								gdlxString += rs2.getString("XH") + ",";
							}
						} else {
							i = strings.length;
						}
					}
					if (rrs) {
						map.put("GDLX", gdlxString.substring(0,
								gdlxString.length() - 1));
					} else {
						map.put("GDLX", rs.getString("GDLX"));
					}

				}

				/*// 安装方式处理
				if (rs.getString("AZFS") != null) {
					String azfsString = "";
					String azfs = rs.getString("AZFS");
					String string = "^[A-Za-z0-9]+$";// 字母数字正则表达式
					boolean rrs = false;

					String[] strings = azfs.split(",");
					for (int i = 0; i < strings.length; i++) {

						// 编译正则表达式
						Pattern pattern = Pattern.compile(string);
						Matcher matcher = pattern.matcher(strings[i]);
						// 查找字符串中是否有匹配正则表达式的字符/字符串
						rrs = matcher.find();
						if (rrs) {
							String sq = "select * from PROJECT_GDLX t where t.SQU = ('"
									+ strings[i] + "')";
							pstmt2 = conn.prepareStatement(sq);
							rs2 = pstmt2.executeQuery();
							while (rs2.next()) {
								azfsString += rs2.getString("MS") + ",";
							}
						} else {

							i = strings.length;
						}
					}
					if (rrs) {
						map.put("AZFS", azfsString.substring(0,
								azfsString.length() - 1));
					} else {
						map.put("AZFS", rs.getString("AZFS"));
					}
				}
*/
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.closeCon(conn, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * TODO 简单描述该方法的实现功能
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#getKzzjBjList(monitor.user.bean.vo.PageInfoVo,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public PageInfoVo getKzzjBjList(PageInfoVo page, String dxsqu, int op,
			String searchKey) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		String sql = null;
		if (op == 0) {
			sql = "SELECT COUNT(*) FROM (SELECT A.* FROM(SELECT D.DXSQU,D.BJSQU,D.SL,C.* FROM PROJECT_DXMXB D ,PROJCRT_CPMXB C WHERE  D.BJSQU = C.SQU AND  D.DXSQU ='"
					+ dxsqu
					+ "'AND D.BJLX = '"
					+ op
					+ "'AND C.CYMC LIKE'%"
					+ searchKey + "%')A)";
		} else {
			sql = "SELECT COUNT(*) FROM(SELECT A.* FROM (SELECT  D.DXSQU,D.BJSQU,D.SL,DH.* FROM PROJECT_DXMXB D,PROJECT_DXZHMXB DH WHERE D.BJSQU = DH.ZHSQU  AND D.DXSQU ='"
					+ dxsqu + "'AND DH.DXZHMC LIKE'%" + searchKey + "%')A)";
		}
		// sql = "SELECT COUNT(*) FROM PROJECT_DXMXB T WHERE T.DXSQU ='"+dxsqu
		// +"'AND T.BJLX ='"+op+"'";
		conn = datasource.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			page.setTotal(rs.getInt(1));
		}

		int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
		StringBuffer sbsql = new StringBuffer();

		int rn = startIndex + page.getPageSize();
		// 普通部件
		if (op == 0) {

			sbsql.substring(0, sbsql.length());

			sbsql.append(" SELECT * FROM ( SELECT A.*,ROWNUM RN FROM (");
			sbsql.append(" SELECT D.DXSQU,D.BJSQU,D.SL,C.* FROM PROJECT_DXMXB D ,PROJCRT_CPMXB C WHERE");
			sbsql.append(" D.BJSQU = C.SQU AND  D.DXSQU ='" + dxsqu
					+ "'AND D.BJLX = '" + op + "'AND C.CYMC LIKE'%" + searchKey
					+ "%'ORDER BY D.CJSJ DESC )A) ");
			sbsql.append("WHERE RN<" + rn + " AND RN>=" + startIndex);

			pstmt1 = conn.prepareStatement(sbsql.toString());
			rs1 = pstmt1.executeQuery();

			CpmxbDto cpmxbDto = null;
			while (rs1.next()) {
				cpmxbDto = new CpmxbDto();

				cpmxbDto.setBJXH(rs1.getString("BJXH"));
				cpmxbDto.setCBDJ(rs1.getString("CBDJ"));
				cpmxbDto.setCPBM(rs1.getString("CPBM"));
				cpmxbDto.setCPTZ(rs1.getString("CPTZ"));
				cpmxbDto.setCPXH(rs1.getString("CPXH"));
				cpmxbDto.setCYMC(rs1.getString("CYMC"));
				cpmxbDto.setCPXL(rs1.getString("CPXL"));
				// 产品子目处理
				if (rs1.getString("CPZM") != null) {
					String cpzm = rs1.getString("CPZM");
					String sq = "select * from PROJECT_WZZDB t where t.SQU = ('"
							+ cpzm + "')";
					pstmt2 = conn.prepareStatement(sq);
					rs2 = pstmt2.executeQuery();
					while (rs2.next()) {
						cpmxbDto.setCPZM(rs2.getString("LBMC"));
					}
				} else {
					cpmxbDto.setCPZM(rs1.getString("CPZM"));
				}

				cpmxbDto.setEDHL(rs1.getString("EDHL"));
				cpmxbDto.setJLDW(rs1.getString("JLDW"));
				// cpmxbDto.setLBMC(rs1.getString("LBMC"));
				cpmxbDto.setSQU(rs1.getString("BJSQU"));
				cpmxbDto.setZP(rs1.getString("ZP"));
				cpmxbDto.setSL(Integer.parseInt(rs1.getString("SL")));

				list.add(cpmxbDto);
			}

			page.setRows(list);
			this.closeCon(conn, pstmt, rs);
			this.closeCon(conn, pstmt1, rs1);
			this.closeCon(conn, pstmt2, rs2);

			return page;
		} else {// 组合部件
			sbsql.substring(0, sbsql.length());
			sbsql.append(" SELECT * FROM ( SELECT A.*,ROWNUM RN FROM (");
			sbsql.append(" SELECT  D.DXSQU,D.BJSQU,D.SL,DH.* FROM PROJECT_DXMXB D,PROJECT_DXZHMXB DH WHERE");
			sbsql.append(" D.BJSQU = DH.ZHSQU  AND D.DXSQU ='" + dxsqu
					+ "'AND DH.DXZHMC LIKE'%" + searchKey
					+ "%'ORDER BY D.CJSJ DESC)A) ");
			sbsql.append("WHERE RN<" + rn + " AND RN>=" + startIndex);

			pstmt3 = conn.prepareStatement(sbsql.toString());
			rs3 = pstmt3.executeQuery();

			DxzhMxbDto dxzhMxbDto = null;
			while (rs3.next()) {
				dxzhMxbDto = new DxzhMxbDto();
				dxzhMxbDto.setZHSQU(rs3.getString("ZHSQU"));
				dxzhMxbDto.setDXZHMC(rs3.getString("DXZHMC"));
				dxzhMxbDto.setAZFS(rs3.getString("AZFS"));
				dxzhMxbDto.setGDLX(rs3.getString("GDLX"));
				dxzhMxbDto.setZJXS(rs3.getString("ZJXS"));
				dxzhMxbDto.setZP(rs3.getString("ZP"));
				dxzhMxbDto.setZJXS(rs3.getString("ZJXS"));
				list.add(dxzhMxbDto);
			}

			page.setRows(list);
			this.closeCon(conn, pstmt3, rs3);
			this.closeCon(conn, pstmt, rs);

			return page;

		}

	}

	/**
	 * TODO 简单描述该方法的实现功能
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#getKzzjBjList(monitor.user.bean.vo.PageInfoVo,
	 *      java.lang.String, java.lang.String)
	 */

	@SuppressWarnings("resource")
	public PageInfoVo searchKzzjBjList(PageInfoVo page, String dxsqu, int op,
			String searchKey) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs5 = null;
		int count = 0;
		StringBuffer sql = new StringBuffer();

		conn = datasource.getConnection();
		StringBuffer sbsql = new StringBuffer();
		 
		String query = "";
		if(op==0){
			query = "";
			
		}else{
			query = "AND D.BJFL = '" + op + "' ";
		}
		sbsql.append("SELECT D.* FROM PROJECT_DXMXB D WHERE D.DXSQU = '"
				+ dxsqu + "' "+query+" ORDER BY D.CJSJ DESC");

		pstmt1 = conn.prepareStatement(sbsql.toString());
		rs1 = pstmt1.executeQuery();

		List<Map<String, String>> list2 = new ArrayList<>();
		while (rs1.next()) {
			Map<String, String> map = new HashMap<>();

			map.put("SQU", rs1.getString("SQU"));
			map.put("BJSQU", rs1.getString("BJSQU"));
			map.put("SL", rs1.getString("SL"));
			map.put("DXSQU", rs1.getString("DXSQU"));
			map.put("BJLX", rs1.getString("BJLX"));
			map.put("BJFL", rs1.getString("BJFL"));
			int lx = Integer.parseInt(rs1.getString("BJLX"));
			String bjsqu = rs1.getString("BJSQU");// 部件squ

			if (lx == 1) {// 组合部件

				sbsql.delete(0, sbsql.length());

				sbsql.append("SELECT * FROM (SELECT A.*,ROWNUM RN FROM (SELECT ZH.*");
				sbsql.append(" FROM PROJECT_DXZHMXB ZH WHERE ZH.ZHSQU ='"
						+ bjsqu + "'");
				sbsql.append(" AND ZH.DXZHMC LIKE '%" + searchKey + "%')A)");

				pstmt2 = conn.prepareStatement(sbsql.toString());
				rs2 = pstmt2.executeQuery();

				while (rs2.next()) {

					map.put("ZHSQU", rs2.getString("ZHSQU"));
					map.put("DXZHMC", rs2.getString("DXZHMC"));

					map.put("ZJXS", rs2.getString("ZJXS"));
					map.put("ZP", rs2.getString("ZP"));

					 map.put("GDLX", rs2.getString("GDLX"));
					 map.put("AZFS", rs2.getString("AZFS"));
					pstmt4 = conn.prepareStatement("SELECT COUNT(*) FROM PROJECT_DXZHMXB_ZB Z,PROJCRT_CPMXB B WHERE Z.BJSQU = B.SQU AND Z.ZHSQU='"+rs2.getString("ZHSQU")+"'");
					rs4 = pstmt4.executeQuery();
					while(rs4.next()){
						count = rs4.getInt(1);
						
					}
					closeCon(null, pstmt4, rs4);
					if(count==1){
						pstmt4 = conn.prepareStatement("SELECT Z.SJ *B.CBDJ DJ FROM PROJECT_DXZHMXB_ZB Z,PROJCRT_CPMXB B WHERE Z.BJSQU = B.SQU AND Z.ZHSQU='"+rs2.getString("ZHSQU")+"'");
						rs4 = pstmt4.executeQuery();
						while(rs4.next()){
							
							map.put("CBDJ", rs4.getString("DJ"));
						}
						closeCon(null, pstmt4, rs4);
					}else if(count>1){
						pstmt4 = conn.prepareStatement("SELECT SUM(Z.SJ *B.CBDJ) DJ FROM PROJECT_DXZHMXB_ZB Z,PROJCRT_CPMXB B WHERE Z.BJSQU = B.SQU AND Z.ZHSQU='"+rs2.getString("ZHSQU")+"'");
					//	System.out.println("SELECT SUM(Z.SJ *B.CBDJ) DJ FROM PROJECT_DXZHMXB_ZB Z,PROJCRT_CPMXB B WHERE Z.BJSQU = B.SQU AND Z.ZHSQU='"+rs2.getString("ZHSQU")+"'");
						rs4 = pstmt4.executeQuery();
						while(rs4.next()){
						
							map.put("CBDJ", rs4.getString("DJ"));
						}
						closeCon(null, pstmt4, rs4);
					}else if(count==0){
						
						map.put("CBDJ", "-1");
					}
					
					
					/*// 管道类型处理
					if (rs2.getString("GDLX") != null) {
						String gdlxString = "";
						String gdlx = rs2.getString("GDLX");

						String string = "^[A-Za-z0-9]+$";// 字母数字正则表达式
						boolean rrs = false;

						String[] strings = gdlx.split(",");

						for (int i = 0; i < strings.length; i++) {

							// 编译正则表达式
							Pattern pattern = Pattern.compile(string);
							Matcher matcher = pattern.matcher(strings[i]);
							// 查找字符串中是否有匹配正则表达式的字符/字符串
							rrs = matcher.find();
							if (rrs) {
								String sq = "select * from PROJECT_GDLX t where t.SQU = ('"
										+ strings[i] + "')";
								pstmt4 = conn.prepareStatement(sq);
								rs4 = pstmt4.executeQuery();
								while (rs4.next()) {
									gdlxString += rs4.getString("XH") + ",";
								}
							} else {
								i = strings.length;
							}
						}
						if (rrs) {
							map.put("GDLX", gdlxString.substring(0,
									gdlxString.length() - 1));
						} else {
							map.put("GDLX", rs2.getString("GDLX"));
						}

					}*/

					/*// 安装方式处理
					if (rs2.getString("AZFS") != null) {
						String azfsString = "";
						String azfs = rs2.getString("AZFS");
						String string = "^[A-Za-z0-9]+$";// 字母数字正则表达式
						boolean rrs = false;

						String[] strings = azfs.split(",");
						for (int i = 0; i < strings.length; i++) {

							// 编译正则表达式
							Pattern pattern = Pattern.compile(string);
							Matcher matcher = pattern.matcher(strings[i]);
							// 查找字符串中是否有匹配正则表达式的字符/字符串
							rrs = matcher.find();
							if (rrs) {
								String sq = "select * from PROJECT_GDLX t where t.SQU = ('"
										+ strings[i] + "')";
								pstmt5 = conn.prepareStatement(sq);
								rs5 = pstmt5.executeQuery();
								while (rs5.next()) {
									azfsString += rs5.getString("MS") + ",";
								}
							} else {

								i = strings.length;
							}
						}
						if (rrs) {
							map.put("AZFS", azfsString.substring(0,
									azfsString.length() - 1));
						} else {
							map.put("AZFS", rs2.getString("AZFS"));
						}
					}*/
					list2.add(map);
				}
			} else {// 普通部件

				sbsql.delete(0, sbsql.length());

				sbsql.append("SELECT * FROM (SELECT A.*,ROWNUM RN FROM (SELECT C.SQU CSQU,");
				sbsql.append("C.CPBM,C.CYMC,C.BJXH,C.JLDW,C.CBDJ,C.EDHL,C.CPXH,C.CPXL,C.CPZM,C.CPTZ,C.ZP FROM PROJCRT_CPMXB C WHERE ");
				sbsql.append("C.SQU = '" + bjsqu + "' AND C.CYMC LIKE '%"
						+ searchKey + "%')A)");

				pstmt2 = conn.prepareStatement(sbsql.toString());
				rs2 = pstmt2.executeQuery();

				while (rs2.next()) {

					map.put("CSQU", rs2.getString("CSQU"));
					map.put("CPBM", rs2.getString("CPBM"));
					map.put("CYMC", rs2.getString("CYMC"));
					map.put("BJXH", rs2.getString("BJXH"));
					map.put("JLDW", rs2.getString("JLDW"));
					map.put("CBDJ", rs2.getString("CBDJ"));
					map.put("EDHL", rs2.getString("EDHL"));
					map.put("CPXH", rs2.getString("CPXH"));
					map.put("CPXL", rs2.getString("CPXL"));

					map.put("CPTZ", rs2.getString("CPTZ"));
					map.put("ZP", rs2.getString("ZP"));

					// map.put("CPZM", rs2.getString("CPZM"));

					// 产品子目处理
					if (rs2.getString("CPZM") != null) {
						String cpzm = rs2.getString("CPZM");
						String sq = "select * from PROJECT_WZZDB t where t.SQU = '"
								+ cpzm + "'";
						pstmt3 = conn.prepareStatement(sq);
						rs3 = pstmt3.executeQuery();
						while (rs3.next()) {
							map.put("CPZM", rs3.getString("LBMC"));
						}
					} else {
						map.put("CPZM", rs2.getString("CPZM"));
					}

					list2.add(map);
				}

			}

		}

		this.closeCon(conn, pstmt, rs);
		this.closeCon(conn, pstmt1, rs1);
		this.closeCon(conn, pstmt2, rs2);
		this.closeCon(conn, pstmt3, rs3);
		this.closeCon(conn, pstmt4, rs4);
		this.closeCon(conn, pstmt5, rs5);

		int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
		int rn = startIndex + page.getPageSize();
		List<Map<String, String>> list3 = null;

		if (rn > list2.size()) {
			list3 = list2.subList(startIndex - 1, list2.size());
		} else {
			list3 = list2.subList(startIndex - 1, rn - 1);
		}

		page.setTotal(list2.size());
		page.setRows(list3);
		return page;

	}

	/**
	 * TODO 简单描述该方法的实现功能
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#loadKzzjBySqu(java.lang.String)
	 */
	@Override
	public List<Dxinfo> loadKzzjBySqu(String squ) {

		List<Dxinfo> list = new ArrayList<Dxinfo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sbsql = new StringBuffer();
		
		sbsql.append(" SELECT  D.DXSQU,D.DXMC,D.ZJXS,D.ZP,SUM(DD.SL*CC.CONT) COUN  FROM PROJECT_DXMXB DD,");
		sbsql.append(" PROJECT_DXINFO D,(SELECT to_char(CP.CBDJ) CONT,CP.CYMC MC,CP.SQU ");
		sbsql.append(" FROM PROJCRT_CPMXB CP UNION ALL SELECT to_char(SUM(ZB.SJ*CP.CBDJ)) CONT,ZH.DXZHMC MC, ");
		sbsql.append(" ZH.ZHSQU SQU FROM PROJCRT_CPMXB CP,PROJECT_DXZHMXB ZH, PROJECT_DXZHMXB_ZB ZB ");
		sbsql.append(" WHERE ZH.ZHSQU = ZB.ZHSQU AND CP.SQU = ZB.BJSQU GROUP BY ZH.DXZHMC, ZH.ZHSQU) CC WHERE ");
		sbsql.append(" CC.SQU = DD.BJSQU AND D.DXSQU=DD.DXSQU AND D.DXSQU='"+squ+"' GROUP BY D.DXMC,D.ZJXS,D.DXSQU,D.ZP ");	
		 
		String sql = " SELECT COUNT(*) FROM ("+sbsql.toString()+")";
		int sum = 0;
		Dxinfo cp = new Dxinfo();
		try {
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sum = rs.getInt(1);
			
			}
			this.closeCon(null, pstmt, rs);
			
			String sq = "";
			if(sum==0){
				sq = "SELECT D.*,'' AS COUN FROM PROJECT_DXINFO D WHERE D.DXSQU='"+squ+"'";
			}else{
				sq = sbsql.toString();
			}
			pstmt = conn.prepareStatement(sq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cp.setDxSqu(rs.getString("DXSQU"));
				cp.setDxmc(rs.getString("DXMC"));
				cp.setZjxs(rs.getString("ZJXS"));
				cp.setZp(rs.getString("ZP"));
				cp.setCOUN(rs.getString("COUN"));
				// 管道类型处理
	
				list.add(cp);
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			this.closeCon(conn, pstmt, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * TODO 支架部件明细添加普通部件根据条件查询满足条件的部件
	 * 
	 * @see monitor.kzzjMxpz.dao.IKzzjMxpzDao#searchBjInfo(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String, String>> searchBjInfo(String id, String type,
			String azfs) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 获取连接
		conn = datasource.getConnection();
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 添加查询判断
	//	String qusql = "";

		String searchSql = "";

		if (type.equals("5")) {
			searchSql =  " AND ( CPXH='"+id+"' OR CPXL='"+id+"' OR CPZM='"+id+"' OR CPTZ='"+id+"')";

		} else if (type.equals("6")) {
			searchSql = " AND SQU = '" + id + "'";
		}
		sql.append("SELECT SQU,CPBM,CYMC,BJXH,JLDW,CBDJ,EDHL,ZP FROM PROJCRT_CPMXB  WHERE 1=1 "
				+ searchSql + "  ORDER BY SQU ");
		//System.out.println(sql.toString());
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
			list.add(map);
		}
		this.closeCon(conn, pstmt, rs);
		return list;
	}

	@Override
	public List<Map<String, String>> getZjMenu(String squ, String type)
			throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 获取连接
		conn = datasource.getConnection();
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		// 菜单查询条件判断
		if (squ.equals("-1")) {
			squ = "-1";
			// sql1 = " AND DMLB='"+type+"'";
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
		this.closeCon(conn, pstmt, rs);
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

		Map<String, String> map = null;
		// 菜单查询条件判断
		String squ = "";
		if (id.equals("-1")) {
			pstmt = conn
					.prepareStatement(" SELECT * FROM PROJECT_WZZDB T WHERE T.PARENTSQU = '-1' AND T.LBMC = '产品系列' ");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				squ = rs.getString("SQU");
			}
		} else {
			squ = id;
		}
		sql.append("SELECT * FROM PROJECT_WZZDB T WHERE PARENTSQU = '" + squ
				+ "'  ORDER BY T.DMLB ");

		pstmt = conn.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();

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
	 * 查询支架下组合部件中的普通部件
	  * selectBj:接口实现
	  * @author 唐青
	  * @date  2018-1-30 下午2:38:54
	  * @param id
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<Map<String, String>> selectBj(String id) throws SQLException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sql = "SELECT Z.SJ ,B.CBDJ,B.CYMC,B.JLDW FROM PROJECT_DXZHMXB_ZB Z,PROJCRT_CPMXB B WHERE Z.BJSQU = B.SQU AND Z.ZHSQU='"+id+"'";
		//	System.out.println(sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				map = new HashMap<String, String>();
				map.put("SJ", rs.getString("SJ"));
				map.put("CBDJ", rs.getString("CBDJ"));
				map.put("CYMC", rs.getString("CYMC"));
				map.put("JLDW", rs.getString("JLDW"));
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}

	/**
	 * 查询相应支架类型所有的支架列表
	  * selectSeismicStents:接口实现
	  * @author 唐青
	  * @date  2018-2-1 下午2:54:35
	  * @param id
	  * @return
	  * @throws SQLException
	 */
	@Override
	public PageInfoVo selectSeismicStents(PageInfoVo page,String id)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		String query = "";
		if("1".equals(id)){
			query = "";
		}else{
			query = " AND ZJLX = '"+id+"'";
		}
		try {
			conn = datasource.getConnection();
			String sq = "SELECT COUNT(*) FROM  PROJECT_DXINFO WHERE 1=1 "+query+"";
			ps = conn.prepareStatement(sq);
			rs = ps.executeQuery();
			while(rs.next()){
				page.setTotal(rs.getInt(1));
			}
			this.closeCon(null, ps, rs);
			int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT * FROM ( SELECT A.* ,ROWNUM RN FROM ( ");
			sbsql.append(" SELECT DXMC ,ZJXS,LXCS,ZP,DXSQU FROM  PROJECT_DXINFO WHERE 1=1 "+query+")A) ");
			sbsql.append("WHERE RN<"+(startIndex + page.getPageSize())+" AND RN>="+startIndex);	
		
			ps = conn.prepareStatement(sbsql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				map = new HashMap<String, String>();
				map.put("DXMC", rs.getString("DXMC"));
				map.put("ZJXS", rs.getString("ZJXS"));
				map.put("LXCS", rs.getString("LXCS"));
				map.put("ZP", rs.getString("ZP"));
				map.put("DXSQU", rs.getString("DXSQU"));
				list.add(map);
			}
			page.setRows(list);
		} finally{
			closeCon(conn, ps, rs);
		}
		return page;
	}

	
}
