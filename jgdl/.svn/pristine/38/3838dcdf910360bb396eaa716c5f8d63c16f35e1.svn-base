package monitor.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {

	@SuppressWarnings("finally")
	public static String Base64Encoder(InputStream in,String newFlieName){
		ByteArrayOutputStream baos =new  ByteArrayOutputStream() ; 
		StringBuffer temp=new StringBuffer();
		try{
		int len = 0; 
		byte[] bytes = new byte[1024]; 
		while ((len = in.read(bytes)) != -1) { 
			baos.write(bytes, 0, len);
		}
		byte[] tempA=baos.toByteArray();
		sun.misc.BASE64Encoder encoder=new sun.misc.BASE64Encoder();
		temp.append(encoder.encode(tempA));
		if (newFlieName != null) {
				Base64Decoder(temp.toString(), newFlieName);
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return temp.toString();
		}
	}
	
	public static byte[] Base64Decoder(String baseStr,String newFlieName){
		try {
			if(newFlieName!=null){
				File bfile=new File(newFlieName);
				FileOutputStream fos2=new FileOutputStream(bfile);
				fos2.write(new BASE64Decoder().decodeBuffer(baseStr.toString()));
			}
			return new BASE64Decoder().decodeBuffer(baseStr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}
    // 加密  
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    } 
	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
