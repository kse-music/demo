package com.hiekn.demo.rest;

import com.google.gson.reflect.TypeToken;
import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.result.BaseParam;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.bean.search.QueryCondition;
import com.hiekn.demo.service.CommonService;
import com.hiekn.demo.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.SseFeature;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Properties;

@Controller
@Path("/test")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Api("测试模块")
public class TestRestApi {

	@Resource
	private CommonService commonService;
	@Context 
	private HttpServletRequest request;

	@GET
	@Path("/test")
	@ApiOperation("测试1")
	public RestResp<String> test(@BeanParam BaseParam baseParam) {
		Properties props = System.getProperties();
		props.list(System.out);
		return new RestResp<>(System.getProperty("catalina.base"));
	}

	@POST
	@Path("/testES")
	@ApiOperation("测试2")
	public RestResp<List<UserBean>> test2(@BeanParam BaseParam baseParam,
			@FormParam("kw") String kw,
			@FormParam("queryCondition") String queryCondition){
		List<QueryCondition> qcList  = JsonUtils.fromJson(queryCondition, new TypeToken<List<QueryCondition>>() {}.getType());
		return new RestResp<>(commonService.test(kw,qcList));
	}
	@POST
	@Path("/testDel")
	@ApiOperation("测试3")
	public RestResp<String> test3(@BeanParam BaseParam baseParam,
			@ApiParam(required=true)@FormParam("id") Integer id){
		commonService.deleteTest(id);
		return new RestResp<>();
	}

	@GET
	@Path("/sse")
	@Produces(SseFeature.SERVER_SENT_EVENTS)
	public EventOutput getServerSentEvents() {
		return commonService.getServerSentEvents(request.getParameter("userId"));
	}
}
