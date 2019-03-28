/***
 * copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * Project Name:jgdl
 * @since：JDK1.6
 * @version：1.0
 * File Name:KzzjDaoImpl.java
 * Date:2017-10-17下午2:14:02   
 ***/
package monitor.kzzj.dao.impl;


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

import monitor.common.dao.BaseDAO;
import monitor.common.exception.DaoException;
import monitor.common.util.UpdateCalculate;
import monitor.goodsHouse.bean.entity.Cpmxb;
import monitor.goodsHouse.bean.entity.Zhbj;
import monitor.goodsHouse.bean.vo.BjVo;
import monitor.goodsHouse.bean.vo.ZhVo;
import monitor.kzzj.bean.entity.KzzjBean;
import monitor.kzzj.dao.IKzzjDao;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:KzzjDaoImpl
 * @dateTime: 2017-10-17 下午2:14:02
 * @Description: TODO
 * @version
 * @author: 康正秋
 * @since JDK 1.6 History： Editor version Time Operation
 */
public class KzzjDaoImpl extends BaseDAO implements IKzzjDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public int getAllCnts(String sql) {
		int cnt = 0;
		try {
			// 获取连接
			conn = datasource.getConnection();

			// 查询总条数
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				cnt = rs.getInt(1);
		} catch (Exception e) {
			throw new DaoException("数据库异常", e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DaoException("数据库异常", e);
			}
		}

