/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-5-3 下午01:30:36
*/
package monitor.common.aop;

import java.lang.reflect.Method;

import monitor.common.bean.entity.OperationEntity;
import monitor.common.service.ICommonService;
import monitor.common.util.PropertiesUtil;
import monitor.common.util.StringConvert;
import monitor.user.bean.dto.UserDto;

import org.aspectj.lang.JoinPoint;

/** 
 * 记录用户操作信息aop应用 
 * @author  cl
 * @datetime  2011-5-3 下午01:30:37
 */
@SuppressWarnings("all")
public class AOPUserOperation {//implements AfterReturningAdvice{
	private ICommonService commonService = null;
	
	public ICommonService getCommonService() {
		return commonService;
	}
	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}
	
	public void logUserOperation(JoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
//		System.out.println(methodName);
		
		Class clazz = joinPoint.getTarget().getClass();
		
		Method getUserDtoMethod = clazz.getMethod("getUserDto", null);
		Object obj = null;
		obj = getUserDtoMethod.invoke(joinPoint.getTarget(), null);
		UserDto user = null;
		if(obj!=null){
			user = (UserDto) obj;
		}else{
			return;
		}
		Method getOperationDescbMethod = clazz.getMethod("getOperationDescb", null);
		String operationDescb = (String) getOperationDescbMethod.invoke(joinPoint.getTarget(), null);
		OperationEntity opEntity = new OperationEntity();
			//写入用户操作表
			opEntity.setUserInfo(user.getUsername());
			opEntity.setOperateKeyWords(getKeyWord(methodName));
			opEntity.setOperateDatetime(StringConvert.getTime("yyyy-MM-dd HH:mm:ss"));
			opEntity.setOperateDescb(operationDescb);
		
		if(methodName.startsWith("login")){
			opEntity.setOperateType(0);
		}else if(methodName.startsWith("add")){
			opEntity.setOperateType(0);
//			System.out.println("成功记录用户添加操作");
		}else if(methodName.startsWith("delete")){
			opEntity.setOperateType(1);
//			System.out.println("成功记录用户删除操作");
		}else if(methodName.startsWith("edit")){
			opEntity.setOperateType(2);
//			System.out.println("成功记录用户修改操作");
		}else if(methodName.startsWith("query")){
			opEntity.setOperateType(3);
//			System.out.println("成功记录用户查询操作");
		}else if(methodName.startsWith("sync")){
			opEntity.setOperateType(4);
//			System.out.println("成功记录用户同步操作");
		}else if(methodName.startsWith("ws")){
			
		}
		//commonService.recordOperationType(opEntity);
		
	}
	
	private String getKeyWord(String methodName){
		PropertiesUtil util = PropertiesUtil.getInstance("/conf/properties/operation.properties") ;
		String path = util.getProperty(methodName);
		path = (path == null?" ":path);
		return path;
	}
}
