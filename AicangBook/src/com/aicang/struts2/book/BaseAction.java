package com.aicang.struts2.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext; 


public class BaseAction extends ActionSupport{


	private static final long serialVersionUID = 1L;
	
	public static HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public static HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
}
