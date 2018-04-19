package cn.supstore.core.base.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.supstore.core.base.cache.CacheManager;
import cn.supstore.core.base.cache.EntityCache;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static String[] unTokenUrl = new String[] { "/login", "/doc" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request.getRequestURI());

		String urlString = request.getRequestURI();
		boolean interFlag = true;//是否拦截标识；
		for (String url : unTokenUrl) {
			if (urlString.contains(url))
				interFlag = false;
		}
		if (interFlag) {
			String token = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie c : cookies) {
					if (c.getName().equals("accessToken")) {
						token = c.getValue();
					}
				}
			}
			if (StringUtil.isBlank(token)) {//token 不存在
				response.sendRedirect(request.getContextPath() + "/login");
				return false;
			}else{
				//token已失效
				if(CacheManager.isTimeOut(token)){
					response.sendRedirect(request.getContextPath() + "/login");
					return false;
				}else{
					CacheManager.setLastRefreshTimeOut(token);
				}
							 
			}
		} else {
			return true;
		}

		return true;
	}

}