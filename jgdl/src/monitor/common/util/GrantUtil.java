/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-9-28 下午03:16:23
*/
package monitor.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import Aladdin.Hasp;
import Aladdin.HaspStatus;
import Aladdin.HaspTime;

/** 
 * 读取key信息    只针对当前 的主锁
 * @author  liangxj
 * @datetime  2011-9-28 下午03:16:23
 */
public class GrantUtil {
	public static final int DEP_MEMBUFFER_SIZE = 128;

    public static final String vendorCode = new String(
    		"iuFxovxEz12/pwJyctmZ/+zS5xj4+VU2H/HutARzVBg3JQtiskaTyLcwQdW4cqDBQaLBJz/IJuPqD0QR" + 
    		"XXgV4BuyosJv791VqoAg2JmlwAusunqC3dxUeGgN/zV0Ug4YLYKmz9FbEiP4Zt1g0eBiNCRpETh8EfsQ" + 
    		"+R5aZ20ecF/6vghPIZCwDd4ZSJC9H9CyZj+bx7i8k39yX3blFKtJC2O5Vrbj7Cwvl+hnbKWtYPE5gN8R" + 
    		"lgaqFQxZkOBTlktSPGKrV8xATwyRKUtEH4GBMeITmbzHhJqBqQCsdx7q9nXg8lyPmqkSStHlX7Z4S5bD" + 
    		"DXfhOM2GJp3kFZytr79c4UDnxIkPjSQdDDFvvK/GcyGmWcVCyZDGrFqBgBWfYApx9uIPXGtQvtTUKnO4" + 
    		"kQlu0sBJKvKYhdmxB4U8W8F2Ts+bmwe+f1/ZKeKm91vI5MxYh+0tu3dEFkidbkRtVvnTvmLiz5W3Wwwk" + 
    		"qoigHw0LqiFifk+bV8QHmJBuIL575MmeexoyoMzBXGidv7NBgkJ9BB0Sah+Js9Qisx1hhw1JZZiNbALU" + 
    		"W4XId8ew1c56T6LP1BcXZ2TN1RYipX93Khh53rS0ZlJhG1/xNKjI2FjDDTsPTOR02ft1UTsBsu/hZCbf" + 
    		"fHvmeCaZ9NN4z/7so0SWL5FURj6btrj/Wn4nNZ2sF+7RJvkCwWT7lsOYLFwAUSjxGUZZ+g6NlVdPpkNd" + 
    		"/J/je87oeupuXrudsBPfnbqDBO96vWzmgh00Ao3WoFRcTNh3a8YlqANlAFx4U4YHgrGd1rShMyLoEMY/" + 
    		"QX+bVWMVbXZKyApsOGMZTQgr4pwytHvlpVhlr08vqDAK2WcumOWYN8atPNGVdo9jU2DwW11OVDZbDew7" + 
    		"JFbpYzf6J3QrLM+wn45qQr/537/R8WqtdO0O5ucOuJcPZUQXaaNKx5nXnJs=");

    public static final String scope = new String(
      "<haspscope>\n" +
      " <license_manager hostname=\"localhost\" />\n" +
      "</haspscope>\n");

    public static final String scope1 = new String(
      "<haspscope />\n");

    public static final String view = new String(
      "<haspformat root=\"my_custom_scope\">\n" +
      "  <hasp>\n" +
      "    <attribute name=\"id\" />\n" +
      "    <attribute name=\"type\" />\n" +
      "    <feature>\n" +
      "      <attribute name=\"id\" />\n" +
      "      <element name=\"concurrency\" />\n" +
      "      <element name=\"license\" />\n" +
      "      <session>\n" +
      "        <element name=\"username\" />\n" +
      "        <element name=\"hostname\" />\n" +
      "        <element name=\"ip\" />\n" +
      "        <element name=\"apiversion\" />\n" +
      "      </session>\n" +
      "    </feature>\n" +
      "  </hasp>\n" +
      "</haspformat>\n");

    public static final byte[] data = { 0x74, 0x65, 0x73, 0x74, 0x20, 0x73, 0x74, 0x72,
                                        0x69, 0x6e, 0x67, 0x20, 0x31, 0x32, 0x33, 0x00 };

	/**
	 * 检测过期时间
	 * 
	 * @return void
	 */
	public static int checkDate() throws Exception {
		int afterDays = -40;
		try {
			int status;
			String infos;
			Hasp hasp = new Hasp(Hasp.HASP_DEFAULT_FID);
			// 登录key 获key信息
			hasp.login(vendorCode);
			// 获取可以信息
			infos = hasp.getInfo(scope1, view, vendorCode);
			status = hasp.getLastError();
			if (HaspStatus.HASP_STATUS_OK == status) {
				// 获取过期时间
				Document document = DocumentHelper.parseText(infos);
				List<Element> elements = document
						.selectNodes("/my_custom_scope/hasp/feature/license");
				List<Long> lst = new ArrayList<Long>();
				for (Element e : elements) {
					if (e.selectSingleNode("license_type").getText().equals(
							"expiration")) {
						lst.add(Long.valueOf(e.selectSingleNode("exp_date")
								.getText()));
					}
				}
				if(lst.size()>0){
					Collections.sort(lst);
					HaspTime expTime = new HaspTime(lst.get(lst.size() - 1)); // 过期时间
					if (expTime.getYear() == hasp.getRealTimeClock().getYear()
							&& expTime.getMonth() == hasp.getRealTimeClock().getMonth()) {
						afterDays = expTime.getDay() - hasp.getRealTimeClock().getDay();
					}
				}
			}
		} catch (Exception e) {
			afterDays = -40 ;
		}
		return afterDays;
	}
    public static void main(String[] args) throws Exception {
    	System.out.println(GrantUtil.checkDate()) ;
	}
}
