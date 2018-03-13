/**
 * @(#)BeanFactory.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��15��
 */
package org.xiudun.mvc;

/**
 * TODO ����ģʽ-����ʽ
 * @author ��ͮ
 */
public class BeanFactory {
	
	//1.����˽�о�̬�������
	private static BeanFactory factory;//*=new BeanFactory();����
	//2.���췽��˽�л�
	private BeanFactory(){
	}
	//3.�ṩ���󷽷�
	public static BeanFactory getInstance(){
		if(factory == null){
			//*����ͬ����--���߳�ͬʱ������������ȴ�
			synchronized("st"){
				//Ϊ����ȴ���������߳�ͬʱ���������ٴ��ж�
				if(factory == null){
					//*�������� --ֻ�ж��󲻴���ʱ�Ŵ���
					factory = new BeanFactory();
				}
			}
		}
		return factory;
	}
	
	public Object createBean(Class<? extends Object> clazz) throws InstantiationException, IllegalAccessException{
		return clazz.newInstance();//Ҫ�󹹽��Ķ�������ṩ�޲ι�������
	}
	
	
}
