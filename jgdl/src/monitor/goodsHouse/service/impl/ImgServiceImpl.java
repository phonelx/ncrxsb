package monitor.goodsHouse.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import monitor.goodsHouse.bean.entity.Img;
import monitor.goodsHouse.bean.entity.ImgFl;
import monitor.goodsHouse.dao.IImgDao;
import monitor.goodsHouse.dao.impl.ImgDaoImpl;
import monitor.goodsHouse.service.IImgService;
import monitor.user.bean.vo.PageInfoVo;

public class ImgServiceImpl implements IImgService{
	
	@Resource(name = "imgDao")
	private ImgDaoImpl imgDao;
	
	

	
	@Override
	public String addImgFl(ImgFl imgFl) {
		// TODO Auto-generated method stub
		return imgDao.addImgFl(imgFl);
	}

	@Override
	public int delImgFl(String squ) {
		// TODO Auto-generated method stub
		return imgDao.delImgFl(squ);
	}

	@Override
	public String editImgFl(ImgFl imgFl) {
		// TODO Auto-generated method stub
		return imgDao.editImgFl(imgFl);
	}

	@Override
	public List<Map<String, String>> getImgFlList() {
		// TODO Auto-generated method stub
		return imgDao.getImgFlList();
	}

	@Override
	public PageInfoVo getImgFlXq(PageInfoVo page, String key) {
		// TODO Auto-generated method stub
		return imgDao.getImgFlXq(page, key);
	}

	@Override
	public List<ImgFl> getImgFlBySqu(String squ) {
		// TODO Auto-generated method stub
		return imgDao.getImgFlBySqu(squ);
	}

	@Override
	public String addImg(Img img) {
		// TODO Auto-generated method stub
		return imgDao.addImg(img);
	}

	@Override
	public String delImg(String squ) {
		// TODO Auto-generated method stub
		return imgDao.delImg(squ);
	}

	@Override
	public String editImg(Img img) {
		// TODO Auto-generated method stub
		return imgDao.editImg(img);
	}

	@Override
	public PageInfoVo getImgList(PageInfoVo page, String flsqu, String key) {
		// TODO Auto-generated method stub
		return imgDao.getImgList(page, flsqu, key);
	}

}
