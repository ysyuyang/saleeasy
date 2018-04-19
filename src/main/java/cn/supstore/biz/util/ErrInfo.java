package cn.supstore.biz.util;
/**
 * 错误信息
 * @author hezw
 *
 */
public class ErrInfo {
	
	public static String ERR_CODE = "101";	//错误码
	public static String ERR_SUCCESS = "102";	//成功码
	public static String EXCEPTION_CODE = "103";	//异常码
	public static String ERR_NOLOGIN = "1001";	//错误码
	private String code;
	private String msg;
	private String value;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
