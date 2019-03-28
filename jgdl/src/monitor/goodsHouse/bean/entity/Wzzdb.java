package monitor.goodsHouse.bean.entity;
/**
 *@ClassName
 *@dataTime 2017-10-14-下午3:24:45
 *@version
 *@author:唐青
 *@since
 */
public class Wzzdb {
	//ID
	private String SQU;
	//代码类别
	private String DMLB;
	//类别名称
	private String LBMC;
	//父级代码
	private String PARENTSQU;
	//节点
	private String JD;
	//组合代码
	private String ZHDM;
	
	private String XSCS;
	
	public String getSQU() {
		return SQU;
	}
	public void setSQU(String sQU) {
		SQU = sQU;
	}
	public String getDMLB() {
		return DMLB;
	}
	public void setDMLB(String dMLB) {
		DMLB = dMLB;
	}
	public String getLBMC() {
		return LBMC;
	}
	public void setLBMC(String lBMC) {
		LBMC = lBMC;
	}
	public String getPARENTSQU() {
		return PARENTSQU;
	}
	public void setPARENTSQU(String pARENTSQU) {
		PARENTSQU = pARENTSQU;
	}
	public String getJD() {
		return JD;
	}
	public void setJD(String jD) {
		JD = jD;
	}
	public String getZHDM() {
		return ZHDM;
	}
	public void setZHDM(String zHDM) {
		ZHDM = zHDM;
	}
	public String getXSCS() {
		return XSCS;
	}
	public void setXSCS(String xSCS) {
		XSCS = xSCS;
	}
	@Override
	public String toString() {
		return "Wzzdb [SQU=" + SQU + ", DMLB=" + DMLB + ", LBMC=" + LBMC
				+ ", PARENTSQU=" + PARENTSQU + ", JD=" + JD + ", ZHDM=" + ZHDM
				+ ", XSCS=" + XSCS + "]";
	}
	
	
}
