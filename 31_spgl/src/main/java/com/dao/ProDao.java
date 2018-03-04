/**
 * @(#)ProDao.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年1月31日
 */
package com.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.xiudun.jdbc.BaseDao;

import com.domain.Product;

/**
 * TODO 填写功能说明
 * @author 单彤
 */
public class ProDao extends BaseDao{

	
	public List<Product> dbdata(int page,int rows){
		System.out.println("分页数据库交互");
		int start = ((page-1)*rows)+1;
		int end = page*rows;
		String sql="select t2.* from(select rownum rn,t1.* from(select * from t_products) t1 where rownum<=?) t2 where rn>=?";
		return this.selectForObjects(sql,Product.class,end,start);
	}
	public int total(){
		String sql="select count(*) total from t_products";
		Map<String,Object> m = this.selectForMap(sql);
		return ((Number) m.get("total")).intValue();
		
	}
	public void delete(int[] pno) {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.xml");
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession(true);
		System.out.println(pno);
		session.delete("pro.prosDelete", pno);
	}
	public void save(Product p) {
		System.out.println("进入dao即将保存文件的数据");
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.xml");
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession(true);
		session.insert("pro.save", p);
	}
}
