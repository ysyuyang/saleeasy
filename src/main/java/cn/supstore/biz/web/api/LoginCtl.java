package cn.supstore.biz.web.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import cn.supstore.biz.api.base.ILogin;
import cn.supstore.biz.model.UserInfo;
import cn.supstore.biz.util.BaseWebWind;
import cn.supstore.biz.util.InfoBean;
import cn.supstore.core.base.cache.CacheManager;
import cn.supstore.core.base.util.DigestUtil;
import cn.supstore.core.base.util.TokenUtil;
@Controller
@Transactional
public class LoginCtl extends BaseWebWind implements ILogin {
	@Context
	HttpServletRequest req;
	@Context
	HttpServletResponse res;
	@Override
	public String logon(String userName, String pwd) {
		// TODO Auto-generated method stub
		InfoBean info = new InfoBean();
		req.getSession().setAttribute("user", "name:"+userName+"--password:"+pwd);		
		UserInfo ui = new UserInfo();
		ui.setLoginName(userName);
		String token = DigestUtil.getMD5(userName);
		CacheManager.putCache(token, ui,1000*60*30L);
		 
		TokenUtil.addCookie(res,"accessToken",token); 
		 
		info.setMsg("");
		info.setCode("200");
		info.setValue(userName);
		 
		return this.convertToJSON(info);
	}
}
