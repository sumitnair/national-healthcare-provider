package com.healthcare.provider.util;


/**
 * Value Object class used for CSV conversion
 *
 */
public class ProviderDetail {
	
	private Long id;
	private String drgDefinition;
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
	
	
	public ProviderDetail() {
		super();
	}
	public ProviderDetail(Long id, String drgDefinition, Long providerId, String providerName,
			String providerStreetAddress, String providerCity, String providerState, Integer providerZipCode,
			String hospitalReferralRegionDescription, Long totalDischarges, Double averageCoveredCharges,
			Double averageTotalPayments, Double averageMedicarePayments) {
		super();
		this.id = id;
		this.drgDefinition = drgDefinition;
		this.providerId = providerId;
		this.providerName = providerName;
		this.providerStreetAddress = providerStreetAddress;
		this.providerCity = providerCity;
		this.providerState = providerState;
		this.providerZipCode = providerZipCode;
		this.hospitalReferralRegionDescription = hospitalReferralRegionDescription;
		this.totalDischarges = totalDischarges;
		this.averageCoveredCharges = averageCoveredCharges;
		this.averageTotalPayments = averageTotalPayments;
		this.averageMedicarePayments = averageMedicarePayments;
	}
	
	public String getDrgDefinition() {
		return drgDefinition;
	}
	public void setDrgDefinition(String drgDefinition) {
		this.drgDefinition = drgDefinition;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
