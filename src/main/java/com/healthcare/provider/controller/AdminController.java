package com.healthcare.provider.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.provider.service.ElasticSearchService;
import com.healthcare.provider.util.CSVAdapter;
import com.healthcare.provider.util.ProviderDetail;



/**
 * This controller class is used for indexing Elastic Search document.
 * 
 */
@RestController
public class AdminController {
	
	public static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	CSVAdapter csvAdapter;
	
	@Autowired
	ElasticSearchService elasticsearchService; 
	
	@Transactional
	@RequestMapping(value = "/@index", method = RequestMethod.GET)
	public void index() {
		
		List<ProviderDetail> providerDatalist = null;
		try {
			
			providerDatalist = csvAdapter.getProviderDetails();
			
			if(!providerDatalist.isEmpty()) {
				elasticsearchService.indexAll(providerDatalist);
				logger.info("Indexing complete");
			}
			
		} catch (Exception ex) {
			logger.error("Indexing failed !! :"+ex);
		}
	}
}
