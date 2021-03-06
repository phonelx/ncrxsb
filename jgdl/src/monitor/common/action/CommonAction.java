package monitor.common.action;

import monitor.common.BaseAction;
import monitor.common.bean.entity.ErrorDataReportEntity;
import monitor.common.bean.vo.DataSizeVo;
import monitor.common.service.ICommonService;
import monitor.common.util.MD5;


/** 
 * <description> 
 * @author  cl
 * @datetime  2011-5-18 下午04:59:00
 */
@SuppressWarnings("static-access")
public class CommonAction extends BaseAction {
	private ICommonService commonService = null; 	 
	private String statType;
	
	public String getStatType() {
		return statType;
	}
	public void setStatType(String statType) {
		this.statType = statType;
	}
	public ICommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}
	// 注入 ErrorDataReportEntity 实体 Bean
	private ErrorDataReportEntity rer ;
	
	/**
	 * 计算首页所有资源概况数据综合 
	 * @author: cl 
	 * @param 
	 * @return void
	 */
	public void calculateAllFactourCounts(){
		super.writeStringBack(commonService.calculateAllFactourCounts());
	}
	
	/**
	 * 首页数据空间统计
	 * @author: cl 
	 * @param  
	 * @return String
	 */
	public String showWelcome(){
		//获取数据空间大小统计
		DataSizeVo datasize = commonService.calculateAllDataSize();
		getRequest().setAttribute("datasize", datasize);
		return this.SUCCESS;
	}
	
	/**
	 * 首页保存错误数据信息
	 * @author: yl 
	 * @param  rer
	 * @return null
	 */
	public String saveErrorData(){
		//首页保存错误数据信息
		commonService.saveErrorData(rer);	
		return null ;
	}
	public ErrorDataReportEntity getRer() {
		return rer;
	}
	public void setRer(ErrorDataReportEntity rer) {
		this.rer = rer;
	} 
	
	/**
	 * 
	 * 进行同名验证，
	 */
	 
	 public void sameNameJudge(){
		 //获取表名参数
		 String tableName=getRequest().getParameter("tableNameTran");
		 //获取同名验证字段参数
		 String filedName=getRequest().getParameter("filedNameTran");
		 //获取需要验证的 名字
		 String nowUseName=getRequest().getParameter("nowUseNameTran");
		 String dbid = getRequest().getParameter("dbid");
		 String tabid= getRequest().getParameter("tabid");
		 StringBuffer returnStr=new StringBuffer();
		 if(tableName!=null&&(!("".equals(tableName.trim())))
				 &&(!("null".equals(tableName.trim().toLowerCase())))
				 &&filedName!=null&&(!("".equals(filedName.trim())))
				 &&(!("null".equals(filedName.trim().toLowerCase())))
				 &&nowUseName!=null&&(!("".equals(nowUseName.trim())))
				 &&(!("null".equals(nowUseName.trim().toLowerCase())))){
		   boolean trueOrFalse=commonService.sameNameJudge(tableName, filedName, 
				   stringReplace(nowUseName),dbid,tabid);
		   if(trueOrFalse){
			   returnStr.append("{cloudUse:true}");
		   }else{
			   returnStr.append("{cloudUse:false}");
		   }
		 }else{
			 //在没有参数的情况下默认通过验证
			 returnStr.append("{cloudUse:true}");
		 }
		 super.writeStringBack(returnStr.toString());
	 }
		/**
		 * 
		 *获取远程登录密钥
		 *密钥由     (remoteLoginFlag + 一个随机数 )  MD5码组成
		 */
	 public void loginOtherSsp(){ 
	      String remoteLoginFlag = "remoteLoginFlag"; // 远程登录标志
		  String key = System.currentTimeMillis()+"" ;// 随机加密密钥 
	      super.writeStringBack(MD5.getMd5(remoteLoginFlag+key)+"@"+key);
	 }

	 /**
	  * 检测系统运行状态
	  */
	 public void checkSysStatus(){
	     writeStringBack(getRequest().getParameter("callback")+"({area:'"+getRequest().getParameter("area")+"',state:"+commonService.checkSysStatus()+"})");
	 }
}
