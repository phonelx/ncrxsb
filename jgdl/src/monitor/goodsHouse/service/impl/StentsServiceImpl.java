package monitor.goodsHouse.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import monitor.goodsHouse.bean.entity.Cpmxb;
import monitor.goodsHouse.bean.entity.Zhbj;
import monitor.goodsHouse.bean.vo.ZhbjVo;
import monitor.goodsHouse.dao.IStentsDao;
import monitor.goodsHouse.dao.impl.StentsDaoImpl;
import monitor.goodsHouse.service.IStentsService;
import monitor.user.bean.vo.PageInfoVo;

/**
 *@ClassName
 *@dataTime 2017-10-14-下午2:43:48
 *@version
 *@author:唐青
 *@since
 */
public class StentsServiceImpl implements IStentsService{
	@Resource(name = "stentsDao")
	private StentsDaoImpl stentsDao;


	/**
	 * 左侧的部件系列菜单
	  * getStentsMenu:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:17:33
	  * @param squ
	  * @return
	  * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getStentsMenu(String squ,String type) throws Exception {
		
		return stentsDao.getStentsMenu(squ,type);
	}

	/**
	 * 获取产品部件
	  * selectChildCp:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:17:55
	  * @param page
	  * @param squ
	  * @param key
	  * @return
	  * @throws Exception
	 */
	@Override
	public PageInfoVo selectChildCp(PageInfoVo page, String squ,String key)
			throws Exception {
		try {
			return  stentsDao.selectChildCp(page, squ,key);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	/**
	 * 添加产品部件
	  * addCp:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:18:05
	  * @param cpmxb
	  * @return
	  * @throws Exception
	 */
	@Override
	public int addCp(Cpmxb cpmxb) throws Exception {
		try {
			return  stentsDao.addCp(cpmxb);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 修改产品部件
	  * editCp:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:18:13
	  * @param cpmxb
	  * @return
	  * @throws Exception
	 */
	@Override
	public int editCp(Cpmxb cpmxb) throws Exception {
		try {
			return  stentsDao.editCp(cpmxb);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 修改产品部件
	  * deleteCp:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:18:20
	  * @param squ
	  * @throws Exception
	 */
	@Override
	public int deleteCp(String squ) throws Exception {
		try {
			return stentsDao.deleteCp(squ);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	/**
	 * 获取部件详情
	  * selectStentsBySqu:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:18:29
	  * @param squ
	  * @return
	  * @throws Exception
	 */
	@Override
	public List<Cpmxb> selectStentsBySqu(String squ) throws Exception {
		
		return stentsDao.selectStentsBySqu(squ);
	}
/**
 * 批量导入部件
  * addBatchStents:接口实现
  * @author 唐青
  * @date  2017-10-26 下午4:18:40
  * @param list
  * @throws Exception
 */
	@Override
	public void addBatchStents(List<Cpmxb> list) throws Exception {
		stentsDao.addBatchStents(list);
		
	}

	/**
	 * 查询组合部件
	  * selectZhbj:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:19:05
	  * @param page
	  * @param squ
	  * @return
	  * @throws Exception
	 */
	@Override
	public PageInfoVo selectZhbj(PageInfoVo page,String squ,String key) throws Exception {
		
		return stentsDao.selectZhbj(page,squ,key);
	}

	/**
	 * 查询组合部件菜单
	  * selectZhbjInfo:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:19:14
	  * @param squ
	  * @return
	  * @throws Exception
	 */
	@Override
	public List<Zhbj> selectZhbjInfo(String squ) throws Exception {
		
		return stentsDao.selectZhbjInfo(squ);
	}

	/**
	 * 加组合部件是获取部件系列信息
	  * selectLbInfo:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:19:24
	  * @param id
	  * @return
	  * @throws Exception
	 */
	@Override
	public List<Map<String, String>> selectLbInfo(String id) throws Exception {
		
		return stentsDao.selectLbInfo(id);
	}

	/**
	 * 添加组合部件时获取部件信息
	  * queryBjInfo:接口实现
	  * @author 唐青
	  * @date  2017-10-26 下午4:19:33
	  * @param id
	  * @return
	  * @throws Exception
	 */
	@Override
	public PageInfoVo queryBjInfo(PageInfoVo page,String id,String type) throws Exception {
		
		return stentsDao.queryBjInfo(page,id,type);
	}
/**
 * 获取组合部件详情
  * selectZhbjXq:接口实现
  * @author 唐青
  * @date  2017-10-27 上午11:43:18
  * @param page
  * @param squ
  * @return
  * @throws Exception
 */
	public  PageInfoVo selectZhbjXq(PageInfoVo page ,String squ,String key) throws Exception{
		return stentsDao.selectZhbjXq(page, squ,key);
	}
	
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
	public List<Map<String,String>> selectSingleZhbj(String squ) throws Exception{
		return stentsDao.selectSingleZhbj(squ);
	}
/**
 * 添加组合部件
  * addZhbj:接口实现
  * @author 唐青
  * @date  2017-10-27 上午11:43:39
  * @param zhbjVo
  * @throws Exception
 */
	@Override
	public void addZhbj(ZhbjVo zhbjVo) throws Exception {
		stentsDao.addZhbj(zhbjVo);   
		
	}
/**
 * 修改组合部件中的部件
  * updateBuJian:接口实现
  * @author 唐青
  * @date  2017-10-30 下午7:09:19
  * @param zhbjVo
  * @throws Exception
 */
@Override
public void updateBuJian(ZhbjVo zhbjVo) throws Exception {
	stentsDao.updateBuJian(zhbjVo);
}

@Override
public void deleteBuJian(String squ) throws Exception {
	stentsDao.deleteBuJian(squ);
	
}

/**
 * 添加组合部件中的部件
  * addBuJian:接口实现
  * @author 唐青
  * @date  2017-10-30 下午7:54:44
  * @param zhbjVo
  * @throws Exception
 */
@Override
public void addBuJian(String[] arry,String squ) throws Exception {
	stentsDao.addBuJian(arry,squ);
	
}

/**
 * 修改组合部件
  * updateZhbj:接口实现
  * @author 唐青
  * @date  2017-10-31 下午6:52:09
  * @param zhbjVo
  * @throws Exception
 */
@Override
public void updateZhbj(ZhbjVo zhbjVo) throws Exception {
	stentsDao.updateZhbj(zhbjVo);
	
}

@Override
public String deleteZhbj(String squ) throws Exception {
	
	return stentsDao.deleteZhbj(squ);
}

@Override
public List<Zhbj> getZhbjBysqu(String squ) throws Exception {
	
	return stentsDao.getZhbjBysqu(squ);
};
	
}