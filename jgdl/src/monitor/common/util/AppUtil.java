package monitor.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

/**
 * @ClassName:AppUtil
 * @author: yangli solin_l@163.com
 * @datetime: Sep 7, 2012 1:25:34 PM
 * @Description: 工具函数类 History： Editor version Time Operation
 */
public class AppUtil {

	/**
	 * 应用程序全局对象
	 */
	private static ServletContext servletContext = null;

	private static ApplicationContext appContext;

	public static void setApplicationContext(
			ApplicationContext applicationContext) {
		appContext = applicationContext;
	}

	public static void setServletContext(ServletContext s) {
		servletContext = s;
	}

	/**
	 * 取得Bean
	 * 
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		return appContext.getBean(beanId);
	}

	/**
	 * 取得应用程序的绝对路径
	 * 
	 * @return
	 */
	public static String getAppAbsolutePath() {
		return servletContext.getRealPath("/");
	}

	/**
	 * @Title: getIpAddr
	 * @author: yangli
	 * @datetime: Sep 7, 2012 1:27:38 PM
	 * @param: @return String IP地址
	 * @return: String
	 * @throws
	 * @Description: 取得当前系统IP地址
	 */
	public static String getIpAddr() {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
						netip = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
						localip = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
}
