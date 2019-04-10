package com.hiekn.demo.test.db.es;

import com.hiekn.demo.test.TestBase;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

public class RestClientDemo extends TestBase{

	@Test
	public void testRestClient() throws IOException{
		//RestClient是线程安全的
		RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("202.127.3.194", 10200, "http"));
		//		restClientBuilder.setMaxRetryTimeoutMillis(10);//在为同一请求进行多次尝试时应该遵守的超时。默认值为10秒
		RestClient restClient = restClientBuilder.build();


		HttpEntity entity = new NStringEntity("{\"query\":{\"bool\":{\"must\":[{\"query_string\":{\"default_field\":\"name\",\"query\":\"机器人\"}},{\"term\":{\"type\":\"发明专利\"}},{\"range\":{\"dateApplication\":{\"gt\":\"2016.01.01\",\"lt\":\"2016.12.31\"}}}],\"must_not\":[],\"should\":[]}},\"size\":0,\"_source\":{\"excludes\":[]},\"aggs\":{\"2\":{\"date_histogram\":{\"field\":\"datePublication\",\"interval\":\"1M\",\"min_doc_count\":0,\"extended_bounds\":{\"min\":\"2016.01.01\",\"max\":\"2016.12.31\"}}}}}", ContentType.APPLICATION_JSON);

		Response response = restClient.performRequest("GET", "/patent/_search", Collections.singletonMap("pretty", "true"),entity);
		System.out.println(EntityUtils.toString(response.getEntity()));
		restClient.close();

	}

	@Test
	public void testAsyncRestClient() throws IOException, InterruptedException{
		RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200))
				.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
					@Override
					public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
						return requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000);
					}
				})
				.setMaxRetryTimeoutMillis(60000)
				.build();
		int numRequests = 10;
		final CountDownLatch latch = new CountDownLatch(numRequests);
		HttpEntity[] entities = new HttpEntity[numRequests];
		for (int i = 0; i < numRequests; i++) {
			restClient.performRequestAsync(
					"PUT",
					"/twitter/tweet/" + i,
					Collections.<String, String>emptyMap(),
					//assume that the documents are stored in an entities array
					entities[i],

					new ResponseListener() {
						@Override
						public void onSuccess(Response response) {
							System.out.println(response);
							latch.countDown();
						}

						@Override
						public void onFailure(Exception exception) {
							latch.countDown();
						}
					}
					);
		}

		//wait for all requests to be completed
		latch.await();		
	}

	
}
