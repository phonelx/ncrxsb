package monitor.common.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import monitor.projectConfig.bean.entity.Project;
import monitor.projectConfig.bean.entity.Project_Child;

import org.apache.struts2.ServletActionContext;

/**
 * @ClassName
 * @dataTime 2018-6-2-上午10:21:14
 * @version
 * @author:唐青
 * @since
 */
public class ExcleUtil {
	/*public static void main(String[] args) {
		drowExcle();
	}
*/
	public static void drowExcle(Project project,Project_Child child) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		//执行导出
		WritableWorkbook workbook = null;
		OutputStream os = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			//取得输出流
			   os = response.getOutputStream(); // 清空输出流
			   response.reset(); // 不能用用中文设置 filename，会出错 // 设定输出文件头
			   response.setHeader("Content-disposition", "attachment; filename="+sdf.format(new Date()) + "report.xls"); // 定义输出类型
			   response.setContentType("application/msexcel"); 
			  // 打开文件 
			  workbook = Workbook.createWorkbook(os);
			 
			/*workbook = Workbook.createWorkbook(new File(
					"C://Users//Administrator//Desktop//工作//ywg//test.xls"));*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 生成名为“sheet1”的工作表，参数0表示这是第一页
		WritableSheet sheet = workbook.createSheet("sheet1", 0);
		WritableSheet sheet2 = workbook.createSheet("sheet2", 1);
		//第一部分
		getPart1(sheet,project,child);
		//第一部分和第二部分
		getPart2(sheet2);
		try {
			workbook.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				workbook.write();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				workbook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getPart1(WritableSheet sheet,Project project,Project_Child child) {
		
		
		try {
			
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0) sheet.mergeCells(0,0,5,0);
			// 合并第一列第一行到第六列第一行的所有单元格
			try {
				// list = ceService.policeStationList(date, lb, arr[4]);
				// 表格内容水平居中
				WritableCellFormat cellFormatx = new WritableCellFormat();
				cellFormatx.setAlignment(jxl.format.Alignment.CENTRE);
				// 表格内容垂直居中
				WritableCellFormat cellFormaty = new WritableCellFormat();
				cellFormaty
						.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				cellFormaty.setWrap(true);

				sheet.mergeCells(0, 0, 7, 0);
				Label label = new Label(0, 0, "第1部分  信息汇总表");
				label.setCellFormat(cellFormatx);
				sheet.addCell(label);

				sheet.mergeCells(0, 1, 0, 7);
				label = new Label(0, 1, "工程信息");
				label.setCellFormat(cellFormaty);
				sheet.addCell(label);

				label = new Label(1, 1, "1");
				sheet.addCell(label);

				label = new Label(2, 1, "工程名称");
				sheet.addCell(label);
				
				label = new Label(3, 1, project.getXmmc());
				sheet.addCell(label);

				sheet.mergeCells(4, 1, 0, 9);
				label = new Label(4, 1, "支架信息");
				label.setCellFormat(cellFormaty);
				sheet.addCell(label);

				label = new Label(5, 1, "12");
				sheet.addCell(label);

				label = new Label(6, 1, "型号");
				sheet.addCell(label);
				label = new Label(7, 1, "");
				sheet.addCell(label);
				
				label = new Label(1, 2, "2");
				sheet.addCell(label);
				
				label = new Label(2, 2, "子项名称");
				sheet.addCell(label);
				
				label = new Label(3, 2, child.getZdwxmmc());
				sheet.addCell(label);

				label = new Label(5, 2, "13");
				sheet.addCell(label);

				label = new Label(6, 2, "编号");
				sheet.addCell(label);
				label = new Label(7, 2, project.getXmbh()+child.getChildNum());
				sheet.addCell(label);

				label = new Label(1, 3, "3");
				sheet.addCell(label);

				label = new Label(2, 3, "建筑类别");
				sheet.addCell(label);
				label = new Label(3, 3, child.getJzlb());
				sheet.addCell(label);

				label = new Label(5, 3, "14");
				sheet.addCell(label);

				label = new Label(6, 3, "服务系统");
				sheet.addCell(label);
				label = new Label(7, 3, "");
				sheet.addCell(label);

				label = new Label(1, 4, "4");
				sheet.addCell(label);

				label = new Label(2, 4, "工程地区");
				sheet.addCell(label);
				label = new Label(3, 4, project.getXmdz());
				sheet.addCell(label);

				label = new Label(5, 4, "15");
				sheet.addCell(label);

				label = new Label(6, 4, "安装部位");
				sheet.addCell(label);
				label = new Label(7, 4, "");
				sheet.addCell(label);


				label = new Label(1, 5, "5");
				sheet.addCell(label);

				label = new Label(2, 5, "抗震设防烈度");
				sheet.addCell(label);
				label = new Label(3, 5, project.getDzsfld());
				sheet.addCell(label);

				label = new Label(5, 5, "15");
				sheet.addCell(label);

				label = new Label(6, 5, "安装吊高");
				sheet.addCell(label);
				label = new Label(7, 5, "");
				sheet.addCell(label);

				label = new Label(1, 6, "6");
				sheet.addCell(label);

				label = new Label(2, 6, "地震加速度");
				sheet.addCell(label);
				label = new Label(3, 6, project.getDzjsd());
				sheet.addCell(label);
				
				label = new Label(5, 5, "17");
				sheet.addCell(label);
				label = new Label(6, 5, "安装标高");
				sheet.addCell(label);
				label = new Label(7, 5, "");
				sheet.addCell(label);
				
				label = new Label(1, 6, "7");
				sheet.addCell(label);

				label = new Label(2, 6, "地震类型");
				sheet.addCell(label);
				label = new Label(3, 6, project.getDzlx());
				sheet.addCell(label);

				label = new Label(5, 6, "18");
				sheet.addCell(label);

				label = new Label(6, 6, "设置状态");
				sheet.addCell(label);
				label = new Label(7, 6, "");
				sheet.addCell(label);

				label = new Label(1, 7, "8");
				sheet.addCell(label);

				label = new Label(2, 7, "建筑高度");
				sheet.addCell(label);
				label = new Label(3, 7, String.valueOf(child.getJzgd()));
				sheet.addCell(label);


				label = new Label(5, 7, "19");
				sheet.addCell(label);

				label = new Label(6, 7, "斜撑角度");
				sheet.addCell(label);
				label = new Label(7, 7, "");
				sheet.addCell(label);

				sheet.mergeCells(0, 8, 0, 10);
				label = new Label(0, 8, "管线信息");
				label.setCellFormat(cellFormaty);
				sheet.addCell(label);

				label = new Label(1, 8, "9");
				sheet.addCell(label);

				label = new Label(2, 8, "管线类型");
				sheet.addCell(label);
				label = new Label(3, 8, "");
				sheet.addCell(label);

				label = new Label(5, 8, "20");
				sheet.addCell(label);

				label = new Label(6, 8, "结构连接方式");
				sheet.addCell(label);
				label = new Label(7, 8, "");
				sheet.addCell(label);

				label = new Label(1, 9, "10");
				sheet.addCell(label);

				label = new Label(2, 9, "管线材质");
				sheet.addCell(label);
				label = new Label(3, 9, "");
				sheet.addCell(label);

				label = new Label(5, 9, "21");
				sheet.addCell(label);

				label = new Label(6, 9, "侧向斜撑数量");
				sheet.addCell(label);
				label = new Label(7, 9, "");
				sheet.addCell(label);

				label = new Label(1, 10, "11");
				sheet.addCell(label);

				label = new Label(2, 10, "管线规格");
				sheet.addCell(label);
				label = new Label(3, 10, "");
				sheet.addCell(label);

				label = new Label(5, 10, "22");
				sheet.addCell(label);

				label = new Label(6, 10, "纵向斜撑数量");
				sheet.addCell(label);
				label = new Label(7, 10, "");
				sheet.addCell(label);

				sheet.mergeCells(0, 12, 0, 30);
				label = new Label(0, 12, "支架形式简图");
				label.setCellFormat(cellFormaty);
				sheet.addCell(label);

				File imgFile = new File(
						"C:/Users/Administrator/Desktop/工作/ywg/part1.png");
				sheet.mergeCells(1, 11, 6, 29);
				WritableImage image = new WritableImage(2, 13, 6, 30, imgFile);
				image.setHeight(18);
				image.setWidth(4);
				label.setCellFormat(cellFormaty);
				sheet.addImage(image);

				// workbook.write();
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	public static void getPart2(WritableSheet sheet) {

		// WritableWorkbook workbook = null;

		try {

			/*
			 * workbook = Workbook.createWorkbook(new File(
			 * "C://Users//Administrator//Desktop//工作//ywg//test.xls"));
			 */
			// 生成名为“sheet1”的工作表，参数0表示这是第一页
			// WritableSheet sheet = workbook.createSheet("sheet2", 1);

			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0) sheet.mergeCells(0,0,5,0);
			// 合并第一列第一行到第六列第一行的所有单元格
			try {
				// list = ceService.policeStationList(date, lb, arr[4]);
				// 表格内容水平居中
				WritableCellFormat cellFormatx = new WritableCellFormat();
				cellFormatx.setAlignment(jxl.format.Alignment.CENTRE);
				// 表格内容垂直居中
				WritableCellFormat cellFormaty = new WritableCellFormat();
				cellFormaty
						.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				cellFormaty.setWrap(true);

				sheet.mergeCells(0, 0, 11, 0);
				Label label = new Label(0, 0, "第2部分  荷载计算信息");
				label.setCellFormat(cellFormatx);
				sheet.addCell(label);

				sheet.mergeCells(0, 1, 0, 2);
				label = new Label(0, 1, "管道类别");
				sheet.addCell(label);

				sheet.mergeCells(1, 1, 1, 2);
				label = new Label(1, 1, "规格");
				sheet.addCell(label);

				sheet.mergeCells(2, 1, 2, 2);
				label = new Label(2, 1, "数量");
				sheet.addCell(label);

				sheet.mergeCells(3, 1, 3, 2);
				label = new Label(3, 1, "理论重量");
				sheet.addCell(label);

				sheet.mergeCells(4, 1, 5, 1);
				label = new Label(4, 1, "支架间距（m)");
				label.setCellFormat(cellFormatx);
				sheet.addCell(label);

				// sheet.mergeCells(4, 1, 5, 1);
				label = new Label(4, 2, "侧向");
				label.setCellFormat(cellFormatx);
				sheet.addCell(label);

				label = new Label(5, 2, "纵向");
				label.setCellFormat(cellFormatx);
				sheet.addCell(label);

				sheet.mergeCells(6, 1, 6, 2);
				label = new Label(6, 1, "αEK");
				sheet.addCell(label);

				sheet.mergeCells(7, 1, 7, 2);
				label = new Label(7, 1, "水平地震分项系数");
				sheet.addCell(label);

				sheet.mergeCells(8, 1, 8, 2);
				label = new Label(8, 1, "重力载荷分项系数");
				sheet.addCell(label);

				sheet.mergeCells(9, 1, 10, 1);
				label = new Label(9, 1, "地震水平力设计值(KN)");
				sheet.addCell(label);

				label = new Label(9, 2, "侧向");
				sheet.addCell(label);

				label = new Label(10, 2, "纵向");
				sheet.addCell(label);

				label = new Label(11, 1, "备注");
				sheet.addCell(label);

				sheet.mergeCells(0, 4, 11, 4);
				label = new Label(0, 4, "第3部分  受力分析信息");
				label.setCellFormat(cellFormatx);
				sheet.addCell(label);

				File imgFile = new File(
						"C:/Users/Administrator/Desktop/工作/ywg/part2-1.png");
				sheet.mergeCells(0, 5, 4, 17);
				WritableImage image = new WritableImage(0, 6, 4, 17, imgFile);
				image.setHeight(10);
				image.setWidth(4);
				label.setCellFormat(cellFormatx);
				sheet.addImage(image);

				File imgFile1 = new File(
						"C:/Users/Administrator/Desktop/工作/ywg/part2-2.png");

				sheet.mergeCells(5, 5, 10, 17);
				WritableImage image1 = new WritableImage(6, 6, 10, 17, imgFile1);
				image1.setHeight(10);
				image1.setWidth(4);
				label.setCellFormat(cellFormatx);
				sheet.addImage(image1);

				label = new Label(11, 5, "备注");
				sheet.addCell(label);

				label = new Label(11, 6, "模型-3-Z");
				sheet.addCell(label);

				sheet.mergeCells(0, 18, 4, 18);
				label = new Label(0, 18, "侧向受力分析图");
				sheet.addCell(label);

				sheet.mergeCells(5, 18, 10, 18);
				label = new Label(5, 18, "纵向受力分析图");
				sheet.addCell(label);

				sheet.mergeCells(11, 5, 11, 17);
				label = new Label(11, 5, "");
				sheet.addCell(label);

				sheet.mergeCells(0, 19, 1, 19);
				label = new Label(0, 19, "侧向支架斜撑受力");
				sheet.addCell(label);

				// sheet.mergeCells(2, 22, 2, 22);
				label = new Label(2, 19, "N5=");
				sheet.addCell(label);

				sheet.mergeCells(3, 19, 4, 19);
				label = new Label(3, 19, "18.5");
				sheet.addCell(label);

				sheet.mergeCells(5, 19, 6, 19);
				label = new Label(5, 19, "纵向支架斜撑受力");
				sheet.addCell(label);

				label = new Label(7, 19, "N7=");
				sheet.addCell(label);

				label = new Label(8, 19, "17.7");
				sheet.addCell(label);

				label = new Label(9, 19, "N6=");
				sheet.addCell(label);

				label = new Label(10, 19, "N5=13.6");
				sheet.addCell(label);

				/* workbook.write(); */
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
