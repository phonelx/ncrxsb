/* * @copyright: heli Technologies Co., Ltd. Copyright 2010-9999, All rights reserved * @description: <description> * @author: cl * @datetime: 2011-8-4 下午04:20:21 */
package monitor.common.util;

import java.io.InputStream;
import java.util.Properties;

/** <description>
 * @author cl 
 * @datetime 2011-8-4 下午04:20:21 
 */
public class PropertiesFileUtil {
	public static Properties getPropertiesInClassPath(String propertiesUrl){
    	if(propertiesUrl == null){
    		return null;
    	}
    	InputStream is = PropertiesUtil.class.getResourceAsStream(propertiesUrl);
        Properties ppts = new Properties();
		try {
			ppts.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ppts;
    }
}
