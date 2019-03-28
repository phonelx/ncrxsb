package monitor.common;

import java.io.File;

public class DBDefault {
	public static String separator = null;//文件分隔符
	public static String downloadPath = "d:/download";//下载目录，/download/tmp
	public static String downloadWordPath = null;
	public static String cacheFilePath = null;
	public static String wscIniPath = null;//ws客户端访问ini配置
	
	static{
		separator = System.getProperty("file.separator");
		/*
		String appContent = null;
		if(System.getProperty("os.name").equalsIgnoreCase("Linux")){//linux
			String installPath=System.getenv("DEPRoot");
			if(installPath==null){
				appContent="/DEP";
			}else{
				appContent=installPath;
			}
		}else{//windows
			//从注册表中获得安装路径
			Preferences pref = Preferences.systemRoot();
			appContent= pref.get("path",null);
			
			if(appContent==null){
				System.out.println("平台安装目录："+appContent);
				logger.error("平台安装目录："+appContent);
			}
		}
		*/
		
	}
	
	/**
	 * 清空临时下载目录下的所有可清除文件 
	 * @author: cl 
	 * @param 
	 * @return void
	 */
	public synchronized static void clearDownloadTmpDir(){
		System.out.println("下载目录:"+downloadPath);
		File downloadContentFile = new File(downloadPath);
		if(downloadContentFile.exists()){
			File[] files = downloadContentFile.listFiles();
			for(File tmpFile:files){
				if(!"tmp.tmp".equals(tmpFile.getName()))
				tmpFile.delete();
			}
		}else{
			Boolean a =downloadContentFile.mkdirs();
			System.out.println("创建是否成功："+a);
		}
	}
	
}
