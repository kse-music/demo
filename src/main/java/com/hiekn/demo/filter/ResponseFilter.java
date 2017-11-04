package com.hiekn.demo.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseFilter implements ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		
		//跨域设置,生产环境应该去掉
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");  
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");  
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "x-requested-with");  
		responseContext.getHeaders().add("Access-Control-Max-Age", "3600");
	}

}