		return cnt;
	}

	/**
	 * TODO抗震支架添加
	 * 
	 * @see monitor.kzzjJbpz.dao.IKzzjDao#addKzzj(monitor.kzzjJbpz.bean.entity.KzzjBean)
	 */
	@Override
	public String addKzzj(KzzjBean kzzjBean) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO PROJECT_DXINFO(GDLX,AZFS,ZJXS,DXMC,ZP,ZJLX,LXCS,SZZT,ZYM)"
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "");
			pstmt.setString(2, "");
			pstmt.setString(3, kzzjBean.getZjxs());
			pstmt.setString(4, kzzjBean.getDxmc());
			pstmt.setString(5, kzzjBean.getZp());
			pstmt.setString(6, kzzjBean.getZjlx());
			pstmt.setString(7, kzzjBean.getLxcs());
			pstmt.setString(8, kzzjBean.getSzzt());
			pstmt.setString(9, kzzjBean.getZym());

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

	/**
	 * TODO 删除抗震支架
	 * 
	 * @see monitor.kzzjJbpz.dao.IKzzjDao#delKzzj(java.lang.String)
	 */
	@Override
	public String delKzzj(String dxSqu) {

		String str = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int count = 0;//支架下是否有部件
		int j = 0;//支架是否被使用
		try {
			conn = datasource.getConnection();
			//T_PROJECT_ZJ 判断表中是否关联的该支架

			String searchSql = "select count(*) from PROJECT_DXMXB where DXSQU = ?";

			pstmt = conn.prepareStatement(searchSql);
			pstmt.setString(1, dxSqu);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			close(null, pstmt, rs);
			
			String sq = "SELECT COUNT(*) FROM T_PROJECT_ZJ WHERE ZJSQU = ?";

			pstmt = conn.prepareStatement(sq);
			pstmt.setString(1, dxSqu);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				j = rs.getInt(1);
			}
			close(null, pstmt, rs);
			if(count>0){
				str = "bjExit";
			}else if(count>0){
				str = "xmExit";	
			}else{
				String sql = "delete from PROJECT_DXINFO where DXSQU = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dxSqu);
				pstmt.executeUpdate();
				str = "success";
			}
			
			
		/*	// 先删除部件
			if (count > 0) {
				String sqls = "delete from PROJECT_DXMXB where DXSQU = ?";
				pstmt1 = conn.prepareStatement(sqls);
				pstmt1.setString(1, dxSqu);
				pstmt1.executeUpdate();

				conn.commit();
			}*/
			
			

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
		
		
		

		return str;
	    
	}

	/**
	 * TODO 编辑抗震支架
	 * 
	 * @see monitor.kzzjJbpz.dao.IKzzjDao#editKzzj(monitor.kzzjJbpz.bean.entity.KzzjBean)
	 */
	@Override
	public String editKzzj(KzzjBean kzzj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			
			String sql = "UPDATE PROJECT_DXINFO SET ZYM=?,SZZT=?,ZJXS=?,DXMC=?,ZP=?,LXCS=? where DXSQU = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, kzzj.getZym());
			pstmt.setString(2, kzzj.getSzzt());
			pstmt.setString(3, kzzj.getZjxs());
			pstmt.setString(4, kzzj.getDxmc());
			pstmt.setString(5, kzzj.getZp());
			pstmt.setString(6, kzzj.getLxcs());
			pstmt.setString(7, kzzj.getDxSqu());
			pstmt.executeUpdate();
			
			
			//修改支架
			new UpdateCalculate().updateInfo(4, kzzj.getDxSqu(),conn);
		} catch (Exception e) {
			
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

	public static void close(Connection conn, PreparedStatement stmt,
			ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != stmt)
				stmt.close();
			if (null != conn)
				conn.close();
		} catch (Throwable e) {
		}
	}

	@Override
	public PageInfoVo getListKzzj(PageInfoVo page, String searchKey,
			String lxsqu) throws SQLException {

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

		sql = "SELECT COUNT(*) FROM (SELECT A.* FROM(SELECT D.* FROM PROJECT_DXINFO D WHERE  D.DXMC LIKE'%"
				+ searchKey + "%' AND D.ZJLX ='" + lxsqu + "')A)";

		conn = datasource.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			page.setTotal(rs.getInt(1));
		}
		close(null, pstmt, rs);
		
		int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
		int rn = startIndex + page.getPageSize();

		StringBuffer sbsql = new StringBuffer();

		sbsql.substring(0, sbsql.length());

		sbsql.append(" SELECT * FROM ( SELECT A.*,ROWNUM RN FROM (");
		sbsql.append("SELECT D.* FROM PROJECT_DXINFO D  WHERE");
		sbsql.append("  D.DXMC LIKE'%" + searchKey + "%' AND D.ZJLX = '"
				+ lxsqu + "' ORDER BY D.CJSJ DESC )A) ");
		sbsql.append("WHERE RN<" + rn + " AND RN>=" + startIndex);

		pstmt1 = conn.prepareStatement(sbsql.toString());
		rs1 = pstmt1.executeQuery();

		KzzjBean kzzjBean = null;

		while (rs1.next()) {
			kzzjBean = new KzzjBean();

			kzzjBean.setDxSqu(rs1.getString("DXSQU"));
			kzzjBean.setDxmc(rs1.getString("DXMC"));
			kzzjBean.setZjxs(rs1.getString("ZJXS"));
			kzzjBean.setZp(rs1.getString("ZP"));
			kzzjBean.setZjlx(rs1.getString("ZJLX"));
			kzzjBean.setGdlx(rs1.getString("GDLX"));
			kzzjBean.setAzfs(rs1.getString("AZFS"));
			kzzjBean.setSzzt(rs1.getString("SZZT"));
			kzzjBean.setZym(rs1.getString("ZYM"));
		/*	// 管道类型处理
			if (rs1.getString("GDLX") != null) {
				String gdlxString = "";
				String gdlx = rs1.getString("GDLX");
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
						close(null, pstmt2, rs2);
					} else {
						i = strings.length;
					}
				}
				if (rrs) {
					kzzjBean.setGdlx(gdlxString.substring(0,
							gdlxString.length() - 1));
				} else {
					kzzjBean.setGdlx(gdlx);
				}

			}*/
			//close(null, pstmt2, rs2);
			// 安装方式处理

			/*if (rs1.getString("AZFS") != null) {
				String azfsString = "";
				String azfs = rs1.getString("AZFS");
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
						pstmt3 = conn.prepareStatement(sq);
						rs3 = pstmt3.executeQuery();
						while (rs3.next()) {
							azfsString += rs3.getString("MS") + ",";
						}
						close(null, pstmt3, rs3);
					} else {

						i = strings.length;
					}
				}
				if (rrs) {
					kzzjBean.setAzfs(azfsString.substring(0,
							azfsString.length() - 1));
				} else {
					kzzjBean.setAzfs(azfs);
				}

			}*/
			
			list.add(kzzjBean);
		}

		page.setRows(list);
		close(conn, pstmt, rs);
	

		return page;
	}

	/**
	 * TODO 简单描述该方法的实现功能
	 * 
	 * @see monitor.kzzj.dao.IKzzjDao#queryKzzjBySqu(java.lang.String)
	 */
	@Override
	public List<KzzjBean> queryKzzjBySqu(String squ) {

		List<KzzjBean> list = new ArrayList<KzzjBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sbsql = new StringBuffer();

		sbsql.append("SELECT D.* ");
		sbsql.append(" FROM PROJECT_DXINFO D ");
		sbsql.append(" WHERE 1=1 AND D.DXSQU='" + squ + "'");
		try {
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			KzzjBean cp = new KzzjBean();

			while (rs.next()) {
				cp.setDxSqu(rs.getString("DXSQU"));
				cp.setDxmc(rs.getString("DXMC"));
				cp.setZjxs(rs.getString("ZJXS"));
				cp.setZp(rs.getString("ZP"));
				cp.setZjlx(rs.getString("ZJLX"));

				cp.setSzzt(rs.getString("SZZT"));
				cp.setZym(rs.getString("ZYM"));
				cp.setLxcs(rs.getString("LXCS"));
				list.add(cp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}

		
		return list;

	}

	@Override
	public List<Map<String, String>> getZjMenu(String squ, String type)
			throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		try {
			// 获取连接
			conn = datasource.getConnection();
			StringBuffer sql = new StringBuffer();
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
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close(conn, pstmt, rs);
		}
		return list;

	}

	String lbmcs = "";

	@Override
	public List<Map<String, String>> searchParentSquBySqu(String squ) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 获取连接
		try {
			conn = datasource.getConnection();

			sql.append("SELECT * FROM PROJECT_WZZDB T WHERE SQU = '" + squ
					+ "' ");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			String lbmc;
			String parentsqu = null;
			while (rs.next()) {
				lbmc = rs.getString("LBMC");
				lbmcs += lbmc + ",";
				parentsqu = rs.getString("PARENTSQU");
			}

			if (!parentsqu.equals("-1")) {
				searchParentSquBySqu(parentsqu);
			}
			map = new HashMap<String, String>();
			map.put("LBMC", lbmcs.substring(0, lbmcs.length() - 1));
			list.add(map);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}		
		return list;
	}

	/**
	 * 获取支架信息
	  * selectZjInfo:接口实现
	  * @author 唐青
	  * @date  2018-6-23 下午3:16:49
	  * @param zjsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<KzzjBean> selectZjInfo(String zjsqu) throws SQLException {

		List<KzzjBean> list = new ArrayList<KzzjBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sbsql = new StringBuffer();
		sbsql.append("SELECT * FROM PROJECT_DXINFO WHERE DXSQU = '"+zjsqu+"'");		
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			KzzjBean zj = null;
			while (rs.next()) {
				zj =  new KzzjBean();
				zj.setDxSqu(rs.getString("DXSQU"));
				zj.setDxmc(rs.getString("DXMC"));
				zj.setZjxs(rs.getString("ZJXS"));
				zj.setZp(rs.getString("ZP"));
				zj.setZjlx(rs.getString("ZJLX"));
				zj.setSzzt(rs.getString("SZZT"));
				zj.setZym(rs.getString("ZYM"));
				zj.setLxcs(rs.getString("LXCS"));
				zj.setAzfs(rs.getString("AZFS"));
				zj.setGdlx(rs.getString("GDLX"));
				list.add(zj);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}

		
		return list;
	}

	/**
	 * 部件信息
	  * selectBjInfo:接口实现
	  * @author 唐青
	  * @date  2018-6-23 下午3:17:00
	  * @param zjsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<BjVo> selectBjInfo(String zjsqu) throws SQLException {

		List<BjVo> list = new ArrayList<BjVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sbsql = new StringBuffer();
		 
		
		sbsql.append(" SELECT C.*,A.SL,A.BJLX,A.BJFL FROM PROJCRT_CPMXB C,(SELECT DD.BJSQU,");
		sbsql.append(" DD.SL,DD.BJLX,DD.BJFL FROM PROJECT_DXINFO D ,PROJECT_DXMXB DD ");
		sbsql.append(" WHERE D.DXSQU = DD.DXSQU AND DD.BJLX=0 AND D.DXSQU='"+zjsqu+"') A WHERE C.SQU = A.BJSQU");
		try {
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			BjVo bj = null;

			while (rs.next()) {
				bj = new BjVo();
				bj.setSQU(rs.getString("SQU"));
				bj.setCPBM(rs.getString("CPBM"));
				bj.setCYMC(rs.getString("CYMC"));
				bj.setBJXH(rs.getString("BJXH"));
				bj.setJLDW(rs.getString("JLDW"));
				bj.setCBDJ(rs.getString("CBDJ"));
				bj.setPARENTSQU(rs.getString("PARENTSQU"));
				bj.setEDHL(rs.getString("EDHL"));
				bj.setCPXH(rs.getString("CPXH"));
				bj.setCPXL(rs.getString("CPXL"));
				bj.setCPZM(rs.getString("CPZM"));
				bj.setCPTZ(rs.getString("CPTZ"));
				bj.setZP(rs.getString("ZP"));			
				bj.setAZFSDM(rs.getString("AZFSDM"));
				bj.setLXCS(rs.getString("LXCS"));
				bj.setSL(rs.getString("SL"));
				bj.setBJFL(rs.getString("BJFL"));
				bj.setBJLX(rs.getString("BJLX"));
				list.add(bj);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}

		
		return list;
	}

	/**
	 * 组合部件信息
	  * selectZhbjInfo:接口实现
	  * @author 唐青
	  * @date  2018-6-23 下午3:17:12
	  * @param zjsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<ZhVo> selectZhbjInfo(String zjsqu) throws SQLException {

		List<ZhVo> list = new ArrayList<ZhVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sbsql = new StringBuffer();

		sbsql.append(" SELECT C.*,A.SL,A.BJLX,A.BJFL FROM PROJECT_DXZHMXB C,(SELECT DD.BJSQU,");
		sbsql.append(" DD.SL,DD.BJLX,DD.BJFL FROM PROJECT_DXINFO D ,PROJECT_DXMXB DD ");
		sbsql.append(" WHERE D.DXSQU = DD.DXSQU AND DD.BJLX=0 AND D.DXSQU='"+zjsqu+"') A WHERE C.ZHSQU = A.BJSQU");
		try {
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			ZhVo zhbj = null;

			while (rs.next()) {
				zhbj = new ZhVo();
				zhbj.setZHSQU(rs.getString("ZHSQU"));
				zhbj.setGDLX(rs.getString("GDLX"));
				zhbj.setAZFS(rs.getString("AZFS"));
				zhbj.setZJXS(rs.getString("ZJXS"));
				zhbj.setDXZHMC(rs.getString("DXZHMC"));
				zhbj.setZP(rs.getString("ZP"));
				zhbj.setSL(rs.getString("SL"));
				zhbj.setBJLX(rs.getString("BJLX"));
				zhbj.setBJFL(rs.getString("BJFL"));
				list.add(zhbj);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}

		
		return list;
	}

	@Override
	public KzzjBean selectKzzjBySqu(String squ) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sbsql = new StringBuffer();
		KzzjBean kzzj = new KzzjBean();
		sbsql.append("SELECT DXSQU, ZJXS,DXMC,ZP ");
		sbsql.append(" ZJLX,LXCS,SZZT,ZYM FROM PROJECT_DXINFO WHERE DXSQU='"+squ+"'");
		try {
			conn = datasource.getConnection();

			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				kzzj.setDxSqu(rs.getString("DXSQU"));
				kzzj.setDxmc(rs.getString("DXMC"));
				kzzj.setZjxs(rs.getString("ZJXS"));
				kzzj.setZp(rs.getString("ZP"));
				kzzj.setZjlx(rs.getString("ZJLX"));
				kzzj.setSzzt(rs.getString("SZZT"));
				kzzj.setZym(rs.getString("ZYM"));
				kzzj.setLxcs(rs.getString("LXCS"));
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}

		return kzzj;
	}

}
