package com.hiekn.demo.rest;

import java.io.IOException;

import javax.annotation.Resource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.service.UserService;
import com.hiekn.demo.util.PakoGzipUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Path("/user")
@Api(tags="用户")
@Produces({MediaType.APPLICATION_JSON})
public class UserRestApi {
	
	@Resource
	private UserService userService;
	
	@POST
	@ApiOperation(value = "登录")
	@Path("/login")
	public  RestResp<String> test(@QueryParam("userId") Integer userId, 
			@FormParam("mobile") String mobile,
			@FormParam("mcode") String mcode,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		try {
			String m = PakoGzipUtils.uncompress(mobile);
			String m2 = PakoGzipUtils.uncompress(mcode);
			System.out.println(m);
			System.out.println(m2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new RestResp<String>(System.currentTimeMillis()+"",tt);
	}
	
	@POST
	@ApiOperation(value = "根据用户名查询用户")
	@Path("/get")
	public  RestResp<UserBean> getUserByName(@QueryParam("userId") Integer userId, 
			@FormParam("name") String name,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		return  new RestResp<UserBean>(userService.getByName(name),tt);
	}
	
}
