package cn.supstore.biz.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestUrlUtil {
	
	public static void main(String[] args) {
		Map map =new HashMap();
		map.put("AK", "b93538934db72457d10a26c57729b2e04479e0b7a3c7eeb47c231585d6882102");
		map.put("TP", "getSnode");
		map.put("PAGESIZE", "100");
		map.put("PAGEINDEX", "2");
		map.put("SDATE", "2016-08-01");
		request("http://ydzl.gujing.net/api/outapi.aspx","POST",map);
	}
	
	
//调用其他接口时所需要的方法
	public static Object request(String address, String method, Map args){
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(address);
			conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod(method);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			conn.connect();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			//request arguments
			String param = genParams(args);
			out.writeBytes(param);
            out.flush();
            out.close();

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			StringBuffer sb = new StringBuffer("");
			String lines = "";
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes("utf-8"), "utf-8");
                sb.append(lines);
            }
            reader.close();
            return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
			conn = null;
		}
		return null;
	}
	/**
	 * 参数字串
	 * @param args
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String genParams(Map args) throws UnsupportedEncodingException{
		StringBuffer buf = new StringBuffer();		
		Iterator it = args.keySet().iterator();
		try {
			while (it.hasNext()) {
				String key = (String) it.next();
				buf.append(key);
				buf.append("=");
				buf.append(args.get(key)!=null?URLEncoder.encode(args.get(key).toString(),"UTF-8"):null);
				buf.append("&");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	
}
