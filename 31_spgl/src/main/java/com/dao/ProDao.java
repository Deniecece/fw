/**
 * @(#)ProDao.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��31��
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
 * TODO ��д����˵��
 * @author ��ͮ
 */
public class ProDao extends BaseDao{

	
	public List<Product> dbdata(int page,int rows){
		System.out.println("��ҳ���ݿ⽻��");
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
		System.out.println("����dao���������ļ�������");
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.xml");
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession(true);
		session.insert("pro.save", p);
	}
}
