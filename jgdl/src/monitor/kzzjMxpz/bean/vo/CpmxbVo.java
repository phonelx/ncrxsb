/***
	* copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
	* Project Name:jgdl
	* @since：JDK1.6
	* @version：1.0
	* File Name:CpmxbVo.java
	* Date:2017-10-30上午10:27:08   
	***/
package monitor.kzzjMxpz.bean.vo;

/**
 * @ClassName:CpmxbVo
 * @dateTime: 2017-10-30 上午10:27:08
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
/**
 * @ClassName:CpmxbVo
 * @dateTime: 2017-10-30 上午10:29:01
 * @Description: TODO
 * @version 
 * @author: 康正秋
 * @since JDK 1.6
 * History：
 * Editor     version      Time               Operation
 */
public class CpmxbVo {
	
	private	String	SQU; // id
	private	String	CPBM;//产品编码
	private	String	CYMC;//部件名称
	private	String	BJXH;//部件型号
	private	String	JLDW;//计量单位
	private	String	CBDJ;//成本单价
	private	String	PARENTSQU;//父级节点
	private	String	EDHL;//额定耗量
	private	String	CPXH;//产品型号
	private	String	CPXL;//产品系列
	private	String	CPZM;//产品子目
	private	String	CPTZ;//产品特征
	
	private Integer SL;//数量

	public String getSQU() {
		return SQU;
	}

	public void setSQU(String sQU) {
		SQU = sQU;
	}

	public String getCPBM() {
		return CPBM;
	}

	public void setCPBM(String cPBM) {
		CPBM = cPBM;
	}

	public String getCYMC() {
		return CYMC;
	}

	public void setCYMC(String cYMC) {
		CYMC = cYMC;
	}

	public String getBJXH() {
		return BJXH;
	}

	public void setBJXH(String bJXH) {
		BJXH = bJXH;
	}

	public String getJLDW() {
		return JLDW;
	}

	public void setJLDW(String jLDW) {
		JLDW = jLDW;
	}

	public String getCBDJ() {
		return CBDJ;
	}

	public void setCBDJ(String cBDJ) {
		CBDJ = cBDJ;
	}

	public String getPARENTSQU() {
		return PARENTSQU;
	}

	public void setPARENTSQU(String pARENTSQU) {
		PARENTSQU = pARENTSQU;
	}

	public String getEDHL() {
		return EDHL;
	}

	public void setEDHL(String eDHL) {
		EDHL = eDHL;
	}

	public String getCPXH() {
		return CPXH;
	}

	public void setCPXH(String cPXH) {
		CPXH = cPXH;
	}

	public String getCPXL() {
		return CPXL;
	}

	public void setCPXL(String cPXL) {
		CPXL = cPXL;
	}

	public String getCPZM() {
		return CPZM;
	}

	public void setCPZM(String cPZM) {
		CPZM = cPZM;
	}

	public String getCPTZ() {
		return CPTZ;
	}

	public void setCPTZ(String cPTZ) {
		CPTZ = cPTZ;
	}

	public Integer getSL() {
		return SL;
	}

	public void setSL(Integer sL) {
		SL = sL;
	}

	@Override
	public String toString() {
		return "CpmxbVo [SQU=" + SQU + ", CPBM=" + CPBM + ", CYMC=" + CYMC
				+ ", BJXH=" + BJXH + ", JLDW=" + JLDW + ", CBDJ=" + CBDJ
				+ ", PARENTSQU=" + PARENTSQU + ", EDHL=" + EDHL + ", CPXH="
				+ CPXH + ", CPXL=" + CPXL + ", CPZM=" + CPZM + ", CPTZ=" + CPTZ
				+ ", SL=" + SL + "]";
	}
	
	

}
