/**
 * @(#)NoActionFoundException.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc.exception;

/**
 * TODO ��д����˵��
 * @author ��ͮ
 */
public class NoActionFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoActionFoundException(){}
	
	public NoActionFoundException(String msg){
		super(msg) ;
	}
}
