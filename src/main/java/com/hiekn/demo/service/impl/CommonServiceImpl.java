package com.hiekn.demo.service.impl;

import com.google.common.collect.Lists;
import com.hiekn.demo.aop.Intercept;
import com.hiekn.demo.bean.UserBean;
import com.hiekn.demo.bean.result.RestResp;
import com.hiekn.demo.bean.search.QueryCondition;
import com.hiekn.demo.config.CommonResource;
import com.hiekn.demo.dao.UserDao;
import com.hiekn.demo.dao.UserMapper;
import com.hiekn.demo.parser.EsParser;
import com.hiekn.demo.service.CommonService;
import com.hiekn.demo.util.CommonUtils;
import com.mongodb.MongoClient;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class CommonServiceImpl implements CommonService{
	
	@Resource
	private MongoClient mongoClient;
	@Resource
	private JedisPool jedisPool;
	@Resource
	private TransportClient client;
	
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserDao userDao;
	
	@Override
	public String uploadPic(InputStream fileIn, FormDataContentDisposition fileInfo) {
		UploadManager uploadManager = new UploadManager();
		Auth auth = Auth.create(CommonResource.QINIU_AK, CommonResource.QINIU_SK);
		String token = auth.uploadToken(CommonResource.QINIU_BUCKET);
		byte[] fileByte = null;
		String fileName = fileInfo.getFileName();
		fileName = CommonUtils.getRandomUUID() + fileName.substring(fileName.lastIndexOf("."));
		try {
			fileByte = IOUtils.toByteArray(fileIn);
			uploadManager.put(fileByte, fileName, token);
		} catch (IOException e) {
			
		}
		return CommonResource.IMG_SRC+fileName;
	}

	@Intercept
	@Override
	public List<UserBean> test(String kw,List<QueryCondition> qcList) {
		List<UserBean> list = Lists.newArrayList();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch("twitter").setTypes("twitter_data");
		BoolQueryBuilder query = QueryBuilders.boolQuery();
//		searchRequestBuilder.storedFields("content");//官方已经不推荐，用source filtering替代
		
		HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
//		highlightBuilder.preTags("<span style=\"color:red\">");
//		highlightBuilder.postTags("</span>");
		EsParser.queryCondition2ES(qcList,query);
//		String field = "title";
		SearchResponse response = searchRequestBuilder
				.setQuery(query)//在分词的结果上全完全匹配，不带分析器
//				.setQuery(QueryBuilders.termQuery(field,kw))//在分词的结果上全完全匹配，不带分析器
//				.setQuery(QueryBuilders.queryStringQuery(kw).field(field))//带了分析器
//				.setQuery(QueryBuilders.matchQuery(field, kw).analyzer("ik_max_word"))//带了分析器
				
//				.setQuery(QueryBuilders.matchPhraseQuery(field, text))//term像matchPhrase
				
//				.setFetchSource(new String[]{"content"},new String[]{})
				.highlighter(highlightBuilder)
				.get();
		
		/* match query搜索的时候，首先会解析查询字符串，进行分词，然后查询，
		 * 而term query,输入的查询内容是什么，就会按照什么去查询，并不会解析查询内容，对它分词。
		 */
		 
		SearchHits hits = response.getHits();
		for (SearchHit hit : hits) {
			Map<String, HighlightField> titleField = hit.getHighlightFields();
			for (Entry<String, HighlightField> searchHitField : titleField.entrySet()) {
				System.out.println(searchHitField.getKey() + "=" + searchHitField.getValue().getFragments()[0]);
			}
			
//			String str = (String)(hit.getField("content").getValue());
//			System.out.println(str);
			
			Map<String, Object> map = hit.getSourceAsMap();
			UserBean u = new UserBean();
			u.setName(map.get("name").toString());
			u.setTitle(map.get("title").toString());
			u.setContent(map.get("content").toString());
			u.setNum(Integer.parseInt(map.get("num").toString()));
			u.setSearch(map.get("search").toString());
			u.setNosearch(map.get("nosearch").toString());
			list.add(u);
		}
		return list;
	}

	@Override
	public void uploadFile(InputStream fileIn, FormDataContentDisposition fileInfo) {
		String path = System.getProperty("catalina.base")+File.separator+"upload";
		String fileName = fileInfo.getFileName();
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		fileName = CommonUtils.getRandomUUID() + "." + ext;
		File file = new File(path+File.separator+fileName);
		try {
			FileUtils.copyInputStreamToFile(fileIn, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void multiUpload(FormDataMultiPart form) {
		List<FormDataBodyPart> list = form.getFields("file");
		if(Objects.nonNull(list) && !list.isEmpty()){
	        String path = System.getProperty("catalina.base")+File.separator+"upload";
			for (FormDataBodyPart formDataBodyPart : list) {
				MediaType type = formDataBodyPart.getMediaType();
				System.out.println(type);
				InputStream fileIn = formDataBodyPart.getValueAs(InputStream.class);
				FormDataContentDisposition detail = formDataBodyPart.getFormDataContentDisposition();
				String fileName = detail.getFileName();
				File file = new File(path+File.separator+fileName);
				try {
					FileUtils.copyInputStreamToFile(fileIn, file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void testTransaction() {
		int num = userMapper.deleteById(18);
		System.out.println(num);
	}

	@Override
	public void deleteTest(Integer id) {
		userDao.delete(id);
	}

	@Override
	public EventOutput getServerSentEvents(String userId) {
		final EventOutput eventOutput = new EventOutput();
//		try {
//			OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
//			eventBuilder.name("uid_"+userId+"identifier_"+tt);
//			eventBuilder.data(RestResp.class, new RestResp<String>("The server time is: " + new Date(),1L));
//			eventBuilder.mediaType(MediaType.APPLICATION_JSON_TYPE);
//			OutboundEvent event = eventBuilder.build();
//			eventOutput.write(event);
////			TimeUnit.SECONDS.sleep(3);
//		} catch (Exception e) {
//			throw new RuntimeException("Error when writing the event.", e);
//		} finally {
//			try {
//				eventOutput.close();
//			} catch (IOException ioClose) {
//				throw new RuntimeException("Error when closing the event output.", ioClose);
//			}
//		}
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	Jedis jedis = jedisPool.getResource();
                	final OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
                	eventBuilder.name("uid_"+userId+"identifier_");
                	while(true){
                		String str = jedis.get( "s");
                		if(str!=null){
                			eventBuilder.data(String.class,str);
                			eventOutput.write(eventBuilder.build());
                			jedis.del("s");
                			break;
                		}
            			eventBuilder.data(RestResp.class, new RestResp<String>("The server time is: " + new Date()));
            			eventBuilder.mediaType(MediaType.APPLICATION_JSON_TYPE);
                		final OutboundEvent event = eventBuilder.build();
            			eventOutput.write(event);
                		TimeUnit.SECONDS.sleep(1);
                	}
                } catch (Exception e) {
//                  throw new RuntimeException("Error when writing the event.", e);
                } finally {
                    try {
                        eventOutput.close();
                    } catch (IOException ioClose) {
                        throw new RuntimeException("Error when closing the event output.", ioClose);
                    }
                }
            }
        }).start();
        return eventOutput;
	}
	
}
