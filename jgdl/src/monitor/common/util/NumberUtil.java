/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-7-27 下午05:51:24
*/
package monitor.common.util;

import java.text.DecimalFormat;

/** 
 * <description> 
 * @author  cl
 * @datetime  2011-7-27 下午05:51:24
 */
public class NumberUtil {
	/**
	 * 格式化double数字(#占位表示一个整数，0占位表示一个小数)
	 * @param formatter 格式化字符串:"###.00"返回123.45
	 * @param number 要格式化的数字
	 * @return double
	 */
	public static double round(String formatter,double number){
		DecimalFormat df = new DecimalFormat(formatter);
		return Double.valueOf(df.format(number));
	}
	
	/**
	 * 格式化double数字,支持特殊字符
	 * @param number 要格式化的数字
	 * @param formatter 格式化字符串:"#,###.00"返回1,234.12
	 * @return double
	 */
	public static String round(double number,String formatter){
		DecimalFormat df = new DecimalFormat(formatter);
		return df.format(number);
	}
	
	
	
	public static void main(String args[]){
		/*NumberUtil util = new NumberUtil();
		double c =  8213.42393412414;
		System.out.println(util.round("###.00", c));
		*/
		
		System.out.println((double)(Math.round(1234.12442)));
	}
}
