package com.epam.finaltask.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.epam.finaltask.dao.entity.User;

/**
 * 	This class is used when run user's tag
 */
public class JSPListBean {
	private Iterator<User> it;
	private List<User> list;
	
	public JSPListBean(List<User> list){
		this.list = list;
	}
	
	public JSPListBean(){
		list = new ArrayList<User>();
	}
	
	public Integer getSize(){
		it = list.iterator();
		return list.size();
	}
	
	public User getElement(){
		return it.next();
	}
}
