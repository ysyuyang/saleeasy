package cn.supstore.biz.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 用来封装返回值的类型
 * @author wrunqi
 *
 */
public class JSonData {
	/**
	 * 当用户状态发生改变的时候请设置为该值 {@link #setStatus(int)}
	 */
	public static final int STATUS_USER_UPDATE = 1;
	/**
	 * 当任务的数量发生改变的时候请设置为该值 {@link #setStatus(int)}
	 */
	public static final int STATUS_TASK_UPDATE = 2;

	public static final int DATA_TYPE_OBJECT = 101;
	public static final int DATA_TYPE_ARRAY = 102;
	
	public static final int CODE_SUCCESS = 102;
	public static final int CODE_FAILD = 101;
	public static final int CODE_NOLOGING = 1001;
	 // 账号另一个客户端登陆
    public static final int CODE_ELIMINATE = 1003;
    
    public static final int FORCE_UPDATE = 2001;//强制更新
    
    
	public static JSonData getJSonData() {
		return new JSonData();
	}

	public JSonData() {
	}

	private int dataType;
	private int userStatus;
	private int code;
	private int status;
	private Object dataObject;
	private List<Object> dataArray;
	private Map<String, String> params; 

	public int getDataType() {
		return dataType;
	}

	/**
	 * 用来设置返回值的类型
	 * 
	 * @see #DATA_TYPE_ARRAY
	 * @see #DATA_TYPE_OBJECT
	 * 
	 * @param dataType
	 * @return
	 */
	public JSonData setDataType(int dataType) {
		this.dataType = dataType;
		return this;
	}

	public JSonData putExtra(String key, String value){
		if(params == null){
			params = new HashMap<String, String>();
		}
		params.put(key, value);
		return this;
	}
	
	public int getUserStatus() {
		return userStatus;
	}

	public JSonData setUserStatus(int userStatus) {
		this.userStatus = userStatus;
		return this;
	}

	public int getCode() {
		return code;
	}

	public JSonData setCode(int code) {
		this.code = code;
		return this;
	}

	public int getStatus() {
		return status;
	}

	/**
	 * 设置返回值的状态 如果有需要更新的状态的时候调用setDataType来设置状态值
	 * 
	 * @see #STATUS_TASK_UPDATE
	 * @see #STATUS_USER_UPDATE
	 * @param status
	 * @return
	 */
	public JSonData setStatus(int status) {
		this.status = status;
		return this;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public JSonData setDataObject(Object dataObject) {
		this.dataObject = dataObject;
		return this;
	}

	public List<Object> getDataArray() {
		return dataArray;
	}

	public JSonData setDataArray(List<Object> dataArray) {
		this.dataArray = dataArray;
		return this;
	}

	@Override
	public String toString() {
		//System.out.println(status);
		JsonConfig jsonConfig = new JsonConfig();
	    jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(this,jsonConfig).toString();
	}

	/**
	 * 用来做键值对匹配的时候
	 * @author wrunqi
	 *
	 */
	public static class Value {
		private String value;
		public Value(String value){
			this.value = value;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
