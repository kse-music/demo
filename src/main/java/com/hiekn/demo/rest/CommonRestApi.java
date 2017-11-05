package com.hiekn.demo.rest;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.hiekn.demo.bean.result.BaseParam;
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
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Api("公共接口")
public class CommonRestApi {

    @Resource
    private CommonService commonService;

    @POST
    @Path("/uploadPic")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ApiOperation("上传图片")
    public RestResp<String> uploadPic(@BeanParam BaseParam baseParam,
                                      @FormDataParam("fileData") InputStream fileIn,
                                      @FormDataParam("fileData") FormDataContentDisposition fileInfo) {
        return new RestResp<>(commonService.uploadPic(fileIn, fileInfo), baseParam.getTt());
    }

    @POST
    @Path("/uploadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ApiOperation("上传文件")
    public RestResp<String> uploadFile(@BeanParam BaseParam baseParam,
                                       @FormDataParam("fileData") InputStream fileIn,
                                       @FormDataParam("fileData") FormDataContentDisposition fileInfo) {
        commonService.uploadFile(fileIn, fileInfo);
        return new RestResp<>(baseParam.getTt());
    }


    @POST
    @Path("/im/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ApiOperation("im上传")
    public RestResp<String> imUpload(@BeanParam BaseParam baseParam,
                                     @FormDataParam("file") InputStream fileIn,
                                     @FormDataParam("file") FormDataContentDisposition fileInfo) {
        return new RestResp<>(commonService.uploadPic(fileIn, fileInfo), baseParam.getTt());
    }

    @POST
    @Path("/multiUpload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ApiOperation("批量上传")
    public RestResp<String> multiUpload(@BeanParam BaseParam baseParam,
                                        FormDataMultiPart form) {
        commonService.multiUpload(form);
        return new RestResp<>(baseParam.getTt());
    }

}
