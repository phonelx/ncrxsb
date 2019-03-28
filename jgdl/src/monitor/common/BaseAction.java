package monitor.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import monitor.common.exception.BizException;
import monitor.common.exception.SysException;
import monitor.user.bean.dto.UserDto;
import monitor.user.bean.dto.UserLoginInfoDto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author Administrator
 * description：
 * 	  系统ACTION基类
 */
public class BaseAction extends ActionSupport {
		private static final long serialVersionUID = -4565636787878166666L;
		protected transient final Log logger = LogFactory.getLog(getClass());
		protected UserDto userDto = null;
		protected UserLoginInfoDto userLoginInfoDto = null;
		protected String operationDescb = "正常";
		
		public UserLoginInfoDto getUserLoginInfoDto() {
			return userLoginInfoDto;
		}

		public void setUserLoginInfoDto(UserLoginInfoDto userLoginInfoDto) {
			this.userLoginInfoDto = userLoginInfoDto;
		}

		public void setUserDto(UserDto userDto) {
			this.userDto = userDto;
		}

		public BaseAction(){
			if(getRequest()!=null){
				this.userDto = (UserDto)getSession().getAttribute("user");
				this.userLoginInfoDto=(UserLoginInfoDto)getSession().getAttribute("userLoginDto");
//				System.out.println(getRequest());
				// 获取加密锁的 过期时间 并保存到 application中
				try {
//					ServletActionContext.getServletContext().setAttribute("_afterDays_",GrantUtil.checkDate() ) ;
					ServletActionContext.getServletContext().setAttribute("_afterDays_",40 ) ;
				} catch (Exception e) {
					throw new SysException(e);
				}
			}
			
		}
		/**
		 * 返回json字符串
		 * 
		 * @author: cl
		 * @param
		 * @return void
		 */
		public void writeJsonBack(String buff) {
			PrintWriter writer = null;
			try {
				writer = getResponse().getWriter();
			} catch (IOException e) {
				throw new BizException(e);
			}
			writer.write(buff);
			writer.flush();
			writer.close();
		}
		public UserDto getUserDto() {
			return userDto;
		}

		public HttpServletRequest getRequest(){
			return ServletActionContext.getRequest();
		}
		
		public HttpServletResponse getResponse(){
			HttpServletResponse response = ServletActionContext.getResponse() ;
			response.setCharacterEncoding("utf-8") ;
			return response;
		}
		
		public HttpSession getSession(){
			return getRequest().getSession();
		}
		
		public ServletContext getServletContext(){
			return ServletActionContext.getServletContext();
		}
		
		/**
		 * 获取输出流向request写入字符串
		 * @author:  cl 
		 * @param 
		 * @return void
		 */
		public void writeStringBack(String buff){
			PrintWriter writer = null;
			try {
				writer = getResponse().getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			writer.write(buff);
			writer.flush();
			writer.close();
		}

		public String getOperationDescb() {
			return operationDescb;
		}

		public void setOperationDescb(String operationDescb) {
			this.operationDescb = operationDescb;
		}
		
		public void setQueryCommandBack(){
			Map paramTranMap=new HashMap();
			for (Enumeration<String> e = getRequest().getParameterNames(); e.hasMoreElements();)
			{ 
				String paramNameStr=e.nextElement().toString();
				Object tempT=getRequest().getParameter(paramNameStr);
				if(tempT!=null&&(!("".equals(tempT.toString().trim())))){
					tempT=stringReplaceBack(tempT.toString());
					paramTranMap.put(paramNameStr, (Object)tempT);
				}
			}
		    getRequest().setAttribute("QUERYCOMMANDS",paramTranMap);
		}
		
		/**
		 * 有大部分的查询是直接用url方式传递的参数，其中不能存在' " & 等符号所以进行转换
		 * singleQuot#Sun 替换成单引号，但数据库中的一个单引号应该写成两个单引号
		 * singleAnd#Sun  替换成 & 符号，数据库无需转换
		 * doubleQuot#Sun 替换成双引号，但数据库中的一个双引号应该写成两个双引号
		 * @param strRe
		 * @return
		 */
		public String stringReplace(String strRe){
			if(strRe!=null){
				strRe=strRe.trim();
				strRe=strRe.replace("[singleQuotHL]", "''");
				strRe=strRe.replace("[singleAndHL]", "&");
				strRe=strRe.replace("[doubleQuotHL]", "\"");
				return strRe;
			}else{
				return null;
			}
		}
		
		/**
		 * 查询参数回显到界面的input中是还原原始参数
		 * @param strRe
		 * @return
		 */
		public String stringReplaceBack(String strRe){
			if(strRe!=null){
				strRe=strRe.trim();
				strRe=strRe.replace("[singleQuotHL]", "&#039;");
				strRe=strRe.replace("[singleAndHL]", "&#038;");
				strRe=strRe.replace("[doubleQuotHL]", "&#034;");
				return strRe;
			}else{
				return null;
			}
		}
		/**
		 * 改变在线用户数，当用户登录系统时，在线用户数加1，
		 * 当用户退出系统或者Session超时时，在线用户数减1
		 * @param isInc 是否增加   isInc = true 增加；isInc = false 减少
		 * @return void
		 */
		protected void changeOnLineUserCount(boolean isInc){
			//在线用户数
			int onLineUserCount = 0 ;
			// 获取application对象
			ServletContext application = ServletActionContext.getServletContext() ;
			//获取在线用户数
			String countStr = (String) application.getAttribute("onLineUserCount");
			if(countStr != null && !"".equals(countStr.trim())){//已经有用户登录过该系统
				onLineUserCount = Integer.parseInt(countStr); // 获取在线用户数
				application.removeAttribute("onLineUserCount") ;
			}
			// 用户登录 在线用户数加1
			if(isInc){
				onLineUserCount ++ ;
			}else{ //用户退出系统或者Session超时时，在线用户数减1
				onLineUserCount -- ;
			}
			// 重新保存在线用户数 
			application.setAttribute("onLineUserCount", onLineUserCount+"");
		}
		/**
		 * 改变在线用户列表
		 * @param loginInfo  登录用户信息
		 * @isLogin 是否是登录操作
		 * @return void
		 */
		protected void changeOnLineUserList(UserLoginInfoDto loginInfo,boolean isLogin){
			// 在线用户列表
			List<UserLoginInfoDto> onLineUserList = null ;
			// 获取application对象
			ServletContext application = ServletActionContext.getServletContext() ;
			onLineUserList = (List<UserLoginInfoDto>)application.getAttribute("onLineUserList") ;
			if(onLineUserList == null){
				onLineUserList = new ArrayList<UserLoginInfoDto>();
			}else{
				application.removeAttribute("onLineUserList") ;
			}
			// 登录操作
			if(isLogin && !onLineUserList.contains(loginInfo)){
				onLineUserList.add(loginInfo) ;
			}else if(!isLogin){ //注销或者session失效
				onLineUserList.remove(loginInfo);
			}
			application.setAttribute("onLineUserList", onLineUserList) ;
		}
}