/**
 * @(#)ConstantTag.java
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
public class ConstantTag implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ConstantTag() {
		super();
	}
	public ConstantTag(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
}
