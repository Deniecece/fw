/**
 * @(#)ActionContext.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TODO ction�����Ķ���
 * �ö���װ��action������Ӧ����������Ҫ�Ķ�����Ϣ��
 * �磺request�� response�ȡ�
 * @author ��ͮ
 */
public class ActionContext {
	
	static ThreadLocal<ActionContext> tl = new ThreadLocal<ActionContext>();
	
	public static ActionContext getActionContext(){
		return tl.get();//��ȡ��Ϣ����
	}
	
	private String encoding;
	private HttpServletRequest request ;
	private HttpServletResponse response ;
	private HttpSession session ;
	//װ����������д��ݵĲ�����
	private Map<String,List<? extends Object>> valueStack = new HashMap<String,List<? extends Object>>();

	public String getEncoding() {
		return encoding;
	}

	 void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	 void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	 void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	 void setSession(HttpSession session) {
		this.session = session;
	}
	 
	 public void putValue(String key , List<? extends Object> value) {
		valueStack.put(key, value);
	}
	 
	public List<? extends Object> getValue(String key){
		return valueStack.get(key) ;
	}
	


}
