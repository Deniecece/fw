/**
 * @(#)AbstractFilter.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * TODO ȱʡ������
 * @author ��ͮ
 */
public abstract class AbstractFilter implements Filter{

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
