package com.hiekn.demo.util;

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;


public class FtlMethod implements TemplateMethodModelEx {
	
	@Override
	public Object exec(@SuppressWarnings("rawtypes") List args) throws TemplateModelException {
		String k = args.get(0).toString();  
		Integer l = Integer.valueOf(args.get(1).toString());  
		return k.length()>l?k.substring(0, l)+"...":k;
	}

}
