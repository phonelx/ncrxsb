//用于密码的base64加密
package monitor.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

/**
 * 用于字符串之间的替换和操作
 * 
 * 部分方法吸收了一些open source api。
 * 
 */

public class StringConvert {
	private static Logger logger = Logger.getLogger(StringConvert.class);
	private static final String CHARSET_NAME = "ISO8859_1";

	/**
	 * 转换成16进制的字符串表示
	 * 
	 * @param bytes
	 * @return 转换后的字符串
	 */
	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * 将换成16进制的字符串还原为字节数组
	 * 
	 * @param hex
	 * @return byte[]
	 */
	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * 字节和字符之间的转换
	 * 
	 * @param ch
	 * @return byte
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	public static byte[] base64Decode(String s) throws IOException,
			javax.mail.MessagingException {
		if (s == null || s.length() == 0) {
			return null;
		}
		ByteArrayInputStream bais = new ByteArrayInputStream(s
				.getBytes(CHARSET_NAME));
		InputStream is = MimeUtility.decode(bais, "base64");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch = -1;
		while ((ch = is.read()) != -1) {
			out.write(ch);
		}
		out.flush();
		bais.close();
		return out.toByteArray();
	}

	public static String base64Encode(byte[] buf) throws IOException,
			javax.mail.MessagingException {
		if (buf == null || buf.length == 0) {
			return "";
		}
		InputStream is = new ByteArrayInputStream(buf);
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStream os = javax.mail.internet.MimeUtility
				.encode(baos, "base64");
		int chr = -1;
		while ((chr = bis.read()) != -1) {
			os.write(chr);
		}
		bis.close();
		baos.close();
		// return new String(baos.toByteArray(), CHARSET_NAME);
		return new String(baos.toByteArray());
	}

	// ////////////
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取当前具体时间的函数 param @format 需要返回的具体时间的格式，比如"yyyy-MM-dd HH:mm:ss"
	 * @author:  cl 
	 * @param format 如"yyyymmdd"
	 * @return String
	 */
	public static String getTime(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
		return simpleFormat.format(cal.getTime());
	}

//	public static void main(String[] args) {
//		System.out.println(StringConvert.getBASE64("oracle123"));
//		System.out.println(StringConvert.getFromBASE64("YjNKaFkyeGxNVEl6"));
//		System.out.println(File.separatorChar);
//	}

}
