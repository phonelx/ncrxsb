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
import monitor.goodsHouse.bean.entity.Gdlx;
import monitor.goodsHouse.bean.entity.Wzzdb;
import monitor.goodsHouse.dao.IPartsDao;
import monitor.user.bean.vo.PageInfoVo;

/**
 *@ClassName
 *@dataTime 2017-10-14-下午2:10:18
 *@version
 *@author:唐青
 *@since
 */
public class PartsDaoImpl extends BaseDAO implements IPartsDao{
	
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
	 * 加载部件目录
	  * getPartsMenu:接口实现
	  * @author 唐青
	  * @date  2017-10-14 下午3:29:22
	  * @param squ
	  * @return
	  * @throws SQLException
	 */
	public List<Map<String, String>> getPartsMenu(String squ,String type)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 获取连接
		conn = datasource.getConnection();
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		// 菜单查询条件判断
		if(squ.equals("-1")){
			squ = "-1";
			//sql1 = " AND DMLB='"+type+"'";
		}
		sql.append("SELECT * FROM PROJECT_WZZDB T WHERE PARENTSQU = '"+squ+"'  ORDER BY T.DMLB ");

		pstmt = conn.prepareStatement(sql.toString());
		rs = pstmt.executeQuery();
		Map<String, String> map = null ;
		while (rs.next()) {
			map = new HashMap<String, String>();
			map.put("SQU", rs.getString("SQU"));
			map.put("DMLB", rs.getString("DMLB"));
			map.put("LBMC", rs.getString("LBMC"));
			map.put("PARENTSQU", rs.getString("PARENTSQU"));
			map.put("JD", rs.getString("JD"));
			map.put("ZHDM", rs.getString("ZHDM"));	
			map.put("XSCS", rs.getString("XSCS"));		
			list.add(map);
		}		
		this.closeCon(conn, pstmt, rs);
		return list;

	}
	@Override
	public PageInfoVo selectChildMenu(PageInfoVo page,String squ,String key) throws SQLException {
	
			List<Wzzdb> list = new ArrayList<Wzzdb>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "";
		
			if(key.equals("1")){
				query = "";
			}else{
				query=" AND (DMLB LIKE '%"+key+"%' OR LBMC LIKE'%"+key+"%' )";
			}
			String sql = "SELECT COUNT(*) FROM PROJECT_WZZDB WHERE PARENTSQU ='"+squ+"'"+query+"";
		
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				page.setTotal(rs.getInt(1));
			}
			this.closeCon(null, pstmt, rs);
			int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT * FROM ( SELECT A.* ,ROWNUM RN FROM ( ");
			sbsql.append(" SELECT * FROM PROJECT_WZZDB WHERE PARENTSQU ='"+squ+"'"+query+" ORDER BY DMLB)A) ");
			sbsql.append("WHERE RN<"+(startIndex + page.getPageSize())+" AND RN>="+startIndex);
			
			pstmt = conn.prepareStatement(sbsql.toString());
	
			rs = pstmt.executeQuery();
			Wzzdb wzz = null;
			while(rs.next()){
				wzz = new Wzzdb();
				wzz.setDMLB(rs.getString("DMLB"));
				wzz.setJD(rs.getString("JD"));
				wzz.setLBMC(rs.getString("LBMC"));
				wzz.setPARENTSQU(rs.getString("PARENTSQU"));
				wzz.setSQU(rs.getString("SQU"));
				wzz.setZHDM(rs.getString("ZHDM"));
				wzz.setXSCS(rs.getString("XSCS"));
				list.add(wzz);
			}
			page.setRows(list);
			this.closeCon(conn, pstmt, rs);
		return page;
	}
	/**
	 * 添加产品子目
	  * addMenu:接口实现
	  * @author 唐青
	  * @date  2017-10-16 下午6:26:56
	  * @param wzz
	  * @throws SQLException
	 */
	@Override
	public void addMenu(Wzzdb wzz)
			throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO  PROJECT_WZZDB (DMLB,LBMC,PARENTSQU,JD,ZHDM,XSCS) VALUES (?,?,?,?,?,?)";
		
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wzz.getDMLB());
			pstmt.setString(2,wzz.getLBMC());
			pstmt.setString(3,wzz.getPARENTSQU());
			pstmt.setString(4, wzz.getJD());
			pstmt.setString(5, wzz.getZHDM());
			pstmt.setString(6, wzz.getXSCS());
			pstmt.executeUpdate();
		} finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	@Override
	public void updateMenu(Wzzdb wzz) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE PROJECT_WZZDB SET DMLB=?,LBMC=?,JD=?,ZHDM=?,XSCS=? WHERE SQU=?";
		//System.out.println(sql);
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wzz.getDMLB());
			pstmt.setString(2,wzz.getLBMC());
			pstmt.setString(3,wzz.getJD());
			pstmt.setString(4,wzz.getZHDM());
			pstmt.setString(5,wzz.getXSCS());
			pstmt.setString(6,wzz.getSQU());

			pstmt.executeUpdate();
		} finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	@Override
	public int delChildMenu(String squ) throws SQLException {
		int i = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = datasource.getConnection();
			String sq = "SELECT COUNT(*) FROM PROJECT_WZZDB WHERE PARENTSQU='"+squ+"'";
			pstmt = conn.prepareStatement(sq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				i = rs.getInt(1);
			}
			pstmt.close();
			rs.close();
			if(i==0){
				String sql = "DELETE PROJECT_WZZDB  WHERE SQU=?";
				//System.out.println(sql);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, squ);
				
				pstmt.executeUpdate();
			}
			
		} finally{
			this.closeCon(conn, pstmt, null);
		}
		return i;
	}
	@Override
	public void addBatchParts(List<Wzzdb> list) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String sql = "INSERT INTO  PROJECT_WZZDB (DMLB,LBMC,PARENTSQU,JD,ZHDM,XSCS) VALUES (?,?,?,?,?,?)";
		try {
			conn = datasource.getConnection();
			 // 关闭事务自动提交  
			conn.setAutoCommit(false); 
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				
				pstmt.setString(1, list.get(i).getDMLB());
				pstmt.setString(2,list.get(i).getLBMC());
				pstmt.setString(3,list.get(i).getPARENTSQU());
				pstmt.setString(4, list.get(i).getJD());
				pstmt.setString(5, list.get(i).getDMLB());
				pstmt.setString(6, list.get(i).getXSCS());			
				pstmt.addBatch();  
			}
			
			 pstmt.executeBatch();  
	         conn.commit();  
		} finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	
	/**
	 * 批量导入管道类型
	  * addBatchGdlx:接口实现
	  * @author 唐青
	  * @date  2017-11-9 下午5:04:03
	  * @param list
	  * @throws SQLException
	 */
	@Override
	public void addBatchGdlx(List<Gdlx> list) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String sql = "INSERT INTO  PROJECT_GDLX (ZH,XH,MS,LBDM,FL,LRR,LRDATE) VALUES (?,?,?,?,?,?,sysdate)";
		try {
			conn = datasource.getConnection();
			 // 关闭事务自动提交  
			conn.setAutoCommit(false); 
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				
				pstmt.setString(1, list.get(i).getZH());
				pstmt.setString(2,list.get(i).getXH());
				pstmt.setString(3,null);
				pstmt.setString(4, list.get(i).getLBDM());
				pstmt.setString(5, "0");	
				pstmt.setString(6, list.get(i).getLRR());			
				pstmt.addBatch();  
			}
			
			 pstmt.executeBatch();  
	         conn.commit();  
		} catch(Exception e) {  
	        try {  
	            conn.rollback(); //回滚  
	        } catch (SQLException e1) {  
	            e1.printStackTrace();  
	        }  
	           
	    } finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	/**
	 * 批量导入安装方式
	  * addBatchAzfs:接口实现
	  * @author 唐青
	  * @date  2017-11-9 下午5:04:13
	  * @param list
	  * @throws SQLException
	 */
	@Override
	public void addBatchAzfs(List<Gdlx> list) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String sql = "INSERT INTO  PROJECT_GDLX (ZH,XH,MS,LBDM,FL,LRR,LRDATE) VALUES (?,?,?,?,?,?,sysdate)";
		try {
			conn = datasource.getConnection();
			 // 关闭事务自动提交  
			conn.setAutoCommit(false); 
			pstmt = conn.prepareStatement(sql);
			for(int i=0;i<list.size();i++){
				

				pstmt.setString(1, null);
				pstmt.setString(2,null);
				pstmt.setString(3,list.get(i).getMS());
				pstmt.setString(4, list.get(i).getLBDM());
				pstmt.setString(5, "1");
				pstmt.setString(6, null);
				
				pstmt.addBatch();  
			}
			
			 pstmt.executeBatch();  
	         conn.commit();  
		}catch(Exception e) {  
	        try {  
	            conn.rollback(); //回滚  
	        } catch (SQLException e1) {  
	            e1.printStackTrace();  
	        }  
	          
	    }  finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	/**
	 * 添加管道类型
	  * addGdlx:接口实现
	  * @author 唐青
	  * @date  2017-11-7 上午10:54:28
	  * @param gdlx
	  * @throws SQLException
	 */
	@Override
	public void addGdlx(Gdlx gdlx) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String sql = "INSERT INTO  PROJECT_GDLX (ZH,XH,MS,LBDM,FL,LRR,LRDATE,PARENTID,FLAGE) VALUES (?,?,?,?,?,?,sysdate,?,'-1')";
		try {
			conn = datasource.getConnection();		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gdlx.getZH());
			pstmt.setString(2,gdlx.getXH());
			pstmt.setString(3,null);
			pstmt.setString(4, gdlx.getLBDM());
			pstmt.setString(5, "0");
			pstmt.setString(6, gdlx.getLRR());
			pstmt.setString(7, gdlx.getPARENTID());
			pstmt.executeUpdate();
		}finally{
			this.closeCon(conn, pstmt, null);
		}
	        
	}
	/**
	 * 添加安装类型
	  * addAzlx:接口实现
	  * @author 唐青
	  * @date  2017-11-7 上午10:54:43
	  * @param gdlx
	  * @throws SQLException
	 */
	@Override
	public void addAzlx(Gdlx gdlx) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		String sql = "INSERT INTO  PROJECT_GDLX (ZH,XH,MS,LBDM,FL,LRR,LRDATE) VALUES (?,?,?,?,?,?,sysdate)";
		try {
			conn = datasource.getConnection();		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setString(2,null);
			pstmt.setString(3,gdlx.getMS());
			pstmt.setString(4, gdlx.getLBDM());
			pstmt.setString(5, "1");
			pstmt.setString(6, null);
		
			pstmt.executeUpdate();
		}finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	
	/**
	 * 分页查询安装方式
	  * selectAzlx:接口实现
	  * @author 唐青
	  * @date  2017-11-7 上午11:54:05
	  * @param page
	  * @param key
	  * @return
	  * @throws SQLException
	 */
	@Override
	public PageInfoVo selectAzlx(PageInfoVo page, String key)
			throws SQLException {
		List<Gdlx> list = new ArrayList<Gdlx>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
	
		if(key.equals("1")){
			query = "";
		}else{
			query=" AND (XH LIKE '%"+key+"%' OR ZH LIKE'%"+key+"%' OR LBDM LIKE'%"+key+"%')";
		}
		String sql = "SELECT COUNT(*) FROM PROJECT_GDLX WHERE FL = '1' "+query+"";
	
		conn = datasource.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			page.setTotal(rs.getInt(1));
		}
		this.closeCon(null, pstmt, rs);
		int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
		StringBuffer sbsql = new StringBuffer();
		sbsql.append(" SELECT * FROM ( SELECT A.* ,ROWNUM RN FROM ( ");
		sbsql.append(" SELECT * FROM PROJECT_GDLX WHERE FL = '1'"+query+" ORDER BY SQU)A) ");
		sbsql.append("WHERE RN<"+(startIndex + page.getPageSize())+" AND RN>="+startIndex);
		
		pstmt = conn.prepareStatement(sbsql.toString());
	//	System.out.println("sql："+sbsql.toString());
		rs = pstmt.executeQuery();
		Gdlx gdlx = null;
		while(rs.next()){
			gdlx = new Gdlx();
			gdlx.setSQU(rs.getString("SQU"));
			gdlx.setLBDM(rs.getString("LBDM"));
			gdlx.setXH(rs.getString("XH"));
			gdlx.setZH(rs.getString("ZH"));
			gdlx.setMS(rs.getString("MS"));
			gdlx.setFL(rs.getString("FL"));
		
			list.add(gdlx);
		}
		page.setRows(list);
		this.closeCon(conn, pstmt, rs);
	return page;
	}
	/**
	 * 分页查询管道类型    
	  * selectGdlx:接口实现
	  * @author 唐青
	  * @date  2017-11-7 上午11:54:15
	  * @param page
	  * @param key
	  * @return
	  * @throws SQLException
	 */
	@Override
	public PageInfoVo selectGdlx(PageInfoVo page, String key,String pid)
			throws SQLException {
		List<Gdlx> list = new ArrayList<Gdlx>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
	
		if(key.equals("1")){
			query = "";
		}else{
			query=" AND (G.XH LIKE '%"+key+"%' OR G.ZH LIKE'%"+key+"%' OR G.LBDM LIKE'%"+key+"%')";
		}
		String sql = "SELECT COUNT(*) FROM  PROJECT_GDLX G,SSP_TUSERINFO T WHERE FL = '0' AND G.LRR = T.SQU  AND PARENTID='"+pid+"'"+query+"";
	
		conn = datasource.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			page.setTotal(rs.getInt(1));
		}
		this.closeCon(null, pstmt, rs);
		int startIndex = (page.getPageNumber() - 1) * page.getPageSize() + 1;
		StringBuffer sbsql = new StringBuffer();
		sbsql.append(" SELECT * FROM ( SELECT A.* ,ROWNUM RN FROM ( ");
		sbsql.append(" SELECT G.*,T.USERNAME FROM PROJECT_GDLX G,SSP_TUSERINFO T");
		sbsql.append(" WHERE FL = '0' AND  G.LRR = T.SQU  AND PARENTID='"+pid+"'"+query+" ORDER BY G.SQU)A) ");
		sbsql.append("WHERE RN<"+(startIndex + page.getPageSize())+" AND RN>="+startIndex);
		pstmt = conn.prepareStatement(sbsql.toString());
		
		rs = pstmt.executeQuery();
		Gdlx gdlx = null;
		while(rs.next()){
			gdlx = new Gdlx();
			gdlx.setSQU(rs.getString("SQU"));
			gdlx.setLBDM(rs.getString("LBDM"));
			gdlx.setXH(rs.getString("XH"));
			gdlx.setZH(rs.getString("ZH"));
			gdlx.setMS(rs.getString("MS"));
			gdlx.setFL(rs.getString("FL"));
			gdlx.setLRR(rs.getString("USERNAME"));
			gdlx.setLRDATE(rs.getString("LRDATE"));
			list.add(gdlx);
		}
		page.setRows(list);
		this.closeCon(conn, pstmt, rs);
	return page;
	}
	/**
	 * 查询安装方式
	  * selectAzlxMenu:接口实现
	  * @author 唐青
	  * @date  2017-11-7 下午2:56:08
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<Gdlx> selectAzlxMenu() throws SQLException {
		List<Gdlx> list = new ArrayList<Gdlx>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = datasource.getConnection();
		try {
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT * FROM PROJECT_GDLX WHERE FL = '1' ORDER BY SQU ");
			
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			Gdlx gdlx = null;
			while(rs.next()){
				gdlx = new Gdlx();
				gdlx.setSQU(rs.getString("SQU"));
				gdlx.setLBDM(rs.getString("LBDM"));
				gdlx.setXH(rs.getString("XH"));
				gdlx.setZH(rs.getString("ZH"));
				gdlx.setMS(rs.getString("MS"));
				gdlx.setFL(rs.getString("FL"));
				
				list.add(gdlx);
			}
		}finally{
			this.closeCon(conn, pstmt, rs);
		}

		return list ;
	}
	/**
	 * 查询管道类型
	  * selectGdlxMenu:接口实现
	  * @author 唐青
	  * @date  2017-11-7 下午2:56:19
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<Gdlx> selectGdlxMenu(String pid) throws SQLException {
		List<Gdlx> list = new ArrayList<Gdlx>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = datasource.getConnection();
		if("1".equals(pid)){
			pid = getPid();
		}
		try {
			StringBuffer sbsql = new StringBuffer();
			sbsql.append(" SELECT * FROM PROJECT_GDLX WHERE FL = '0' AND PARENTID ='"+pid+"' ORDER BY SQU ");
		
			pstmt = conn.prepareStatement(sbsql.toString());
			rs = pstmt.executeQuery();
			Gdlx gdlx = null;
			while(rs.next()){
				gdlx = new Gdlx();
				gdlx.setSQU(rs.getString("SQU"));
				gdlx.setLBDM(rs.getString("LBDM"));
				gdlx.setXH(rs.getString("XH"));
				gdlx.setZH(rs.getString("ZH"));
				gdlx.setMS(rs.getString("MS"));
				gdlx.setFL(rs.getString("FL"));	
				gdlx.setPARENTID(rs.getString("PARENTID"));
				gdlx.setFLAGE(rs.getInt("FLAGE"));	
				list.add(gdlx);
			}
		}finally{
			this.closeCon(conn, pstmt, rs);
		}

		return list ;
	}
	@Override
	public void updateGdlx(Gdlx gdlx) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PROJECT_GDLX SET LBDM=?,XH=?,ZH=? WHERE SQU = ?";
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gdlx.getLBDM());
			pstmt.setString(2, gdlx.getXH());
			pstmt.setString(3, gdlx.getZH());
			pstmt.setString(4, gdlx.getSQU());
			pstmt.executeUpdate();
		} finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	@Override
	public void updateAzlx(Gdlx gdlx) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PROJECT_GDLX SET LBDM=?,MS=? WHERE SQU = ?";
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gdlx.getLBDM());
			pstmt.setString(2, gdlx.getMS());		
			pstmt.setString(3, gdlx.getSQU());
			pstmt.executeUpdate();
		}  finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	@Override
	public void deleteGdlx(String squ) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PROJECT_GDLX  WHERE SQU = ?";
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, squ);
			pstmt.executeUpdate();
		}  finally{
			this.closeCon(conn, pstmt, null);
		}
		
	}
	
	
	
	/**
	  * getParamConfig:获取参数配置对象
	  * @author 黄月
	  * @date  2018-2-2 下午3:32:30
	  * @param conn
	  * @param search
	  * @param param
	  * @return 
	*/
	@Override
	public List<Wzzdb> getParamConfig( String search, String param) throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SQU,DMLB,LBMC,PARENTSQU,JD,ZHDM,XSCS FROM PROJECT_WZZDB WHERE ");
		sql.append(param).append(" = '").append(search).append("'");
		
		
		List<Wzzdb> list = new ArrayList<Wzzdb>();
		Wzzdb wzz = null;
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				wzz = new Wzzdb();
				wzz.setSQU(rs.getString("SQU"));
				wzz.setDMLB(rs.getString("DMLB"));
				wzz.setJD(rs.getString("JD"));
				wzz.setLBMC(rs.getString("LBMC"));
				wzz.setPARENTSQU(rs.getString("PARENTSQU"));
				wzz.setXSCS(rs.getString("XSCS"));
				wzz.setZHDM(rs.getString("ZHDM"));
				list.add(wzz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeCon(conn, ps, rs);
		}
		return list;
	}
	
	private String getPid(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str = "";
		String sql = "SELECT SQU FROM PROJECT_GDLX WHERE LBDM = 'GXLX'";
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	@Override
	public Wzzdb selectWzzdb(String squ) throws SQLException {
		Wzzdb wzzdb = new Wzzdb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PROJECT_WZZDB WHERE SQU = '"+squ+"'";
		try {
			conn = datasource.getConnection();
			ps = conn.prepareStatement(sql.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				wzzdb.setSQU(rs.getString("SQU"));
				wzzdb.setDMLB(rs.getString("DMLB"));
				wzzdb.setJD(rs.getString("JD"));
				wzzdb.setLBMC(rs.getString("LBMC"));
				wzzdb.setPARENTSQU(rs.getString("PARENTSQU"));
				wzzdb.setXSCS(rs.getString("XSCS"));
				wzzdb.setZHDM(rs.getString("ZHDM"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeCon(conn, ps, rs);
		}
		
		return wzzdb;
	}
}
