/**
 * @(#)ActionTag.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc.domain;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * TODO ��д����˵��
 * @author ��ͮ
 */
public class ActionTag implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Class<? extends Object> clazz;
	private Method method;
	
	private List<FunctionTag> aft;
	private Map<String,ResultTag> frt;
	public ActionTag(String name, Class<? extends Object> clazz, Method method, List<FunctionTag> aft,
			Map<String, ResultTag> frt) {
		super();
		this.name = name;
		this.clazz = clazz;
		this.method = method;
		this.aft = aft;
		this.frt = frt;
	}
	public ActionTag() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<? extends Object> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends Object> clazz) {
		this.clazz = clazz;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public List<FunctionTag> getAft() {
		return aft;
	}
	public void setAft(List<FunctionTag> aft) {
		this.aft = aft;
	}
	public Map<String, ResultTag> getFrt() {
		return frt;
	}
	public void setFrt(Map<String, ResultTag> frt) {
		this.frt = frt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
