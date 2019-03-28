/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:kzzjDao.java
	* Date:2017-10-17下午2:08:44   
	***/
package monitor.kzzj.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


import monitor.goodsHouse.bean.entity.Cpmxb;
import monitor.goodsHouse.bean.entity.Zhbj;
import monitor.goodsHouse.bean.vo.BjVo;
import monitor.goodsHouse.bean.vo.ZhVo;
import monitor.kzzj.bean.entity.KzzjBean;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:kzzjDao
 * @dateTime: 2017-10-17 下午2:08:44
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public interface IKzzjDao {
	
	
	PageInfoVo getListKzzj(PageInfoVo page, String searchKey,String lxsqu) throws SQLException;

	String addKzzj(KzzjBean kzzjBean);
	
	String delKzzj(String dxSqu) ;
	
	String editKzzj(KzzjBean kzzj);
		

	List<KzzjBean> queryKzzjBySqu(String squ);
	
	/**
	 * 
	  * getZjMenu:(配置抗震支架是的支架类型目录  ).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-1-31 上午9:48:00
	  * @param squ
	  * @param type
	  * @return
	  * @throws SQLException List<Map<String,String>>
	  * @since JDK 1.7
	 */
	public List<Map<String, String>> getZjMenu(String squ,String type)throws SQLException;
	
	List<Map<String,String>> searchParentSquBySqu(String squ);
	
	
	/**
	 * 
	  * selectZjInfo:(获取支架信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-6-23 下午12:58:08
	  * @param zjsqu
	  * @return
	  * @throws SQLException List<KzzjBean>
	  * @since JDK 1.7
	 */
	List<KzzjBean>  selectZjInfo(String zjsqu)throws SQLException;
	
	/**
	 * 
	  * selectBjInfo:(部件信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-6-23 下午1:10:31
	  * @param zjsqu
	  * @return
	  * @throws SQLException List<Cpmxb>
	  * @since JDK 1.7
	 */
	List<BjVo>  selectBjInfo(String zjsqu)throws SQLException;
	
	/**
	 * 
	  * selectZhbjInfo:(组合部件信息   ).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-6-23 下午1:11:08
	  * @param zjsqu
	  * @return
	  * @throws SQLException List<Zhbj>
	  * @since JDK 1.7
	 */
	List<ZhVo>  selectZhbjInfo(String zjsqu)throws SQLException;
	
	/**
	 * 
	  * selectKzzjBySqu:(通过squ查询抗震支架).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-6-23 下午6:13:26
	  * @param squ
	  * @return KzzjBean
	  * @since JDK 1.7
	 */
	
	KzzjBean selectKzzjBySqu(String squ);
	
}
