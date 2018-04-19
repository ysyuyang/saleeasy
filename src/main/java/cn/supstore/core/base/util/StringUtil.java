package cn.supstore.core.base.util;

public class StringUtil {

	public static boolean isBlank(String s){
		if(s==null||"".equals(s.replaceAll("\\s*", ""))){
			return true;
		}
		return false;
	}
}
