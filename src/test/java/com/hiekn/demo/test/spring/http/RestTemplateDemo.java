package com.hiekn.demo.test.spring.http;

import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.util.HttpRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RestTemplateDemo extends TestBase {

    private static final String BASE_PATH = "http://localhost:8080/api/";

    private RestTemplate restTemplate;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
    }


    /**
     * 各种第三方http工具试用
     */
    @Test
    public void okHttp() throws IOException {
        //1、HttpRequest基于原生URLConnection封装
        String s = HttpRequest.sendGet("http://www.hiboot.cn/");
        System.out.println(s);
    }

    @Test
    public void getForObject(){
        String s = restTemplate.getForObject(BASE_PATH+"test/list",String.class);
        RestResp restResp = restTemplate.getForObject(BASE_PATH+"test/list",RestResp.class);
        logger.info("Result = {}",s);
        logger.info("Result = {}",restResp.getData());
    }

    @Test
    public void getForEntity(){
        ResponseEntity<String> s = restTemplate.getForEntity(BASE_PATH+"test/list",String.class);
        logger.info("Result = {}",s.getStatusCodeValue());
        logger.info("Result = {}",s.getHeaders());
        logger.info("Result = {}",s.getBody());
    }

    @Test
    public void postForObject(){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        body.add("bean",timestamp);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        String s = restTemplate.postForObject(BASE_PATH+"test/add", requestEntity, String.class);
        logger.info(s);

    }

    @Test
    public void postForEntity(){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        body.add("bean",timestamp);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> s = restTemplate.postForEntity(BASE_PATH+"test/add", requestEntity, String.class);
        logger.info("Result = {}",s);

    }

}
