/**
 * @(#)DispatcherServlet.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xiudun.mvc.domain.ActionTag;
import org.xiudun.mvc.domain.ConstantTag;
import org.xiudun.mvc.exception.NoActionFoundException;

/**
 * TODO ��д����˵��
 * @author ��ͮ
 */
public class DispatcherFilter extends AbstractFilter{
	ReadXml r;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		r = new ReadXml();
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0 ;
		HttpServletResponse resp = (HttpServletResponse) arg1 ;
		
		//*����constant��������
		
		
		//init����ִ�к�r�����д���Ĭ��Ȩ�޵�װ�������ǩ������
		ConstantTag ct = r.ctMap.get("encoding");
		String encoding = "iso8859-1";
		if(ct!=null){
			encoding = ct.getValue();
			arg0.setCharacterEncoding(encoding);
		}
	
		//*�ȽϺ�׺
		
		//�õ�suffix��constantTag����
		ct = r.ctMap.get("suffix");
		//����Ĭ��ֵ��û�ж�ȡ������ʱʹ��
		String [] ss = new String[]{".do"};
		//����suffix��Ӧ�ı�ǩʱ����Ҫ��ĺ�׺
		if(ct != null){
			ss = ct.getValue().split(",");
		}
		//��ȡ�����е�uri
		String path = req.getRequestURI();//qxgl/login.do
		///�ȽϺ�׺
		boolean f = false;
		for(String s:ss){
			//����ָ����׺�ķ���Ҫ�������
			if(path.indexOf(s)!=-1){
				f = true;
				path = path.replace(s, "");//qxgl/login
				break ;
			}
		}
		//����������Ҫ���������Ź�
		if(!f){
			arg2.doFilter(arg0, arg1);
			return ;
		}
		//�������ȡ��ֻ�������Ƶ�
		path = path.substring(path.lastIndexOf("/")+1);// login
		//���Ҷ�Ӧaction
		ActionTag at = r.ats.get(path);
		//���û�ж�Ӧaction���쳣
		if(at==null){
			throw new NoActionFoundException("404 no action name ["+path+"]");
		}
		//����һ����ǰ�̵߳�ȫ�ֶ���洢��Ϣ
		ActionContext ac = new ActionContext();
		ac.setEncoding(encoding);
		ac.setRequest(req);
		ac.setResponse(resp);
		ac.setSession(req.getSession());
		
		ActionContext.tl.set(ac);
		
		ActionProxy proxy = new ActionProxy();
		proxy.at = at;
		try{
			proxy.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
