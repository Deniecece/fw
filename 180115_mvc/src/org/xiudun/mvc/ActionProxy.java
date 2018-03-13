/**
 * @(#)ActionProxy.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
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
 * TODO �����࣬����action���� �����ڵ���action֮ǰ��ִ�����档
 * 	����ģʽ
 * 		��̬����
 * 		��̬����
 * @author ��ͮ
 */
public class ActionProxy {
	
	ActionTag at ;
	public void execute() throws Exception{
		//����Ŀ�����action
		Object target = BeanFactory.getInstance().createBean(at.getClazz());

		FunctionChain chain = new FunctionChain();
		//1.��Ŀ�����ͷ���������
		chain.target = target;
		chain.targetMethod = at.getMethod();
		
		//2.�������漯�ϸ���
		List<IFunction> fs = new ArrayList<IFunction>();
		//����װ����add
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
		
		//3.���ִ�����浽���Ŀ��ķ���ֵ
		String result = chain.execute();
		
		if(result == null||"".equals(result)){
			//����Ҫ�����Ӧ��
			return ;
		}
		
		//4.���ݷ���ֵ����xml��Ӧ����
		
		ResponseBody r = at.getMethod().getAnnotation(ResponseBody.class);
		if(r!=null){//����ע��--�Զ��巵��ֵ			
			//response.setContentType("text/html;charset="+encoding);
			//response.getWriter().print(result);
			ActionContext.getActionContext().getResponse().setContentType("text/html;charset="+ActionContext.getActionContext().getEncoding());
			ActionContext.getActionContext().getResponse().getWriter().print(r);
			return ;
		}
		
		//��ȡ�����е���Ӧ����
		ResultTag rt = at.getFrt().get(result);
		if(rt == null){
			throw new NoResultFoundException("404 no result name ["+result+"]");
		}
		
		
		String type = rt.getType();
		String content = rt.getContent();
		//4.1ת��
		if(type == null || "dispatcher".equals(type)){
			//ת�� , ���Զ���action������get�����ķ���ֵװ�ص�request�С�ʹ��request.setAttribute(key,value);
			Method[] ms = at.getClazz().getMethods();
			for(Method m:ms){
				//ͨ���������ҵ���Ҫ������ҳ�Ĳ���
				String mname = m.getName();
				if(mname.startsWith("get")){
					//�õ�������
					String key = mname.substring(3);
					if(key.length()==1){
						key = key.toLowerCase();
					}else{
						key = key.substring(0, 1).toLowerCase()+key.substring(1);
					}
					//ͨ������ִ����Ӧget�����õ�ֵ
					Object value = m.invoke(target);
					//��ֵͨ�������Ķ���setAttribute�洢��request������
					ActionContext.getActionContext().getRequest().setAttribute(key, value);
				}
			}//��request��response����ת����ȥ
			ActionContext.getActionContext().getRequest().getRequestDispatcher(content).forward(
					ActionContext.getActionContext().getRequest(),
					ActionContext.getActionContext().getResponse());
		
		}else if(type.equals("redirect")){
			//4.2�ض���
			ActionContext.getActionContext().getResponse().sendRedirect(content);
		}else if(type.equals("download")){
			HttpServletResponse resp = ActionContext.getActionContext().getResponse() ;
			//4.3����
			//����action��getFileName�������÷����ķ���ֵ��Ϊ�����ļ����ļ�����
			Method m = target.getClass().getMethod("getFileName") ;
			String filename = (String) m.invoke(target) ;
			resp.setHeader("content-disposition", "attachment;filename="+filename);
			/*
			 * ���صı��ʾ��Ƕ�ȡ���������ص��ļ�������������
			 * ��ȡ�����ļ�����Ҫ�ļ������� . 
			 * 	Ҫ��action�����ṩһ��xxx�������÷��������ṩһ�����Զ�ȡ�����������ļ�����������
			 *  content ��Ϊxxx������
			 *  <result name="success" type="download" >getInputStream</result>
			 * ����������Ҫresponse.getOutputStream()|getWriter()
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
