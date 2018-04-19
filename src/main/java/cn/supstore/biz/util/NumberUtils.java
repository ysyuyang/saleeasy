package cn.supstore.biz.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberUtils {
	public static long longValue(Long numObj){
		if(numObj == null) return 0;
		return numObj.longValue();
	}
	public static long bigint2long(Object obj){
		if(obj == null) return 0;
		return ((BigInteger)obj).longValue();
	}
	public static int bigint2int(Object obj){
		if(obj == null) return 0;
		return Integer.parseInt(obj.toString());
//		return ((BigInteger)obj).intValue();
	}
	public static int obj2int(Object obj){
		if(obj == null) return 0;
		return ((Integer)obj).intValue();
	}
	public static int byte2int(Object obj){
		if(obj == null) return 0;
		return ((Byte)obj).intValue();
	}
	public static float floatValue(Float num){
		if(num == null) return 0;
		return num.floatValue();
	}
	public static int str2int(String str){
		if(str == null || "".equals(str)) return 0;
		return Integer.parseInt(str);
	}
	public static Integer str2Integer(String str){
		if(str == null || "".equals(str)) return null;
		return Integer.parseInt(str);
	}
	public static long str2long(String str){
		if(str == null || "".equals(str)) return 0L;
		return Long.parseLong(str);
	}
	public static Long str2Long(String str){
		if(str == null || "".equals(str)) return 0L;
		return Long.valueOf(str);
	}
	
	public static Float Obj2Float(Object obj){
		if(obj == null) return 0f;
		return Float.valueOf(obj.toString());
	}
	
	public static Double Obj2Double(Object obj){
		if(obj == null) return 0.0;
		return Double.valueOf(obj.toString());
	}
	
	public static String Obj2Str(Object obj)
	{
		if(obj == null) return null;
		return obj.toString();
	}
	
	public static Long obj2Long(Object obj)
	{
		if(obj==null) return null;
		return Long.parseLong(obj.toString());
	}
	public static long obj2long(Object obj)
	{
		if(obj==null) return 0;
		return Long.parseLong(obj.toString());
	}
	
	public static Integer obj2Integer(Object obj)
	{
		if(obj==null) return null;
		return Integer.parseInt(obj.toString());
	}
	public static float floatFormat(float num,int scale){
		BigDecimal b = new BigDecimal(num);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue() ; 
	}
	
	/**
	 * 字符串转化时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date formatStringToUtilDate(String str) {
		Date date = null;
		if (str == null || "".equals(str)) return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.getMessage();
		}

		return date;
	}
	
}
