/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:KzzjMxpzServiceImpl.java
	* Date:2017-10-26下午3:38:05   
	***/
package monitor.kzzjMxpz.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import monitor.kzzjMxpz.bean.entity.Dxinfo;
import monitor.kzzjMxpz.bean.entity.Dxmxb;
import monitor.kzzjMxpz.dao.IKzzjMxpzDao;
import monitor.kzzjMxpz.dao.impl.KzzjMxpzDaoImpl;
import monitor.kzzjMxpz.service.IKzzjMxpzService;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:KzzjMxpzServiceImpl
 * @dateTime: 2017-10-26 下午3:38:05
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public class KzzjMxpzServiceImpl implements IKzzjMxpzService {
	
	@Resource(name = "kzzjMxpzDao")
	private KzzjMxpzDaoImpl kzzjMxpzDao;

	



	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#getPartsMenu(java.lang.String)
	 */
	@Override
	public PageInfoVo getPartsMenu(PageInfoVo page,String squ) throws Exception {
		try {
			return kzzjMxpzDao.getPartsMenu(page,squ);
		} catch (Exception e) {
			throw new Exception("加载支架目录失败"+e.getMessage());
		}
	}

	

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#addKzzjBj(monitor.kzzjMxpz.bean.entity.Dxmxb)
	 */
	@Override
	public String addKzzjBj(Dxmxb dxmxb) {
		
		return kzzjMxpzDao.addKzzjMxpz(dxmxb);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#delKzzjBj(java.lang.String, java.lang.String)
	 */
	@Override
	public String delKzzjBj(String dxsqu, String bjsqu) {
		
		return kzzjMxpzDao.delKzzjBj(dxsqu, bjsqu);
	}
	
	

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#editKzzjBj(monitor.kzzjMxpz.bean.entity.Dxmxb)
	 */
	@Override
	public String editKzzjBj(Dxmxb dxmxb) {
		
		return kzzjMxpzDao.editKzzjBj(dxmxb);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#selectZhbjInfo()
	 */
	@Override
	public List<Map<String, String>> selectZhbjInfo(String gdlx,String azfs) {
		// TODO Auto-generated method stub
		return kzzjMxpzDao.selectZhbjInfo(gdlx,azfs);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#selectZhbjInfoById(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> selectZhbjInfoById(String ZHSQU) {
		// TODO Auto-generated method stub
		return kzzjMxpzDao.selectZhbjInfoById(ZHSQU);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @throws SQLException 
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#getKzzjBjList(monitor.user.bean.vo.PageInfoVo, java.lang.String, java.lang.String, int)
	 */
	@Override
	public PageInfoVo getKzzjBjList(PageInfoVo pages, String dxsqu,
			String searchKey, int op) throws SQLException {
		
		return kzzjMxpzDao.getKzzjBjList(pages, dxsqu, op, searchKey);
	}
	
	@Override
	public PageInfoVo searchKzzjBjList(PageInfoVo pages, String dxsqu,
			String searchKey, int op) throws SQLException {
		
		return kzzjMxpzDao.searchKzzjBjList(pages, dxsqu, op, searchKey);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#loadKzzjBySqu(java.lang.String)
	 */
	@Override
	public List<Dxinfo> loadKzzjBySqu(String squ) {
		// TODO Auto-generated method stub
		return kzzjMxpzDao.loadKzzjBySqu(squ);
	}

	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see monitor.kzzjMxpz.service.IKzzjMxpzService#searchBjInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Map<String, String>> searchBjInfo(String id, String type,
			String azfs) throws Exception {
		
		return kzzjMxpzDao.searchBjInfo(id, type, azfs);
	}

	@Override
	public List<Map<String, String>> getZjMenu(String squ, String type)
			throws Exception {
		// TODO Auto-generated method stub
		return kzzjMxpzDao.getZjMenu(squ, type);
	}

	@Override
	public List<Map<String, String>> selectLbInfo(String id) throws Exception {
		// TODO Auto-generated method stub
		return kzzjMxpzDao.selectLbInfo(id);
	}

	/**
	 * 查询支架下组合部件中的普通部件
	  * selectBj:接口实现
	  * @author 唐青
	  * @date  2018-1-30 下午2:39:26
	  * @param id
	  * @return
	  * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectBj(String id) throws Exception {
		try {
			return kzzjMxpzDao.selectBj(id);
		} catch (Exception e) {
			throw new Exception();
		}
		
	}

	/**
	 * 查询相应支架类型所有的支架列表
	  * selectSeismicStents:接口实现
	  * @author 唐青
	  * @date  2018-2-2 下午3:40:33
	  * @param page
	  * @param id
	  * @return
	  * @throws Exception
	 */
	@Override
	public PageInfoVo selectSeismicStents(PageInfoVo page, String id)
			throws Exception {
		try {
			return kzzjMxpzDao.selectSeismicStents(page, id);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
