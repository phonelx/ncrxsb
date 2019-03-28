/***
 * copyright ：  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * Project Name:monitor
 * @since：JDK1.6
 * @version：1.0
 * File Name:InterLogEntity.java
 * Date:2016-3-22上午11:34:40
 ***/
package monitor.log.bean.entity;

/**
 * @ClassName:InterLogEntity
 * @dateTime: 2016-3-22 上午11:34:40
 * @version
 * @author: 张家俊
 * @since JDK 1.6 History： Editor version Time Operation
 */
public class InterLogEntity {
	private int num_id;
	private String interface_time;
	private String requester;
	private String terminal_id;
	private String interface_condition;
	private String interface_result;

	public int getNum_id() {
		return num_id;
	}

	public void setNum_id(int num_id) {
		this.num_id = num_id;
	}

	public String getInterface_time() {
		return interface_time;
	}

	public void setInterface_time(String interface_time) {
		this.interface_time = interface_time;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getTerminal_id() {
		return terminal_id;
	}

	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	public String getInterface_condition() {
		return interface_condition;
	}

	public void setInterface_condition(String interface_condition) {
		this.interface_condition = interface_condition;
	}

	public String getInterface_result() {
		return interface_result;
	}

	public void setInterface_result(String interface_result) {
		this.interface_result = interface_result;
	}
}
