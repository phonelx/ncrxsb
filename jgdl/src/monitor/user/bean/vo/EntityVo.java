/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-23 下午04:14:45
 */
package monitor.user.bean.vo;

/**
 * <description>
 * 
 * @author cl
 * @datetime 2011-4-23 下午04:14:45
 */
public class EntityVo {
	private int squ = -1;
	private int datasourceSqu = -1;
	private String name = null;
	private String title = null;

	public int getSqu() {
		return squ;
	}

	public void setSqu(int squ) {
		this.squ = squ;
	}

	public int getDatasourceSqu() {
		return datasourceSqu;
	}

	public void setDatasourceSqu(int datasourceSqu) {
		this.datasourceSqu = datasourceSqu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
