/*
 * @copyright:  heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  Apr 21, 2011 11:49:49 AM
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
 * @datetime  Apr 21, 2011 11:49:49 AM
 */
public class QuerySystemRunAction extends BaseAction{
	
	private static final long serialVersionUID = 6874838262937036285L;
	private String exceptionType = "";
	private String exceptionClassName = "";
	private String exceptionMessage = "";
	private String exceptionDetail = "";
	private String throwStartDatetime = "";
	private String throwEndDatetime = "";
	private ISearchLogService searchLogService = null;
	private String squ="";
	// 最大访问数量
	private int maxRows ;
	private String downFilePath = null;
	private String downFileName = null;
	
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
	public ISearchLogService getSearchLogService() {
		return searchLogService;
	}
	public void setSearchLogService(ISearchLogService searchLogService) {
		this.searchLogService = searchLogService;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public String getExceptionClassName() {
		return exceptionClassName;
	}
	public void setExceptionClassName(String exceptionClassName) {
		this.exceptionClassName = exceptionClassName;
		if(this.exceptionClassName !=null){
			this.exceptionClassName=stringReplace(this.exceptionClassName);
		}
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getExceptionDetail() {
		return exceptionDetail;
	}
	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}
	public String getThrowStartDatetime() {
		return throwStartDatetime;
	}
	public void setThrowStartDatetime(String throwStartDatetime) {
		this.throwStartDatetime = throwStartDatetime;
	}
	public String getThrowEndDatetime() {
		return throwEndDatetime;
	}
	public void setThrowEndDatetime(String throwEndDatetime) {
		this.throwEndDatetime = throwEndDatetime;
	}
	public String getSqu() {
		return squ;
	}
	public void setSqu(String squ) {
		this.squ = squ;
	}
	
	public String showSysRunPage(){
		return SUCCESS;
	}
	
	public String listSystemRunInPage(){

//		System.out.println(this.getServletContext().getRealPath("/")+"WEB-INF\\WS.xml") ;
		String []columns={"exceptionType","exceptionClassName","exceptionmessage","throwDatetime","squ"};
		String opraDatetime = throwStartDatetime+"#@#@#@#"+throwEndDatetime;
		String []input={exceptionType,exceptionClassName,exceptionMessage,opraDatetime,String.valueOf(maxRows)};
		String []checkcols={"exceptionType","%exceptionClassName%","exceptionmessage","@throwDatetime@","@%maxRows@%"};
		List<String> results = new ArrayList<String>();
		
		int pageSize = Integer.parseInt(getRequest().getParameter("rows")) ;
		// 当前页
		int pageNo = Integer.parseInt(getRequest().getParameter("page")) ;
		
		results = searchLogService.searchLogS("SYSTEMRUN", columns, checkcols, input, pageSize, pageNo);
		
		StringBuffer datas = new StringBuffer("{\"pageNumber\":"+pageNo+",\"pageSize\":"+pageSize+",\"total\":"+results.get(0)+",\"rows\":[");
		if(results.size()>1){
			datas.append("{\"");
		}
		
		
		for(int i = 1;i<results.size();i=i+columns.length){
			datas.append("exceptionType");
			datas.append("\":\"");
			
			if(results.get(i).equals("0")){
				datas.append("业务逻辑异常\",\"");
			}else if(results.get(i).equals("1")){
				datas.append("系统异常\",\"");
			}
			else if(results.get(i).equals("2")){
				datas.append("数据库异常\",\"");
			}else{
				datas.append("其他异常\",\"");
			}
			
			datas.append("exceptionClassName");
			datas.append("\":\"");
			datas.append(results.get(i+1)+"\",\"");
			datas.append("exceptionmessage");
			datas.append("\":\"");
			datas.append(results.get(i+2)+"\",\"");
			datas.append("throwDatetime");
			datas.append("\":\"");
			datas.append(results.get(i+3)+"\",\"");
			datas.append("squ");
			datas.append("\":\"");
			datas.append(results.get(i+4));

			
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
//		System.out.println(datas.toString());
		setQueryCommandBack();
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
			this.downFileName = java.net.URLEncoder.encode("系统日志"+StringConvert.getTime("yyyyMMddmmss")+".xls","UTF-8");//支持中文文件名
			inStream = new FileInputStream(new File(downFilePath));
		} catch (UnsupportedEncodingException e) {
			throw new BizException(e);
		} catch (FileNotFoundException e) {
			throw new BizException(e);
		}
		return inStream;
	}
	
	
	public String exportSysRunAsExcel(){
		
		Date now = new Date();
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd_ksS");
		
		String userName = bartDateFormat.format(now) ;
		
		String []xszd={"#exceptionType#","exceptionClassName","exceptionmessage","throwDatetime"};
		String opraDatetime = throwStartDatetime+"#@#@#@#"+throwEndDatetime;
		String []input={exceptionType,exceptionClassName,exceptionMessage,opraDatetime};
		String []checkcols={"exceptionType","exceptionClassName","exceptionmessage","@throwDatetime@"};
		
		this.downFilePath = searchLogService.exportSysRunAsExcel("SYSTEMRUN", xszd, checkcols, input,userName);
		
		return SUCCESS;

	}
	
	
	public String deleteSysRunLog(){
		
		String opraDatetime = throwStartDatetime+"#@#@#@#"+throwEndDatetime;
		String []input={squ,exceptionType,exceptionClassName,exceptionMessage,opraDatetime};
		String []checkcols={"$squ$","exceptionType","%exceptionClassName%","exceptionmessage","@throwDatetime@"};
		
		searchLogService.deleteLogs("systemrun",checkcols, input);
		
		return SUCCESS;
		
	}
	public int getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	
}
