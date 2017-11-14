package com.hiekn.demo.app;

import com.hiekn.demo.exception.FtlException;
import com.hiekn.demo.util.FtlMethod;
import freemarker.cache.*;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Version;
import jersey.repackaged.com.google.common.collect.Lists;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerConfigurationFactory;
import org.jvnet.hk2.annotations.Optional;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;
import java.util.Properties;

public class FreemarkerConfig implements FreemarkerConfigurationFactory {

	protected final Configuration configuration;
	
	@Inject
	public FreemarkerConfig(@Optional final ServletContext servletContext){
		Version version = Configuration.VERSION_2_3_23;
		configuration = new Configuration(version);
		configuration.setSharedVariable("cutStr", new FtlMethod());
		configuration.setDefaultEncoding("utf-8");//如果.ftl不在返回视图里，而是通过include引进去的，受此属性影响，不受@Produces影响
		configuration.setTemplateExceptionHandler(new FtlException());
		configuration.setIncompatibleImprovements(version);
		configuration.setObjectWrapper(new DefaultObjectWrapper(version));
		final List<TemplateLoader> loaders = Lists.newArrayList();
        Properties p = new Properties();  
        try {
			p.load(FreemarkerConfig.class.getClassLoader().getResourceAsStream("freemarker.properties"));
			configuration.setSettings(p); 
			configuration.setSharedVariable("IMG_SRC", "http://angelnode.qiniudn.com/");
	        if (servletContext != null) {
	            loaders.add(new WebappTemplateLoader(servletContext));
	        }
	        loaders.add(new ClassTemplateLoader(FreemarkerConfig.class, "/"));
            loaders.add(new FileTemplateLoader(new File("/")));
        } catch (Exception e) {
        	e.printStackTrace();
        }
    	configuration.setTemplateLoader(new MultiTemplateLoader(loaders.toArray(new TemplateLoader[loaders.size()])));
	}
	
	@Override
	public Configuration getConfiguration() {
		return configuration;
	}
}
