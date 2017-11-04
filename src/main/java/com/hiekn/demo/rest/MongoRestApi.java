package com.hiekn.demo.rest;

import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.hiekn.demo.bean.result.RestData;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.mongo.MongoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Path("/mongo")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Api(tags = {"数据模块"})
public class MongoRestApi {

	@Resource
	private MongoService mongoService;

	@POST
	@Path("/connect")
	@ApiOperation(value = "连接db")
	public RestResp<String> connect(@ApiParam(value="用户Id")@QueryParam("userId") Integer userId,
			@ApiParam(value="ip",required = true)@FormParam("ip") String ip,
			@ApiParam(value="端口",required = true)@FormParam("port") Integer port,
			@ApiParam(value="用户名")@FormParam("username") String username,
			@ApiParam(value="密码")@FormParam("password") String password,
			@ApiParam(value="用户token")@QueryParam("accessToken") String accessToken,
			@ApiParam(value="请求时间戳")@QueryParam("tt") Long tt){
		mongoService.connect(userId,ip,port,username,password);
		return new RestResp<String>(tt);
	}

	@GET
	@Path("/list/db")
	@ApiOperation(value = "获取所有数据库")
	public  RestResp<String> dbList(@QueryParam("userId") Integer userId,
			@ApiParam(value="ip",required = true)@QueryParam("ip") String ip,
			@ApiParam(value="端口",required = true)@QueryParam("port") Integer port,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		return new RestResp<String>(mongoService.getDbList(userId,ip,port),tt);
	}

	@GET
	@Path("/list/table")
	@ApiOperation(value = "获取表")
	public RestResp<String>  tableList(@QueryParam("userId") Integer userId,
			@ApiParam(value="ip",required = true)@QueryParam("ip") String ip,
			@ApiParam(value="端口",required = true)@QueryParam("port") Integer port,
			@ApiParam(value="数据库名",required = true)@QueryParam("db") String db,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		return new RestResp<String>(mongoService.getTableList(userId,db,ip,port),tt);
	}

	@GET
	@Path("/list/data")
	@ApiOperation(value = "分页获取数据")
	public RestResp<Map<String, Object>>  dataList(@QueryParam("userId") Integer userId,
			@ApiParam(value="ip",required = true)@QueryParam("ip") String ip,
			@ApiParam(value="端口",required = true)@QueryParam("port") Integer port,
			@ApiParam(value="数据库名",required = true)@QueryParam("db") String db,
			@ApiParam(value="表名",required = true)@QueryParam("table") String table,
			@QueryParam("pageNo")@DefaultValue("1") Integer pageNo,
			@QueryParam("pageSize")@DefaultValue("10")  Integer pageSize,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		RestData<Map<String,Object>> rd = mongoService.getData(userId,db, table, pageNo, pageSize,ip,port);
		return new RestResp<Map<String,Object>>(rd,tt);
	}
}
