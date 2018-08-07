package com.healthcare.provider.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare.provider.elasticsearch.ElasticSearchProvider;
import com.healthcare.provider.elasticsearch.ElasticSearchProviderRepository;
import com.healthcare.provider.util.ProviderDetail;


/**
 * This service class is used for indexing ES
 *
 */

@Service
public class ElasticSearchService {

	public static final Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);

	@Autowired 
	private ElasticSearchProviderRepository elasticSearchProviderRepository;

	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * This util method maps util VO to ES document object.
	 * @param providerDetail
	 * @return ElasticSearchProvider
	 * @throws IOException
	 */
	private ElasticSearchProvider createElasticSeachProduct(ProviderDetail providerDetail) throws IOException{

		return objectMapper.readValue(objectMapper.writeValueAsBytes(providerDetail),
				ElasticSearchProvider.class);

	}


	/**
	 * This method bulk indexes ES documents.
	 * @param list
	 */
	public void indexAll(List<ProviderDetail> list) {

		List<ElasticSearchProvider> elasticSearchProviderList = new LinkedList<>();
		
		try {
			
			for (ProviderDetail providerDetail : list) {
				elasticSearchProviderList.add(createElasticSeachProduct(providerDetail));
			}

			logger.info("Preparing to index list of documents with count : "+elasticSearchProviderList.size());			
			elasticSearchProviderRepository.save(elasticSearchProviderList);

		} catch (Exception e) {
			logger.error("error occured while indexing documents :" + e);
		}
	}

}
