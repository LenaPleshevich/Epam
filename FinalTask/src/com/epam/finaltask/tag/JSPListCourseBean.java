package com.epam.finaltask.tag;

import com.epam.finaltask.dao.entity.Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 	This class is used when run course's tag
 */
public class JSPListCourseBean {
	private Iterator<Course> it;
	private List<Course> list;

	public JSPListCourseBean(List<Course> list){
		this.list = list;
	}

	public JSPListCourseBean(){
		list = new ArrayList<Course>();
	}
	
	public Integer getSize(){
		it = list.iterator();
		return list.size();
	}
	
	public Course getElement(){
		return it.next();
	}
}
