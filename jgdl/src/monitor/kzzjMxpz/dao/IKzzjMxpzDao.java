/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:IKzzjMxpzDao.java
	* Date:2017-10-26下午3:35:04   
	***/
package monitor.kzzjMxpz.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import monitor.kzzjMxpz.bean.entity.Dxinfo;
import monitor.kzzjMxpz.bean.entity.Dxmxb;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:IKzzjMxpzDao
 * @dateTime: 2017-10-26 下午3:35:05
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public interface IKzzjMxpzDao {
	
	public List<Map<String, String>> getZjMenu(String squ,String type)
			throws SQLException;
	
	/**
	 * 
	  * getPartsMenu:.<br/>
	  * TODO(加载支架目录).<br/>
	  * @author: 康正秋
	  * @dateTime: 2017-10-27 上午11:37:26
	  * @param: @param squ
	  * @param: @return
	  * @param: @throws SQLException
	  * @return: List<Map<String,String>>
	  * @throws
	  * @since JDK 1.6
	 */
	public PageInfoVo getPartsMenu(PageInfoVo pages,String squ)
			throws SQLException;
	
	
	/**
	 * 
	  * addKzzjMxpz:添加抗震支架部件.<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 康正秋
	  * @dateTime: 2017-11-2 上午9:04:15
	  * @param: @return
	  * @return: String
	  * @throws
	  * @since JDK 1.6
	 */
    public String addKzzjMxpz(Dxmxb dxmxb);
    /**
     * 删除抗震支架部件
      * delKzzjBj:(这里用一句话描述这个方法的作用).<br/>
      * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
      * @author: 康正秋
      * @dateTime: 2017-11-2 上午11:28:14
      * @param: @param dxsqu
      * @param: @param bjsqu
      * @param: @return
      * @return: String
      * @throws
      * @since JDK 1.6
     */
    public String delKzzjBj(String dxsqu, String bjsqu);
    
   
    
    /**
     * 编辑抗震支架部件明细
      * editKzzjBj:(这里用一句话描述这个方法的作用).<br/>
      * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
      * @author: 康正秋
      * @dateTime: 2017-11-3 下午2:29:06
      * @param: @param dxmxb
      * @param: @return
      * @return: String
      * @throws
      * @since JDK 1.6
     */
    public String editKzzjBj(Dxmxb dxmxb);
    
    /**
     * 查询所有组合部件
      * selectZhbjInfo:(这里用一句话描述这个方法的作用).<br/>
      * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
      * @author: 康正秋
      * @dateTime: 2017-11-6 下午3:40:36
      * @param: @return
      * @return: List<Map<String,String>>
      * @throws
      * @since JDK 1.6
     */
  public List<Map<String, String>> selectZhbjInfo(String gdlx,String azfs);
  
  /**
   * 通过组合部件ID查询组合部件
    * selectZhbjInfoById:(这里用一句话描述这个方法的作用).<br/>
    * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
    * @author: 康正秋
    * @dateTime: 2017-11-6 下午3:41:53
    * @param: @param ZHSQU
    * @param: @return
    * @return: List<Map<String,String>>
    * @throws
    * @since JDK 1.6
   */
  public List<Map<String, String>> selectZhbjInfoById(String ZHSQU);
  
  /**
   * 获取部件列表
    * getKzzjBjList:(这里用一句话描述这个方法的作用).<br/>
    * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
    * @author: 康正秋
    * @dateTime: 2017-11-7 上午10:32:08
    * @param: @param page
    * @param: @param dxsqu
    * @param: @param op
    * @param: @return
    * @return: PageInfoVo
    * @throws
    * @since JDK 1.6
   */
  public PageInfoVo getKzzjBjList(PageInfoVo page,String dxsqu,int op,String searchKey)throws SQLException;
 
  public PageInfoVo searchKzzjBjList(PageInfoVo page,String dxsqu,int op,String searchKey)throws SQLException;
  
  
  
  /**
   * 通过squ查询支架信息
    * loadKzzjBySqu:(这里用一句话描述这个方法的作用).<br/>
    * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
    * @author: 康正秋
    * @dateTime: 2017-11-16 上午11:07:14
    * @param: @param squ
    * @param: @return
    * @return: List<Dxinfo>
    * @throws
    * @since JDK 1.6
   */
  List<Dxinfo> loadKzzjBySqu(String squ);
  
  /**
   * 
    * searchBjInfo:(添加普通部件获取部件信息).<br/>
    * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
    * @author: 康正秋
    * @dateTime: 2017-11-16 下午4:39:01
    * @param: @param id
    * @param: @param type
    * @param: @param azfs
    * @param: @return
    * @param: @throws SQLException
    * @return: List<Map<String,String>>
    * @throws
    * @since JDK 1.6
   */
  public List<Map<String, String>> searchBjInfo(String id,String type,String azfs) throws SQLException;



  /**
	 * 
	  * selectLbInfo:(添加组合部件是获取部件系列信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午6:59:10
	  * @param id
	  * @return
	  * @throws SQLException List<Wzzdb>
	  * @since JDK 1.7
	 */
	public List<Map<String, String>> selectLbInfo(String id) throws SQLException;
	/**
	 * 
	  * selectBj:(查询支架下组合部件中的普通部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-1-29 下午3:25:59
	  * @param id
	  * @return
	  * @throws SQLException List<Map<String,String>>
	  * @since JDK 1.7
	 */
	public List<Map<String,String>> selectBj(String id) throws SQLException;
	
	/**
	 * 
	  * selectUnderZh:(查询相应支架类型所有的支架列表).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-1 下午2:36:28
	  * @param id 支架类型squ
	  * @return
	  * @throws SQLException List<Map<String,String>>
	  * @since JDK 1.7
	 */
	public PageInfoVo selectSeismicStents(PageInfoVo page,String id)throws SQLException;
	
	

}
