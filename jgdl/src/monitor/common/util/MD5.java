package monitor.common.util;

import java.security.MessageDigest;

/**
 * @description 数据加密
 * @author LiBin
 * @date  2010.05.06
 *
 */
public class MD5 {
    public MD5()
    {
    }

    /**
     * ----------------------------------------------------------------------------
     * @Description   : 对指定字符串加密
     * @param         :    @param s 需要加密的字符串
     * @return        :    String 加密处理后的字符串
     * @author        :    LiBin
     * @CreateTime    :    2010-5-6 上午10:45:09
     * @version       : 1.00
     * -----------------------------------------------------------------------------
     */
    public static final String getMd5(String s)
    {
        char hexDigits[] = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
        };
        try{
        char str[];
        byte strTemp[] = s.getBytes();
        MessageDigest mdTemp = MessageDigest.getInstance("MD5");
        mdTemp.update(strTemp);
        byte md[] = mdTemp.digest();
        int j = md.length;
        str = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++)
        {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }

        return new String(str);
        }catch(Exception e){
        return null;
        }
    }
}
