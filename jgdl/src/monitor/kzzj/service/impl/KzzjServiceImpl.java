/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:KzzjServiceImpl.java
	* Date:2017-10-17下午4:44:35   
	***/
package monitor.kzzj.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jws.WebService;


import monitor.goodsHouse.bean.vo.BjVo;
import monitor.goodsHouse.bean.vo.ZhVo;
import monitor.kzzj.bean.entity.KzzjBean;
import monitor.kzzj.dao.IKzzjDao;
import monitor.kzzj.dao.impl.KzzjDaoImpl;
import monitor.kzzj.service.IKzzjService;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:KzzjServiceImpl
 * @dateTime: 2017-10-17 下午4:44:35
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
@WebService
public class KzzjServiceImpl implements IKzzjService {

	@Resource(name = "kzzjDao")
	private KzzjDaoImpl kzzjDao;
	
	
	

	
	@Override
	public PageInfoVo listKzzj(PageInfoVo pageVo, String searchKey,String lxsqu)throws SQLException {
		
		return kzzjDao.getListKzzj(pageVo, searchKey,lxsqu);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjJbpz.service.IKzzjService#addKzzj(monitor.kzzjJbpz.bean.entity.KzzjBean)
	 */
	@Override
	public String addKzzj(KzzjBean kzzj) {
		
		return kzzjDao.addKzzj(kzzj);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjJbpz.service.IKzzjService#delKzzj(java.lang.String)
	 */
	@Override
	public String delKzzj(String dxSqu){
		// TODO Auto-generated method stub
		return kzzjDao.delKzzj(dxSqu);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @return 
	 * @see monitor.kzzjJbpz.service.IKzzjService#editKzzj(monitor.kzzjJbpz.bean.entity.KzzjBean)
	 */
	@Override
	public String editKzzj(KzzjBean kzzj){
		
		return kzzjDao.editKzzj(kzzj);
		
		
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzj.service.IKzzjService#queryKzzjBySqu(java.lang.String)
	 */
	@Override
	public List<KzzjBean> queryKzzjBySqu(String squ) {
		// TODO Auto-generated method stub
		return kzzjDao.queryKzzjBySqu(squ);
	}

	@Override
	public List<Map<String, String>> getZjMenu(String squ, String type)
			throws Exception {
		// TODO Auto-generated method stub
		return kzzjDao.getZjMenu(squ, type);
	}

	@Override
	public List<Map<String, String>> searchParentSquBySqu(String squ) {
		// TODO Auto-generated method stub
		return kzzjDao.searchParentSquBySqu(squ);
	}

	/**
	 * 获取支架信息
	  * selectZjInfo:接口实现
	  * @author 唐青
	  * @date  2018-6-23 下午3:34:42
	  * @param zjsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<KzzjBean> selectZjInfo(String zjsqu) throws SQLException {
		
		return kzzjDao.selectZjInfo(zjsqu);
	}

	/**
	 * 部件信息
	  * selectBjInfo:接口实现
	  * @author 唐青
	  * @date  2018-6-23 下午3:34:54
	  * @param zjsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<BjVo> selectBjInfo(String zjsqu) throws SQLException {
		return kzzjDao.selectBjInfo(zjsqu);
	}

	/**
	 * 组合部件信息
	  * selectZhbjInfo:接口实现
	  * @author 唐青
	  * @date  2018-6-23 下午3:35:03
	  * @param zjsqu
	  * @return
	  * @throws SQLException
	 */
	@Override
	public List<ZhVo> selectZhbjInfo(String zjsqu) throws SQLException {
		return kzzjDao.selectZhbjInfo(zjsqu);
	}

	
}
