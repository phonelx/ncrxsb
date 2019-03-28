package monitor.goodsHouse.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import monitor.common.BaseAction;
import monitor.goodsHouse.bean.entity.Cpmxb;
import monitor.goodsHouse.bean.entity.Zhbj;
import monitor.goodsHouse.bean.vo.ZhbjVo;
import monitor.goodsHouse.service.IStentsService;
import monitor.goodsHouse.service.impl.StentsServiceImpl;
import monitor.user.bean.vo.PageInfoVo;

/**
 * @ClassName
 * @dataTime 2017-10-14-下午2:44:29
 * @version
 * @author:唐青
 * @since
 */
public class StentsAction extends BaseAction {
	/**
	 * @Fields serialVersionUID:TODO(用一句话描述这个变量表示什么)
	 * @since JDK1.7
	 */
	// private File[] uploadFile;
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "stentsService")
	private StentsServiceImpl stentsService;
	
	private PageInfoVo pageVo = new PageInfoVo();
	
	private File userUploadFile;
	
	private ZhbjVo zhVo = null;
	
	private File zhfile = null;

	/**
	 * 
	 * goStentsIndex:(跳转抗震支架部件配置页面). TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	 * 
	 * @author: 唐青
	 * @dateTime: 2017-10-18 下午6:28:47
	 * @return String
	 * @since JDK 1.7
	 */
	public String goStentsIndex() {
		/* System.out.println(11); */
		List<Map<String, String>> listMenu;
		try {
			listMenu = stentsService.getStentsMenu("","CPXL");
			getRequest().setAttribute("list", listMenu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}
	
	public String goZhBjIndex() {
		/* System.out.println(11); */
		List<Map<String, String>> listMenu;
		try {
			listMenu = stentsService.getStentsMenu("","CPXL");
			getRequest().setAttribute("list", listMenu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}
	public void queryChileStents() {

		String squ = getRequest().getParameter("squ");
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");
		String key = getRequest().getParameter("key");

		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}

		try {
			this.pageVo = stentsService.selectChildCp(pageVo, squ,key);
			// jsonObj.accumulate("pageVo", pageVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
		
		writeJsonBack(jsonObj.toString());
	}

	private String bjmc = null;
	private String cppm = null;
	private String bjxh = null;
	private String cbdj = null;
	private String edhl = null;
	private String jldw = null;
	private String azfs = null;
	private String lxcs = null;
	private File file = null;
	// 提交过来的file的名字
	private String fileFileName;

	private String cpxl = null;
	private String cpzm = null;
	private String cptz = null;
	private String cpxh = null;
	private String cpid = null;

	/**
	 * 添加 addStents:(这里用一句话描述这个方法的作用). TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	 * 
	 * @author: 唐青
	 * @dateTime: 2017-10-18 下午6:51:07 void
	 * @since JDK 1.7
	 */

	public void addStents() {
		int i = 0;

		cp.setCYMC(bjmc);
		cp.setCBDJ(cbdj);

		cp.setCPBM(cppm);
		cp.setJLDW(jldw);
		cp.setEDHL(edhl);
		cp.setBJXH(bjxh);
	
		cp.setCPXH(cpxh);
		cp.setAZFSDM(azfs);
		cp.setLXCS(lxcs);
	//	System.out.println(fileFileName);
		JSONObject json = new JSONObject();
		// System.out.println("--------------------"+cp.getCPXH());
		getResponse().setCharacterEncoding("utf-8");

		getResponse().setContentType("text/html;charset=utf-8");

		String path = getRequest().getSession().getServletContext()
				.getRealPath("/");
		File prjPath = new File(path);
		File sourcePath = new File(prjPath.getParentFile().getAbsoluteFile()
				+ "/upload");
		if (!sourcePath.exists()) {
			sourcePath.mkdirs();
		}

		String fileName = null;
		String imgsrc = null;
		try {

			String realName = fileFileName;
			if (realName == null) {
				cp.setZP("1");
			} else {
				fileName = System.currentTimeMillis()
						+ realName.substring(realName.lastIndexOf("."));
				imgsrc = sourcePath + "/" + fileName;
				cp.setZP(fileName);
				InputStream is = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(imgsrc);
				byte b[] = new byte[1024 * 1024];
				int length = 0;
				while (-1 != (length = is.read(b))) {
					fos.write(b, 0, length);
				}
				fos.flush();
				fos.close();
			}

			i = stentsService.addCp(cp);
			if (i == 1) {
				json.accumulate("status", "success");
			} else {
				json.accumulate("status", "error");
			}
			// System.out.println(i);
			// json = JSONObject.fromObject(String.valueOf(i).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(json.toString());
		writeJsonBack(json.toString());
	}

	/**
	 * 修改 editStents:(这里用一句话描述这个方法的作用). TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	 * 
	 * @author: 唐青
	 * @dateTime: 2017-10-18 下午6:52:04 void
	 * @since JDK 1.7
	 */
	public void editStents() {  
		cp.setSQU(cpid);
		cp.setCYMC(bjmc);
		cp.setCBDJ(cbdj);

		cp.setCPBM(cppm);
		cp.setJLDW(jldw);
		cp.setEDHL(edhl);
		cp.setBJXH(bjxh);
		cp.setCPXL(cpxl);
		cp.setCPZM(cpzm);
		cp.setCPTZ(cptz);
		cp.setCPXH(cpxh);		
		cp.setAZFSDM(azfs);
		cp.setLXCS(lxcs);

		JSONObject json = new JSONObject();
		int i = 0;
		// System.out.println("=========="+file);

		getResponse().setCharacterEncoding("utf-8");

		getResponse().setContentType("text/html;charset=utf-8");

		String path = getRequest().getSession().getServletContext()
				.getRealPath("/");
		File prjPath = new File(path);
		File sourcePath = new File(prjPath.getParentFile().getAbsoluteFile()
				+ "/upload");
		if (!sourcePath.exists()) {
			sourcePath.mkdirs();
		}

		String fileName = null;
		String imgsrc = null;
		try {

			String realName = fileFileName;
			if (realName == null) {
				cp.setZP("1");
			} else {
				fileName = System.currentTimeMillis()
						+ realName.substring(realName.lastIndexOf("."));
				imgsrc = sourcePath + "/" + fileName;
				cp.setZP(fileName);
				InputStream is = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(imgsrc);
				byte b[] = new byte[1024 * 1024];
				int length = 0;
				while (-1 != (length = is.read(b))) {
					fos.write(b, 0, length);
				}
				fos.flush();
				fos.close();
			}

			// 复制文件

			i = stentsService.editCp(cp);
			if (i == 1) {
				json.accumulate("status", "success");
			} else {
				json.accumulate("status", "error");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		writeJsonBack(json.toString());

	}

	/**
	 * 
	 * deleteStents:(删除). TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	 * 
	 * @author: 唐青
	 * @dateTime: 2017-10-18 下午6:53:23 void
	 * @since JDK 1.7
	 */
	public void deleteStents() {
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		int count = 0;
	
		
		try {
			count = stentsService.deleteCp(squ);
			json.accumulate("count", String.valueOf(count));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		writeJsonBack(json.toString());
	}

	public void queryStentsBySqu() {
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();

		try {
			List<Cpmxb> list = stentsService.selectStentsBySqu(squ);
			json.accumulate("slist", list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writeJsonBack(json.toString());
	}

	public void dowloadStentsMB() {
		String filePath = ServletActionContext.getServletContext().getRealPath(
				File.separator + "download/word/BJZJPZMB.zip");
		String saveFileName = "BJZJPZMB.zip";
		// response.setContentType("application/octet-stream;charset=utf-8");
		getResponse().setContentType("application/zip");
		// response.setContentType("application/x-download");
		OutputStream outp = null;
		FileInputStream in = null;
		try {

			getResponse()
					.addHeader(
							"Content-Disposition",
							"attachment;filename=\""
									+ encodeFilename(getRequest(), saveFileName)
									+ "\"");// 名称两边的双引号不能省略 兼容火狐 文件名中的空格
			outp = getResponse().getOutputStream();
			in = new FileInputStream(filePath);
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) > 0) {
				outp.write(b, 0, i);
			}
			outp.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				in = null;
			}
			if (outp != null) {
				try {
					outp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				outp = null;
			}
		}
	}

	private static String encodeFilename(HttpServletRequest request,
			String fileName) throws UnsupportedEncodingException {
		String agent = request.getHeader("USER-AGENT");

		try {
			// IE
			if (null != agent && -1 != agent.indexOf("MSIE")) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
				// Firefox
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			try {
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return fileName;
	}

	public void addBatchStents() {

		// 此时的Workbook应该是从 客户端浏览器上传过来的 uploadFile了,其实跟读取本地磁盘的一个样
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
					userUploadFile));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			// HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(new
			// File("C:\\Users\\Administrator\\Downloads\\201708081523.xls")));
			HSSFSheet hssfSheet = wb.getSheetAt(0);
			List<Cpmxb> list = new ArrayList<Cpmxb>();
			Cpmxb cpb = null;
			if (hssfSheet != null) {
				// 遍历excel,从第一行开始 即 rowNum=0,逐个获取单元格的内容,然后进行格式处理,最后插入数据库
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					// System.out.println(hssfRow.toString());
					if (hssfRow == null) {
						continue;
					}
					//System.out.println(String.valueOf(hssfRow.getCell(4)));
					cpb = new Cpmxb();
					cpb.setCYMC(String.valueOf(hssfRow.getCell(0)));
					cpb.setCPBM(String.valueOf(hssfRow.getCell(1)));
					cpb.setBJXH(String.valueOf(hssfRow.getCell(2)));
					cpb.setCBDJ(String.valueOf(hssfRow.getCell(3)));
					cpb.setJLDW(String.valueOf(hssfRow.getCell(4)));
					cpb.setEDHL(String.valueOf(hssfRow.getCell(5)));
					
					cpb.setCPXL(cpxl);
					cpb.setCPZM(cpzm);
					cpb.setCPTZ(cptz);
					cpb.setCPXH(cpxh);
					cpb.setZP("");
					list.add(cpb);

				}

			}
			stentsService.addBatchStents(list);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// writeJsonBack(json.toString());
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 
  * queryZhbjInfo:(查询组合部件菜单).
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
  * @author: 唐青
  * @dateTime: 2017-10-25 下午6:36:21 void
  * @since JDK 1.7
 */
	public void queryZhbjMenu(){
		//System.out.println(99);
		JSONObject json = new JSONObject();
		try {
			List<Zhbj> list = stentsService.selectZhbjInfo("");
			json.accumulate("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writeJsonBack(json.toString());
	}
	/**
	 * 
	  * queryZhbjMenu:(组合部件信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午6:36:38 void
	  * @since JDK 1.7
	 */
	public void queryZhbjInfo(){
		//System.out.println(99);
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");
		String key = getRequest().getParameter("key");
		
		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
		try {
			pageVo = stentsService.selectZhbj(pageVo,"",key);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jsonObj = JSONObject.fromObject(pageVo);

		writeJsonBack(jsonObj.toString());
	}
	/**
	 * 
	  * queryLbInfo:(添加组合部件是获取部件系列信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-25 下午7:20:45 void
	  * @since JDK 1.7
	 */
	public void queryLbInfo(){
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		try {
			List<Map<String, String>> listMenu = stentsService.selectLbInfo(squ);
			json.accumulate("state", "success");
			json.accumulate("list", listMenu);
		} catch (Exception e) {
			json.accumulate("state", "wrong");
			json.accumulate("state", e.getMessage());
		}
		//System.out.println(json.toString());
		writeJsonBack(json.toString());
	}
	/**
	 *
	  * queryBjInfo:( 添加组合部件是获取部件信息).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-26 下午4:09:45 void
	  * @since JDK 1.7
	 */
	public void queryBjInfo(){
		String squ = getRequest().getParameter("squ");
		String type = getRequest().getParameter("type");
	
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");

		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
	
		
		try {
			this.pageVo = stentsService.queryBjInfo(pageVo,squ,type);
			
		} catch (Exception e) {
			
		}
		JSONObject json = JSONObject.fromObject(pageVo);
		System.out.println(json);
		writeJsonBack(json.toString());
	}
	
	
	public void queryZhbjXq(){
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");
		String squ = getRequest().getParameter("squ");
		String key = getRequest().getParameter("key");

		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
		try {
			pageVo = stentsService.selectZhbjXq(pageVo, squ,key);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
		//System.out.println(jsonObj.toString());
		writeJsonBack(jsonObj.toString());
	}
	
	public void querySingleZhbj(){
		JSONObject json = new JSONObject();
		String squ = getRequest().getParameter("squ");
		try {
			List<Map<String,String>> list = stentsService.selectSingleZhbj(squ);
			json.accumulate("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeStringBack(json.toString());
	}
	/**
	 * 
	  * addZhbj:(添加组合部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-27 上午11:45:11 void
	  * @since JDK 1.7
	 */
	public void addZhbj(){
		JSONObject json = new JSONObject();
	
		getResponse().setCharacterEncoding("utf-8");

		getResponse().setContentType("text/html;charset=utf-8");

		String path = getRequest().getSession().getServletContext()
				.getRealPath("/");
		File prjPath = new File(path);
		File sourcePath = new File(prjPath.getParentFile().getAbsoluteFile()
				+ "/upload");
		if (!sourcePath.exists()) {
			sourcePath.mkdirs();
		}

		String fileName = null;
		String imgsrc = null;
		try {
			
			if (zhfile == null) {
				zhVo.setZP("1");
			} else {
				String realName = zhfile.getName();
				fileName = System.currentTimeMillis()
						+ realName.substring(realName.lastIndexOf("."));
				imgsrc = sourcePath + "/" + fileName;
				zhVo.setZP(fileName);
				InputStream is = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(imgsrc);
				byte b[] = new byte[1024 * 1024];
				int length = 0;
				while (-1 != (length = is.read(b))) {
					fos.write(b, 0, length);
				}
				fos.flush();
				fos.close();
			}
			
			stentsService.addZhbj(zhVo);
			json.accumulate("staus", "success");
			// System.out.println(i);
			// json = JSONObject.fromObject(String.valueOf(i).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(json.toString());
		writeStringBack(json.toString());
	}
	
	/**
	 * 
	  * updateBuJian:(修改组合部件在的部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-30 下午7:26:09 void
	  * @since JDK 1.7
	 */
	public void updateBuJian(){
		JSONObject json = new JSONObject();
		try {
			stentsService.updateBuJian(zhVo);
			json.accumulate("staus", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeStringBack(json.toString());
	}
	
	/**
	 * 
	  * deleteBuJian:(删除组合部件中的部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-30 下午7:26:24 void
	  * @since JDK 1.7
	 */
	public void deleteBuJian(){
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		try {
			stentsService.deleteBuJian(squ);
			json.accumulate("staus", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeStringBack(json.toString());
	}
	
	/**
	 * 
	  * addBuJian:(添加组合部件中的部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-31 下午6:53:09 void
	  * @since JDK 1.7
	 */
	public void addBuJian(){
		JSONObject json = new JSONObject();
		try {
			String bj = getRequest().getParameter("bjsl");
			String squ = getRequest().getParameter("squ");
			
			String[] arry = bj.split(",");
			stentsService.addBuJian(arry,squ);
			json.accumulate("staus", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeStringBack(json.toString());
	}
	/**
	 * 
	  * updateZhbj:(修改组合部件).
	  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项).
	  * @author: 唐青
	  * @dateTime: 2017-10-31 下午6:53:28 void
	  * @since JDK 1.7
	 */
	public void updateZhbj(){
		getResponse().setCharacterEncoding("utf-8");

		getResponse().setContentType("text/html;charset=utf-8");
		JSONObject json = new JSONObject();
		String path = getRequest().getSession().getServletContext()
				.getRealPath("/");
		File prjPath = new File(path);
		File sourcePath = new File(prjPath.getParentFile().getAbsoluteFile()
				+ "/upload");
		if (!sourcePath.exists()) {
			sourcePath.mkdirs();
		}

		String fileName = null;
		try {

		
			if(zhfile==null){
				zhVo.setZP("1");
			}else{
				String realName = zhfile.getName();
				fileName = System.currentTimeMillis()
						+ realName.substring(realName.lastIndexOf("."));
				String imgsrc = sourcePath + "/" + fileName;
				zhVo.setZP(fileName);
				// 复制文件
				InputStream is = new FileInputStream(zhfile);
				FileOutputStream fos = new FileOutputStream(imgsrc);
				byte b[] = new byte[1024 * 1024];
				int length = 0;
				while (-1 != (length = is.read(b))) {
					fos.write(b, 0, length);
				}
				fos.flush();
				fos.close();
			}
			
			
			stentsService.updateZhbj(zhVo);
			json.accumulate("staus", "success");
			// System.out.println(i);
			// json = JSONObject.fromObject(String.valueOf(i).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeStringBack(json.toString());
	}
	
	public void deleteZhbj(){
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		String str = "";
		try {
			str = stentsService.deleteZhbj(squ);
			json.accumulate("staus", str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(String.valueOf(i));
		writeJsonBack(json.toString());
	}
	
	public void getZhbjBysqu(){
		String squ = getRequest().getParameter("squ");
		JSONObject json = new JSONObject();
		try {
			List<Zhbj> list = stentsService.getZhbjBysqu(squ);
			json.accumulate("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		writeJsonBack(json.toString());
	}
	public Cpmxb getCp() {
		return cp;
	}

	public void setCp(Cpmxb cp) {
		this.cp = cp;
	}



	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public void setCppm(String cppm) {
		this.cppm = cppm;
	}

	public void setBjxh(String bjxh) {
		this.bjxh = bjxh;
	}

	public void setCbdj(String cbdj) {
		this.cbdj = cbdj;
	}

	public void setEdhl(String edhl) {
		this.edhl = edhl;
	}

	public void setJldw(String jldw) {
		this.jldw = jldw;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setCpxl(String cpxl) {
		this.cpxl = cpxl;
	}

	public void setCpzm(String cpzm) {
		this.cpzm = cpzm;
	}

	public void setCptz(String cptz) {
		this.cptz = cptz;
	}

	public void setCpxh(String cpxh) {
		this.cpxh = cpxh;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public File getUserUploadFile() {
		return userUploadFile;
	}

	public void setUserUploadFile(File userUploadFile) {
		this.userUploadFile = userUploadFile;
	}
	private Cpmxb cp = new Cpmxb();

	public PageInfoVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageInfoVo pageVo) {
		this.pageVo = pageVo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public ZhbjVo getZhVo() {
		return zhVo;
	}

	public void setZhVo(ZhbjVo zhVo) {
		this.zhVo = zhVo;
	}

	public File getZhfile() {
		return zhfile;
	}

	public void setZhfile(File zhfile) {
		this.zhfile = zhfile;
	}

	public String getAzfs() {
		return azfs;
	}

	public void setAzfs(String azfs) {
		this.azfs = azfs;
	}

	public void setLxcs(String lxcs) {
		this.lxcs = lxcs;
	}
	
	
}
