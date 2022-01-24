package com.example.demo.jsonpersoncat.data;

import java.util.List;

public class DataItem{
	private int visible;
	private List<Object> children;
	private String name;
	private boolean userControlSetTop;
	private int id;
	private int courseId;
	private int parentChapterId;
	private int order;

	public int getVisible(){
		return visible;
	}

	public List<Object> getChildren(){
		return children;
	}

	public String getName(){
		return name;
	}

	public boolean isUserControlSetTop(){
		return userControlSetTop;
	}

	public int getId(){
		return id;
	}

	public int getCourseId(){
		return courseId;
	}

	public int getParentChapterId(){
		return parentChapterId;
	}

	public int getOrder(){
		return order;
	}
}