/**
 * @(#)ResultTag.java
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
public class FunctionTag implements Serializable{

	
	private static final long serialVersionUID = 1L;
	//可以实现在读取xml时就加载类
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
