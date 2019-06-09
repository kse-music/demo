package com.hiekn.demo.app;

import com.hiekn.demo.config.CommonResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class DemoApplication extends ResourceConfig {
    public DemoApplication() {
        packages(CommonResource.BASE_PACKAGE)
                .register(JacksonFeature.class)
                .register(MultiPartFeature.class);

        //register(MvcFeature.class);
        property(FreemarkerMvcFeature.TEMPLATE_BASE_PATH, "freemarker");
//		property(FreemarkerMvcFeature.ENCODING, "GBK");//如果@Produces没指定输出编码，则以此设置为准,默认UTF-8
        property(FreemarkerMvcFeature.CACHE_TEMPLATES, new Boolean(false));
        property(FreemarkerMvcFeature.TEMPLATE_OBJECT_FACTORY, new FreemarkerConfig(null));
        register(FreemarkerMvcFeature.class);//依赖MvcFeature

        if (CommonResource.SWAGGER_INIT) {
            register(ApiListingResource.class);
            register(SwaggerSerializers.class);
            initSwagger();
        }
    }

    private void initSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(CommonResource.SWAGGER_VERSION);
        beanConfig.setTitle(CommonResource.SWAGGER_TITLE);
        beanConfig.setHost(CommonResource.SWAGGER_HOST);
        beanConfig.setBasePath(CommonResource.SWAGGER_BASE_PATH);
        beanConfig.setResourcePackage(CommonResource.BASE_PACKAGE);
        beanConfig.setScan(true);
    }
}
