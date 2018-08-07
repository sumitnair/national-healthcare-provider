package com.healthcare.provider.elasticsearch;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ElasticSearchProviderRepository extends PagingAndSortingRepository<ElasticSearchProvider, String> {

}
