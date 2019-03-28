package monitor.goodsHouse.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import monitor.goodsHouse.bean.entity.Cpmxb;
import monitor.goodsHouse.bean.entity.Zhbj;
import monitor.goodsHouse.bean.vo.ZhbjVo;
import monitor.user.bean.vo.PageInfoVo;

/**
 *@ClassName
 *@dataTime 2017-10-14-下午2:42:22
 *@version
 *@author:唐青
 *@since
 */
public interface IStentsService {
	/**
	 * 
	  * getStentsMenu:(左侧部件菜单).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-17 下午6:36:40
	  * @param squ
	  * @return
	  * @throws Exception List<Map<String,String>>
	  * @since JDK 1.7
	 */
	public List<Map<String, String>> getStentsMenu(String squ,String type)
			throws Exception;
	/**
	 * 
	  * selectChildCp:(获取产品部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-17 下午7:33:23
	  * @param page
	  * @param squ
	  * @return
	  * @throws SQLException PageInfoVo
	  * @since JDK 1.7
	 */
	public PageInfoVo selectChildCp(PageInfoVo page,String squ,String key) throws Exception;
	
	/**
	 * 
	  * addCp:(添加产品部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-17 下午7:34:50
	  * @param cpmxb
	  * @return
	  * @throws SQLException int
	  * @since JDK 1.7
	 */
	public int addCp(Cpmxb cpmxb) throws Exception;
	
	/**
	 * 
	  * editCp:(修改产品部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-17 下午7:35:52
	  * @param cpmxb
	  * @return
	  * @throws SQLException int
	  * @since JDK 1.7
	 */
	public int editCp(Cpmxb cpmxb) throws Exception;
	
	/**
	 * 
	  * deleteCp:(修改产品部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-17 下午7:36:57
	  * @param cpmxb
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	public int deleteCp(String squ)throws Exception;
	
	/**
	 * 
	  * selectStentsBySqu:(获取部件详情).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午6:16:24
	  * @param squ
	  * @return
	  * @throws Exception List<Cpmxb>
	  * @since JDK 1.7
	 */
	public List<Cpmxb> selectStentsBySqu(String squ)throws Exception;
	
	/**
	 * 
	  * addBatchStents:(批量导入不加).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午6:16:44
	  * @param list
	  * @throws Exception void
	  * @since JDK 1.7
	 */
	public void addBatchStents(List<Cpmxb> list) throws Exception;
	
	/**
	 * 
	  * selectZhbj:(查询组合部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午6:17:03
	  * @param page
	  * @param squ
	  * @return
	  * @throws Exception PageInfoVo
	  * @since JDK 1.7
	 */
	public PageInfoVo selectZhbj(PageInfoVo page,String squ,String key) throws Exception ;
	
	
	
	/**
	 * 
	  * selectZhbjInfo:(查询组合部件菜单).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午6:34:19
	  * @param squ
	  * @return
	  * @throws Exception List<Zhbj>
	  * @since JDK 1.7
	 */
	public List<Zhbj> selectZhbjInfo(String squ) throws Exception ;
	
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
	public List<Map<String, String>> selectLbInfo(String id) throws Exception;
	/**
	 * 
	  * queryBjInfo:(添加组合部件时获取部件信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-26 下午4:16:41
	  * @param id
	  * @return
	  * @throws Exception List<Map<String,String>>
	  * @since JDK 1.7
	 */
	public PageInfoVo queryBjInfo(PageInfoVo page,String id,String type) throws Exception;
	
	/**
	 * 
	  * selectZhbjXq:(获取组合部件详情).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-26 下午4:57:01
	  * @param page
	  * @param squ
	  * @return
	  * @throws Exception PageInfoVo
	  * @since JDK 1.7
	 */
	public  PageInfoVo selectZhbjXq(PageInfoVo page ,String squ,String key) throws Exception;
	
	/**
	 * 
	  * selectSingleZhbj:(获取组合部件的基本信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-1-23 下午5:26:48
	  * @param squ
	  * @return
	  * @throws Exception List<Map<String,String>>
	  * @since JDK 1.7
	 */
	public List<Map<String,String>> selectSingleZhbj(String squ) throws Exception;
	/**
	 * 
	  * addZhbj:(添加组合部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-27 上午11:22:12
	  * @param zhbjVo
	  * @throws Exception void
	  * @since JDK 1.7
	 */
	public void addZhbj(ZhbjVo zhbjVo) throws Exception;
	
	/**
	 * 
	  * updateBuJian:(修改组合部件中的部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-30 下午7:07:47
	  * @param zhbjVo
	  * @throws Exception void
	  * @since JDK 1.7
	 */
	public void updateBuJian(ZhbjVo zhbjVo)throws Exception;
	
	/**
	 * 
	  * deleteBuJian:(删除组合部件中的部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-30 下午7:24:52
	  * @param squ
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	public void deleteBuJian(String squ) throws Exception;
	/**
	 * 
	  * addBuJian:(添加组合部件中的部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-30 下午7:53:58
	  * @param zhbjVo
	  * @throws SQLException void
	  * @since JDK 1.7
	 */
	public void addBuJian(String[] arry,String squ) throws Exception;
	
	/**
	 * 
	  * updateZhbj:(修改组合部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-31 下午6:51:40
	  * @param zhbjVo
	  * @throws Exception void
	  * @since JDK 1.7
	 */
	public void updateZhbj(ZhbjVo zhbjVo) throws Exception;
	
	/**
	 * 
	  * deleteZhbj:(删除组合部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-11-1 下午6:43:56
	  * @param squ
	  * @return
	  * @throws Exception int
	  * @since JDK 1.7
	 */
	public String deleteZhbj(String squ) throws Exception;
	
	public List<Zhbj> getZhbjBysqu(String squ) throws Exception;
	
	
}
