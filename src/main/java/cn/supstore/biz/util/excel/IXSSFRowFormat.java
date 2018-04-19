package cn.supstore.biz.util.excel;

import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * xlsx文件行转换
 * @author BZhou
 *
 */
public interface IXSSFRowFormat<T> {

	public  T format(XSSFRow row, Class<T> clz);
}
