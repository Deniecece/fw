/**
 * @(#)ConstantTag.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
 */
package org.xiudun.mvc.domain;

import java.io.Serializable;

/**
 * TODO 填写功能说明
 * @author 单彤
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
