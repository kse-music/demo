package com.hiekn.demo.filter;

import com.hiekn.demo.util.BeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.*;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

@Provider
public class RequestFilter implements ContainerRequestFilter{
	
	private JedisPool jedisPool = BeanUtils.getBean(JedisPool.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
//		studyContainerRequestContext(requestContext);
		UriInfo uri = requestContext.getUriInfo();
		String url = uri.getPath();
		if("swagger.json".equals(url)){
			return;
		}
		String userId = null;
		String tt = null;
		MultivaluedMap<String, String> query = uri.getQueryParameters();
		if(query.size() > 0){
			userId = uri.getQueryParameters().get("userId").get(0);
			tt = uri.getQueryParameters().get("tt").get(0);
		}
		
		if(Objects.nonNull(userId)){
			if(checkFrequency(userId, url)){
//				requestContext.abortWith(Response.ok(new RestResp<String>(ErrorCodes.INVOKE_FAST_ERROR.getErrorCode(),ErrorCodes.INVOKE_FAST_ERROR.toString(),Objects.isNull(tt)?0L:Long.parseLong(tt))).build());
				return;
			}
			setFrequency(userId, url);
		}
		
	}
	
	public void studyContainerRequestContext(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getHeaderString("Accept-Language"));
		System.out.println(requestContext.getLength());//form data内容长度
		System.out.println(requestContext.getMethod());
		System.out.println(requestContext.hasEntity());//是否有form data
		System.out.println(requestContext.getAcceptableLanguages());
		System.out.println(requestContext.getAcceptableMediaTypes());
		Map<String, javax.ws.rs.core.Cookie> cookies = requestContext.getCookies();
		for (Map.Entry<String, Cookie> cookie : cookies.entrySet()) {
			System.out.println(cookie.getValue().getName() +" || " + cookie.getValue().getValue());
		}
		System.out.println(requestContext.getDate());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(requestContext.getEntityStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine()) != null){
			sb.append(URLDecoder.decode(line,"utf-8")).append("\n");
		}
		InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
		requestContext.setEntityStream(in);

		Map<String, List<String>> headers = requestContext.getHeaders();
		for (Entry<String, List<String>> header : headers.entrySet()) {
			System.out.println(header.getKey()+" || " + header.getValue());
		}
		System.out.println(requestContext.getLanguage());
		System.out.println(requestContext.getMediaType());
		Collection<String> properties = requestContext.getPropertyNames();
		System.out.println(properties);
		UriInfo uri = requestContext.getUriInfo();
		System.out.println(uri.getPath());
		System.out.println(uri.getBaseUri().getPath());
		System.out.println(uri.getAbsolutePath().getPath());
		System.out.println(uri.getMatchedResources());
		System.out.println(uri.getMatchedURIs());
		System.out.println(uri.getQueryParameters());
		System.out.println(uri.getPathSegments());
		
	}
	
	public void setFrequency(String userId,String url){
		Jedis jedis = jedisPool.getResource();
		jedis.select(1);
		String key = userId+":"+url;
		jedis.set(key, userId,"NX","EX",3);
		jedis.close();
	}
	
	public boolean checkFrequency(String userId,String url){
		Jedis jedis = jedisPool.getResource();
		jedis.select(1);
		String key = userId+":"+url;
		String uid = jedis.get(key);
		jedis.close();
		if(Objects.equals(userId, uid)){
			return true;
		}
		return false;
	}
	

}
