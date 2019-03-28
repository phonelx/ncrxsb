/*
 * @copyright:  heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  Apr 18, 2011 1:45:52 PM
 */
package monitor.log.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


import monitor.common.dao.BaseDAO;
import monitor.common.exception.DaoException;
import monitor.log.dao.ISearchLogDAO;

/**
 * <description>
 * 
 * @author jk
 * @datetime Apr 18, 2011 1:45:52 PM
 */
public class SearchLogDAOImpl extends BaseDAO implements ISearchLogDAO {

/**
 * @description 当选择字段第一个为”#\\w*#“型时，表示采用不分页查询，当检索字段为”@\\w*@“型时，为日期查询，为"%\\w*%"时为模糊查询，
 * 当检索字段为“@%\\w*@%”d 时，表示设置返回的最大数量
 * @param table 数据表或视图 
 * @param xszd 查询结果字段
 * @param cxzd 检索字段
 * @param input 输入的数据
 * @return
 */
	public List<String> searchLogS(String table,String[] xszd,String[] cxzd,String[] input,int pageSize,int pageNo) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null ;
		
		List<String> data = new ArrayList<String>();
		
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		//获得结果数,因为要翻页所以得data的size（）在变化，所以得加个固定的长度大小
		StringBuffer sqlcount = new StringBuffer("select count(*) from ");
		
		sqlcount.append(table +" where 1=1 ");
		
		for (int c = 0; c < input.length; c++) {
			//判断字段是否为空
			if(input[c].equals("")||input[c].equals("#@#@#@#")){
				continue;
			}
			// 判断是否是date型
			if (Pattern.matches("@\\w+@",cxzd[c])) {
				sqlcount.append(" and ");
				sqlcount.append(cxzd[c].replaceAll("@", ""));
				sqlcount.append(" between '");
				String[] a = input[c].split("#@#@#@#");
				sqlcount.append(a[0]);
				sqlcount.append("' and '");
				sqlcount.append(a[1]);
				sqlcount.append("'");
			} 
			else if(Pattern.matches("%\\w+%",cxzd[c])){
				//模糊查询
				sqlcount.append(" and ");
				sqlcount.append(cxzd[c].replaceAll("%",""));
				sqlcount.append(" like '%"+input[c]+"%'");
			}
			else if(Pattern.matches("\\$\\w+\\$",cxzd[c])){
				//判断是否用in
				sqlcount.append(" and ");
				sqlcount.append(cxzd[c].replaceAll("\\$",""));
				sqlcount.append(" in("+ input[c] +")");
			}else if(Pattern.matches("@%\\w+\\@%",cxzd[c])){//设置最大返回数
//				sqlcount.append(cxzd[c].replaceAll("@%",""));
				if(!input[c].equals("-1")){//当值为-1时返回所有数据
					sqlcount.append(" and ");
					sqlcount.append(" ROWNUM <= "+input[c]);
				}
			}
			else{
				sqlcount.append(" and ");
				sqlcount.append(cxzd[c]);
				sqlcount.append(" like'%" + input[c] + "%'");
			}
		}
		
