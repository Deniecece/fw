/**
 * @(#)DispatcherServlet.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
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
 * TODO 填写功能说明
 * @author 单彤
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
		
		//*设置constant配置内容
		
		
		//init方法执行后r对象中存在默认权限的装载两大标签的属性
		ConstantTag ct = r.ctMap.get("encoding");
		String encoding = "iso8859-1";
		if(ct!=null){
			encoding = ct.getValue();
			arg0.setCharacterEncoding(encoding);
		}
	
		//*比较后缀
		
		//拿到suffix的constantTag对象
		ct = r.ctMap.get("suffix");
		//设置默认值在没有读取到数据时使用
		String [] ss = new String[]{".do"};
		//存在suffix对应的标签时处理要求的后缀
		if(ct != null){
			ss = ct.getValue().split(",");
		}
		//读取请求中的uri
		String path = req.getRequestURI();//qxgl/login.do
		///比较后缀
		boolean f = false;
		for(String s:ss){
			//带有指定后缀的符合要求的请求
			if(path.indexOf(s)!=-1){
				f = true;
				path = path.replace(s, "");//qxgl/login
				break ;
			}
		}
		//若不是满足要求的请求则放过
		if(!f){
			arg2.doFilter(arg0, arg1);
			return ;
		}
		//将请求截取成只带有名称的
		path = path.substring(path.lastIndexOf("/")+1);// login
		//查找对应action
		ActionTag at = r.ats.get(path);
		//如果没有对应action报异常
		if(at==null){
			throw new NoActionFoundException("404 no action name ["+path+"]");
		}
		//构建一个当前线程的全局对象存储信息
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
