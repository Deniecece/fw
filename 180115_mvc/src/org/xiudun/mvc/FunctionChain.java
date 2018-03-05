/**
 * @(#)FunctionChain.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
 */
package org.xiudun.mvc;

import java.lang.reflect.Method;
import java.util.List;

/**
 * TODO 责任链模式
 * @author 单彤
 */
public class FunctionChain {

	//目标对象用于最后链反射执行目标方法
	Object target;
	Method targetMethod ;
	//切面集合随着执行在删除元素
	List<IFunction> fs;
	
	public String execute() throws Exception{
		if(fs.size()==0){
			return (String) targetMethod.invoke(target);
		}
		//删除元素并拿出当前元素f
		IFunction f = fs.remove(0);
		return f.doRound(this);
	}
}
