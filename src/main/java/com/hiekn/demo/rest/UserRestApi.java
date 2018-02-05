package com.hiekn.demo.rest;

import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.result.BaseParam;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.service.UserService;
import com.hiekn.demo.util.PakoGzipUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Controller
@Path("/user")
@Api("用户")
@Produces({MediaType.APPLICATION_JSON})
public class UserRestApi {
	
	@Resource
	private UserService userService;
	
	@POST
	@ApiOperation("登录")
	@Path("/login")
	public RestResp<Object> test(@BeanParam BaseParam baseParam,
			@FormParam("mobile") String mobile,
			@FormParam("mcode") String mcode){
		try {
			String m = PakoGzipUtils.uncompress(mobile);
			String m2 = PakoGzipUtils.uncompress(mcode);
			System.out.println(m);
			System.out.println(m2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new RestResp<>(System.currentTimeMillis());
	}
	
	@POST
	@ApiOperation("根据用户名查询用户")
	@Path("/get")
	public RestResp<UserBean> getUserByName(@BeanParam BaseParam baseParam,
											 @FormParam("name") String name){
		return  new RestResp<>(userService.getByName(name));
	}
	
}
