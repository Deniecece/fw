/**
 * @(#)FunctionChain.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc;

import java.lang.reflect.Method;
import java.util.List;

/**
 * TODO ������ģʽ
 * @author ��ͮ
 */
public class FunctionChain {

	//Ŀ������������������ִ��Ŀ�귽��
	Object target;
	Method targetMethod ;
	//���漯������ִ����ɾ��Ԫ��
	List<IFunction> fs;
	
	public String execute() throws Exception{
		if(fs.size()==0){
			return (String) targetMethod.invoke(target);
		}
		//ɾ��Ԫ�ز��ó���ǰԪ��f
		IFunction f = fs.remove(0);
		return f.doRound(this);
	}
}
