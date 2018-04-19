package cn.supstore.core.base.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
 

public class CacheManager {
	 public static Map<String, EntityCache> caches = new  HashMap<String, EntityCache>();
	 
	  /**
	   * 存入缓存
	   * @param key
	   * @param cache
	   */
	  public static void putEntityCache(String key, EntityCache cache) {
	    caches.put(key, cache);
	  }
	 
	  /**
	   * 存入缓存
	   * @param key
	   * @param cache
	   */
	  public static void  putCache(String key, Object datas, long timeOut) {
	    timeOut = timeOut > 0 ? timeOut : 0L;
	    putEntityCache(key, new EntityCache(datas, timeOut, System.currentTimeMillis()));
	  }
	 
	  /**
	   * 获取对应缓存
	   * @param key
	   * @return
	   */
	  public EntityCache getCacheByKey(String key) {
	    if (this.isContains(key)) {
	      return caches.get(key);
	    }
	    return null;
	  }
	 
	  /**
	   * 获取对应缓存
	   * @param key
	   * @return
	   */
	  public Object getCacheDataByKey(String key) {
	    if (this.isContains(key)) {
	      return caches.get(key).getDatas();
	    }
	    return null;
	  }
	 
	  /**
	   * 获取所有缓存
	   * @param key
	   * @return
	   */
	  public Map<String, EntityCache> getCacheAll() {
	    return caches;
	  }
	 
	  /**
	   * 判断是否在缓存中
	   * @param key
	   * @return
	   */
	  public boolean isContains(String key) {
	    return caches.containsKey(key);
	  }
	 
	  /**
	   * 清除所有缓存
	   */
	  public void clearAll() {
	    caches.clear();
	  }
	 
	  /**
	   * 清除对应缓存
	   * @param key
	   */
	  public void clearByKey(String key) {
	    if (this.isContains(key)) {
	      caches.remove(key);
	    }
	  }
	 
	  /**
	   * 缓存是否超时失效
	   * @param key
	   * @return
	   */
	  public static boolean isTimeOut(String key) {
	    if (!caches.containsKey(key)) {
	      return true;
	    }
	    EntityCache cache = caches.get(key);
	    long timeOut = cache.getTimeOut();
	    long lastRefreshTime = cache.getLastRefeshTime();
	    if (timeOut == 0 || System.currentTimeMillis() - lastRefreshTime >= timeOut) {
	      return true;
	    }
	    return false;
	  }
	  
	  public static void setLastRefreshTimeOut(String key) {
		    if ( caches.containsKey(key)) {
			    EntityCache cache = caches.get(key);
			    cache.setLastRefeshTime(System.currentTimeMillis() );
		    }
		  }
	  
	  /**
	   * 获取所有key
	   * @return
	   */
	  public Set<String> getAllKeys() {
	    return caches.keySet();
	  }
}
