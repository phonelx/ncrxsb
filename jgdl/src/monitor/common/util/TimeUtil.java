/* * @copyright: heli Technologies Co., Ltd. Copyright 2010-9999, All rights reserved * @description: <description> * @author: cl * @datetime: 2011-8-1 下午05:46:39 */
package monitor.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** <description>
 * @author cl 
 * @datetime 2011-8-1 下午05:46:39 
 */
public class TimeUtil {
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
	
	public static String getLastTime(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
		cal.add(Calendar.HOUR, -2);
		return simpleFormat.format(cal.getTime());
	}
	/**
	 * 根据指定date格式将字符串转换为Date对象
	 * @author: cl 
	 * @param dateFormatter 解析日期格式："yyyyMMddHHmm"
	 * @param dateStr 解析的日期字符串
	 * @return Date
	 */
	public static Date parseDate(String dateFormatter,String dateStr){
		Date returnDate = null;
		try {
			SimpleDateFormat sdt = new SimpleDateFormat(dateFormatter);
			returnDate = sdt.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
}
