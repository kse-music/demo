package com.hiekn.demo.rest;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;

import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.service.CommonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Path("/common")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Api(tags = {"公共接口"}) 
public class CommonRestApi {
	
	@Resource
	private CommonService commonService;
	
	@POST
	@Path("/uploadPic")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@ApiOperation(value = "上传图片")
	public  RestResp<String> uploadPic(@QueryParam("userId") Integer userId, 
			@FormDataParam("fileData") InputStream fileIn, 
			@FormDataParam("fileData") FormDataContentDisposition fileInfo,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		return new RestResp<String>(commonService.uploadPic(fileIn,fileInfo),tt);
	}
	
	@POST
	@Path("/uploadFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@ApiOperation(value = "上传文件")
	public  RestResp<String> uploadFile(@QueryParam("userId") Integer userId, 
			@FormDataParam("fileData") InputStream fileIn, 
			@FormDataParam("fileData") FormDataContentDisposition fileInfo,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		commonService.uploadFile(fileIn,fileInfo);
		return new RestResp<String>(tt);
	}
	
	
	
	@POST
	@Path("/im/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@ApiOperation(value = "im上传")
	public  RestResp<String> imUpload(@QueryParam("userId") Integer userId, 
			@FormDataParam("file") InputStream fileIn, 
			@FormDataParam("file") FormDataContentDisposition fileInfo,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		return new RestResp<String>(commonService.uploadPic(fileIn,fileInfo),tt);
	}
	
	@POST
	@Path("/multiUpload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@ApiOperation(value = "批量上传")
	public  RestResp<String> multiUpload(@QueryParam("userId") Integer userId, 
			FormDataMultiPart form,
			@QueryParam("accessToken") String accessToken,
			@QueryParam("tt") Long tt){
		commonService.multiUpload(form);
		return new RestResp<String>(tt);
	}
	
}
