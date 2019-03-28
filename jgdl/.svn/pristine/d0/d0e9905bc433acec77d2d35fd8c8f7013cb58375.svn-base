/**
 * 
 */
package monitor.common.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import monitor.common.DBDefault;
import monitor.common.util.StringConvert;
import monitor.user.bean.dto.UserDto;
import monitor.user.bean.dto.UserLoginInfoDto;
import monitor.user.service.IUserService;
import monitor.user.service.impl.UserServiceImpl;

/**
 * @author skallen
 *
 */
public class OnlineUserListener implements HttpSessionListener,ServletContextListener {
	private static Logger logger = Logger.getLogger(OnlineUserListener.class);
	private ServletContext context = null;
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		UserDto _user = (UserDto)session.getAttribute("user");
		
		UserLoginInfoDto _userLoginInfoDto = (UserLoginInfoDto)session.getAttribute("userLoginDto");
		
		
		if(_userLoginInfoDto!=null){
			_userLoginInfoDto.setLogoutDatetime(StringConvert.getTime("yyyy-MM-dd HH:mm:ss"));
			
			//写入数据库
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
			IUserService userService = (UserServiceImpl)ctx.getBean("userService");
			userService.recordUserLogout(_userLoginInfoDto);//记录用户退出信息，本操作只修改退出时间
			
			
			System.out.println(_userLoginInfoDto.getUserInfo()+" has logout overtime.("+_userLoginInfoDto.getLoginIp()+")");
			logger.info(_userLoginInfoDto.getUserInfo()+" has logout overtime.("+_userLoginInfoDto.getLoginIp()+")");
			
			//在线用户数减少1
			List<UserLoginInfoDto> onLineUserList = (List<UserLoginInfoDto>)context.getAttribute("onLineUserList") ;
			if(onLineUserList!=null){
				onLineUserList.remove(_userLoginInfoDto);
			}
		}
		
		/*if(_user!=null){
			OnlineUserList list = OnlineUserList.getInstance();
			list.removeUser(_user.getUsername());
		}*/
		
		
	}
	
	public void contextDestroyed(ServletContextEvent servletcontextevent) {
		
	}

	public void contextInitialized(ServletContextEvent servletcontextevent) {
		String rootPath = "" ;
		String filePath="";
		context = servletcontextevent.getServletContext() ;
		rootPath = context.getRealPath("/") ;
		if(rootPath!= null && !rootPath.trim().equalsIgnoreCase("null")){
			filePath = rootPath ;
		}else{
			String path = "" ;
			try { 
				path = this.getClass().getResource("").getPath() ; 
			} catch (Exception e) { 
				path = this.getClass().getClassLoader().getResource("/").getPath() ; 
			}
			filePath = path.substring(0,path.lastIndexOf("war")+4) ;
		}
		 
		DBDefault.downloadPath = filePath+"/download/tmp";//读取download/tmp目录存入DBDefault
		DBDefault.downloadWordPath = filePath+"/download/word";
		DBDefault.downloadPath+=DBDefault.separator;
		DBDefault.downloadWordPath += DBDefault.separator;
		DBDefault.wscIniPath = filePath+"/pages/WSclient/conf/";
		}
}
