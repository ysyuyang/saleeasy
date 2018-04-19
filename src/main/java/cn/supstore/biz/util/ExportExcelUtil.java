package cn.supstore.biz.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class ExportExcelUtil {

	/**
	 * 通用导出方法
	 * @param <T>
	 * @param list 数据源
	 * @param hearders 表头数组
	 * @param title 标题
	 * @param resp
	 * @throws IOException
	 */
	public<T> void exportCommon(List<T> list, String[] hearders, String title, 
			HttpServletResponse resp) throws IOException{
		ExportExcel<T> ex = new ExportExcel<T>();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
 	    String filename = timeFormat.format(new Date())+".xls";  
 	    resp.setContentType("application/ms-excel;charset=UTF-8");  
 	    resp.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));  
 	    OutputStream out = resp.getOutputStream();  
 	    ex.exportExcelHttp(title, hearders, list, out,"yyyy-MM-dd HH:mm:ss");  
 	    out.close();  
	}
	
	/**
	 * 通用导出方法
	 * @param <T>
	 * @param list 数据源
	 * @param hearders 表头数组
	 * @param title 标题
	 * @param filedNames 字段过滤数组
	 * @param resp
	 * @throws IOException
	 */
	public<T> void exportCommon(List<T> list, String[] hearders, String title, 
			String[] filedNames, HttpServletResponse resp) throws IOException{
		ExportExcel<T> ex = new ExportExcel<T>();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
 	    String filename = timeFormat.format(new Date())+".xls";  
    	resp.setContentType("application/ms-excel;charset=UTF-8");  
    	resp.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));  
 	    OutputStream out = resp.getOutputStream();  
 	    ex.exportExcel(title, hearders, list, out, "yyyy-MM-dd HH:mm:ss", filedNames);  
 	    out.close();  
	}
	
	/**
	 * 通用到出方法(Map)
	 * @param list
	 * @param headers
	 * @param title
	 * @param filedNames
	 * @param resp
	 * @throws Exception
	 */
	public void exportMapCommon(List<Map<String, Object>> list, String[] headers, String title, 
			String[] filedNames, HttpServletResponse resp) throws Exception {
		ExportExcel<Map<String, Object>> ex = new ExportExcel<Map<String, Object>>();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
 	    String filename = timeFormat.format(new Date())+".xls";  
    	resp.setContentType("application/ms-excel;charset=UTF-8");  
    	resp.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));  
 	    OutputStream out = resp.getOutputStream();
 	    ex.exportMapExcel(title, headers, list, out, "yyyy-MM-dd HH:mm:ss", filedNames);
 	    out.close();
	}
	
	public<T> void clockOutExcel(List list, String[] hearders, String title, 
			HttpServletResponse resp, String title2) throws IOException{
		ExportExcel<T> ex = new ExportExcel<T>();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
 	    String filename = timeFormat.format(new Date())+".xls";  
 	    resp.setContentType("application/ms-excel;charset=UTF-8");  
 	    resp.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));  
 	    OutputStream out = resp.getOutputStream();  
 	    ex.clockExportExcelHttp(title, hearders, list, out,"yyyy-MM-dd HH:mm:ss", title2);  
 	    out.close();  
	}
}
