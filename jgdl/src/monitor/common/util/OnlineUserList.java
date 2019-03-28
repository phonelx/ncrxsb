package monitor.common.util;

import java.util.Enumeration;
import java.util.Vector;

public class OnlineUserList {
	private static final OnlineUserList userList = new OnlineUserList();
	private Vector<String> v;
	private OnlineUserList(){
		v = new Vector<String>();
	}
	public static OnlineUserList getInstance(){
		return userList;
	}
	
	public void addUser(String username){
		if(username!=null){
			for(int i=0;i<v.size();i++){
				if(username.equalsIgnoreCase(v.get(i))){
					return ;
				}
			}
			v.addElement(username);
		}
	}
	
	public void removeUser(String username){
		if(username!=null){
			for(int i=0;i<v.size();i++){
				if(username.equalsIgnoreCase(v.get(i))){
					v.remove(username);
				}
			}
		}
	}
	
	public Enumeration<String> getOnlineUserList(){
		return v.elements();
	}
	
	public int getUserCount(){
		return v.size();
	}
	
	public boolean hasLogin(String username){
		for(int i=0;i<v.size();i++){
			if(username.equalsIgnoreCase(v.get(i))){
				return true;
			}
		}
		return false;
	}
}
