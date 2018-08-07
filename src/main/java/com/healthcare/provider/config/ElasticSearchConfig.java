package com.healthcare.provider.config;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.core.DefaultEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableElasticsearchRepositories("com.mindsmash.elasticsearch")
public class ElasticSearchConfig {

	public static final String INDEX = "healthcare";

	@Autowired
	private Client client;

	@Autowired
	private ObjectMapper objectMapper;

	@Profile("!development")
	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() {
		return new ElasticsearchTemplate(client, new EntityMapperImpl());
	}

	protected ObjectMapper createMapperForES() {
		ObjectMapper clone = objectMapper.copy();
		clone.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		clone.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		return clone;
	}

	public class EntityMapperImpl extends DefaultEntityMapper {

		ObjectMapper esMapper = createMapperForES();

		@Override
		public String mapToString(Object object) throws IOException {
			return esMapper.writeValueAsString(object);
		}

		@Override
		public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
			return esMapper.readValue(source, clazz);
		}

	}
}