package cn.supstore.core.base.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.supstore.core.base.AppUtil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisCacheUtil {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private ObjectMapper mapper;
	
	public static RedisCacheUtil getObject(){
		return AppUtil.getCtx().getBean(RedisCacheUtil.class);
	}
	
	public void set(final String key, final Object value, final long expiredTime) {  
		BoundValueOperations<String,Object> valueOper = redisTemplate.boundValueOps(key);
		// String valueStr = JSONObject.fromObject(value).toString();
		String valueStr = "";
		try {
			valueStr = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        if (expiredTime <= 0) {
            valueOper.set(valueStr);  
        } else {  
            valueOper.set(valueStr, expiredTime, TimeUnit.MILLISECONDS);  
        }  
    }
	
	public void set(final String key, final String value, final long expiredTime) {  
		BoundValueOperations<String,Object> valueOper = redisTemplate.boundValueOps(key);
		// String valueStr = JSONObject.fromObject(value).toString();
        if (expiredTime <= 0) {
            valueOper.set(value);  
        } else {  
            valueOper.set(value, expiredTime, TimeUnit.MILLISECONDS);  
        }  
    }
   
    public Object get(final String key) {  
    	BoundValueOperations<String, Object> valueOper = redisTemplate.boundValueOps(key);
        return valueOper.get();
    }
    
    public Object get(final String key, final long expiredTime) {  
    	BoundValueOperations<String, Object> valueOper = redisTemplate.boundValueOps(key);
    	Object obj = valueOper.get();
    	if(obj != null) {
    		valueOper.expire(expiredTime, TimeUnit.MILLISECONDS);
    	}
        return obj;
    }
   
    public void del(String key) {  
        if (redisTemplate.hasKey(key)) {  
            redisTemplate.delete(key);  
        }  
    }
    
    public Boolean check(String key,String value){  
    	Boolean flag = false;  
    	if (redisTemplate.hasKey(key) && value.equals(get(key))) {  
			flag=true;  
    	}  
    	return flag;  
    }  
    
    public <T> T getGenericObject(String json, Class<T> clz) {
    	T t = null;
    	try {
			t = mapper.readValue(json, clz);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return t;
    }
    
    public <T> List<T> getGenericArray(String json, Class<T> clz) {
    	List<T> list = null;
    	try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clz);
			list = (List<T>)mapper.readValue(json, javaType); 
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    /**
     * 通过token获取泛型对象信息
     * @param token
     * @param clz
     * @return
     */
    /*public <T> T getGenericObject(String token, Class<T> clz) {
    	String json = CoverUtil.Obj2str(get(token));
    	T t = null;
    	try {
			t = mapper.readValue(json, clz);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return t;
    }
    
    *//**
     * 通过token获取泛型对象信息并更新存储时间
     * @param token
     * @param clz
     * @param expiredTime 存储时间
     * @return
     *//*
    public <T> T getGenericObject(String token, Class<T> clz, long expiredTime) {
    	String json = CoverUtil.Obj2str(get(token, expiredTime));
    	T t = null;
    	try {
			t = mapper.readValue(json, clz);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return t;
    }*/
    
}
