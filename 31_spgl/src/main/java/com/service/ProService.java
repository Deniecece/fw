/**
 * @(#)ProService.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��2��1��
 */
package com.service;

import java.util.List;

import com.dao.ProDao;
import com.domain.Product;

/**
 * TODO ��д����˵��
 * @author ��ͮ
 */
public class ProService {

	private ProDao dao = new ProDao();
	
	public int total(){
		return dao.total();
	}
	
	public List<Product> dbData(int page,int rows){
		return dao.dbdata(page,rows);
	}
	public void delete(int[] sno){
		dao.delete(sno);
	}
	
	public void save(Product p){
		dao.save(p);
	}
}
