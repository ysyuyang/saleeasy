package cn.supstore.biz.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author BZhou
 * HTTP请求
 */
public class RequestUtil {
	
	public static void main(String[] args) {
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("AK", "b93538934db72457d10a26c57729b2e04479e0b7a3c7eeb47c231585d6882102");
		map.put("TP", "getSnode");
		map.put("PAGESIZE", "100");
		map.put("PAGEINDEX", "2");
		map.put("SDATE", "2016-08-01");
		request("http://ydzl.gujing.net/api/outapi.aspx", "POST",map);*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", "22");
		map.put("taskName", "22");
		map.put("templateFlag", "22");
		map.put("refObjId", "22");
		map.put("refId", "22");
		map.put("gold", "22");
		map.put("abilityExp", "22");
		map.put("roleExp", "22");
		map.put("energyVal", "22");
		try {
			System.out.println(readContentFromGet("http://192.168.0.125:8180/shopguide/api/newTask/createTask/task_rule", map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 模拟http的get请求
	 * @param url 请求地址
	 * @param param 参数
	 * @return
	 * @throws Exception 
	 */
	public static String readContentFromGet(String url, Map param) throws Exception {
		// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码 
		String getURL = url+"?"+MapToString(param);
		URL getUrl = new URL(getURL);
		// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		// 建立与服务器的连接，并未发送数据
		connection.setRequestProperty("Accept", "application/json");
		connection.connect();
		// 发送数据到服务器并使用Reader读取返回的数据
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		StringBuffer lineArr = new StringBuffer("");
		String lines = "";
        while ((lines = reader.readLine()) != null) { 
        	lineArr.append(lines);
        } 
        reader.close();
        // 断开连接 
        connection.disconnect();
        return lineArr.toString();
	}
	
	public static String readJsonPost(String url, String accessToken, String jsonStr) throws Exception {
		// Post请求的url，与get不同的是不需要带参数
		if(accessToken!=null && !"".equals(accessToken))
			url = url+"?access_token="+accessToken;
		URL postUrl = new URL(url);
		// 打开连接
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		//打开读写属性，默认均为false
		connection.setDoOutput(true);
		connection.setDoInput(true);
		// 设置请求方式，默认为GET
		connection.setRequestMethod("POST");
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		// URLConnection.setFollowRedirects是static 函数，作用于所有的URLConnection对象。
		// connection.setFollowRedirects(true);
		// URLConnection.setInstanceFollowRedirects 是成员函数，仅作用于当前函数
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Accept", "application/json");
		// 配置连接的Content-type，配置为application/x- www-form-urlencoded的意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码 
		connection.setRequestProperty("Content-Type", "application/json");
		// 连接，从postUrl.openConnection()至此的配置必须要在 connect之前完成，
		// 要注意的是connection.getOutputStream()会隐含的进行调用 connect()，所以这里可以省略 
		//connection.connect();
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		// DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		//正文内容其实跟get的URL中'?'后的参数字符串一致
		// String content = MapToString(param);
		//String content = JsonUtil.ObjectTurnString(param);
		System.out.println(jsonStr);
		// DataOutputStream.writeBytes将字符串中的16位的 unicode字符以8位的字符形式写道流里面
		// out.writeBytes(content);
		// out.write(jsonStr);
		out.append(jsonStr);
		out.flush();
		out.close(); // flush and close
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		StringBuffer lineArr = new StringBuffer("");
		String line = "";
		while ((line = reader.readLine()) != null) {
			lineArr.append(line);
		}
		reader.close();
		connection.disconnect();
		return lineArr.toString();
	}
	
	/**
	 * 模拟http的post请求
	 * @param url 地址
	 * @param accessToken token
	 * @param param 参数
	 * @return
	 * @throws IOException
	 */
	public static String readContentFromPost(String url, String accessToken, String jsonStr) throws IOException {
		// Post请求的url，与get不同的是不需要带参数
		if(accessToken!=null && !"".equals(accessToken))
			url = url+"?access_token="+accessToken;
		URL postUrl = new URL(url);
		// 打开连接
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		//打开读写属性，默认均为false
		connection.setDoOutput(true);
		connection.setDoInput(true);
		// 设置请求方式，默认为GET
		connection.setRequestMethod("POST");
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		// URLConnection.setFollowRedirects是static 函数，作用于所有的URLConnection对象。
		// connection.setFollowRedirects(true);
		// URLConnection.setInstanceFollowRedirects 是成员函数，仅作用于当前函数
		connection.setInstanceFollowRedirects(true);
		// 配置连接的Content-type，配置为application/x- www-form-urlencoded的意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码 
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// 连接，从postUrl.openConnection()至此的配置必须要在 connect之前完成，
		// 要注意的是connection.getOutputStream()会隐含的进行调用 connect()，所以这里可以省略 
		//connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		//正文内容其实跟get的URL中'?'后的参数字符串一致
		// String content = MapToString(param);
		// DataOutputStream.writeBytes将字符串中的16位的 unicode字符以8位的字符形式写道流里面
		// out.writeBytes(content);
		// String content = JsonUtil.ObjectTurnString(param);
		out.write(jsonStr.toString().getBytes("utf-8"));
		out.flush();
		out.close(); // flush and close
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
		StringBuffer lineArr = new StringBuffer("");
		String line = "";
		while ((line = reader.readLine()) != null) {
			lineArr.append(line);
		}
		reader.close();
		connection.disconnect();
		return lineArr.toString();
	}
	
	public static String MapToString(Map param) throws Exception{
		StringBuffer outStr = new StringBuffer("");
		if(param!=null && param.size()>0)
		{
			int i = 0;
			Set outSet = param.keySet();
			Iterator ite = outSet.iterator();
			while (ite.hasNext()) {
				String key = ite.next().toString();
				String value = param.get(key)!=null?param.get(key).toString():null;
				if(value != null){
					value = URLEncoder.encode(value, "utf-8");
					if(i==0)
						outStr.append(key+"="+value);
					else
						outStr.append("&"+key+"="+value);
					i++;
				}
			}
		}
		return outStr.toString();
	}
	
	/**
	 * 调用HTTP请求
	 * @param address 请求地址
	 * @param method 请求方式
	 * @param args 请求参数
	 * @return
	 * @throws Exception 
	 */
	public static String request(String address, String method, Map<String, Object> args) throws Exception{
		return readContentFromGet(address, args);
	}
	/**
	 * 参数字串
	 * @param args
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String structParams(Map<String, Object> args) throws UnsupportedEncodingException{
		Iterator<String> it = args.keySet().iterator();
		List<String> strList = new ArrayList<String>();
		while (it.hasNext()) {
			String key = (String) it.next();
			strList.add(key+"="+args.get(key)!=null?URLEncoder.encode(args.get(key).toString(),"UTF-8"):null);
		}
		return StringUtils.join(strList, '&');
	}

	
}
