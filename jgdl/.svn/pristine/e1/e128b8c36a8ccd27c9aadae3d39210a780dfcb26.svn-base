/*
 * @copyright:  heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  Apr 21, 2011 9:46:48 AM
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
import java.util.Map;

import monitor.common.BaseAction;
import monitor.common.exception.BizException;
import monitor.common.util.StringConvert;
import monitor.log.service.ISearchLogService;
import net.sf.json.JSONObject;

/** 
 * <description> 
 * @author  jk
 * @datetime  Apr 21, 2011 9:46:48 AM
 */
public class QueryUserOperateAction extends BaseAction{
	/** 
	 * @field serialVersionUID
	*/
	private static final long serialVersionUID = 4505968635104980723L;
	private String usertitle = "";
	private String operatetype = "";
	private String operatemodule = "";
	private String operatekeywords = "";
	private String operateStartime = "";
	private String operateEndtime = "";
	private String operatedescb = "";
	private String downFilePath = null;
	private String downFileName = null;
	private String squ ="";
	// 最大访问数量
	private int maxRows ;
	private ISearchLogService searchLogService = null;
	
	public String getOperateStartime() {
		return operateStartime;
	}
	public void setOperateStartime(String operateStartime) {
		this.operateStartime = operateStartime;
	}
	public String getOperateEndtime() {
		return operateEndtime;
	}
	public void setOperateEndtime(String operateEndtime) {
		this.operateEndtime = operateEndtime;
	}
	public ISearchLogService getSearchLogService() {
		return searchLogService;
	}
	public void setSearchLogService(ISearchLogService searchLogService) {
		this.searchLogService = searchLogService;
	}
	public String getUsertitle() {
		return usertitle;
	}
	public void setUsertitle(String usertitle) {
		this.usertitle = usertitle;
		if(this.usertitle!=null){
			this.usertitle=stringReplace(this.usertitle);
		}
	}
	public String getOperatetype() {
		return operatetype;
	}
	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}
	public String getOperatemodule() {
		return operatemodule;
	}
	public void setOperatemodule(String operatemodule) {
		this.operatemodule = operatemodule;
	}
	public String getOperatekeywords() {
		return operatekeywords;
	}
	public void setOperatekeywords(String operatekeywords) {
		this.operatekeywords = operatekeywords;
	}
	public String getOperatedescb() {
		return operatedescb;
	}
	public void setOperatedescb(String operatedescb) {
		this.operatedescb = operatedescb;
		if(this.operatedescb!=null){
			this.operatedescb=stringReplace(this.operatedescb);
		}
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
	public String showUserOprPage(){
		return SUCCESS;
	}
	
	public String getSqu() {
		return squ;
	}
	public void setSqu(String squ) {
		this.squ = squ;
	}
	
	
	public String listUserOpraInPage(){
		String []columns={"user_name","manage_type","manage_condition","manage_time","manage_result","num_id"};
		String opraDatetime = operateStartime+"#@#@#@#"+operateEndtime;
		String []input={usertitle,operatetype,operatekeywords,opraDatetime,operatedescb,String.valueOf(maxRows)};
		String []checkcols={"%user_name%","manage_type","%manage_condition%","@manage_time@","%manage_result%","@%maxRows@%"};
		
		List<String> results = new ArrayList<String>();
		
		// 每页显示条数
		int pageSize = Integer.parseInt(getRequest().getParameter("rows")) ;
		// 当前页
		int pageNo = Integer.parseInt(getRequest().getParameter("page")) ;
		results = searchLogService.searchLogS("manage_log", columns, checkcols, input, pageSize, pageNo);
		
		StringBuffer datas = new StringBuffer("{\"pageNumber\":"+pageNo+",\"pageSize\":"+pageSize+",\"total\":"+results.get(0)+",\"rows\":[");
		
		if(results.size()>1){
			datas.append("{\"");
		}
		
		for(int i = 1;i<results.size();i=i+columns.length){
			datas.append("user_name");
			datas.append("\":\"");
			datas.append(results.get(i)+"\",\"");
			datas.append("operate_type");
			datas.append("\":\"");
			
			if(results.get(i+1).equals("0")){
				datas.append("添加\",\"");
			}else if(results.get(i+1).equals("1")){
				datas.append("删除\",\"");
			}
			else if(results.get(i+1).equals("2")){
				datas.append("修改\",\"");
			}
			else if(results.get(i+1).equals("3")){
				datas.append("查询\",\"");
			}
			else if(results.get(i+1).equals("5")){
				datas.append("申请\",\"");
			}
			else {
				datas.append("WS查询\",\"");
			}
			datas.append("operateke_condition");
			datas.append("\":\"");
			
			datas.append(results.get(i+2)+"\",\"");
		
			
			datas.append("operate_time");
			datas.append("\":\"");
			datas.append(results.get(i+3)+"\",\"");
			datas.append("operate_result");
			datas.append("\":\"");
			datas.append(replaceQuot(results.get(i+4))+"\",\"");
			datas.append("squ");
			datas.append("\":\"");
			datas.append(results.get(i+5));
			
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
//		String ss="{\"total\":20,\"rows\":[{\"usertitle\":\"超级管理员\",\"operatetype\":\"查询\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:19:33\",\"operatedescb\":\"正常操作\",\"squ\":\"249\",\"operatemodule\":\"系统日志查询\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"查询\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:17:27\",\"operatedescb\":\"正常操作\",\"squ\":\"248\",\"operatemodule\":\"数据查询服务\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"查询\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:11:08\",\"operatedescb\":\"正常操作\",\"squ\":\"246\",\"operatemodule\":\"操作日志查询\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"添加\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:10:12\",\"operatedescb\":\"正常操作\",\"squ\":\"245\",\"operatemodule\":\"数据资源注册服务\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"删除\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:06:16\",\"operatedescb\":\"正常操作\",\"squ\":\"247\",\"operatemodule\":\"数据资源注册服务\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"删除\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:05:51\",\"operatedescb\":\"正常操作\",\"squ\":\"242\",\"operatemodule\":\"用户角色管理\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"查询\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:04:45\",\"operatedescb\":\"正常操作\",\"squ\":\"241\",\"operatemodule\":\"系统日志查询\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"查询\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:01:13\",\"operatedescb\":\"正常操作\",\"squ\":\"244\",\"operatemodule\":\"系统日志查询\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"添加\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-17 10:00:58\",\"operatedescb\":\"正常操作\",\"squ\":\"243\",\"operatemodule\":\"用户角色管理\"},{\"usertitle\":\"超级管理员\",\"operatetype\":\"查询\",\"operatekeywords\":\"queryExec\",\"operateDatetime\":\"2011-05-16 16:58:17\",\"operatedescb\":\"正常操作\",\"squ\":\"230\",\"operatemodule\":\"数据资源注册服务\"}]}";
		setQueryCommandBack();
		logger.debug(datas.toString());
		writer.write(datas.toString());
		writer.flush();
		writer.close();
		return null;
	}

	private String replaceQuot(String str){
		str=str.replaceAll("<img", "singleAndHLImg");
		return str;
	}
	
	
	/**
	 * 下载时提供输入流，即需要下载的文件的实际文件资源，对应在配置文件中的InputName属性
	 * @return 
	 */
	public InputStream getInputStream(){
		InputStream inStream = null;
		try {
			this.downFileName = java.net.URLEncoder.encode("操作日志"+StringConvert.getTime("yyyyMMddmmss")+".xls","UTF-8");//支持中文文件名
			inStream = new FileInputStream(new File(downFilePath));
		} catch (UnsupportedEncodingException e) {
			throw new BizException(e);
		} catch (FileNotFoundException e) {
			throw new BizException(e);
		}
		return inStream;
	}
	
	
	public String exportUserOprAsExcel(){
		
		Date now = new Date();
		
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd_ksS");
		
		String userName = bartDateFormat.format(now) ;
		

		String []xszd={"#user_name#","operate_type","operateke_condition","operate_time","operate_result"};
		//String []xszd={"usertitle","operatetype","operatemodule","operatekeywords","operateDatetime","operatedescb"};
		
		String opraDatetime = operateStartime+"#@#@#@#"+operateEndtime;
		String []input={usertitle,operatetype,operatekeywords,opraDatetime};
		//String []input={usertitle,operatetype,operatemodule,operatekeywords,opraDatetime};
		
		String []checkcols={"%user_name%","operate_type","%operate_condition%","operate_time"};
		//String []checkcols={"usertitle","operatetype","operatemodule","operatekeywords","operateDatetime"};
		
		this.downFilePath = searchLogService.exportUserOprAsExcel("log_info", xszd, checkcols, input,userName);
		
		return SUCCESS;

	}

	public String deleteUserOpr(){
		
		String opraDatetime = operateStartime+"#@#@#@#"+operateEndtime;
		String []input={squ,usertitle,operatetype,operatekeywords,opraDatetime,};
		//String []input={usertitle,operatetype,operatemodule,operatekeywords,opraDatetime};
		
		//squ的格式为12,23,24,24
		String []checkcols={"$squ$","%userInfo%","operatetype","%operatekeywords%","@operateDatetime@"};
		
		searchLogService.deleteLogs("INTER_LOG", checkcols, input);
		
		return SUCCESS;
	}
	
	
	@SuppressWarnings("unchecked")
	public String	doQueryAgain(){
		
		String sqlTran=getRequest().getParameter("sqlTran");
		PrintWriter out;
		try {
			out = getResponse().getWriter();

			List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();
			// 每页显示条数
			int pageSize = Integer.parseInt("10") ;
			// 当前页
			int pageNo = Integer.parseInt("1") ;
			
			String pageSize_s = getRequest().getParameter("pageSizeTrqn");
			String pageNo_s = getRequest().getParameter("pageNumUseTran");
			// 有时没有传递这两个参数。为了防止抛出异常而做判断
			if (pageSize_s != null && pageSize_s.trim() != "") {
				pageSize = Integer.valueOf(pageSize_s);
			}
			if (pageNo_s != null && pageNo_s.trim() != "") {
				pageNo = Integer.valueOf(pageNo_s);
			}
			squ="2";
			String sql_s = getRequest().getParameter("dbsSquTUseTran");
			if (sql_s != null && sql_s.trim() != "") {
				squ = sql_s;
			}
			
			String maxRows_s = getRequest().getParameter("maxRowsUseTran");
			if (maxRows_s != null && maxRows_s.trim() != "") {
				maxRows = Integer.valueOf(maxRows_s);
			}
			//System.out.println("sqlTran"+sqlTran);
			results =searchLogService.doQueryAgain(sqlTran,Integer.valueOf(squ),maxRows, pageSize, pageNo);
			JSONObject obj = new JSONObject();
			obj.put("total", results.get(0));
			obj.put("rows", results.get(1));
			out.print(obj);
			out.flush();
			out.close();
			//setOperationDescb("SQL语句：" + sqlTran);
			//out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public int getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

}
