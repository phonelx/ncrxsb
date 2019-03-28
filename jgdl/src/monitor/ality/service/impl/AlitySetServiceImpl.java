package monitor.ality.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import monitor.ality.bean.entity.AlityBean;
import monitor.ality.dao.IAlitySetDao;
import monitor.ality.service.IAlitySetService;
import monitor.user.bean.vo.PageInfoVo;


/**
 *@ClassName
 *@dataTime 2017-10-13-下午1:50:47
 *@version
 *@author:唐青
 *@since
 */
public class AlitySetServiceImpl implements IAlitySetService{
	
	private IAlitySetDao alityDao;

	@Override
	public List<Map<String, Object>> fethParentMenu(String squ) throws SQLException {//1.加载功能模块
		return alityDao.fethParentMenu(squ);
	}
	@Override
	public PageInfoVo fethAlityMenu(String pSqu, PageInfoVo pageVo,String query)
			throws SQLException {
		
		return alityDao.fethAlityMenu(pSqu,pageVo,query);
	}
	@Override
	public int fetchMaxOrder(String pSqu) {
		
		return alityDao.fetchMaxOrder(pSqu);
	}
	public void setAlityDao(IAlitySetDao alityDao) {
		this.alityDao = alityDao;
	}
	@Override
	public int urlHendiadys(String urlName) {
		
		return alityDao.urlHendiadys(urlName);
	}
	@Override
	public int delParentMenu(String squ) {
		
		return alityDao.delParentMenu(squ);
	}
	@Override
	public int addParentMenu(String mainName) {
		return alityDao.addParentMenu(mainName);
	}
	@Override
	public void editParentMenu(String mainName, String squ) {
		alityDao.editParentMenu(mainName,squ);
	}
	@Override
	public int addAlityMenu(AlityBean ality) {
		return alityDao.addAlityMenu(ality);
	}
	@Override
	public void editAlityMenu(AlityBean ality) {
		alityDao.editAlityMenu(ality);
	}
	@Override
	public int delAlityMenu(String squ) {
		
		return alityDao.delAlityMenu(squ);
	}
	@Override
	public PageInfoVo fetchAlityUrl(String squ, PageInfoVo pageVo) throws SQLException {
		return alityDao.fetchAlityUrl(squ,pageVo);
	}
	@Override
	public void delAlityUrl(String squ,String doSqu) {
		 alityDao.delAlityUrl(squ,doSqu);
		
	}
	@Override
	public void editAlityUrl(AlityBean ality,String squ) {
		alityDao.editAlityUrl(ality,squ);
		
	}
	@Override
	public int addAlityUrl(AlityBean ality,String squ) {
		return alityDao.addAlityUrl(ality,squ);
	}




}

