package cn.supstore.biz.api.base;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("登陆相关API")
@Path("auth")
public interface ILogin {
	@Path("logon")
	@POST
	@ApiOperation(
            value = "用户登录",
            notes =  "",
            response = String.class)
    @Produces(MediaType.APPLICATION_JSON)
	public String logon(@NotNull @FormParam("name")String userName,@NotNull @FormParam("pwd")String pwd);
}
