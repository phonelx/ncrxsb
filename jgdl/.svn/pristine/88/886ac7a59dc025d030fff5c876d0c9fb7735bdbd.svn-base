/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  liangxj 
 * @datetime:  May 16, 2011 1:46:08 PM
*/
package monitor.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import monitor.common.exception.SysException;

/** 
 * 读取properties文件工具类，采用单例模式
 * @author  liangxj
 * @datetime  May 16, 2011 1:46:08 PM
 */
public class PropertiesUtil extends Properties
{   
	private static final long serialVersionUID = 1L;
	private static PropertiesUtil instance;
    private PropertiesUtil(String propertiesUrl)
        throws IOException
    {
        InputStream is = getClass().getResourceAsStream(propertiesUrl);
        try
        {
            load(is);
        }
        catch(IOException e)
        {
            throw e;
        }
    }
    private static synchronized void makeInstance(String propertiesUrl)
        throws IOException
    {
        if(instance == null)
            instance = new PropertiesUtil(propertiesUrl);
    }
    public static PropertiesUtil getInstance(String propertiesUrl)
    {
    	try {
//    		 if(instance != null)
//    	        {
//    	            return instance;
//    	        } else
//    	        {
    	            makeInstance(propertiesUrl);
    	            return instance;
//    	        }
		} catch (IOException e) {
			throw new SysException(e);
		}
       
    }
}
