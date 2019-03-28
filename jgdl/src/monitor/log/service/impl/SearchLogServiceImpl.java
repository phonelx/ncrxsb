/*
 * @copyright:  heli Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  jk 
 * @datetime:  Apr 18, 2011 5:22:41 PM
*/
package monitor.log.service.impl;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import monitor.common.DBDefault;
import monitor.common.exception.BizException;
import monitor.common.util.StringConvert;
import monitor.log.dao.ISearchLogDAO;
import monitor.log.service.ISearchLogService;

/** 
 * <description> 
 * @author  jk
 * @datetime  Apr 18, 2011 5:22:41 PM
 */
public class SearchLogServiceImpl implements ISearchLogService {
	
	private ISearchLogDAO searchLogDAO =null;
	
	public ISearchLogDAO getSearchLogDAO() {
		return searchLogDAO;
	}

	public void setSearchLogDAO(ISearchLogDAO searchLogDAO) {
		this.searchLogDAO = searchLogDAO;
	}
	
	/**
	 * 返回日志查询的结果
	 * @param table
	 * @param columns
	 * @param checkcols
	 * @param input
	 * @param pageSize
	 * @param pageNo
	 * @return List<String>
	 */
	public List<String> searchLogS(String table,String []columns,String []checkcols,String []input,int pageSize,int pageNo){
		
		return searchLogDAO.searchLogS(table, columns, checkcols, input,pageSize,pageNo);
		
	}
	
