/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:KzzjMxpzAction.java
	* Date:2017-10-26下午2:49:04   
	***/
package monitor.kzzjMxpz.action;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import monitor.common.BaseAction;
import monitor.kzzjMxpz.bean.entity.Dxinfo;
import monitor.kzzjMxpz.bean.entity.Dxmxb;
import monitor.kzzjMxpz.service.IKzzjMxpzService;
import monitor.kzzjMxpz.service.impl.KzzjMxpzServiceImpl;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName:KzzjMxpzAction
 * @dateTime: 2017-10-26 下午2:49:04
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public class KzzjMxpzAction extends BaseAction{
	
	/**
	  * @Fields  serialVersionUID:TODO(用一句话描述这个变量表示什么)
	  * @since JDK1.6
	*/
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "kzzjMxpzService")
	private KzzjMxpzServiceImpl kzzjMxpzService;
	/**
	 * @Fields pageVo:分页工具
	 * @since JDK1.6
	 */
	private PageInfoVo pageVo = new PageInfoVo();
	
	private String dxsqu;
	

	public PageInfoVo getPageVo() {
		return pageVo;
	}


	public void setPageVo(PageInfoVo pageVo) {
		this.pageVo = pageVo;
	}


	public String getDxsqu() {
		return dxsqu;
	}


	public void setDxsqu(String dxsqu) {
		this.dxsqu = dxsqu;
	}





	/**
	 * showDeptIndex:跳转到抗震支架明细配置
	 * 
	 * @author: 
	 * @dateTime: 2015-8-13 下午8:59:01
	 * @param: @return
	 * @return: String
	 * @throws
	 * @since JDK 1.6
	 */
	
	public String showKzzjMxpzIndex() {
		
		
		List<Map<String, String>> listMenu;
		
		try {
			listMenu = kzzjMxpzService.getZjMenu("-1","ZJLX");
			getRequest().setAttribute("lists", listMenu);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public void getKzzjMenu(){
		
		String pageStr = "1";
		String rowsStr = "1000000000";
		String squ = getRequest().getParameter("zjlx");
		
		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}

		try {
			this.pageVo = kzzjMxpzService.getPartsMenu(pageVo,squ);
			//jsonObj.accumulate("pageVo", pageVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo); 

		writeJsonBack(jsonObj.toString());
	}
	
	
	public void getKzzjBjList(){
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");
		
		String dxsqu = getRequest().getParameter("dxsqu");
		int op =Integer.parseInt(getRequest().getParameter("op"));
		String searchKey = getRequest().getParameter("searchKey");
		
		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
		
		try {
			this.pageVo = kzzjMxpzService.searchKzzjBjList(pageVo, dxsqu, searchKey, op);
			//jsonObj.accumulate("pageVo", pageVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo); 
		writeJsonBack(jsonObj.toString());
		
	}
	
	public void queryBj() {
		JSONObject json= new JSONObject();
		String id = getRequest().getParameter("squ");
		List<Map<String, String>> list = null;
		try {
			list = kzzjMxpzService.selectBj(id);
			json.accumulate("row", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}
		
		writeJsonBack(json.toString());
	}
	
	public void addKzzjBj(){
		
		String bjsqu = getRequest().getParameter("bjsqu") ;
		String sl = getRequest().getParameter("sl") ; 
		String dxsqu = getRequest().getParameter("dxsqu") ;
		String bjop = getRequest().getParameter("bjop") ;
		String bjfl = getRequest().getParameter("bjfl");
		
		Dxmxb dxmxb = new Dxmxb();
		
		dxmxb.setBJSQU(bjsqu);
		dxmxb.setSL(Integer.parseInt(sl));
		dxmxb.setDXSQU(dxsqu);
		dxmxb.setBJLX(Integer.parseInt(bjop));//部件类型
		dxmxb.setBJFL(Integer.parseInt(bjfl));//部件分类
		
		String result = kzzjMxpzService.addKzzjBj(dxmxb);
		writeJsonBack(getText(result));
	}
	
	
	
	public void delKzzjBj(){
		
		String squ = getRequest().getParameter("bjsqu") ;//DXMXBSQU
		String dxsqu = getRequest().getParameter("dxsqu") ;
		
		String result =kzzjMxpzService.delKzzjBj(dxsqu, squ);
		
		writeJsonBack(getText(result));
	}
	
	
	public void editKzzjBj(){
		
		String squ = getRequest().getParameter("squ") ;
		String sl = getRequest().getParameter("sl") ; 
	
		Dxmxb dxmxb = new Dxmxb();
		
		dxmxb.setSQU(squ);
		dxmxb.setSL(Integer.parseInt(sl));
		String result = kzzjMxpzService.editKzzjBj(dxmxb);
		writeJsonBack(getText(result));
		
	}
	
	
	
	public void selectZhbjInfo(){
		//String gdlx = getRequest().getParameter("gdlx");
		//String azfs = getRequest().getParameter("azfs");
		
		
		JSONObject json = new JSONObject();
		try {
			List<Map<String, String>> listZhbj = kzzjMxpzService.selectZhbjInfo("" ,"");
			json.accumulate("state", "success");
			json.accumulate("list", listZhbj);
		} catch (Exception e) {
			json.accumulate("state", "wrong");
			json.accumulate("state", e.getMessage());
		}
	
		writeJsonBack(json.toString());
	}
	
	public void selectZhbjInfoByZhsqu(){
		String ZHSQU = getRequest().getParameter("ZHSQU") ;
		JSONObject json = new JSONObject();
		try {
			List<Map<String, String>> listZhbj = kzzjMxpzService.selectZhbjInfoById(ZHSQU);
			json.accumulate("state", "success");
			json.accumulate("list", listZhbj);
		} catch (Exception e) {
			json.accumulate("state", "wrong");
			json.accumulate("state", e.getMessage());
		}
		
		writeJsonBack(json.toString());
	}
	
	
	
	
	public void loadKzzjBySqu() {
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();

		try {
			List<Dxinfo> list = kzzjMxpzService.loadKzzjBySqu(squ);
			json.accumulate("list", list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writeJsonBack(json.toString());
	}
	
	/**
	 * 支架详细配置时添加普通部件获取部件信息
	  * searchBjInfo:(这里用一句话描述这个方法的作用).<br/>
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).<br/>
	  * @author: 康正秋
	  * @dateTime: 2017-11-17 上午9:26:03
	  * @param: 
	  * @return: void
	  * @throws
	  * @since JDK 1.6
	 */
	
	public void searchBjInfo(){
		String squ = getRequest().getParameter("squ");
		String type = getRequest().getParameter("type");
	//	String azfs = getRequest().getParameter("azfs");
	
		JSONObject json = new JSONObject();
		try {
			List<Map<String, String>> listMenu = kzzjMxpzService.searchBjInfo(squ,type,"");
			json.accumulate("state", "success");
			json.accumulate("list", listMenu);
		} catch (Exception e) {
			json.accumulate("state", "wrong");
			json.accumulate("state", e.getMessage());
		}
	//System.out.println(json.toString());
		writeJsonBack(json.toString());
	}
	
	
	/**
	 * 
	  * queryLbInfo:(添加组合部件是获取普通部件系列信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午7:20:45 void
	  * @since JDK 1.7
	 */
	public void searchLbInfo(){
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		try {
			List<Map<String, String>> listMenu = kzzjMxpzService.selectLbInfo(squ);
			json.accumulate("state", "success");
			json.accumulate("list", listMenu);
		} catch (Exception e) {
			json.accumulate("state", "wrong");
			json.accumulate("state", e.getMessage());
		}
		// System.out.println(json.toString());
		writeJsonBack(json.toString());
	}
	/**
	 * 
	  * querySeismicStents:(查询相应支架类型所有的支架列表).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2018-2-2 下午4:03:05 void
	  * @since JDK 1.7
	 */
	public void querySeismicStents(){
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");	
		String squ = getRequest().getParameter("squ");
		
		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
		
		try {
			this.pageVo = kzzjMxpzService.selectSeismicStents(pageVo, squ);
			//jsonObj.accumulate("pageVo", pageVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo); 
//System.out.println(jsonObj.toString());
		writeJsonBack(jsonObj.toString());
	}
}
