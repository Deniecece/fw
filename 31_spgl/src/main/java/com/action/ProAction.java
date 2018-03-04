/**
 * @(#)ProTable.java
 * Description:	TODO 填写文件作用简要说明
 * Version :	0.0.0
 * Copyright:	Copyright (c) 哈尔滨修盾信息科技有限公司  版权所有
 * Create by:	单彤  2018年2月1日
 */
package com.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.domain.Product;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ProService;
import com.util.Zjm;

/**
 * TODO 填写功能说明
 * @author 单彤
 */
public class ProAction {

	private int page;
	private int rows;
	private int total;
	private List<Product> pros;
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public List<Product> getPros() {
		return pros;
	}

	public int getTotal() {
		return total;
	}
	
	private ProService service = new ProService();
	public void tForPag() throws JsonGenerationException, JsonMappingException, IOException{
		System.out.println("分页action方法============");
		total = service.total();
		pros = service.dbData(page, rows);
		//[rows:{},total:]
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("rows", pros);
		m.put("total", total);
		ObjectMapper om = new ObjectMapper();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		om.writeValue(ServletActionContext.getResponse().getWriter(), m);
	}
	//批量删除操作
	private int[] pno;

	public void setPno(int[] pno) {
		this.pno = pno;
	}
	public void delete(){
		System.out.println(pno);
		service.delete(pno);
	}
	//文件上传操作
	
	private File file;
	
	public void setFile(File file) {
		this.file = file;
	}

	public void fileUpload() throws InvalidFormatException, IOException{
		System.out.println("根据配置找到方法");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheetAt(0);
		for(int i=1;i<sheet.getLastRowNum();i++){
			Row row = sheet.getRow(i);
			Cell c1 = row.getCell(0);
			Cell c2 = row.getCell(1);
			Cell c3 = row.getCell(2);
			
			String pname = c1.getStringCellValue();
			String dw = c2.getStringCellValue();
			String xh = c3.getStringCellValue();
			String zjm = Zjm.String2Alpha(pname);
			
			Product p = new Product(null,pname,zjm,dw,xh,null,null,null);
			service.save(p);
		}
	}
	
}
