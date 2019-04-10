package com.hiekn.demo.test.db.es;

import com.google.gson.reflect.TypeToken;
import com.hiekn.demo.bean.search.QueryCondition;
import com.hiekn.demo.parser.EsParser;
import com.hiekn.demo.test.TestBase;
import com.hiekn.demo.util.JsonUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.filters.Filters;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.joda.time.DateTime;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

public class AggregationDemo extends TestBase{
	
	private static final String ES_DB_NAME = "twitter";
	private static final String ES_TABLE_NAME = "twitter_data";
	private static final long ES_TIMEOUT = 5l;
	private static float DEFAULT_MIN_SCORE = 0.05f;
	
	@Resource
	private TransportClient client;
	
	@Test
	public void test(){
		String queryCondition = "[{\"field\":\"lastRound\",\"condition\":\"eq\",\"relation\":\"and\",\"vList\":[\"天使轮\"]},{\"field\":\"lastRoundTime\",\"condition\":\"prefix\",\"relation\":\"and\",\"vList\":[\"2010\"]}]";
		BoolQueryBuilder query = QueryBuilders.boolQuery();
		query.should(QueryBuilders.termQuery("dateApplication", "2009.03.24"));
		
//		BoolQueryBuilder filter = QueryBuilders.boolQuery();
//		filter.should(QueryBuilders.termQuery("dateApplication", "2009.03.24"));
//		query.filter(filter);
		
//		AggregationBuilder by_key = AggregationBuilders.filters("by_key",query);
		AggregationBuilder by_key = AggregationBuilders.filter("by_key2",query);
//		AggregationBuilder by_key = AggregationBuilders.terms("terms_ag").field("holder");
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(ES_DB_NAME).setTypes(ES_TABLE_NAME).setMinScore(DEFAULT_MIN_SCORE).setTimeout(TimeValue.timeValueSeconds(ES_TIMEOUT));
		
		List<QueryCondition> qcList = JsonUtils.fromJson(queryCondition, new TypeToken<List<QueryCondition>>() {}.getType());

		EsParser.queryCondition2ES(qcList,query);

		/*
		 * 警告：性能考量
			只有当你需要对搜索结果和聚合使用不同的过滤方式时才考虑使用post_filter。有时一些用户会直接在常规搜索中使用post_filter。
			不要这样做！post_filter会在查询之后才会被执行，因此会失去过滤在性能上帮助(比如缓存)。
			post_filter应该只和聚合一起使用，并且仅当你使用了不同的过滤条件时。
		 */

		SearchResponse response = searchRequestBuilder
								.setQuery(query)
								//.setPostFilter()//后置过滤器，不参与查询
								.addAggregation(by_key)
								.setSize(0)
								.get();
		
		System.out.println(response);

		Filters agg = response.getAggregations().get("by_key");
		for (Filters.Bucket entry : agg.getBuckets()) {
			String key = entry.getKeyAsString();                    
			long docCount = entry.getDocCount();           
			System.out.println(key+"---"+docCount);
		}
		
		Filter  agg2 = response.getAggregations().get("by_key2");
		System.out.println(agg2.getName()+agg2.getDocCount());
		
		Terms genders = response.getAggregations().get("terms_ag");
		for (Terms.Bucket entry : genders.getBuckets()) {
			System.out.println(entry.getKey()+"---"+entry.getDocCount());
		}
	}
	
	@Test
	public void testDateH(){
		AggregationBuilder by_date = AggregationBuilders.dateRange("date_range").field("publishTime")
				//				.addRange(getPrevDay(-2), getPrevDay(-1))
				//				.addRange(getPrevDay(-3), getPrevDay(-2))
				//				.addRange(getPrevDay(-4), getPrevDay(-3))
				//				.addRange(getPrevDay(-5), getPrevDay(-4))
				//				.addRange(getPrevDay(-6), getPrevDay(-5))
				//				.addRange(getPrevDay(-7), getPrevDay(-6))
				.addRange(getPrevDay(-145), getPrevDay(-143));

		//		AggregationBuilder by_date =
		//				AggregationBuilders
		//				.dateHistogram("agg")
		//				.field("publishTime")
		//				.dateHistogramInterval(DateHistogramInterval.DAY);
		BoolQueryBuilder bool = QueryBuilders.boolQuery();
		String start = getPrevDay(-145);
		String end = getPrevDay(-143);
				bool.must(QueryBuilders.rangeQuery("publishTime").gt(start).lt(end));
		SearchResponse response = client.prepareSearch("u260").setTypes("u260_data_4eb523a0").setMinScore(DEFAULT_MIN_SCORE).setTimeout(TimeValue.timeValueSeconds(ES_TIMEOUT))
				.setQuery(bool)
				.addAggregation(by_date)
				.setSize(0)
				.get();
		System.out.println(response);
		Range agg = response.getAggregations().get("date_range");
		for (Range.Bucket entry : agg.getBuckets()) {
			String key = entry.getKeyAsString();                // Date range as key
			DateTime fromAsDate = (DateTime) entry.getFrom();   // Date bucket from as a Date
			DateTime toAsDate = (DateTime) entry.getTo();       // Date bucket to as a Date
			long docCount = entry.getDocCount();                // Doc count
			System.out.println(key);
			System.out.println(fromAsDate);
			System.out.println(toAsDate);
			System.out.println(docCount);
		}

		//		Histogram  agg = response.getAggregations().get("agg");
		//		for (Histogram .Bucket entry : agg.getBuckets()) {
		//			String date = entry.getKeyAsString();
		//			Long num = entry.getDocCount();
		//		}

	}
	private String getPrevDay(int day) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, day + 2);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		DateFormatUtils.format(now, "yyyyMMdd");
		return DateFormatUtils.format(now, "yyyyMMdd");
	}

}
