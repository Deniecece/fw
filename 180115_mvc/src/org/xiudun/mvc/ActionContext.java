/**
 * @(#)ActionContext.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
 */
package org.xiudun.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TODO ction上下文对象。
 * 该对象装载action请求响应过程中所需要的额外信息。
 * 如：request， response等。
 * @author 单彤
 */
public class ActionContext {
	
	static ThreadLocal<ActionContext> tl = new ThreadLocal<ActionContext>();
	
	public static ActionContext getActionContext(){
		return tl.get();//获取信息方法
	}
	
	private String encoding;
	private HttpServletRequest request ;
	private HttpServletResponse response ;
	private HttpSession session ;
	//装载请求过程中传递的参数。
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
