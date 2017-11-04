package com.hiekn.demo.config;

import java.util.Properties;

import com.hiekn.demo.util.BeanUtils;

public class CommonResource {
	
	public static Properties props = BeanUtils.getBean(Properties.class);
	
	public static final String BASE_PACKAGE = props.getProperty("base.package");
	public static final boolean SWAGGER_INIT = Boolean.parseBoolean(props.getProperty("swagger.init"));
	public static final String SWAGGER_VERSION = props.getProperty("swagger.version");
	public static final String SWAGGER_TITLE = props.getProperty("swagger.title");
	public static final String SWAGGER_HOST = props.getProperty("swagger.host");
	public static final String SWAGGER_BASE_PATH = props.getProperty("swagger.base.path");
	public static final String SWAGGER_RESOURCE_PACKAGE = props.getProperty("swagger.resource.package");

	public static final String QINIU_AK = props.getProperty("QINIU_AK");
	public static final String QINIU_SK = props.getProperty("QINIU_SK");
	public static final String QINIU_BUCKET = props.getProperty("QINIU_BUCKET");
	public static final String IMG_SRC = props.getProperty("IMG_SRC");
	public static final String INVITE_EMAIL_FILE_PATH = props.getProperty("invite_email_file_path");
	
}