/**
 * @(#)ActionProxy.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
 */
package org.xiudun.mvc;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.xiudun.mvc.domain.ActionTag;
import org.xiudun.mvc.domain.FunctionTag;
import org.xiudun.mvc.domain.ResultTag;
import org.xiudun.mvc.exception.NoResultFoundException;
import org.xiudun.mvc.function.FileuploadFunction;
import org.xiudun.mvc.function.ParamFunction;
import org.xiudun.mvc.function.ServletFunction;
import org.xiudun.mvc.function.SetParamFunction;

/**
 * TODO 代理类，调用action对象。 可以在调用action之前先执行切面。
 * 	代理模式
 * 		静态代理
 * 		动态代理
 * @author 单彤
 */
public class ActionProxy {
	
	ActionTag at ;
	public void execute() throws Exception{
		//创建目标对象action
		Object target = BeanFactory.getInstance().createBean(at.getClazz());

		FunctionChain chain = new FunctionChain();
		//1.将目标对象和方法赋给链
		chain.target = target;
		chain.targetMethod = at.getMethod();
		
		//2.创建切面集合给链
		List<IFunction> fs = new ArrayList<IFunction>();
		//将封装切面add
		IFunction f1 = new FileuploadFunction();
		IFunction f2 = new ServletFunction();
		IFunction f3 = new ParamFunction();
		IFunction f4 = new SetParamFunction();
		fs.add(f1);
		fs.add(f2);
		fs.add(f3);
		fs.add(f4);
		
		if(at.getAft()!=null){
			for(FunctionTag ft:at.getAft()){
				IFunction f = (IFunction) BeanFactory.getInstance().createBean(ft.getClazz());
				fs.add(f);
			}
		}
		chain.fs = fs;
		
		//3.获得执行切面到最后目标的返回值
		String result = chain.execute();
		
		if(result == null||"".equals(result)){
			//不需要框架响应。
			return ;
		}
		
		//4.根据返回值参照xml响应处理
		
		ResponseBody r = at.getMethod().getAnnotation(ResponseBody.class);
		if(r!=null){//处理注解--自定义返回值			
			//response.setContentType("text/html;charset="+encoding);
			//response.getWriter().print(result);
			ActionContext.getActionContext().getResponse().setContentType("text/html;charset="+ActionContext.getActionContext().getEncoding());
			ActionContext.getActionContext().getResponse().getWriter().print(r);
			return ;
		}
		
		//获取配置中的响应处理
		ResultTag rt = at.getFrt().get(result);
		if(rt == null){
			throw new NoResultFoundException("404 no result name ["+result+"]");
		}
		
		
		String type = rt.getType();
		String content = rt.getContent();
		//4.1转发
		if(type == null || "dispatcher".equals(type)){
			//转发 , 会自动将action中所有get方法的返回值装载到request中。使用request.setAttribute(key,value);
			Method[] ms = at.getClazz().getMethods();
			for(Method m:ms){
				//通过方法名找到需要传到网页的参数
				String mname = m.getName();
				if(mname.startsWith("get")){
					//得到参数名
					String key = mname.substring(3);
					if(key.length()==1){
						key = key.toLowerCase();
					}else{
						key = key.substring(0, 1).toLowerCase()+key.substring(1);
					}
					//通过反射执行相应get方法得到值
					Object value = m.invoke(target);
					//将值通过上下文对象setAttribute存储在request对象中
					ActionContext.getActionContext().getRequest().setAttribute(key, value);
				}
			}//将request和response对象转发过去
			ActionContext.getActionContext().getRequest().getRequestDispatcher(content).forward(
					ActionContext.getActionContext().getRequest(),
					ActionContext.getActionContext().getResponse());
		
		}else if(type.equals("redirect")){
			//4.2重定向
			ActionContext.getActionContext().getResponse().sendRedirect(content);
		}else if(type.equals("download")){
			HttpServletResponse resp = ActionContext.getActionContext().getResponse() ;
			//4.3下载
			//调用action的getFileName方法，该方法的返回值作为下载文件的文件名。
			Method m = target.getClass().getMethod("getFileName") ;
			String filename = (String) m.invoke(target) ;
			resp.setHeader("content-disposition", "attachment;filename="+filename);
			/*
			 * 下载的本质就是读取服务器本地的文件，输出到浏览器
			 * 读取本地文件，需要文件输入流 . 
			 * 	要求action对象提供一个xxx方法。该方法用来提供一个可以读取服务器本地文件的输入流。
			 *  content 即为xxx方法名
			 *  <result name="success" type="download" >getInputStream</result>
			 * 输出浏览器需要response.getOutputStream()|getWriter()
			 * */
			m = target.getClass().getMethod(content) ;
			InputStream is = (InputStream) m.invoke(target) ; // userAction.getInputStream()
			while(true){
				int b = is.read() ;
				if(b == -1)break ;
				resp.getOutputStream().write(b);
			}
			is.close(); 
			resp.getOutputStream().flush();
		}
		
	}
}
