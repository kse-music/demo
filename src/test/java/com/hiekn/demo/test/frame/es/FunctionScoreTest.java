package com.hiekn.demo.test.frame.es;

import javax.annotation.Resource;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ExponentialDecayFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder.FilterFunctionBuilder;

import com.hiekn.demo.test.TestBase;

import org.elasticsearch.index.query.functionscore.LinearDecayFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;

public class FunctionScoreTest extends TestBase {
	
	@Resource
	private TransportClient esClient;
	
	public void t() {
		
		SearchRequestBuilder srb = esClient.prepareSearch("");
		
		ScoreFunctionBuilder<ExponentialDecayFunctionBuilder> exp = ScoreFunctionBuilders.exponentialDecayFunction("count", 0, 100,0,0.5).setWeight(-0.2f);
		ScoreFunctionBuilder<LinearDecayFunctionBuilder> line = ScoreFunctionBuilders.linearDecayFunction("coefficient", 0, 10,0,0.5).setWeight(1f);
		
		FilterFunctionBuilder[] functions = {
		        new FunctionScoreQueryBuilder.FilterFunctionBuilder(exp),                    
		        new FunctionScoreQueryBuilder.FilterFunctionBuilder(line)
		};
		
		BoolQueryBuilder bool = QueryBuilders.boolQuery();
		
		FunctionScoreQueryBuilder s = QueryBuilders.functionScoreQuery(bool,functions).boostMode(CombineFunction.SUM);
		
		srb.setQuery(s).setExplain(true);
	}

}
