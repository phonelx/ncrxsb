package monitor.goodsHouse.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import monitor.common.BaseAction;
import monitor.goodsHouse.bean.entity.Img;
import monitor.goodsHouse.bean.entity.ImgFl;
import monitor.goodsHouse.service.IImgService;
import monitor.goodsHouse.service.impl.ImgServiceImpl;
import monitor.user.bean.vo.PageInfoVo;

public class ImgAction extends BaseAction {
	
	/**
	  * @Fields  serialVersionUID:TODO(用一句话描述这个变量表示什么)
	  * @since JDK1.7
	*/
	private static final long serialVersionUID = 1L;

	@Resource(name = "imgService")
	private ImgServiceImpl imgService;
	
	private File file;
	
	private String squ;
	
	
	private PageInfoVo pageVo = new PageInfoVo();
	
	
	
	

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

	public String getSqu() {
		return squ;
	}

	public void setSqu(String squ) {
		this.squ = squ;
	}



	/**
	 * 跳转到图片资料管理
	 * @return
	 */
	public String pictureManage(){
		List<Map<String, String>> listMenu;
		
		try {
			listMenu = imgService.getImgFlList();
			getRequest().setAttribute("list", listMenu);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	//获取图片分类列表
	public void getImgFlList(){
		JSONObject json = new JSONObject();
		List<Map<String, String>> list = imgService.getImgFlList();		
		json.accumulate("list", list);
		writeJsonBack(json.toString());
	}
	
	
	
	public void searchImgFlInfo(){
		
		String pageStr = getRequest().getParameter("page");
		String rowsStr = getRequest().getParameter("rows");
		
		String key = getRequest().getParameter("searchKey");
		
		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
		
		try {
			this.pageVo = imgService.getImgFlXq(pageVo, key);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
	
		writeJsonBack(jsonObj.toString());
		
	}
	
	
	
	//添加图片分类
	public void addImgFl(){
		String flmc = getRequest().getParameter("flmc");
		String fldm = getRequest().getParameter("fldm");
		
		ImgFl imgFl = new ImgFl();
		imgFl.setFLMC(flmc);
		imgFl.setFLDM(fldm);
		String result = imgService.addImgFl(imgFl);
		writeJsonBack(getText(result));	
		
	}
	//获取分类详情通过squ
	public void getImgFlBysqu(){
		JSONObject json = new JSONObject();
		String flsqu = getRequest().getParameter("squ");
		List<ImgFl> list = imgService.getImgFlBySqu(flsqu);
		
		json.accumulate("list", list);
		writeJsonBack(json.toString());
		
	}
	//编辑图片分类
	public void updateImgFl(){
		String flsqu = getRequest().getParameter("squ");
		String flmc = getRequest().getParameter("flmc");
		String fldm = getRequest().getParameter("fldm");
		
		ImgFl imgFl = new ImgFl();
		imgFl.setSQU(flsqu);
		imgFl.setFLMC(flmc);
		imgFl.setFLDM(fldm);
		String result = imgService.editImgFl(imgFl);
		writeJsonBack(getText(result));	
		
	}
	
	public void delImgFl(){
		String squ = getRequest().getParameter("squ");
		
		JSONObject json = new JSONObject();
		int i = 0;
		try {
			i = imgService.delImgFl(squ);
			json.accumulate("staus", i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(String.valueOf(i));
		writeJsonBack(json.toString());
	}
	
	//获取图片详情
	public void getImgList(){
		
		String squ = getRequest().getParameter("squ");
		String key = getRequest().getParameter("key");
		
		String pageStr = getRequest().getParameter("pages");
		String rowsStr = getRequest().getParameter("rows");
		if (pageStr != null || !"".equals(pageStr)) {
			this.pageVo.setPageNumber(Integer.parseInt(pageStr));
		}
		if (rowsStr != null) {
			this.pageVo.setPageSize(Integer.parseInt(rowsStr));
		}
		
		try {
			this.pageVo = imgService.getImgList(pageVo, squ, key);
			// jsonObj.accumulate("pageVo", pageVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.fromObject(pageVo);
	//	 System.out.println(jsonObj.toString());
		writeJsonBack(jsonObj.toString());
	}
	
	
	public void addImg(){
		
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

			String realName = file.getName();
			
			fileName = System.currentTimeMillis()
					+ realName.substring(realName.lastIndexOf("."));
			 imgsrc = sourcePath + "/" + fileName;
			//cp.setZP(fileName);
			
			// 复制文件
			InputStream is = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(imgsrc);
			byte b[] = new byte[1024 * 1024];
			int length = 0;
			while (-1 != (length = is.read(b))) {
				fos.write(b, 0, length);
			}
			fos.flush();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String tpname = getRequest().getParameter("tpName");
		String tpsm = getRequest().getParameter("tpsm");
		String tpfl = getRequest().getParameter("TPFLsqu");
		
		Img img = new Img();
		img.setTPNAME(tpname);
		img.setTPSM(tpsm);
		img.setTPFL(tpfl);
		img.setTPDZ(fileName);
		
		String result = imgService.addImg(img);
		writeJsonBack(getText(result));	
	}
	
	//编辑图片
	public void editImg(){
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
		if (file == null) {
			fileName = getRequest().getParameter("image");
		}else{
		String realName = file.getName();
		fileName = System.currentTimeMillis()
				+ realName.substring(realName.lastIndexOf("."));
		 imgsrc = sourcePath + "/" + fileName;
		//cp.setZP(fileName);
	//	System.out.println(imgsrc);
		// 复制文件
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
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String squ = getRequest().getParameter("TPsqu");
	String tpname = getRequest().getParameter("tpName");
	String tpsm = getRequest().getParameter("tpsm");
	String tpfl = getRequest().getParameter("TPFLsqu");
	
	Img img = new Img();
	img.setSQU(squ);
	img.setTPDZ(fileName);
	img.setTPNAME(tpname);
	img.setTPSM(tpsm);
	img.setTPFL(tpfl);
	
	String result = imgService.editImg(img);
	writeJsonBack(getText(result));	
}
//删除图片	
public void deleteImg(){
	
	String squ = getRequest().getParameter("squ");
	String result = imgService.delImg(squ);
	writeJsonBack(getText(result));	
}	
	
}
