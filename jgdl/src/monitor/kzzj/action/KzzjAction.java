/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:KzzjAction.java
	* Date:2017-10-17下午4:58:48   
	***/
package monitor.kzzj.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONObject;

import monitor.common.BaseAction;
import monitor.common.exception.DaoException;
import monitor.common.util.Constant;

import monitor.kzzj.bean.entity.KzzjBean;
import monitor.kzzj.service.IKzzjService;
import monitor.kzzj.service.impl.KzzjServiceImpl;


import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:KzzjAction
 * @dateTime: 2017-10-17 下午4:58:48
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public class KzzjAction  extends BaseAction implements ModelDriven<KzzjBean> {
	
	
	

	/**
	  * @Fields  serialVersionUID:TODO(用一句话描述这个变量表示什么)
	  * @since JDK1.6
	*/
	private static final long serialVersionUID = 1L;
	/**
	 * service注入
	 */
	@Resource(name = "kzzjService")
	private KzzjServiceImpl kzzjService;
	
	

	/**
	 * @Fields pageVo:分页工具
	 * @since JDK1.6
	 */
	private PageInfoVo pageVo = new PageInfoVo();
	
	
	
	public PageInfoVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageInfoVo pageVo) {
		this.pageVo = pageVo;
	}


	/**
	 * bean 
	 */
	private KzzjBean kzzj = new KzzjBean();
	
	private String dxSqu;
	
	private File file;
	
	
	/**
	 * showDeptIndex:跳转到抗震支架基本配置
	 * 
	 * @author: 
	 * @dateTime: 2015-8-13 下午8:59:01
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since JDK 1.6
	 */
	
	public String showKzzjIndex() {
		
		List<Map<String, String>> listMenu;
		
		try {
			listMenu = kzzjService.getZjMenu("-1","ZJLX");
			getRequest().setAttribute("slist", listMenu);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * loadCategoryMenu:抗震支架列表
	 * @author: 张敏
	 * @dateTime: 2015-6-23 下午7:42:50
	 * @param:
	 * @return: void
	 * @throws
	 * @since JDK 1.6
	 */
	public void listKzzj() {
		// 每页显示条数
		int pageSize = Integer.parseInt(getRequest().getParameter(
				Constant.STRROWS));
		// 当前页
		int pageNo = Integer.parseInt(getRequest().getParameter(
				Constant.STRPAGE));
		String searchKey = getRequest().getParameter("searchKey");
		String lxsqu = getRequest().getParameter("lxsqu");
		
		this.pageVo.setPageNumber(pageNo);
		this.pageVo.setPageSize(pageSize);
		
		try {
			pageVo = kzzjService.listKzzj(pageVo, searchKey,lxsqu);
			
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
		writeJsonBack(jsonObj.toString());
	}
	
	
	/**
	 * 
	  * editKzzj:修改抗震支架.<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 康正秋
	  * @dateTime: 2017-10-20 下午4:53:48
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	 */
	public  void editKzzj(){
		
		
		String path = getRequest().getSession().getServletContext()
				.getRealPath("/");
		File prjPath = new File(path);
		File sourcePath = new File(prjPath.getParentFile().getAbsoluteFile()
				+ "/upload");
		if (!sourcePath.exists()) {
			sourcePath.mkdirs();
		}

		String fileName = null;
		String imgsrc = null;
		try {
			if (file == null) {
				fileName = getRequest().getParameter("image");
			}else{
			String realName = file.getName();
			fileName = System.currentTimeMillis()
					+ realName.substring(realName.lastIndexOf("."));
			 imgsrc = sourcePath + "/" + fileName;
			
		
			// 复制文件
			InputStream is = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(imgsrc);
			byte b[] = new byte[1024 * 1024];
			int length = 0;
			while (-1 != (length = is.read(b))) {
				fos.write(b, 0, length);
			}
			fos.flush();
			fos.close();
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String dxSqu = getRequest().getParameter("kzzjDxsqu");
		String zjmc = getRequest().getParameter("zjmc") ;
		//String gdlx = getRequest().getParameter("gdlx") ; 
	//	String azfs = getRequest().getParameter("azfs") ;
		String zjxs = getRequest().getParameter("zjxs") ;
		String lxcs = getRequest().getParameter("lxcs") ;
		String szzt = getRequest().getParameter("szzt") ;
		String zym = getRequest().getParameter("zym") ;
		KzzjBean kzzjBean = new KzzjBean();
		
		kzzjBean.setDxSqu(dxSqu);
		kzzjBean.setDxmc(zjmc);
		//kzzjBean.setGdlx(gdlx);
		//.setAzfs(azfs);
		kzzjBean.setZjxs(zjxs);
		kzzjBean.setZjxs(zjxs);
		kzzjBean.setZp(fileName);
		kzzjBean.setLxcs(lxcs);
		kzzjBean.setSzzt(szzt);
		kzzjBean.setZym(zym);
		String result = kzzjService.editKzzj(kzzjBean);
		writeJsonBack(getText(result));	
		
	}
	
	
	public void deleteKzzj() {
		
		String result = kzzjService.delKzzj(kzzj.getDxSqu());
		writeJsonBack(getText(result));
		this.setOperationDescb("支架名称:"+kzzj.getDxmc());
	}
	/**
	 * 
	  * addKzzj:抗震支架添加.<br/>
	  * TODO(.<br/>
	  * @author: 康正秋
	  * @dateTime: 2017-10-24 上午10:08:23
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	 */
	
	public void addKzzj(){
		
		String path = getRequest().getSession().getServletContext()
				.getRealPath("/");
		File prjPath = new File(path);
		File sourcePath = new File(prjPath.getParentFile().getAbsoluteFile()
				+ "/upload");
		if (!sourcePath.exists()) {
			sourcePath.mkdirs();
		}

		String fileName = null;
		String imgsrc = null;
		try {

			String realName = file.getName();
			
			fileName = System.currentTimeMillis()
					+ realName.substring(realName.lastIndexOf("."));
			 imgsrc = sourcePath + "/" + fileName;
			
			
			// 复制文件
			InputStream is = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(imgsrc);
			byte b[] = new byte[1024 * 1024];
			int length = 0;
			while (-1 != (length = is.read(b))) {
				fos.write(b, 0, length);
			}
			fos.flush();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String zjmc = getRequest().getParameter("zjmc") ;
		//String gdlx = getRequest().getParameter("gdlx") ; 
		//String azfs = getRequest().getParameter("azfs") ;
		String zjxs = getRequest().getParameter("zjxs") ;
		
		String zjlx = getRequest().getParameter("zjlxsqu") ;
		String lxcs = getRequest().getParameter("lxcs") ;
		
		String szzt = getRequest().getParameter("szzt") ;
		String zym = getRequest().getParameter("zym") ;
		
	
		
		KzzjBean kzzjBean = new KzzjBean();
		kzzjBean.setDxmc(zjmc);
	//	kzzjBean.setGdlx(gdlx);
		//kzzjBean.setAzfs(azfs);
		kzzjBean.setZjxs(zjxs);
		kzzjBean.setZp(fileName);
		kzzjBean.setZjlx(zjlx);
		kzzjBean.setLxcs(lxcs);
		kzzjBean.setSzzt(szzt);
		kzzjBean.setZym(zym);
		
		String result = kzzjService.addKzzj(kzzjBean);
		writeJsonBack(getText(result));
		this.setOperationDescb("支架名称:"+kzzj.getDxmc());
	}
	
	
	
	
	public void queryKzzjBySqu() {
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();

		try {
			List<KzzjBean> list = kzzjService.queryKzzjBySqu(squ);
			json.accumulate("slist", list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writeJsonBack(json.toString());
	}
	
	
	
	
	
	
	public void searchLbmc(){
		
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		List<Map<String, String>> list = kzzjService.searchParentSquBySqu(squ);
		json.accumulate("list", list);
		writeJsonBack(json.toString());
	}
	
	
	public KzzjBean getKzzj() {
		return kzzj;
	}

	public void setKzzj(KzzjBean kzzj) {
		this.kzzj = kzzj;
	}

	
	public String getDxSqu() {
		return dxSqu;
	}


	public void setDxSqu(String dxSqu) {
		this.dxSqu = dxSqu;
	}



	/** 
	 * TODO 简单描述该方法的实现功能
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public KzzjBean getModel() {
		// TODO Auto-generated method stub
		return this.kzzj;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}
	
	
}
