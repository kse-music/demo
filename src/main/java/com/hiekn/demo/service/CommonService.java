package com.hiekn.demo.service;

import java.io.InputStream;
import java.util.List;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.sse.EventOutput;

import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.search.QueryCondition;

public interface CommonService {
	
	String uploadPic(InputStream fileIn, FormDataContentDisposition fileInfo);
	void uploadFile(InputStream fileIn, FormDataContentDisposition fileInfo);
	void multiUpload(FormDataMultiPart form);
	List<UserBean> test(String kw,List<QueryCondition> qcList);
	void deleteTest(Integer id);
	void testTransaction();
	EventOutput getServerSentEvents(String userId);
}
