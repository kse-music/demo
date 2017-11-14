package com.hiekn.demo.rest;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Controller
@Path("/ftl")
@Produces(MediaType.TEXT_HTML+";charset=UTF-8")
@Api("返回视图")
public class Ftl {
	static final Logger log = Logger.getLogger(Ftl.class);

	@GET
    @Path("/test")
	@ApiOperation("测试返回视图")
    public Viewable exampleView(@Context HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("test", "this is the ViewResource test text");
        data.put("request", request);

        BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_23).build();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        data.put("statics", staticModels);
        TemplateHashModel enumModels = wrapper.getEnumModels();
        data.put("enums",enumModels);
        try {
			TemplateHashModel fileStatics =  (TemplateHashModel) staticModels.get("java.io.File");
			data.put("File", fileStatics);
			TemplateHashModel roundingModeEnums = (TemplateHashModel) enumModels.get("java.math.RoundingMode");
			data.put("RoundingMode", roundingModeEnums);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
        return new Viewable("/test", data);
    }

	@Path("/")
	@GET
	@Template(name = "/index")
    @ApiOperation("测试返回视图默认页")
	public Map<String, Object> indexView() {
		Map<String, Object> map = new HashMap<>();
		System.out.println("我是首页");
	    return map;
	}
	

	@Path("{path}")
	@GET
    @ApiOperation("根据请求路径返回对应页")
	public Viewable common(@PathParam("path") String path) {
	    Map<String, Object> map = new HashMap<>();
	    return new Viewable("/"+path,map);
	}

}
