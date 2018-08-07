package com.healthcare.provider.service;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.healthcare.provider.elasticsearch.ElasticSearchProvider;
import com.healthcare.provider.elasticsearch.ElasticSearchProviderRepository;
import com.healthcare.provider.util.Constants;
import com.healthcare.provider.util.ProviderFilterBean;

/**
 * This is a service class used for querying data from ElasticSearch
 *
 */
@Service
public class ProviderSearchService {

	public static final Logger logger = LoggerFactory.getLogger(ProviderSearchService.class);

	@Autowired private ElasticSearchProviderRepository elasticSearchProviderRepository;
	@Autowired private ElasticsearchTemplate elasticsearchTemplate;


	/** 
	 * This method queries ES based on the query and return list of documents
	 * @param filter 
	 * @param boolQueryBuilder
	 * @return List<ElasticSearchProvider>
	 */
	public List<ElasticSearchProvider> searchProviderDetails(ProviderFilterBean filter) {

		List<ElasticSearchProvider> providerDetails = new ArrayList<>();

		BoolQueryBuilder boolQueryBuilder = getQueryBuilder(filter);

		NativeSearchQueryBuilder nq = new NativeSearchQueryBuilder().withIndices(Constants.HEALTHCARE).withTypes(Constants.PROVIDER).
				withQuery(boolQueryBuilder);

		int count = (int) elasticSearchProviderRepository.count();
		logger.info("number of documents present : " +count);

		if (count != 0) {
			SearchQuery searchQuery = nq.withPageable(new PageRequest(0, count)).build();
			providerDetails = elasticsearchTemplate.queryForList(searchQuery,ElasticSearchProvider.class);		
		}

		logger.info("number of queried documents :" + providerDetails.size());

		return providerDetails;
	}


	/**
	 *  This method queries ES based on the filtered query for selected fields and return list of documents
	 * @param filter 
	 * @param boolQueryBuilder
	 * @param fields
	 * @return List<ElasticSearchProvider>
	 */
	public List<JsonNode> searchProviderDetailsWithRequiredFields(ProviderFilterBean filter) {
		
		List<JsonNode> providerDetails = new ArrayList<>();

		BoolQueryBuilder boolQueryBuilder = getQueryBuilder(filter);

		NativeSearchQueryBuilder nq = new NativeSearchQueryBuilder().withIndices(Constants.HEALTHCARE).withTypes(Constants.PROVIDER)
				.withQuery(boolQueryBuilder).withFields(filter.getFields()).withSearchType(SearchType.QUERY_AND_FETCH);

		int count = (int) elasticSearchProviderRepository.count();
		logger.info("number of documents present : " +count);

		if (count != 0) {
			SearchQuery searchQuery = nq.withPageable(new PageRequest(0, count)).build();
			providerDetails = elasticsearchTemplate.queryForList(searchQuery,JsonNode.class);
		}
		logger.info("number of queried documents :" + providerDetails.size());

		return providerDetails;
	}

	/**
	 * This util method is used for generating queryBuilder based on user inputs
	 * 
	 * @param maxDischarges
	 * @param minDischarges
	 * @param maxAvgCoveredCharges
	 * @param minAvgCoveredCharges
	 * @param maxAvgMedicarePayments
	 * @param minAvgMedicarePayments
	 * @param state
	 * @param boolQueryBuilder
	 */
	private BoolQueryBuilder getQueryBuilder(ProviderFilterBean filter) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		/* range query for maximum number of Total Discharges */
		if(null != filter.getMaxDischarges() && !filter.getMaxDischarges().isEmpty()){
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Constants.TOTAL_DISCHARGES).lte(Double.valueOf(filter.getMaxDischarges())));
		}

		/* range query for minimum number of Total Discharges */
		if(null != filter.getMinDischarges() && !filter.getMinDischarges().isEmpty()){
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Constants.TOTAL_DISCHARGES).gte(Double.valueOf(filter.getMinDischarges())));
		}

		/* range query maximum Average Covered Charges */
		if(null != filter.getMaxAvgCoveredCharges() && !filter.getMaxAvgCoveredCharges().isEmpty()){
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Constants.AVG_COVERED_CHARGES).lte(Double.valueOf(filter.getMaxAvgCoveredCharges())));
		}

		/* range query for minimum Average Covered Charges */
		if(null != filter.getMinAvgCoveredCharges() && !filter.getMinAvgCoveredCharges().isEmpty()){
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Constants.AVG_COVERED_CHARGES).gte(Double.valueOf(filter.getMinAvgCoveredCharges())));
		}

		/* range query for maximum Average Medicare Payment */
		if(null != filter.getMinAvgMedicarePayments() && !filter.getMinAvgMedicarePayments().isEmpty()){
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Constants.AVG_MEDICARE_PAYMENTS).gte(Double.valueOf(filter.getMinAvgMedicarePayments())));
		}

		/* range query for minimum Average Medicare Payment */
		if(null != filter.getMaxAvgMedicarePayments() && !filter.getMaxAvgMedicarePayments().isEmpty()){
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Constants.AVG_MEDICARE_PAYMENTS).lte(Double.valueOf(filter.getMaxAvgMedicarePayments())));
		}

		/* query for state that the provider is from */
		if(null != filter.getState() && !filter.getState().isEmpty()){
			boolQueryBuilder.must(createWildcardQuery(Constants.STATE, filter.getState()));
		}

		return boolQueryBuilder;
	}

	/**
	 * Creates wild card query for string based field
	 * @param key
	 * @param value
	 * @return
	 */
	private static QueryStringQueryBuilder createWildcardQuery(String key, String value) {
		return new QueryStringQueryBuilder("*" + value + "*").analyzeWildcard(true)
				.minimumShouldMatch("100%")
				.defaultOperator(QueryStringQueryBuilder.Operator.AND)
				.field(key);
	}
}
