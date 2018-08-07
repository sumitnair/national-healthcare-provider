package com.healthcare.provider.controller;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.healthcare.provider.elasticsearch.ElasticSearchProvider;
import com.healthcare.provider.service.ProviderSearchService;
import com.healthcare.provider.util.ProviderFilterBean;


/**
 * This is Main controller class which exposes REST Endpoint for querying 
 *
 */
@RestController
public class ProviderController {

	public static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

	@Autowired private ProviderSearchService providerSearchService;
	
	@GetMapping(value = "/providers",produces="application/json")
	@ResponseBody
	public ResponseEntity<List<?>> searchProviderDetails(
			@RequestParam(value = "max_discharges", required = false) String maxDischarges,
			@RequestParam(value = "min_discharges", required = false) String minDischarges,
			@RequestParam(value = "max_average_covered_charges", required = false) String maxAvgCoveredCharges,
			@RequestParam(value = "min_average_covered_charges", required = false) String minAvgCoveredCharges,
			@RequestParam(value = "max_average_medicare_payments", required = false) String maxAvgMedicarePayments,
			@RequestParam(value = "min_average_medicare_payments", required = false) String minAvgMedicarePayments,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "fields", required = false) String[] fields) {
				
		HttpStatus status = HttpStatus.OK;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		ProviderFilterBean filter = new ProviderFilterBean(maxDischarges, minDischarges, maxAvgCoveredCharges, minAvgCoveredCharges,
				maxAvgMedicarePayments, minAvgMedicarePayments, state, fields);
		
		if(null == filter.getFields()) {
			
			logger.info("searching for results with all the fields");
			List<ElasticSearchProvider> providerDetails = providerSearchService.searchProviderDetails(filter);	
			if(providerDetails.isEmpty()){
				status = HttpStatus.NO_CONTENT;
				logger.info("No results found");
			}
			return new ResponseEntity<>(providerDetails, httpHeaders, status);
		}else {
			
			logger.info("searching for fields :" + Arrays.toString(fields));

			List<JsonNode> providerDetails = providerSearchService.searchProviderDetailsWithRequiredFields(filter);	
			if(providerDetails.isEmpty()){
				status = HttpStatus.NO_CONTENT;
				logger.info("No results found");
			}
			return new ResponseEntity<>(providerDetails, httpHeaders, status);
		}
		
	}

	
}