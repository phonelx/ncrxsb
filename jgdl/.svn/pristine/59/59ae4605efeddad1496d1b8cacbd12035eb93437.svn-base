/*
 * @copyright:  heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  Apr 18, 2011 5:32:19 PM
*/
package monitor.log.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import monitor.common.BaseAction;
import monitor.common.exception.BizException;
import monitor.common.util.StringConvert;
import monitor.log.service.ISearchLogService;

/** 
 * <description> 
 * @author  jk
 * @datetime  Apr 18, 2011 5:32:19 PM
 */
public class QueryUserLoginAction extends BaseAction{
	/** 
	 * @field serialVersionUID
	*/
	private static final long serialVersionUID = -4524028508976231111L;
	private ISearchLogService searchLogService = null;
	private String usertitle = "";
	private String loginIp = "";
	private String lgistartDatetime = "";
	private String lgiendDatetime = "";
	private String lgostartDatetime = "";
	private String lgoendDatetime = "";
	private String squ="";
	private String downFilePath = null;
	private String downFileName = null;
	// 最大访问数量
	private int maxRows ;
	
	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public ISearchLogService getSearchLogService() {
		return searchLogService;
	}

	public String getDownFilePath() {
		return downFilePath;
	}

	public void setDownFilePath(String downFilePath) {
		this.downFilePath = downFilePath;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public void setSearchLogService(ISearchLogService searchLogService) {
		this.searchLogService = searchLogService;
	}

	public String getUsertitle() {
		return usertitle;
	}

	public void setUsertitle(String usertitle) {
		if(usertitle!=null){
		this.usertitle = stringReplace(usertitle);
		setQueryCommandBack();
		}
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLgistartDatetime() {
		return lgistartDatetime;
	}

	public void setLgistartDatetime(String lgistartDatetime) {
		this.lgistartDatetime = lgistartDatetime;
	}

	public String getLgiendDatetime() {
		return lgiendDatetime;
	}

	public void setLgiendDatetime(String lgiendDatetime) {
		this.lgiendDatetime = lgiendDatetime;
	}

	public String getLgostartDatetime() {
		return lgostartDatetime;
	}

	public void setLgostartDatetime(String lgostartDatetime) {
		this.lgostartDatetime = lgostartDatetime;
	}

	public String getLgoendDatetime() {
		return lgoendDatetime;
	}

	public void setLgoendDatetime(String lgoendDatetime) {
		this.lgoendDatetime = lgoendDatetime;
	}
	public String getSqu() {
		return squ;
	}

	public void setSqu(String squ) {
		this.squ = squ;
	}

	public String showUserLoginPage(){
		return SUCCESS;
	}
	
	/**
	 * @description 向页面输出json数据格式
	 * @param 
	 * @return String
	 */
	public String listUsersInPage(){
		System.out.println("-----------------------------------");
		String []columns={"user_name","TERMINAL_ID","operate_time","num_id"};
		String LoginDatetime = lgistartDatetime+"#@#@#@#"+lgiendDatetime;
		String LogoutDatetime = lgostartDatetime+"#@#@#@#"+lgoendDatetime;
		String lgIP = "";//TERMINAL_ID
		if(!loginIp.equals("...")){
			lgIP = loginIp;
		}
		String []input={usertitle,lgIP,LoginDatetime,LogoutDatetime,String.valueOf(maxRows)};
		String []checkcols={"%user_name%","TERMINAL_ID","@operate_time@","@operate_time@","@%maxRows@%"};
		List<String> results = new ArrayList<String>();
		
		// 每页显示条数
		int pageSize = Integer.parseInt(getRequest().getParameter("rows")) ;
		// 当前页
		int pageNo = Integer.parseInt(getRequest().getParameter("page")) ;		
		results = searchLogService.searchLogS("log_info", columns, checkcols, input, pageSize,pageNo);
		
//		jsonObj.put("pageSize", rowsStr);
//        jsonObj.put("pageNumber", pageStr);
		
		StringBuffer datas = new StringBuffer("{\"pageNumber\":"+pageNo+",\"pageSize\":"+pageSize+",\"total\":"+results.get(0)+",\"rows\":[");
		
		if(results.size()>1){
			datas.append("{\"");
		}

		for(int i = 1;i<results.size();i=i+columns.length){

			datas.append("usertitle");
			datas.append("\":\"");
			datas.append(results.get(i)+"\",\"");
			datas.append("loginIp");
			datas.append("\":\"");
			datas.append(results.get(i+1)+"\",\"");
			datas.append("loginDatetime");
			datas.append("\":\"");
			datas.append(results.get(i+2)+"\",\"");
//			datas.append("logoutDatetime");
//			datas.append("\":\"");
//			datas.append(results.get(i+3)+"\",\"");
			datas.append("squ");
			datas.append("\":\"");
			datas.append(results.get(i+3));
			
			if(i+columns.length<results.size()){
				datas.append("\"},{\"");
			}
		}
		if(results.size()>1){
			datas.append("\"}");
		}
		datas.append("]}");
		PrintWriter writer = null;
		try {
			writer = getResponse().getWriter();
		} catch (IOException e) {
			throw new BizException(e);
		}

		writer.write(datas.toString());
		writer.flush();
		writer.close();
		return null;
	}
	
	/**
	 * 下载时提供输入流，即需要下载的文件的实际文件资源，对应在配置文件中的InputName属性
	 * @return 
	 */
	public InputStream getInputStream(){
		InputStream inStream = null;
		try {
			this.downFileName = java.net.URLEncoder.encode("登录日志"+StringConvert.getTime("yyyyMMddmmss")+".xls","UTF-8");//支持中文文件名
			inStream = new FileInputStream(new File(downFilePath));
		} catch (UnsupportedEncodingException e) {
			throw new BizException(e);
		} catch (FileNotFoundException e) {
			throw new BizException(e);
		}
		return inStream;
	}
	
	/**
	 * @description 以excel格式导出查询出来的结果
	 * @param 
	 * @return String
	 */
	public String exportUserLgIAsExcel(){
		
		Date now = new Date();
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd_ksS");
		
		String userName = bartDateFormat.format(now) ;
		
		String []xszd={"#user_name#","TERMINAL_ID","operate_time"};
		String LoginDatetime = lgistartDatetime+"#@#@#@#"+lgiendDatetime;
		String LogoutDatetime = lgostartDatetime+"#@#@#@#"+lgoendDatetime;
		String lgIP = "";
		if(!loginIp.equals("...")){
			lgIP = loginIp;
		}
		String []input={usertitle,lgIP,LoginDatetime,LogoutDatetime};
		String []checkcols={"%user_name%","TERMINAL_ID","@operate_time@"};
		
		this.downFilePath = searchLogService.exportUserLgIAsExcel("log_info", xszd, checkcols, input,userName);
		
		return SUCCESS;

	}
	
	public String deleteUserLgI(){
		
		String LoginDatetime = lgistartDatetime+"#@#@#@#"+lgiendDatetime;
		String LogoutDatetime = lgostartDatetime+"#@#@#@#"+lgoendDatetime;
		//把IP"..."更改为空
		String lgIP = "";
		if(!loginIp.equals("...")){
			lgIP = loginIp;
		}
		String []input={ squ, usertitle,lgIP,LoginDatetime,LogoutDatetime};
		String []checkcols={"$num_id$","%user_name%","TERMINAL_ID","@operate_time@"};
		
		searchLogService.deleteLogs("log_info",checkcols, input);
		
		return SUCCESS;
	}



}
