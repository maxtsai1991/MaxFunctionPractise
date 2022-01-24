package com.example.demo.jsonpersoncat.data;

import java.util.List;

public class Response{
	private List<DataItem> data;
	private int errorCode;
	private String errorMsg;

	public List<DataItem> getData(){
		return data;
	}

	public int getErrorCode(){
		return errorCode;
	}

	public String getErrorMsg(){
		return errorMsg;
	}
}