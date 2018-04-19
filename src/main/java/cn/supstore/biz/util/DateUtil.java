package cn.supstore.biz.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	public static LocalDateTime Date2LocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	public static Date LocalDateTime2Date(LocalDateTime ldt) {
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static String LocalDateTime2Str(LocalDateTime ldt, String fmt) {
		if(ldt == null) return null;
		return ldt.format(DateTimeFormatter.ofPattern(fmt));
	}
	
	public static LocalDateTime Str2LocalDateTime(String str, String fmt) {
		if(str == null) return null;
		return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(fmt));
	}
	
	/**
	 * 字符串转化时间
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static Date formatStringToUtilDate(String str) throws Exception {
		if (str == null || "".equals(str)) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(str);
	}
}
