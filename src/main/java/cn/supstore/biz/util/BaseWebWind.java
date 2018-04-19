package cn.supstore.biz.util;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Rest API Super Class
 * 
 * @author  
 * 
 */
public class BaseWebWind {
	public Log log = LogFactory.getLog(this.getClass());
	
	public String convertToJSON(String name, String value) {
		JSONObject obj = new JSONObject();
		obj.put("funcs", value);
		return obj.toString();
	}

	/**
	 * 将对象转换为JSON数据格式输出 新代码请用 {@link #convertToJSONWithStatus}
	 * 
	 * @param obj
	 * @return String
	 */
	public String convertToJSON(Object obj) {
		return JSONObject.fromObject(obj).toString();
	}

	/**
	 * 将对象转换为JSON数据格式输出 使用的时候加上状态值 {@link JSonData #setStatus(int)}
	 * @see {@link JSonData #setStatus(int)}
	 * 
	 * @param obj
	 * @return String
	 */
	public JSonData convertToJSONWithStatus(Object obj) {
		if(obj instanceof List){
			return JSonData.getJSonData().setDataType(JSonData.DATA_TYPE_ARRAY)
			.setDataObject(obj).setCode(JSonData.CODE_SUCCESS);
		} else {
			return JSonData.getJSonData().setDataType(JSonData.DATA_TYPE_OBJECT)
			.setDataObject(obj).setCode(JSonData.CODE_SUCCESS);
		}
	}

	/**
	 * 将集合转换为JSON数据格式输出   新代码请用 {@link #convertToJSONWithStatus}
	 * 
	 * @param arrs
	 * @return String
	 */
	public static String convertToJSON(List arrs) {
		return JSONArray.fromObject(arrs).toString();
	}

	/**
	 * 将集合转换为JSON数据格式输出, 使用的时候加上状态值 {@link JSonData #setStatus(int)}
	 * @see {@link JSonData #setStatus(int)}
	 * 
	 * @param arrs
	 * @return String
	 */
	public JSonData convertToJSONWithStatus(List arrs) {
		return JSonData.getJSonData().setDataObject(arrs)
		.setDataType(JSonData.DATA_TYPE_ARRAY).setCode(JSonData.CODE_SUCCESS);
	}
	
	/**
	 * 将集合转换为JSON数据格式输出, 使用的时候加上状态值 {@link JSonData #setStatus(int)}
	 * @see {@link JSonData #setStatus(int)}
	 * 
	 * @param arrs
	 * @return String
	 */
	public JSonData convertToJSONWithStatusNullDate(List arrs) {
		return JSonData.getJSonData().setDataObject(arrs)
		.setDataType(JSonData.DATA_TYPE_OBJECT).setCode(JSonData.CODE_SUCCESS);
	}


	/**
	 * 输出未登录信息, 新代码请用 {@link #wrapNoLoginMsgWithStatus}
	 * 
	 * @param msg
	 * @return json格式的错误信息
	 */
	public String wrapNoLoginMsg(String msg) {
		ErrInfo err = new ErrInfo();
		err.setCode(ErrInfo.ERR_NOLOGIN);
		err.setMsg(msg);
		return convertToJSON(err);
	}
	
	/**
	 * 输出未登录信息
	 * 
	 * @param msg
	 * @return json格式的错误信息
	 */
	public String wrapNoLoginMsgWithStatus(String msg) {
		return JSonData.getJSonData().setCode(JSonData.CODE_NOLOGING)
		.setDataObject(new JSonData.Value(msg)).toString();
	}
	
	/**
	 * 重复登录返回的信息
	 * 
	 * @param msg
	 * @return json格式的错误信息
	 */
	public String wrapAgainLoginMsgWithStatus(String msg) {
		return JSonData.getJSonData().setCode(JSonData.CODE_ELIMINATE)
		.setDataObject(new JSonData.Value(msg)).toString();
	}

	/**
	 * 输出错误信息 
	 * 
	 * @param msg
	 * @return json格式的错误信息
	 */
	public String wrapErrMsgWithStatus(String msg) {
		return JSonData.getJSonData().setCode(JSonData.CODE_FAILD)
		.setDataObject(new JSonData.Value(msg)).toString();
	}
	
	/**
	 * 输出错误信息 （强制更新）
	 * 
	 * @param msg
	 * @return json格式的错误信息
	 */
	public String wrapErrMsgForceUpdateWithStatus(String msg) {
		return JSonData.getJSonData().setCode(JSonData.FORCE_UPDATE)
		.setDataObject(new JSonData.Value(msg)).toString();
	}
	
	public JSonData wrapErrMsgs(String msg,int statusNum) {
		return JSonData.getJSonData().setCode(statusNum)
		.setDataObject(new JSonData.Value(msg));
	}
	/**
	 * 输出错误信息 新代码请用 {@link #wrapErrMsgWithStatus}
	 * 
	 * @param msg
	 * @return json格式的错误信息
	 */
	public String wrapErrMsg(String msg) {
		ErrInfo err = new ErrInfo();
		err.setCode(ErrInfo.ERR_CODE);
		err.setMsg(msg);
		err.setValue(msg);
		return convertToJSON(err);
	}

	/**
	 * 输出成功信息
	 * 
	 * @return
	 */
	public String wrapSuccessMsgWithStatus() {
		return JSonData.getJSonData().setCode(JSonData.CODE_SUCCESS)
		.setDataObject(new JSonData.Value("成功")).toString();
		
	}
	
	/**
	 * 输出成功信息  新代码请用 {@link #wrapSuccessMsgWithStatus}
	 * 
	 * @return
	 */
	public String wrapSuccessMsg() {
		JSONObject obj = new JSONObject();
		obj.put("code", ErrInfo.ERR_SUCCESS);
		obj.put("msg", "success");
		return obj.toString();
	}

	public String jsonOut(String labelName, String msg) {
		JSONObject obj = new JSONObject();
		obj.put(labelName, msg);
		return obj.toString();
	}


	/**
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static Object[] convert2ObjArr(String jsonString, Class clazz) {
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	public static Object convert2Obj(String jsonString, Class clazz) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Object obj = JSONObject.toBean(jsonObject, clazz);
		return obj;
	}

	
	
	
}
