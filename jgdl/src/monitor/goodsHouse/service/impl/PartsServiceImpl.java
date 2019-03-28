package monitor.goodsHouse.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import monitor.goodsHouse.bean.entity.Gdlx;
import monitor.goodsHouse.bean.entity.Wzzdb;
import monitor.goodsHouse.dao.IPartsDao;
import monitor.goodsHouse.dao.impl.PartsDaoImpl;
import monitor.goodsHouse.service.IPartsService;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName
 * @dataTime 2017-10-14-下午2:43:24
 * @version
 * @author:唐青
 * @since
 */
public class PartsServiceImpl implements IPartsService {
	@Resource(name = "partsDao")
	private PartsDaoImpl partsDao;

	public List<Map<String, String>> getPartsMenu(String squ,String type) throws Exception {
		try {
			return partsDao.getPartsMenu(squ,type);
		} catch (Exception e) {
			throw new Exception("加载部件目录失败" + e.getMessage());
		}
	}

	/*public IPartsDao getPartsDao() {
		return partsDao;
	}

	public void setPartsDao(IPartsDao partsDao) {
		this.partsDao = partsDao;
	}*/

	@Override
	public PageInfoVo selectChildMenu(PageInfoVo page, String squ, String key)
			throws Exception {
		try {
			return partsDao.selectChildMenu(page, squ, key);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * 添加子目 addMenu:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-10-16 下午6:36:17
	 * @param wzz
	 * @throws Exception
	 */
	@Override
	public void addMenu(Wzzdb wzz) throws Exception {
		try {
			partsDao.addMenu(wzz);
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

	}

	@Override
	public void updateMenu(Wzzdb wzz) throws Exception {
		try {
			partsDao.updateMenu(wzz);
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

	}

	@Override
	public int delChildMenu(String squ) throws Exception {
		try {
			return partsDao.delChildMenu(squ);
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

	}

	/**
	 * 批量导入部件目录
	  * addBatchParts:接口实现
	  * @author 唐青
	  * @date  2017-11-9 下午5:08:47
	  * @param list
	  * @throws Exception
	 */
	@Override
	public void addBatchParts(List<Wzzdb> list) throws Exception {
		partsDao.addBatchParts(list);
	}

	/**
	 * 批量导入管道类型
	  * addBatchGdlx:接口实现
	  * @author 唐青
	  * @date  2017-11-9 下午5:09:05
	  * @param list
	  * @throws Exception
	 */
	@Override
	public void addBatchGdlx(List<Gdlx> list) throws Exception {
		try {
			partsDao.addBatchGdlx(list);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	/**
	 * 批量导入安装方式
	  * addBatchAzfs:接口实现
	  * @author 唐青
	  * @date  2017-11-9 下午5:09:32
	  * @param list
	  * @throws Exception
	 */
	@Override
	public void addBatchAzfs(List<Gdlx> list) throws Exception {
		try {
			partsDao.addBatchAzfs(list);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	@Override
	public void addGdlx(Gdlx gdlx) throws Exception {

		try {
			partsDao.addGdlx(gdlx);
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	@Override
	public void addAzlx(Gdlx gdlx) throws Exception {
		try {
			partsDao.addAzlx(gdlx);
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	/**
	 * 分页查询安装方式 selectAzlx:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-11-7 下午2:34:05
	 * @param page
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfoVo selectAzlx(PageInfoVo page, String key) throws Exception {
		try {
			return partsDao.selectAzlx(page, key);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * 分页查询管道类型 selectGdlx:接口实现
	 * 
	 * @author 唐青
	 * @date 2017-11-7 下午2:34:17
	 * @param page
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageInfoVo selectGdlx(PageInfoVo page, String key,String pid) throws Exception {

		try {
			return partsDao.selectGdlx(page, key,pid);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 查询安装方式
	  * selectAzlxMenu:接口实现
	  * @author 唐青
	  * @date  2017-11-7 下午3:05:00
	  * @return
	  * @throws Exception
	 */
	@Override
	public List<Gdlx> selectAzlxMenu() throws Exception {

		try {
			return partsDao.selectAzlxMenu();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 查询管道类型
	  * selectGdlxMenu:接口实现
	  * @author 唐青
	  * @date  2017-11-7 下午3:05:11
	  * @return
	  * @throws Exception
	 */
	@Override
	public List<Gdlx> selectGdlxMenu(String pid) throws Exception {

		try {
			return partsDao.selectGdlxMenu(pid);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void updateGdlx(Gdlx gdlx) throws Exception {
		try {
			partsDao.updateGdlx(gdlx);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public void updateAzlx(Gdlx gdlx) throws Exception {
		try {
			partsDao.updateAzlx(gdlx);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public void deleteGdlx(String squ) throws Exception {
		try {
			partsDao.deleteGdlx(squ);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	/**
	  * getParamConfig:获取参数配置对象
	  * @author 黄月
	  * @date  2018-2-2 下午4:04:48
	  * @param search 查询条件
	  * @param param 查询参数 --------  param = 'search'
	  * @return
	  * @throws SQLException 
	*/
	@Override
	public List<Wzzdb> getParamConfig(String search, String param)
			throws SQLException {
		if ("".equals(param) || param == null || "".equals(search)) {
			throw new NullPointerException();
		}
		return partsDao.getParamConfig(search, param);
	}

	

}
