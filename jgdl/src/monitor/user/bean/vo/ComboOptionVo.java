/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  cl 
 * @datetime:  2011-4-21 上午10:11:01
*/
package monitor.user.bean.vo;

/** 
 * easyUi combobox的option项
 * @author  cl
 * @datetime  2011-4-21 上午10:11:01
 */
public class ComboOptionVo {
	private int id = -1;
	private String text = null;
	private boolean selected = false;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
