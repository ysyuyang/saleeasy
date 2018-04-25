package cn.supstore.biz.service;

import java.util.List;

import cn.supstore.biz.model.SysUser;
import cn.supstore.core.base.web.GenericService;
import cn.supstore.core.base.web.Page;

public class SysUserService extends GenericService<SysUser, Long> {
	
	public SysUser getSysUser(String name,String pwd){
		String hql =" from SysUser where login_name =? and password =? ";
		return this.getSingleByHQL(hql, new Object[]{name,pwd});
	}

	 
}
