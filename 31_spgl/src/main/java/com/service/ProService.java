/**
 * @(#)ProService.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年2月1日
 */
package com.service;

import java.util.List;

import com.dao.ProDao;
import com.domain.Product;

/**
 * TODO 填写功能说明
 * @author 单彤
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
