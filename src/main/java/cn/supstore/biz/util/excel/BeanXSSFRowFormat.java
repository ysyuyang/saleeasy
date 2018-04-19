package cn.supstore.biz.util.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import cn.supstore.biz.util.DateUtil;
import cn.supstore.biz.util.NumberUtils;


/**
 * 实体类xlsx文件行转换
 * @author BZhou
 *
 */
public class BeanXSSFRowFormat<T> implements IXSSFRowFormat<T> {

	@Override
	public T format(XSSFRow row, Class<T> clz) {
		if(row == null) {
			return null;
		}
		try {
    		T t = clz.newInstance();
			Field[] fields = t.getClass().getDeclaredFields();
			for(int i=0; i<fields.length; i++)
			{
				Field field = fields[i];
				String fieldName = field.getName();
				String setMethodName = "set"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				Method setMethod = clz.getMethod(setMethodName, new Class[]{field.getType()});
				String value = null;
				XSSFCell cell = row.getCell(i);
				if (cell != null) {
					// 注意：一定要设成这个，否则可能会出现乱码
					switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							/*if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = null;
	                            if (date != null) {
	                            	 value = "";
	                            } else {
	                                value = "";
	                            }
							} else {
								value = cell.getNumericCellValue()+"";
							}*/
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
							value = cell.getStringCellValue();
							break;
						case XSSFCell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							/*if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}*/
							value = cell.getCellFormula();
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							break;
						case XSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							value = "";
					}
					
					if (i == 0 && (value == null || value.trim().equals(""))) {
						return null;
    				}
				} else {
					if(i == 0) {
						return null;
					}
				}
				// 类型转换
				if(value != null && !"".equals(value)) {
					if(field.getType() == Integer.class) {
						BigDecimal bd = new BigDecimal(value);
						setMethod.invoke(t, bd.intValue());
					} else if(field.getType() == Date.class) {
						setMethod.invoke(t, DateUtil.formatStringToUtilDate(value));
					} else if(field.getType() == Double.class) {
						setMethod.invoke(t, Double.valueOf(value));
					} else if(field.getType() == LocalDateTime.class) {
						setMethod.invoke(t, DateUtil.Str2LocalDateTime(value, "yyyy-MM-dd HH:mm:ss"));
					} else if(field.getClass().isEnum()) {
						setMethod = clz.getMethod(setMethodName+"Str", new Class[]{field.getType()});
						setMethod.invoke(t, value);
					} else {
						setMethod.invoke(t, value);
					}
				} else {
					setMethod.invoke(t, value);
				}
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}

}
