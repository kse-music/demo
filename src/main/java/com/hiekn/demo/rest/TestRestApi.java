package com.hiekn.demo.rest;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;
import org.springframework.stereotype.Controller;

import com.google.gson.reflect.TypeToken;
import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.bean.search.QueryCondition;
import com.hiekn.demo.exception.JsonException;
import com.hiekn.demo.service.CommonService;
import com.hiekn.demo.util.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import redis.clients.jedis.JedisPool;

@Controller
@Path("/test")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Api(tags = {"测试模块"})
public class TestRestApi {

	@Resource
	private CommonService commonService;
	@Context 
	private HttpServletRequest request;
	@Resource
	private JedisPool jedisPool;
	
	@GET
	@Path("/test")
	@ApiOperation(value = "测试1")
	public  RestResp<String> test(@QueryParam("userId") Integer userId, 
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
			Properties props = System.getProperties();
			props.list(System.out);
		return new RestResp<String>(System.getProperty("catalina.base"),tt);
	}

	@POST
	@Path("/testES")
	@ApiOperation(value = "测试2")
	public  RestResp<UserBean> test2(@QueryParam("userId") Integer userId, 
			@FormParam("kw") String kw,
			@FormParam("queryCondition") String queryCondition,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		List<QueryCondition> qcList = null;
		try {
			qcList = JsonUtils.fromJson(queryCondition, new TypeToken<List<QueryCondition>>() {}.getType());
		} catch (Exception e) {
			throw JsonException.newInstance();
		}
		return new RestResp<UserBean>(commonService.test(kw,qcList),tt);
	}
	@POST
	@Path("/testDel")
	@ApiOperation(value = "测试3")
	public  RestResp<String> test3(@QueryParam("userId") Integer userId, 
			@ApiParam(required=true)@FormParam("id") Integer id,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		commonService.deleteTest(id);
		return new RestResp<String>(tt);
	}

	@GET
	@Path("/sse")
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	public EventOutput getServerSentEvents() {
		return commonService.getServerSentEvents(request.getParameter("userId"),request.getParameter("tt"));
	}
}
