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
public class ResultTag implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private String content;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ResultTag() {
		super();
	}
	public ResultTag(String name, String type, String content) {
		super();
		this.name = name;
		this.type = type;
		this.content = content;
	}
}
