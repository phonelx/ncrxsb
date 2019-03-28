/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  2012-1-6 上午11:10:52
*/
package monitor.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import monitor.common.BaseAction;

/** 
 * <description> 
 * @author  jk
 * @datetime  2012-1-6 上午11:10:52
 */
public class GateWayAction extends BaseAction{

	/** 
	 * @field serialVersionUID
	*/
	private static final long serialVersionUID = -9027191334577593526L;
	private Properties props = null;	
	/** 认证地址 */
	private final String KEY_AUTHURL = "authURL";

	/** 应用标识 */
	private final String KEY_APP_ID = "appId";

	
	public String accessPage(){
		
		// 设置页面不缓存
		getResponse().setHeader("Pragma", "No-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0);
		
		String parentPath = getSession().getServletContext()
		.getRealPath("/WEB-INF");
		File message = new File(parentPath + "/message.properties");
		
		if (message.exists()) {
			InputStream in = null;
			props = new Properties();
			try {
				in = new FileInputStream(parentPath + "/message.properties");
				props.load(in);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				System.out.println("从配置文件中获得应用标识，网关地址，认证方式发生异常");
			}catch (IOException e) {
				e.printStackTrace();
				System.out.println("从配置文件中获得应用标识，网关地址，认证方式发生异常");
			}
			
			this.setProperties(KEY_APP_ID, getSession());
			this.setProperties(KEY_AUTHURL, getSession());

			String randNum = generateRandomNum();
			if (randNum == null || randNum.trim().equals("")) {
				System.out.println("证书认证数据不完整！");
				getResponse().setStatus(
						HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return "error";
			}

			/**************************
			 * 第三步 服务端返回认证原文 *
			 **************************/
			// 设置认证原文到session，用于程序向后传递，通讯报文中使用
			getSession().setAttribute("original_data", randNum);

			// 设置认证原文到页面，给页面程序提供参数，用于产生认证请求数据包
			getRequest().setAttribute("original", randNum);
			
			getRequest().setAttribute("isGW", "1");
			
		}else{
			getRequest().setAttribute("isGW", "0");
		}
			
		return SUCCESS;
	}
	
	/**
	 * 产生认证原文
	 */
	private String generateRandomNum() {
		/**************************
		 * 第二步 服务端产生认证原文   *
		 **************************/
		String num = "1234567890abcdefghijklmnopqrstopqrstuvwxyz";
		int size = 6;
		char[] charArray = num.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(charArray[((int) (Math.random() * 10000) % charArray.length)]);
		}
		return sb.toString();
	}
	 
	/**
	 * 获取文件中的属性值
	 * @param httpSession 
	 */
	private String   setProperties(String key, HttpSession httpSession) {
		
		httpSession.setAttribute(key,props.get(key) == null ? null : (String) props.get(key) );
		return props.get(key) == null ? null : (String) props.get(key);
	}
	
}
