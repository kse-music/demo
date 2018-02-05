package com.hiekn.demo.rest;

import com.hiekn.demo.bean.result.BaseParam;
import com.hiekn.demo.bean.result.RestData;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.mongo.MongoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Controller
@Path("/mongo")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Api("数据模块")
public class MongoRestApi {

	@Resource
	private MongoService mongoService;

	@POST
	@Path("/connect")
	@ApiOperation("连接db")
	public RestResp<String> connect(@ApiParam("基础参数")@BeanParam BaseParam baseParam,
			@ApiParam(value="ip",required = true)@FormParam("ip") String ip,
			@ApiParam(value="端口",required = true)@FormParam("port") Integer port,
			@ApiParam(value="用户名")@FormParam("username") String username,
			@ApiParam(value="密码")@FormParam("password") String password){
		mongoService.connect(baseParam.getUserId(),ip,port,username,password);
		return new RestResp<>();
	}

	@GET
	@Path("/list/db")
	@ApiOperation("获取所有数据库")
	public  RestResp<List<String>> dbList(@BeanParam BaseParam baseParam,
                                         @ApiParam(value="ip",required = true)@QueryParam("ip") String ip,
                                         @ApiParam(value="端口",required = true)@QueryParam("port") Integer port){
		return new RestResp<>(mongoService.getDbList(baseParam.getUserId(),ip,port));
	}

	@GET
	@Path("/list/table")
	@ApiOperation("获取表")
	public RestResp<List<String>>  tableList(@BeanParam BaseParam baseParam,
			@ApiParam(value="ip",required = true)@QueryParam("ip") String ip,
			@ApiParam(value="端口",required = true)@QueryParam("port") Integer port,
			@ApiParam(value="数据库名",required = true)@QueryParam("db") String db){
		return new RestResp<>(mongoService.getTableList(baseParam.getUserId(),db,ip,port));
	}

	@GET
	@Path("/list/data")
	@ApiOperation("分页获取数据")
	public RestResp<RestData<Map<String,Object>>> dataList(@BeanParam BaseParam baseParam,
			@ApiParam(value="ip",required = true)@QueryParam("ip") String ip,
			@ApiParam(value="端口",required = true)@QueryParam("port") Integer port,
			@ApiParam(value="数据库名",required = true)@QueryParam("db") String db,
			@ApiParam(value="表名",required = true)@QueryParam("table") String table,
			@QueryParam("pageNo")@DefaultValue("1") Integer pageNo,
			@QueryParam("pageSize")@DefaultValue("10")  Integer pageSize){
		RestData<Map<String,Object>> rd = mongoService.getData(baseParam.getUserId(),db, table, pageNo, pageSize,ip,port);
		return new RestResp<>(rd);
	}
}
