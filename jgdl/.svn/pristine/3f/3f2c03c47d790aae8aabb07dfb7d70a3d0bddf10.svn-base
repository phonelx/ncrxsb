package monitor.dept.dao.impl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import monitor.common.dao.BaseDAO;
import monitor.common.exception.DaoException;
import monitor.common.util.Base64Util;
import monitor.dept.bean.entity.DeptBean;
import monitor.dept.dao.IDeptDao;
import monitor.user.bean.vo.PageInfoVo;

public class DeptDaoImpl extends BaseDAO implements IDeptDao {
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	private ResultSet rs = null ;
	
	private List<Map<String, Object>> listResultBySql(String sql,Connection conn) {
		List<Map<String, Object>> list = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<Map<String, Object>>();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map<String,Object> map = new LinkedHashMap<String,Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					if (rsmd.getColumnClassName(i).endsWith("BLOB")) {// 处理blob字段
						// 转换为Base64编码
						InputStream in = rs.getBinaryStream(rsmd.getColumnName(i));
						map.put(rsmd.getColumnName(i), Base64Util.Base64Encoder(in, null));
					} else {
						// 将空对象转换为 空串
						String val = rs.getString(i) == null ? "" : rs.getString(i);
						// 处理日期
						if (val.endsWith("00:00:00"))
							val = val.substring(0, val.indexOf("00:00:00"));
						// 将空对象转换为 空串
						map.put(rsmd.getColumnName(i), val);
					}
				}
				list.add(map);
			}
		} catch (Exception e) {
			throw new DaoException(e);
		} finally {
			close(conn, pstmt, rs);
		}

		return list;
	}
	
	public boolean executeSql(String sql){
		try {
			conn = datasource.getConnection();
			pstmt = conn.prepareStatement(sql);
			return pstmt.execute();
		} catch (Exception e) {
			throw new DaoException(e);
		} finally{
			try {
				// 关闭连接
				if(rs != null) rs.close() ;
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
	}
	
	/**
	 * 返回数据源总数量
	 * @return int  数据源总条数
	 */
	public int getAllCnts(String sql) {
		int cnt = 0 ;
		try {
			// 获取连接
			conn = datasource.getConnection() ;
			
			// 查询总条数
			pstmt = conn.prepareStatement(sql) ;
			rs = pstmt.executeQuery();
			if(rs.next()) cnt = rs.getInt(1) ;
		} catch (Exception e) {
			throw new DaoException("数据库异常",e);
		} finally{
			try {
				if(rs != null) rs.close() ;
				if(pstmt != null) pstmt.close() ;
				if(conn != null) conn.close() ;
			} catch (SQLException e) {
				throw new DaoException("数据库异常",e);	
			}
		}

		return cnt ;
	}

	@Override
	public PageInfoVo listDept(PageInfoVo pageVo,String searchKey) throws SQLException{
		Connection conn1 = null ;
		PreparedStatement pstmt1 = null ;
		ResultSet rs1 = null ;
		String SQL="SELECT MT.SQU,MT.USERTITLE FROM SSP_TUSERINFO MT LEFT JOIN T_BM_USER MTU "
				+"ON MT.SQU=MTU.USERSQU WHERE BMDM=? ORDER BY MT.USERTITLE";
		//System.out.println(SQL);
		int startIndex = (pageVo.getPageNumber() -1)*pageVo.getPageSize() +1;
		String countSql = "SELECT COUNT(*) FROM ";
		String listSql = "SELECT * FROM ";
		StringBuffer sql= new StringBuffer();
		sql.append(" (SELECT B.*,ROWNUM RN FROM (SELECT T.* FROM T_BM_NEW T WHERE 1=1"+searchKey+" ORDER BY T.BMDM )B) ");
		countSql+=sql.toString();
		sql.append("WHERE RN>="+startIndex+" AND RN <"+(startIndex+pageVo.getPageSize()));
		
		
		List<Map<String, Object>> listMap=new ArrayList<Map<String,Object>>();
		conn=datasource.getConnection();
		pstmt=conn.prepareStatement(listSql+sql.toString());
		//System.out.println(listSql+sql.toString());
		rs=pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			Map<String,Object> map = new LinkedHashMap<String,Object>();
			String bmdm="";
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				if (rsmd.getColumnClassName(i).endsWith("BLOB")) {// 处理blob字段
					// 转换为Base64编码
					InputStream in = rs.getBinaryStream(rsmd.getColumnName(i));
					map.put(rsmd.getColumnName(i), Base64Util.Base64Encoder(in, null));
				} else {
					// 将空对象转换为 空串
					String val = rs.getString(i) == null ? "" : rs.getString(i);
					// 处理日期
					if (val.endsWith("00:00:00"))
						val = val.substring(0, val.indexOf("00:00:00"));
					// 将空对象转换为 空串
					map.put(rsmd.getColumnName(i), val);
					bmdm=val;
				}
				
				//System.out.println(rsmd.getColumnName(i));
				if(rsmd.getColumnName(i).equals("BMDM")||rsmd.getColumnName(i)=="BMDM"){
					conn1=datasource.getConnection();
					pstmt1=conn1.prepareStatement (SQL);
					pstmt1.setString(1, bmdm);
					rs1=pstmt1.executeQuery();
					String squ="";
					String title="";
					while(rs1.next()){
						squ=squ+rs1.getString("SQU")+",";
						title=title+rs1.getString("USERTITLE")+",";
					}
					
					if(squ.length()>0){
						squ=squ.substring(0, squ.length()-1);
						title=title.substring(0, title.length()-1);
					}
					
					map.put("USERSQU",squ);
					map.put("USERTITLE",title);
					
					close(conn1, pstmt1, rs1);
				}
			}
			listMap.add(map);
		}
		
		pageVo.setTotal(getAllCnts(countSql));
		pageVo.setRows(listMap);

		return pageVo;
	}

    public int countParentNotes(String pd){
    	String sql="select count(*) from T_BM_NEW where parentbmdm='"+pd+"'";
    	int count =getAllCnts(sql);

    	return count;
    }

	@Override
	public int addDept(DeptBean dept) {
		String countSql = "SELECT COUNT(*) FROM T_BM_NEW WHERE BMDM = '"+dept.getBmdm()+"'";
		String countSql1="SELECT COUNT(*) FROM  T_BM_NEW  WHERE BMDM='"+dept.getParentBmdm()+"'";
		String SQL="";
		
		int count =getAllCnts(countSql);
		int count1=getAllCnts(countSql1);
		StringBuffer sql = new StringBuffer();
		if(count == 0){
			try {
				if(count1>0){
					sql.append("INSERT INTO T_BM_NEW (BMDM,BMMC,PARENTBMDM)VALUES");
					sql.append("('"+dept.getBmdm()+"','"+dept.getBmmc()+"','"+dept.getParentBmdm()+"')");
					executeSql(sql.toString());
				}else{
					sql.append("INSERT INTO T_BM_NEW (BMDM,BMMC,PARENTBMDM)VALUES");
					sql.append("('"+dept.getBmdm()+"','"+dept.getBmmc()+"','-1')");	
					executeSql(sql.toString());
				}
				
				// 删除部门下面原有的用户
				SQL="DELETE T_BM_USER WHERE BMDM='"+dept.getBmdm()+"'";
				executeSql(SQL);
				
				// 将用户插入部门
				String[] user=dept.getAddUser().split(",");
				if(user.length>0){
					SQL="INSERT INTO T_BM_USER (BMDM,USERSQU)VALUES('"+dept.getBmdm()+"',?)";
					conn=datasource.getConnection();
					pstmt=conn.prepareStatement(SQL);
					for(int i=0;i<user.length;i++){
						pstmt.setInt(1, Integer.parseInt(user[i]));
						pstmt.addBatch();
					}
					pstmt.executeBatch();
				}
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally{
				close(conn, pstmt, rs);
			}
		}

		return count;
	}

	@Override
	public int delDept(String bmdm) throws SQLException {
		// countSql判断部门下面是否有子部门
		String countSql="SELECT COUNT(*) FROM T_BM_NEW T WHERE '"+bmdm+"'=T.PARENTBMDM";
		int count =getAllCnts(countSql);
		//System.out.println(count);
		if(count == 0){
			// 从MCS_TBM_NEW表中删除部门信息
			StringBuffer sql = new StringBuffer();
			sql.append("DELETE  T_BM_NEW WHERE BMDM = '"+bmdm+"'");
			executeSql(sql.toString());
			
			// 从MCS_TBM_USER表中删除部门与用户的关联信息
			String SQL="DELETE T_BM_USER WHERE BMDM='"+bmdm+"'";
			executeSql(SQL);
		}

		return count;
	}
	/**
	 * 部门修改
	 */
	@Override
	public void editDept(DeptBean dept) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE T_BM_NEW T SET T.BMMC='" + dept.getBmmc()+ "' WHERE T.BMDM = '" + dept.getBmdm() + "'");
		executeSql(sql.toString());
		
		String SQL = "";
		if (!"".equals(dept.getAddUser())) {
			// 将用户插入部门
			String[] user = dept.getAddUser().split(",");
			if (user.length > 0) {
				SQL = "INSERT INTO T_BM_USER (BMDM,USERSQU)VALUES('"
						+ dept.getBmdm() + "',?)";
				conn = datasource.getConnection();
				pstmt = conn.prepareStatement(SQL);
				for (int i = 0; i < user.length; i++) {
					pstmt.setInt(1, Integer.parseInt(user[i]));
					pstmt.addBatch();
				}
				pstmt.executeBatch();
			}
			close(conn, pstmt, rs);
		}
			
	}

	@Override
	public List<Map<String, Object>> getAlluser() throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM SSP_TUSERINFO U WHERE U.SQU NOT IN");
		sql.append("(SELECT B.USERSQU FROM T_BM_USER B)  ORDER BY CREATETIME DESC");
		return listResultBySql(sql.toString(), datasource.getConnection());
	}

	@Override
	public PageInfoVo getUserBySqu(String bmdm,PageInfoVo pageVo)throws SQLException {
		
		int startIndex = (pageVo.getPageNumber() -1)*pageVo.getPageSize() +1;
		String countSql = "SELECT COUNT(*) FROM ";
		String listSql = "SELECT * FROM ";
		StringBuffer sql = new StringBuffer();
		sql.append("(SELECT U.*,ROWNUM RN FROM SSP_TUSERINFO U WHERE U.SQU IN");
		sql.append("(SELECT B.USERSQU FROM T_BM_USER B WHERE B.BMDM = '"+bmdm+"')  ORDER BY CREATETIME DESC)");
		countSql+=sql.toString();
		sql.append("WHERE RN>="+startIndex+" AND RN <"+(startIndex+pageVo.getPageSize()));
		pageVo.setTotal(getAllCnts(countSql));
		pageVo.setRows(listResultBySql(listSql+sql.toString(), datasource.getConnection()));

		return pageVo;
	}

	@Override
	public void clearDept() {
		String sql = "DELETE FROM T_BM_NEW";
		executeSql(sql.toString());
	}

	@Override
	public void upDataDept(List<DeptBean> list) {
		try{
			conn = datasource.getConnection() ;
		    conn.setAutoCommit(false);
			String sql ="INSERT INTO T_BM_NEW(BMDM,BMMC,JGLX,JGJC,SFGP,GPJG,GJZT,OLDCODE,GXSJ,OLDORGNAME)VALUES(?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)";
			pstmt = conn.prepareStatement(sql);
		for (int i = 0; i < list.size(); i++) {
			DeptBean dept =list.get(i);
			pstmt.setString(1, dept.getBmdm());
			pstmt.setString(2, dept.getBmmc());
			pstmt.setString(3, dept.getJglx());
			pstmt.setString(4, dept.getJgjc());
			pstmt.setString(5, dept.getSfgp());
			pstmt.setString(6, dept.getGpjg());
			pstmt.setString(7, dept.getJgzt());
			pstmt.setString(8, dept.getOldCode());
			pstmt.setString(9, dept.getLastUpTime());
			pstmt.setString(10, dept.getOldOrgName());
			pstmt.addBatch();
			}
		pstmt.executeBatch();
		
		// 执行更新
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.commit();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			close(conn, pstmt, rs);
		}
	}

	@Override
	public void otherDataDept(List<DeptBean> list) {
		for (int i = 0; i < list.size(); i++) {
			DeptBean dept = list.get(i);
			String countSql = "SELECT COUNT(*) FROM T_BM_NEW WHERE BMDM = '"+dept.getBmdm()+"'";
			int count =getAllCnts(countSql);
			if(count == 0){
				StringBuffer sql = new StringBuffer();
				if(dept.getGpjg()== null||dept.getGpjg().equals("")){
					dept.setGpjg("无");
				}
				sql.append("INSERT INTO T_BM_NEW(BMDM,BMMC,JGLX,JGJC,SFGP,GPJG,GJZT,OLDCODE,LASTUPTIME,OLDORGNAME)VALUES");
				sql.append("('"+dept.getBmdm()+"','"+dept.getBmmc()+"','"+dept.getJglx()+"','"+dept.getJgjc()+"',");
				sql.append("'"+dept.getSfgp()+"','"+dept.getGpjg()+"','"+dept.getJgzt()+"','"+dept.getOldCode()+"',");
				sql.append(" to_date('"+dept.getLastUpTime()+"','yyyy-mm-dd hh24:mi:ss'),'"+dept.getOldOrgName()+"')");
				executeSql(sql.toString());
			}else{
				StringBuffer sql = new StringBuffer();
				sql.append("UPDATE T_BM_NEW T SET T.BMMC='"+dept.getBmmc()+"',T.JGLX='"+dept.getJglx()+"',T.JGJC='"+dept.getJgjc()+"',T.SFGP='"+dept.getSfgp()+"',T.GPJG='"+dept.getGpjg()+"',T.GJZT='"+dept.getJgzt()+"',T.LASTUPTIME = SYSDATE WHERE T.BMDM = '"+dept.getBmdm()+"'");
				executeSql(sql.toString());
			}
		}
	}
	/**
	  * listDeptByBmdm:部门代码树菜单查询
	  * @param: @param pd
	  * @param: @return
	 * @throws SQLException 
	 */
	@Override
	public List<DeptBean> listDeptByBmdm(String pd) throws SQLException {
		ArrayList<DeptBean> deptList = new ArrayList<DeptBean>();
		conn = datasource.getConnection();
		String sql = "SELECT * FROM T_BM_NEW T WHERE PARENTBMDM = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pd);
		rs = pstmt.executeQuery();
		DeptBean de = null;
		while (rs.next()) {
			de = new DeptBean();
			de.setBmmc(rs.getString("BMMC"));
			de.setBmdm(rs.getString("BMDM"));
			de.setIsend(1);
			deptList.add(de);
		}
		close(conn, pstmt, rs);
		return deptList;
	}

	@Override
	public List<Map<String, Object>> searchUser(String param) {
		List<Map<String, Object>> listMap=new ArrayList<Map<String,Object>>();
		String SQL="SELECT * FROM SSP_TUSERINFO WHERE "+param+" ORDER BY USERTITLE";
		
//		int pageNo=Integer.parseInt(pages[1]);// 当前页码
//		int pageSize=Integer.parseInt(pages[0]);// 每页显示多少记录
//		String sql="SELECT * FROM (SELECT T.*,ROWNUM R1 FROM ("+SQL+") T "
//				+"WHERE ROWNUM <=" + pageNo * pageSize + ") WHERE R1 >" + ((pageNo - 1) * pageSize);
		
		try {
			conn=datasource.getConnection();
			pstmt=conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			
			// 得到SQL语句查询结果数据集的字段名字，将结果存放到数组name中
			ResultSetMetaData rsmd=rs.getMetaData();
			int count=rsmd.getColumnCount();
			String[] name=new String[count];
			for(int i=0;i<name.length;i++){
				name[i]=rsmd.getColumnName(i+1);
			}
			
			// 创建Map对象，将结果集的每一条先存放到map中，再封装到listMap中返回
			Map<String,Object> map=null;
			while(rs.next()){
				map=new HashMap<String,Object>();
				for(int m=0;m<name.length;m++){
					if(rsmd.getColumnClassName(m+1).endsWith("BLOB")){// 处理<blob>字段
						// 转换为Base64编码
						map.put(rsmd.getColumnName(m), "");
					}else{
						// 将空对象转换为 空串
						String str=rs.getString(name[m])==null?"":rs.getString(name[m]);
						// 处理日期
						if (str.endsWith("00:00:00")){
							str = str.substring(0, str.indexOf("00:00:00"));
						}
						map.put(name[m], str);
					}
				}
				listMap.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(conn, pstmt, rs);
		}
		
		return listMap;
	}

	@Override
	public int searchUserCount(String param) {
		int count=0;
		String sql="SELECT COUNT(SFZHM) FROM SSP_TUSERINFO WHERE "+param;

		try {
			conn=datasource.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();

			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public void addUserToDept(String bmdm, String[] array) throws SQLException {
		StringBuffer del = new StringBuffer();
		del.append("DELETE T_BM_USER U WHERE U.BMDM = '"+bmdm+"'");
		executeSql(del.toString());
		for (int i = 0; i < array.length; i++) {
			StringBuffer sql = new StringBuffer();
			if(array[i].length()>0){
				sql.append("INSERT INTO T_BM_USER(BMDM,USERSQU)VALUES('"+bmdm+"','"+array[i]+"')");
				executeSql(sql.toString());
			}
		}
	}

	public static void close(Connection conn, PreparedStatement stmt,ResultSet rs) {
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
}
