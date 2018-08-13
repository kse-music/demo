package com.hiekn.demo.test;

import com.hiekn.demo.bean.Student;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.exception.ExceptionHandler;
import com.hiekn.demo.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RestTemplateDemo {

    private static final Log logger = LogFactory.getLog(RestTemplateDemo.class);

    private static RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_PATH = "http://localhost:8080/api/";

    public static void main(String[] args) {
        String getUrl = "test/list";
        String postUrl = "test/add";
//        getForObject(getUrl);
//        getForEntity(getUrl);
//        postForObject(postUrl);
        postForEntity(postUrl);
    }

    public static void getForObject(String url){
        String s = restTemplate.getForObject(BASE_PATH+url,String.class);
        RestResp restResp = restTemplate.getForObject(BASE_PATH+url,RestResp.class);
        logger.info(s);
        logger.info(restResp.getData());
    }

    public static void getForEntity(String url){
        ResponseEntity<String> s = restTemplate.getForEntity(BASE_PATH+url,String.class);
        logger.info(s.getStatusCodeValue());
        logger.info(s.getHeaders());
        logger.info(s.getBody());
    }

    public static void postForObject(String url){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        body.add("bean",timestamp);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        String s = restTemplate.postForObject(BASE_PATH+url, requestEntity, String.class);
        logger.info(s);

    }

    public static void postForEntity(String url){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        body.add("bean",timestamp);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> s = restTemplate.postForEntity(BASE_PATH+url, requestEntity, String.class);
        logger.info(s);

    }

}
