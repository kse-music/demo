package com.hiekn.demo.app;

import javax.ws.rs.ApplicationPath;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.hiekn.demo.config.CommonResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

@ApplicationPath("/api/*")
public class DemoApplication extends ResourceConfig{
	public DemoApplication() {
		packages(CommonResource.BASE_PACKAGE)
		.register(JacksonJsonProvider.class)
		.register(MultiPartFeature.class);

        //register(MvcFeature.class);
        property(MvcFeature.TEMPLATE_BASE_PATH+".freemarker", "freemarker");
//		property(MvcFeature.ENCODING+".freemarker", "GBK");//如果@Produces没指定输出编码，则以此设置为准,默认UTF-8
//		property(MvcFeature.CACHE_TEMPLATES, new Boolean(true));//不知咋用
        property(FreemarkerMvcFeature.TEMPLATE_OBJECT_FACTORY,new FreemarkerConfig(null));
        register(FreemarkerMvcFeature.class);//依赖MvcFeature

		if(CommonResource.SWAGGER_INIT){
			register(ApiListingResource.class);
			register(SwaggerSerializers.class);
			initSwagger();
		}
	}

	private void initSwagger(){
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion(CommonResource.SWAGGER_VERSION);
		beanConfig.setTitle(CommonResource.SWAGGER_TITLE);
		beanConfig.setHost(CommonResource.SWAGGER_HOST);
		beanConfig.setBasePath(CommonResource.SWAGGER_BASE_PATH);
		beanConfig.setResourcePackage(CommonResource.BASE_PACKAGE);
		beanConfig.setScan(true);
	}
}