		try {
			System.out.println("Log执行："+sqlcount.toString());
			pstmt = conn.prepareStatement(sqlcount.toString());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				data.add(rs.getString(1));
			}
		} catch (SQLException e1) {
			throw new DaoException(e1);
		}
		//以上代码为获得结果数据为翻页的total
		
		StringBuffer sql = new StringBuffer("SELECT ");
		
		//这里是判断是否为count（*）型，且count（*）应放在传进来的参数的第二字符串
		//且格式严格按照 usertitle ，count（usertitle）
		
		//一下是判断分页条件，逻辑关系如下，当检索字段第一个字段是"#\\w*#"表示不分页，
		//当第二个字段为"count\\(\\w*\\)"
		//且第一个字段不为"@\\#\\w*@\\#"型时，不分页
		if(!Pattern.matches("#\\w*#",xszd[0])){
			
			if(Pattern.matches("count\\(\\w*\\)",xszd[1])){
				
				if(Pattern.matches("@\\#\\w*@\\#",xszd[0])){
					sql.append("* FROM (SELECT T.*,ROWNUM RN FROM (SELECT ");
				}
					
			}else{
				sql.append("* FROM (SELECT T.*,ROWNUM RN FROM (SELECT ");
			}
			
		}else{
		}

		System.out.println("sql:"+sql.toString());
		for (int i = 0; i < xszd.length - 1; i++) {
			sql.append(xszd[i].replaceAll("@?\\#", ""));
			sql.append(",");
		}
		int maxx = xszd.length - 1;
		sql.append(xszd[maxx]);
		sql.append(" from " + table +" WHERE 1=1 ");
		
		//具体的检索条件
		for (int j = 0; j < input.length; j++) {
			//判断字段是否为空
			if(input[j].equals("")||input[j].equals("#@#@#@#")){
				continue;
			}
			// 判断是否是date型
			if (Pattern.matches("@\\w+@",cxzd[j])) {
				sql.append(" and ");
				sql.append(cxzd[j].replaceAll("@", ""));
				sql.append(" between '");
				String[] a = input[j].split("#@#@#@#");
				sql.append(a[0]);
				sql.append("' and '");
				sql.append(a[1]);
				sql.append("'");
			} else if(Pattern.matches("%\\w+%",cxzd[j])){
				//模糊查询
				sql.append(" and ");
				sql.append(cxzd[j].replaceAll("%",""));
				sql.append(" like '%"+input[j]+"%'");
			}
			else if(Pattern.matches("\\$\\w+\\$",cxzd[j])){
				//判断是否为in查询
				sql.append(" and ");
				sql.append(cxzd[j].replaceAll("\\$",""));
				sql.append(" in("+ input[j] +")");
				
			}else if(Pattern.matches("@%\\w+\\@%",cxzd[j])){//设置最大返回数
				
			}
			else{
				sql.append(" and ");
				sql.append(cxzd[j]);
				sql.append(" like '%" + input[j] + "%'");
			}
		}
		
		////这里为where语句后的格式
		if(!Pattern.matches("#\\w*#",xszd[0])){
			
			if(Pattern.matches("count\\(\\w*\\)",xszd[1])){
				
				//显示为倒序
				sql.append(" group by "+xszd[0].replaceAll("@\\#", "") +" order by "+xszd[1]+" desc");
				
				if(Pattern.matches("@\\#\\w*@\\#",xszd[0])){
					sql.append(") T WHERE ROWNUM < ? )");
					sql.append(" WHERE RN >= ?"); 
				}
				
			}else{
				//2011-6-12：按照时间倒叙输出信息
				for (int j = 0; j < cxzd.length; j++) {
					if(Pattern.matches("@\\w+@",cxzd[j])){
						sql.append(" order by "+cxzd[j].replaceAll("@", "")+" desc");
						break;
					}
				}
				
				sql.append(") T WHERE ROWNUM < ? )");
				sql.append(" WHERE RN >= ?");
			}
			
		}else{
			//2011-6-12：按照时间倒叙输出信息
			for (int j = 0; j < cxzd.length; j++) {
				if(Pattern.matches("@\\w+@",cxzd[j])){
					sql.append(" order by "+cxzd[j].replaceAll("@", "")+" desc");
					break;
				}
			}
		}

		try {
			pstmt = conn.prepareStatement(sql.toString()) ;
			 
			if(!Pattern.matches("#\\w*#",xszd[0])){
				
				if(Pattern.matches("count\\(\\w*\\)",xszd[1])){
					if(Pattern.matches("@\\#\\w*@\\#",xszd[0])){
						//当第一个检索字段为"#\\w#"型时，不分页查询
						int startIndex=(pageNo-1)*pageSize +1;
						pstmt.setInt(1, pageSize+startIndex) ;
						pstmt.setInt(2, startIndex) ;
					}
				}else{
					int startIndex=(pageNo-1)*pageSize +1;
					pstmt.setInt(1, pageSize+startIndex) ;
					pstmt.setInt(2, startIndex) ;
				}
			}else{
			}
			
			System.out.println("sql:"+sql.toString());
			rs = pstmt.executeQuery() ;
			
			while (rs.next()) {
				for (int d = 1; d <= xszd.length; d++) {
					if(rs.getString(d)==null){
						data.add("");
						continue;
					}
					//	去掉日期后面的.0
					if(Pattern.matches("\\d{4}-[01][0-9]-[0-3][0-9]\\s[0-1][0-9]\\:[0-5][0-9]\\:[0-5][0-9]\\.0", rs.getString(d))){
						data.add(rs.getString(d).replaceAll("\\.0", "").trim());
					}else{
						String temp = rs.getString(d);
//						temp = temp.replaceAll("\\\\","\\");
						data.add(JsonCharFilter(temp));
					}
				}
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		return data;
	}
	
	/**
	 * 不分页查询，这个查询第一个检索字段(xszd[0])的为"#\\w*#"型
	 * @param table
	 * @param xszd
	 * @param cxzd
	 * @param input
	 * @return
	 */
	public List<String> searchLogNoPage(String table, String[] xszd,String[] cxzd, String[] input) {
		return this.searchLogS(table, xszd, cxzd, input,0,0);
	}
	
	/**
	 * @description 删除选中的日志
	 * @param squ 选中日志的squ
	 * 
	 * @return void
	 */
	public void deleteLog(String table, String[] cxzd, String[] input){
		Connection conn = null;
		PreparedStatement pstmt = null ;
		
		try {
			conn = datasource.getConnection();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		//获得结果数,因为要翻页所以得data的size（）在变化，所以得加个固定的长度大小
		StringBuffer sqlcount = new StringBuffer("delete from ");
		
		sqlcount.append(table);
		
		for (int t= 0;t<input.length;t++){
//			System.out.println(input[t]);
			if(!input[t].equals("")&&!input[t].equals("#@#@#@#")){
				sqlcount.append(" where ");
				break;
			}
		}
		
		for (int c = 0; c < input.length; c++) {
			//判断字段是否为空
			if(input[c].equals("")||input[c].equals("#@#@#@#")){
				continue;
			}
			// 判断是否是date型
			if (Pattern.matches("@\\w+@",cxzd[c])) {
				sqlcount.append(cxzd[c].replaceAll("@", ""));
				sqlcount.append(" between to_date('");
				String[] a = input[c].split("#@#@#@#");
				sqlcount.append(a[0]);
				sqlcount.append("','yyyy-mm-dd hh24:mi:ss') and to_date('");
				sqlcount.append(a[1]);
				sqlcount.append("','yyyy-mm-dd hh24:mi:ss')");
			} else if(Pattern.matches("%\\w+%",cxzd[c])){
				//模糊查询
				sqlcount.append(cxzd[c].replaceAll("%",""));
				sqlcount.append(" like '%"+input[c]+"%'");
			}else if(Pattern.matches("\\$\\w+\\$",cxzd[c])){
				//删除squ用in
				sqlcount.append(cxzd[c].replaceAll("\\$",""));
				sqlcount.append(" in("+ input[c] +")");
			}	
			else{
				
				sqlcount.append(cxzd[c]);
				sqlcount.append("='" + input[c] + "'");
			}
			///			//判断是否加上“and”，如果当期字段的下个字段不是空或者“#@#@#@#”，且是小于倒数第二个字段，则在其后面加上“and”
			if(c<cxzd.length){
				for(int m = 1; m<cxzd.length-c;m++){
					if(input[c+m].equals("")||input[c+m].equals("#@#@#@#")){
						continue;
					}else{
						sqlcount.append(" and ");
						break;
					}
				}
				
			}
		
		}
		
		try {
			pstmt = conn.prepareStatement(sqlcount.toString());
			
//			pstmt.executeQuery();
			pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			throw new DaoException(e1);
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException(e);
			}
		}
		
	}
	
	
	//过滤json特殊字符
	public static String JsonCharFilter(String sourceStr) 
	{ 
		sourceStr = sourceStr.trim();
		
	    StringBuffer buffer = new StringBuffer(sourceStr);
	    
	    //在替换的时候不要使用 String.replaceAll("\\","\\\\"); 这样不会达到替换的效果 因为这些符号有特殊的意义,在正则     ///表达式里要用到
	    int i = 0;
	    while (i < buffer.length()) {
	      if (buffer.charAt(i) == '\\'||buffer.charAt(i) == '\"') {
	         buffer.insert(i, '\\');
	         	i += 2;
	        } else {
	        	i++;
	        }
	    }
	    
	    sourceStr = buffer.toString().replaceAll("\b", ""); 
	    sourceStr = sourceStr.replaceAll("\t", ""); 
		sourceStr = sourceStr.replaceAll("\n", ""); 
	    sourceStr = sourceStr.replaceAll("\f", ""); 
		sourceStr = sourceStr.replaceAll("\r", ""); 
	    return sourceStr; 
	}

	
	
	public  List doQueryAgain(String sql,int dbsSqu,int maxRows,int pageSize,int pageNo){
		 ArrayList retrunList = new ArrayList();
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		ResultSetMetaData rsmd = null ;
		try {
		conn = getConnByDbsSqu(dbsSqu);
		//查询总条数   maxRows = -1 表示 返回所有记录
		if(maxRows != -1){
			
			//jk查询重现时，以免当出现order by时查询出错
			sql = sql.toUpperCase();
			
			if(sql.indexOf("ORDER BY")!=-1){
				String	sqlOrd=sql.substring(sql.indexOf("ORDER BY"));
//				sql = sql.substring(sql.indexOf("FROM"),sql.indexOf("ORDER BY"));
				pstmt = conn.prepareStatement("SELECT COUNT(*) " +sql.substring(sql.indexOf("FROM"),sql.indexOf("ORDER BY"))+" AND ROWNUM <= ? "+sqlOrd) ;
			}else{
				pstmt = conn.prepareStatement("SELECT COUNT(*) " +sql.substring(sql.indexOf("FROM"))+" AND ROWNUM <= ?") ;
				
			}
			
			pstmt.setInt(1, maxRows) ;	
		}else{
			pstmt = conn.prepareStatement("SELECT COUNT(*) " +sql.substring(sql.indexOf("FROM"))) ;
		}
		
		rs = pstmt.executeQuery() ;
		int totalRows = 0 ;
		if(rs.next()){
			totalRows = rs.getInt(1);
		}
		
		retrunList.add(totalRows) ;// 设置总记录数
		// 获取分页数据
		StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM (SELECT T.*,ROWNUM R1 FROM ");
		sqlBuffer.append("("+sql+") T WHERE ROWNUM < ? )");
		sqlBuffer.append(" WHERE R1 >= ?");
		pstmt = conn.prepareStatement(sqlBuffer.toString()) ;
		//设置参数 
		int startIndex=pageNo*pageSize +1;
		pstmt.setInt(1, pageSize+startIndex) ;
		pstmt.setInt(2, startIndex) ;
		
		rs = pstmt.executeQuery() ;
		rsmd = rs.getMetaData();
		List list2 = new ArrayList() ;
		// 封装数据
		while(rs.next()){
			Map map = new HashMap();
			for(int i = 1 ;i<=rsmd.getColumnCount();i++){
					String strVal = rs.getString(i) ;
					if(strVal!=null && !"".equals(strVal.trim())){
						if(strVal.length() == 19 && strVal.substring(11).equals("00:00:00")){
							strVal = strVal.substring(0,10);
						}
					}else{
						strVal="";
					}
					map.put(rsmd.getColumnName(i), strVal) ; 
			}
			list2.add(map) ;
		}
		retrunList.add(list2) ;// 设置分页数据
	} catch (Exception e) {
		throw new DaoException(e);
	} finally {
		try {
			// 关闭连接
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	return retrunList ;
	}
	
	
	/**
	 * @description 根据数据源编号获取 连接
	 * @param 数据源编号 
	 * @return void
	 */
	private Connection getConnByDbsSqu(int squ){
		Connection conn1 = null ;
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		try {
			// 获取连接
			conn = datasource.getConnection() ;
			pstmt = conn.prepareStatement("SELECT * FROM SSP_TREGDATASOURCE WHERE SQU = ?") ;
			pstmt.setInt(1, squ) ;
			rs = pstmt.executeQuery() ;
			if(rs.next()){
				Class.forName(rs.getString("dbclass")) ;
				conn1 = DriverManager.getConnection(rs.getString("dbUrl"), rs.getString("dbuser"), rs.getString("dbpwd")) ;
			}
		} catch (Exception e) {
			throw new DaoException(e);
		} finally{
			try {
				if(rs!=null) rs.close() ;
				if(pstmt!=null) pstmt.close() ;
				if(conn!=null) conn.close() ;
			} catch (Exception e) {
				throw new DaoException(e);
			}
		}
		return conn1 ;
	}
	
	
	
}

