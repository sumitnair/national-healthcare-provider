package com.healthcare.provider.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.healthcare.provider.config.ElasticSearchConfig;
import com.healthcare.provider.util.Constants;

@Document(indexName = ElasticSearchConfig.INDEX, type = Constants.PROVIDER)
public class ElasticSearchProvider {

	@Id
	private String id;
	private Long providerId;
	private String providerName;
	private String providerStreetAddress;
	private String providerCity;
	private String providerState;
	private Integer providerZipCode;
	private String hospitalReferralRegionDescription;
	private Long totalDischarges;
	private Double averageCoveredCharges;
	private Double averageTotalPayments;
	private Double averageMedicarePayments;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getProviderId() {
		return providerId;
	}
	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderStreetAddress() {
		return providerStreetAddress;
	}
	public void setProviderStreetAddress(String providerStreetAddress) {
		this.providerStreetAddress = providerStreetAddress;
	}
	public String getProviderCity() {
		return providerCity;
	}
	public void setProviderCity(String providerCity) {
		this.providerCity = providerCity;
	}
	public String getProviderState() {
		return providerState;
	}
	public void setProviderState(String providerState) {
		this.providerState = providerState;
	}
	public Integer getProviderZipCode() {
		return providerZipCode;
	}
	public void setProviderZipCode(Integer providerZipCode) {
		this.providerZipCode = providerZipCode;
	}
	public String getHospitalReferralRegionDescription() {
		return hospitalReferralRegionDescription;
	}
	public void setHospitalReferralRegionDescription(String hospitalReferralRegionDescription) {
		this.hospitalReferralRegionDescription = hospitalReferralRegionDescription;
	}
	public Long getTotalDischarges() {
		return totalDischarges;
	}
	public void setTotalDischarges(Long totalDischarges) {
		this.totalDischarges = totalDischarges;
	}
	public Double getAverageCoveredCharges() {
		return averageCoveredCharges;
	}
	public void setAverageCoveredCharges(Double averageCoveredCharges) {
		this.averageCoveredCharges = averageCoveredCharges;
	}
	public Double getAverageTotalPayments() {
		return averageTotalPayments;
	}
	public void setAverageTotalPayments(Double averageTotalPayments) {
		this.averageTotalPayments = averageTotalPayments;
	}
	public Double getAverageMedicarePayments() {
		return averageMedicarePayments;
	}
	public void setAverageMedicarePayments(Double averageMedicarePayments) {
		this.averageMedicarePayments = averageMedicarePayments;
	}
	
	
}
