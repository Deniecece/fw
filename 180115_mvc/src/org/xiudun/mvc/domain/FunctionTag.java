/**
 * @(#)ResultTag.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc.domain;

import java.io.Serializable;

/**
 * TODO ��д����˵��
 * @author ��ͮ
 */
public class FunctionTag implements Serializable{

	
	private static final long serialVersionUID = 1L;
	//����ʵ���ڶ�ȡxmlʱ�ͼ�����
	private Class<? extends Object> clazz;
	public FunctionTag(Class<? extends Object> clazz) {
		super();
		this.clazz = clazz;
	}
	public FunctionTag() {
		super();
	}
	public Class<? extends Object> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends Object> clazz) {
		this.clazz = clazz;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
