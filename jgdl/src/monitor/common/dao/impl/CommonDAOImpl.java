package monitor.common.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import monitor.common.bean.entity.ErrorDataReportEntity;
import monitor.common.bean.entity.OperationEntity;
import monitor.common.bean.vo.DataSizeVo;
import monitor.common.dao.BaseDAO;
import monitor.common.dao.ICommonDAO;
import monitor.common.exception.DaoException;
import monitor.common.util.NumberUtil;
/** 
 * 公共数据库DAO
 * @author  cl
 * @datetime  2011-4-28 上午10:00:59
 */
public class CommonDAOImpl extends BaseDAO implements ICommonDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * 记录用户操作信息，存入数据库
	 * @author:  cl 
	 * @param 
	 * @return String
	 */
	public String recordOperationType(OperationEntity opEntity) {
		try {
			conn=datasource.getConnection();
			conn.setAutoCommit(false);

			String sql = "insert into SSP_TUserOperateInfo(userInfo,operateType,operateKeyWords,operateDescb,operateDatetime) " +
			"values(?,?,?,?,to_date(?,'yyyy-mm-dd HH24:mi:ss'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,opEntity.getUserInfo());
			pstmt.setInt(2,opEntity.getOperateType());
			pstmt.setString(3,opEntity.getOperateKeyWords());
			pstmt.setString(4,opEntity.getOperateDescb());
			pstmt.setString(5,opEntity.getOperateDatetime());
			System.out.println(opEntity.getUserInfo()+"-"+opEntity.getOperateType()+"-"+opEntity.getOperateKeyWords());
			System.out.println(opEntity.getOperateDescb()+"-"+opEntity.getOperateDatetime());
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}
			throw new DaoException(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return "saveSuccess";
	}

	/**
	 * 统计数据库中所有资源的记录总数 
	 * @author: cl 
	 * @param 
	 * @return String
	 */
	public String calculateAllFactourCounts(){
		long sum = 0;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer("select sum(counts) from ssp_tresourcesummarystatistic");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()){
				sum = rs.getLong(1);
			}
		} catch (Exception e) {
			System.out.println("==tresourcesummarystatistic_counts==");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}
			throw new DaoException(e);
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		
//		return ""+sum;
		return NumberUtil.round((double)sum/10000, "###.00万");
	}

	/**
	 * 统计资源库中所有资源占用的数据空间情况 
	 * @author: cl 
	 * @param 
	 * @return DataSizeVo
	 */
	public DataSizeVo calculateAllDataSize() {
		
		return null;
	}
	
	/**
	 * 首页保存错误数据信息 
	 * @author: yl 
	 * @param rer
	 * @return null
	 */
	public void saveErrorData(ErrorDataReportEntity rer) {
		try {
			// 获取连接
			conn = datasource.getConnection() ;
			// insert  sql
			StringBuffer sql = new StringBuffer("INSERT INTO SSP_ERRORDATAREPORT(")
			.append("REPORTDATE,USERNAME,TABLEID,DATAENTITYID,ERRORDATA,NOTE)") 
			.append("VALUES(sysdate,?,?,?,?,?)");
			// 添加数据源
			pstmt = conn.prepareStatement(sql.toString()) ;
			// 设置参数
			pstmt.setString(1, rer.getUserName());
			pstmt.setString(2, rer.getTableId());
			pstmt.setInt(3, rer.getDataEntityId());
			pstmt.setString(4, rer.getErrordata());
			pstmt.setString(5, rer.getNote()); 
			pstmt.executeUpdate() ;
		} catch (Exception e) {
			System.out.println("==ERRORDATAREPORT==");
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
	}
	
	
	/**
	 * 进行同名验证
	 * @param tableName 表名
	 * @param fileName 字段名
	 * @param filedName 需要验证的名字
	 * @return boolean(true|false)
	 */
	public boolean sameNameJudge(String tableName,String filedName,String nowUseName,String dbid,String tabid){
		Integer sumHave=0;
		try {
			// 获取连接
			conn = datasource.getConnection() ;
			// insert  sql
			StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE ").append(filedName).append(" ='").append(nowUseName).append("'");
			if(!"".equals(dbid) && tableName.equals("SSP_TREGENTITY")){
				sql.append("AND "+dbid.split(",")[0]+"='"+dbid.split(",")[1]+"'");
			}
			if(!"".equals(tabid)&& tableName.equals("SSP_TREGFIELD")){
				sql.append("AND "+tabid.split(",")[0]+"='"+tabid.split(",")[1]+"'");
			}
			//StringBuffer sql = new StringBuffer("SELECT COUNT(*) FORM ? WHERR ? ='?'");		
			pstmt = conn.prepareStatement(sql.toString()) ;
			// 设置参数
			//pstmt.setString(1, tableName);
			//pstmt.setString(2, filedName);
			//pstmt.setString(3, nowUseName);
			rs = pstmt.executeQuery();
			if(rs.next()){
				sumHave = rs.getInt(1);
			}
			conn.commit();
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

		if(sumHave!=0){
			return false;
		}else{
			return true;
		}
	}

    /* (non-Javadoc)
     * @see ssp.common.dao.ICommonDAO#checkSysStatus()
     */
    @Override
    public String checkSysStatus() {
        String msg = "0";
        Connection conn = null ;
        try {
            // 从数据源获取连接
            conn = datasource.getConnection() ;
            if(conn != null) msg = "1" ;  // 连接不为空
        } catch (Exception e) {
           msg = "0";
        } finally{
            try {
                // 关闭连接释放资源
                if(conn!=null) conn.close() ;
            } catch (SQLException e) {
                throw new DaoException("数据库异常",e);   
            }
        }
        return msg;
    }

	
}