	/**
	 * @description 删除查询出来的日志
	 * @param 
	 * @return void
	 */
	public void deleteLogs(String table,String []checkcols,String []input){
		
//		String []squStr = squ.split(",");
		
		
		searchLogDAO.deleteLog(table, checkcols, input);
	}
	
	
	/**
	  * 将userLoginGrid导出为excel文档
	 * @param exprocessBean
	 * @return String
	 */
	public String exportUserLgIAsExcel(String table, String[] xszd,String[] cxzd, String[] input,String userName) {
		
		List<String> resultList = searchLogDAO.searchLogNoPage(table, xszd, cxzd, input);
		
		if(resultList==null||resultList.size()==0){
			return null;
		}
		
		String downFilePath = null;
		 // 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//=====================================================
		// 生成一个表格
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.autoSizeColumn(0);
		sheet.setDefaultColumnWidth(20);
		
		//样式表
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		
		//生成表头
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("用户名");
	
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("登录Ip");
		
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("登录时间 ");
		
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("退出时间 ");
		
		//循环解析生成每行
		for(int i = 1;i<=(resultList.size()-1)/xszd.length;i++){
			
			row = sheet.createRow(i);
			
			cell = row.createCell(0);
			cell.setCellStyle(style);
			//System.out.println(resultList.get(step));
			cell.setCellValue(resultList.get(1+(i-1)*xszd.length));
			
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(2+(i-1)*xszd.length));
			
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(3+(i-1)*xszd.length));
		}
		
		//输出合计
		row = sheet.createRow((resultList.size()-1)/xszd.length+1);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("共有");
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(""+resultList.get(0)+"行");
	
		
		
		//=====================================================
		// 输出到文件
		DBDefault.clearDownloadTmpDir();
		// 输出到文件
		FileOutputStream fout;
		try {
			downFilePath = DBDefault.downloadPath+"loginLog_"+StringConvert.getTime("yyyyMMddmmss")+".xls";//"_StatisticExpResult.xls" ;
			fout = new FileOutputStream(downFilePath);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			throw new BizException(e);
		}
		
		return downFilePath;
	}
	
	
	/**
	  * 将userOprGrid导出为excel文档
	 * @param exprocessBean
	 * @return String
	 */
	public String exportUserOprAsExcel(String table, String[] xszd,String[] cxzd, String[] input,String userName) {
		
		List<String> resultList = searchLogDAO.searchLogNoPage(table, xszd, cxzd, input);
		
		if(resultList==null||resultList.size()==0){
			return null;
		}
		
		String downFilePath = null;
		 // 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//=====================================================
		// 生成一个表格
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.autoSizeColumn(0);
		sheet.setDefaultColumnWidth(20);
		
		//样式表
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		
		//生成表头
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("用户名");
	
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("操作类型");
		
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("操作关键字");
		
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("操作日期");
		
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("操作描述");
		
		//循环解析生成每行
		for(int i = 1;i<=(resultList.size()-1)/xszd.length;i++){
			
			row = sheet.createRow(i);
			
			cell = row.createCell(0);
			cell.setCellStyle(style);
			//System.out.println(resultList.get(step));
			cell.setCellValue(resultList.get(1+(i-1)*xszd.length));
			
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(2+(i-1)*xszd.length));
			
			if(resultList.get(2+(i-1)*xszd.length).equals("0")){
				cell.setCellValue("添加");
			}else if(resultList.get(2+(i-1)*xszd.length).equals("1")){
				cell.setCellValue("删除");
			}
			else if(resultList.get(2+(i-1)*xszd.length).equals("2")){
				cell.setCellValue("修改");
			}else if(resultList.get(2+(i-1)*xszd.length).equals("3")){
				cell.setCellValue("查询");
			}else{
				cell.setCellValue("WS查询");
			}
			
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(3+(i-1)*xszd.length));
			
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(4+(i-1)*xszd.length));
			
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(5+(i-1)*xszd.length));
			
		}
		
		//输出合计
		row = sheet.createRow((resultList.size()-1)/xszd.length+1);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("共有");
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(""+resultList.get(0)+"行");
	
		
		
		//=====================================================
		DBDefault.clearDownloadTmpDir();
		// 输出到文件
		FileOutputStream fout;
		try {
			downFilePath = DBDefault.downloadPath+"oprtLog_"+StringConvert.getTime("yyyyMMddmmss")+".xls";//"_StatisticExpResult.xls" ;
			fout = new FileOutputStream(downFilePath);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return downFilePath;
	}
	
	
	/**
	  * 将systemRunGrid导出为excel文档
	 * @param exprocessBean
	 * @return String
	 */
	public String exportSysRunAsExcel(String table, String[] xszd,String[] cxzd, String[] input,String userName) {
		
		List<String> resultList = searchLogDAO.searchLogNoPage(table, xszd, cxzd, input);
		
		if(resultList==null||resultList.size()==0){
			return null;
		}
		
		String downFilePath = null;
		 // 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		
		//=====================================================
		// 生成一个表格
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.autoSizeColumn(0);
		sheet.setDefaultColumnWidth(20);
		
		//样式表
		HSSFCellStyle style=wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		
		//生成表头
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("日志类型 ");
	
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("系统对象 ");
		
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("消息  ");
		
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("时间  ");
		
		//循环解析生成每行
		for(int i = 1;i<=(resultList.size()-1)/xszd.length;i++){
			
			row = sheet.createRow(i);
			
			cell = row.createCell(0);
			cell.setCellStyle(style);
			//System.out.println(resultList.get(step));
			if(resultList.get(1+(i-1)*xszd.length).equals("0")){
				cell.setCellValue("业务逻辑异常");
			}else if(resultList.get(1+(i-1)*xszd.length).equals("1")){
				cell.setCellValue("系统异常");
			}else if(resultList.get(1+(i-1)*xszd.length).equals("2")){
				cell.setCellValue("数据库异常");
			}else{
				cell.setCellValue("其他异常");
			}
			
			
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(2+(i-1)*xszd.length));
			
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(3+(i-1)*xszd.length));
			
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(resultList.get(4+(i-1)*xszd.length));
			
		}
		
		//输出合计
		row = sheet.createRow((resultList.size()-1)/xszd.length+1);
		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("共有");
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue(""+resultList.get(0)+"行");
	
		
		
		//=====================================================
		DBDefault.clearDownloadTmpDir();
		// 输出到文件
		FileOutputStream fout;
		try {
			downFilePath = DBDefault.downloadPath+"systemlog_"+StringConvert.getTime("yyyyMMddmmss")+".xls";//"_StatisticExpResult.xls" ;
			fout = new FileOutputStream(downFilePath);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return downFilePath;
	}
	
	public  List doQueryAgain(String sql,int dbsSqu,int maxRows,int pageSize,int pageNo){
		return searchLogDAO.doQueryAgain(sql, dbsSqu,maxRows, pageSize,pageNo);
	}

}
