/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.7
	* @version：1.0
	* File Name:Project_child_item.java
	* Date:2018-1-31下午4:24:52   
	***/
package monitor.projectConfig.bean.entity;

/**
 * @ClassName:Project_child_item  部位表实体
 * @dateTime: 2018-1-31 下午4:24:52
 * @Description: TODO
 * @version 
 * @author: 黄月
 * @since JDK 1.7
 * History：
 * Editor     version      Time               Operation
 */
public class Project_Child_Site {

	private String site_squ;
	
	/**
	  * @Fields  bwmc:TODO(部位名称)
	  * @since JDK1.7
	*/
	private String bwmc;
	
	/**
	  * @Fields  bwbg:TODO(部位标高)
	  * @since JDK1.7
	*/
	private Double bwbg;
	
	/**
	  * @Fields  azdg:TODO(安装吊高)
	  * @since JDK1.7
	*/
	private Double azdg;
	
	/**
	  * @Fields  gjlx:TODO(是否是改建工程)
	  * @since JDK1.7
	*/
	private Integer gjlx;
	
	/**
	  * @Fields  siteNum:TODO(部位2位编码)
	  * @since JDK1.7
	*/
	private String siteNum;
	
/*	*//**
	  * @Fields  gdlx:TODO(管道类型)
	  * @since JDK1.7
	*//*
	private String gdlx;
	
	*//**
	  * @Fields  gdgg:TODO(管道规格)
	  * @since JDK1.7
	*//*
	private String gdgg;
	
	*//**
	  * @Fields  gdcz:TODO(管道材质)
	  * @since JDK1.7
	*//*
	private String gdcz;*/

	/**
	 * 子单位工程id
	 */
	private String child_squ;
	
	/**
	  * @Fields  createDate:TODO(创建时间)
	  * @since JDK1.7
	*/
	private String createDate;
	
	/**
	  * @Fields  updateDate:TODO(修改时间)
	  * @since JDK1.7
	*/
	private String updateDate;
	
	
	
	
	public String getSiteNum() {
		return siteNum;
	}

	public void setSiteNum(String siteNum) {
		this.siteNum = siteNum;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getSite_squ() {
		return site_squ;
	}

	public void setSite_squ(String site_squ) {
		this.site_squ = site_squ;
	}

	public String getBwmc() {
		return bwmc;
	}

	public void setBwmc(String bwmc) {
		this.bwmc = bwmc;
	}

	public Double getBwbg() {
		return bwbg;
	}

	public void setBwbg(Double bwbg) {
		this.bwbg = bwbg;
	}

	public Double getAzdg() {
		return azdg;
	}

	public void setAzdg(Double azdg) {
		this.azdg = azdg;
	}

	public Integer getGjlx() {
		return gjlx;
	}

	public void setGjlx(Integer gjlx) {
		this.gjlx = gjlx;
	}

/*	public String getGdlx() {
		return gdlx;
	}

	public void setGdlx(String gdlx) {
		this.gdlx = gdlx;
	}

	public String getGdgg() {
		return gdgg;
	}

	public void setGdgg(String gdgg) {
		this.gdgg = gdgg;
	}

	public String getGdcz() {
		return gdcz;
	}

	public void setGdcz(String gdcz) {
		this.gdcz = gdcz;
	}*/

	public String getChild_squ() {
		return child_squ;
	}

	public void setChild_squ(String child_squ) {
		this.child_squ = child_squ;
	}
	
}
