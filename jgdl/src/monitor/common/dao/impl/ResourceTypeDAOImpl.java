/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-12-18 上午11:47:47
*/
package monitor.common.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import monitor.common.bean.dto.ResourceTypeDto;
import monitor.common.dao.BaseDAO;
import monitor.common.dao.IResourceTypeDAO;
import monitor.common.exception.DaoException;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-12-18 上午11:47:47
 */
public class ResourceTypeDAOImpl extends BaseDAO implements IResourceTypeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/**
	 * 根据父资源类型squ查询所有子资源类型dtos
	 * @author:  cl 
	 * @param long parentSqu 父资源squ
	 * @return List<ResourceTypeDto> 所有子资源类型dtos的list
	 */
	public List<ResourceTypeDto> fetchResourceTypesByParentSQU(long parentSqu) {
		List<ResourceTypeDto> resourceTypeList = new ArrayList<ResourceTypeDto>();
		try {
			conn=datasource.getConnection();
			conn.setAutoCommit(false);
			
			String sql = "select * from ssp_tregentitytype t where t.parentsqu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,parentSqu);
			rs = pstmt.executeQuery();
			
			ResourceTypeDto resourceTypeDto = null;
			while(rs.next()){
				resourceTypeDto = new ResourceTypeDto();
				resourceTypeDto.setSqu(rs.getLong("SQU"));
				resourceTypeDto.setTypeName(rs.getString("typeName"));
				resourceTypeDto.setDescb(rs.getString("descb"));
				resourceTypeDto.setAbbr(rs.getString("abbr"));
				resourceTypeDto.setAddDatetime(rs.getString("addDatetime"));
				resourceTypeDto.setOrderno(rs.getInt("orderno"));
				resourceTypeDto.setParentSqu(rs.getLong("parentSqu"));
				resourceTypeList.add(resourceTypeDto);
			}
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
		return resourceTypeList;
	}

	/**
	 * 根据子资源类型查询父资源类型squ
	 * @author:  cl 
	 * @param 
	 * @return long 父资源类型squ
	 */
	public long fetchParentResourceTypesSqu(long squ) {
		long parentSqu = 0;
		try {
			conn=datasource.getConnection();
			conn.setAutoCommit(false);
			
			String sql = "select t.parentsqu from ssp_tregentitytype t where t.squ = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,squ);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				parentSqu = rs.getLong("parentSqu");
			}
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
		return parentSqu;
	}

    /* (non-Javadoc)
     * @see ssp.common.dao.IResourceTypeDAO#genResourceTypeTree()
     */
    @Override
    public String genResourceTypeTree() {
        StringBuffer treeStr =  new StringBuffer("")  ;
        try {
            conn=datasource.getConnection();
            conn.setAutoCommit(false);
            // 查询所有数据资源
            String sql = "SELECT SQU,TYPENAME,PARENTSQU FROM SSP_TREGENTITYTYPE T ORDER BY T.ORDERNO ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // 拼接成树形结构数据
            while(rs.next()){
                if(!"".equals(treeStr.toString())) treeStr.append(",");
                treeStr.append("{\"id\":\"").append(rs.getString("SQU"))
                .append("\",\"pId\":\"").append(rs.getString("PARENTSQU"))
                .append("\",\"name\":\"").append(rs.getString("TYPENAME")).append("\"}");
            }
        } catch (Exception e) {
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
        return treeStr.toString();
    }

}
