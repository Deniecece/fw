/**
 * @(#)BeanFactory.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月15日
 */
package org.xiudun.mvc;

/**
 * TODO 单例模式-懒汉式
 * @author 单彤
 */
public class BeanFactory {
	
	//1.创建私有静态本类对象
	private static BeanFactory factory;//*=new BeanFactory();饿汉
	//2.构造方法私有化
	private BeanFactory(){
	}
	//3.提供对象方法
	public static BeanFactory getInstance(){
		if(factory == null){
			//*设置同步锁--多线程同时创建对象在外等待
			synchronized("st"){
				//为避免等待结束多个线程同时创建对象再次判断
				if(factory == null){
					//*懒汉体现 --只有对象不存在时才创建
					factory = new BeanFactory();
				}
			}
		}
		return factory;
	}
	
	public Object createBean(Class<? extends Object> clazz) throws InstantiationException, IllegalAccessException{
		return clazz.newInstance();//要求构建的对象必须提供无参构造器。
	}
	
	
}
