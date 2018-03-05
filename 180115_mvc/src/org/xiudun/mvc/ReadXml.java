/**
 * @(#)ReadXml.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
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
 * TODO 读取xml内容将其反序列化
 * @author 单彤
 */
public class ReadXml {

	Document doc;
	/*在类创建对象时执行读取操作*/
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
			//得到请求对应class属性反射加载类
			String classpath = e.attributeValue("class");
			Class<? extends Object> clazz = Class.forName(classpath);
			
			String methodName = e.attributeValue("method");
			//要求处理的请求方法必须是无参的 save() ;
			Method m = clazz.getMethod(methodName);
			
			//子标签读取
			List<FunctionTag> fts = new ArrayList<FunctionTag>();
			@SuppressWarnings("unchecked")
			List<Element> fes = e.selectNodes("function") ;
			for(Element fe:fes){
				//反射加载自定义切面类
				String fclass = fe.attributeValue("class");
				Class<? extends Object> fclazz = Class.forName(fclass);
				//装载在tag标签中
				FunctionTag f = new FunctionTag(fclazz);
				//将所有function标签组装成一个集合最终放入action中
				fts.add(f);
			}
			
			Map<String,ResultTag> name_rts = new HashMap<String,ResultTag>();
			@SuppressWarnings("unchecked")
			//获得所有result标签
			List<Element> res = e.selectNodes("result");
			for(Element re:res){
				//读取属性
				String rname = re.attributeValue("name");
				String type = re.attributeValue("type");
				String content = re.attributeValue("content");
				//装载在一个Tag标签里
				ResultTag rt = new ResultTag(name,type,content);
				//装载在集合里
				name_rts.put(rname, rt);
			}
			//将所有属性和子标签都装载在ActionTag标签中
			ActionTag at = new ActionTag(name,clazz,m,fts,name_rts);
			//组成Map集合
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
