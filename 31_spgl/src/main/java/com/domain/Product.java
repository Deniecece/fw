/**
 * @(#)Product.java
 * Description:	TODO ��д�ļ����ü�Ҫ˵��
 * Version :	0.0.0
 * Copyright:	Copyright (c) �������޶���Ϣ�Ƽ����޹�˾  ��Ȩ����
 * Create by:	��ͮ  2018��1��31��
 */
package com.domain;

import java.io.Serializable;

/**
 * TODO ��д����˵��
 * @author ��ͮ
 */
public class Product implements Serializable{

	public Product(Number pno, String pname, String zjm, String dw, String xh) {
		super();
		this.pno = pno;
		this.pname = pname;
		this.zjm = zjm;
		this.dw = dw;
		this.xh = xh;
	}
	private static final long serialVersionUID = 1L;
	private Number pno;
	private String pname;
	private String zjm;
	private String dw;
	private String xh;
	private String yl1;
	private String yl2;
	private String yl3;
	public int getPno() {
		return pno.intValue();
	}
	public void setPno(Number pno) {
		this.pno = pno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getZjm() {
		return zjm;
	}
	public void setZjm(String zjm) {
		this.zjm = zjm;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getYl1() {
		return yl1;
	}
	public void setYl1(String yl1) {
		this.yl1 = yl1;
	}
	public String getYl2() {
		return yl2;
	}
	public void setYl2(String yl2) {
		this.yl2 = yl2;
	}
	public String getYl3() {
		return yl3;
	}
	public void setYl3(String yl3) {
		this.yl3 = yl3;
	}
	public Product(Number pno, String pname, String zjm, String dw, String xh, String yl1, String yl2, String yl3) {
		super();
		this.pno = pno;
		this.pname = pname;
		this.zjm = zjm;
		this.dw = dw;
		this.xh = xh;
		this.yl1 = yl1;
		this.yl2 = yl2;
		this.yl3 = yl3;
	}
	public Product() {
		super();
	}
	
}
