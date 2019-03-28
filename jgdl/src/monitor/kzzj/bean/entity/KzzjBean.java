/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:SeismicBean.java
	* Date:2017-10-17上午11:12:38   
	***/
package monitor.kzzj.bean.entity;

/**
 * @ClassName:抗震支架基本配置
 * @dateTime: 2017-10-17 上午11:12:38
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public class KzzjBean {
	
	private String dxSqu;   //唯一标识
	private String dxmc;//支架名称
	private String gdlx;//管道类型
	private String azfs;//安装方式
	private String zjxs;//支架形式
	private String zp;//图片
	private String zjlx;
	private String lxcs;
	//设置状态
	private String szzt;
	//专业码
	private String zym;
	
	
	
	
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getDxSqu() {
		return dxSqu;
	}
	public void setDxSqu(String dxSqu) {
		this.dxSqu = dxSqu;
	}
	public String getDxmc() {
		return dxmc;
	}
	public void setDxmc(String dxmc) {
		this.dxmc = dxmc;
	}
	public String getGdlx() {
		return gdlx;
	}
	public void setGdlx(String gdlx) {
		this.gdlx = gdlx;
	}
	public String getAzfs() {
		return azfs;
	}
	public void setAzfs(String azfs) {
		this.azfs = azfs;
	}
	public String getZjxs() {
		return zjxs;
	}
	public void setZjxs(String zjxs) {
		this.zjxs = zjxs;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
	}
	
	public String getLxcs() {
		return lxcs;
	}
	public void setLxcs(String lxcs) {
		this.lxcs = lxcs;
	}
	
	public String getSzzt() {
		return szzt;
	}
	public void setSzzt(String szzt) {
		this.szzt = szzt;
	}
	public String getZym() {
		return zym;
	}
	public void setZym(String zym) {
		this.zym = zym;
	}
	@Override
	public String toString() {
		return "KzzjBean [dxSqu=" + dxSqu + ", dxmc=" + dxmc + ", gdlx=" + gdlx
				+ ", azfs=" + azfs + ", zjxs=" + zjxs + ", zp=" + zp
				+ ", zjlx=" + zjlx + ", lxcs=" + lxcs + "]";
	}

	
	

}