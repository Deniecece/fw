/**
 * @(#)ReadXml.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xiudun.mvc.domain.ActionTag;
import org.xiudun.mvc.domain.ConstantTag;
import org.xiudun.mvc.domain.FunctionTag;
import org.xiudun.mvc.domain.ResultTag;

/**
 * TODO ��ȡxml���ݽ��䷴���л�
 * @author ��ͮ
 */
public class ReadXml {

	Document doc;
	/*���ഴ������ʱִ�ж�ȡ����*/
	public ReadXml(){
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("mvc.xml");
			
			SAXReader r = new SAXReader();
			
			try {
				doc = r.read(is) ;
				
				this.readConstant();
				this.readAction();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	Map<String,ActionTag> ats ;
	private void readAction() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		@SuppressWarnings("unchecked")
		List<Element> aes = doc.selectNodes("mvc/action");
		if(aes == null || aes.size() == 0){
			return ;
		}
		for(Element e:aes){
			String name = e.attributeValue("name");
			//�õ������Ӧclass���Է��������
			String classpath = e.attributeValue("class");
			Class<? extends Object> clazz = Class.forName(classpath);
			
			String methodName = e.attributeValue("method");
			//Ҫ��������󷽷��������޲ε� save() ;
			Method m = clazz.getMethod(methodName);
			
			//�ӱ�ǩ��ȡ
			List<FunctionTag> fts = new ArrayList<FunctionTag>();
			@SuppressWarnings("unchecked")
			List<Element> fes = e.selectNodes("function") ;
			for(Element fe:fes){
				//��������Զ���������
				String fclass = fe.attributeValue("class");
				Class<? extends Object> fclazz = Class.forName(fclass);
				//װ����tag��ǩ��
				FunctionTag f = new FunctionTag(fclazz);
				//������function��ǩ��װ��һ���������շ���action��
				fts.add(f);
			}
			
			Map<String,ResultTag> name_rts = new HashMap<String,ResultTag>();
			@SuppressWarnings("unchecked")
			//�������result��ǩ
			List<Element> res = e.selectNodes("result");
			for(Element re:res){
				//��ȡ����
				String rname = re.attributeValue("name");
				String type = re.attributeValue("type");
				String content = re.attributeValue("content");
				//װ����һ��Tag��ǩ��
				ResultTag rt = new ResultTag(name,type,content);
				//װ���ڼ�����
				name_rts.put(rname, rt);
			}
			//���������Ժ��ӱ�ǩ��װ����ActionTag��ǩ��
			ActionTag at = new ActionTag(name,clazz,m,fts,name_rts);
			//���Map����
			ats.put(name, at);
		}
		
	}
	
	Map<String,ConstantTag> ctMap ;
	
	private void readConstant(){
		ctMap = new HashMap<String , ConstantTag>() ;
		
		@SuppressWarnings("unchecked")
		List<Element> elements = doc.selectNodes("mvc/constant") ;
		if(elements == null || elements.size() == 0)
			return ;
		for(Element element : elements){
			String name = element.attributeValue("name") ;
			String value = element.attributeValue("value") ;
			ConstantTag ct = new ConstantTag(name,value);
			ctMap.put(name, ct) ;
		}
	}
}
