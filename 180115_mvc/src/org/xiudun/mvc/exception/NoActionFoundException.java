/**
 * @(#)NoActionFoundException.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
 */
package org.xiudun.mvc.exception;

/**
 * TODO 填写功能说明
 * @author 单彤
 */
public class NoActionFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoActionFoundException(){}
	
	public NoActionFoundException(String msg){
		super(msg) ;
	}
}
