package com.healthcare.provider.util;

public class ProviderFilterBean {

	public ProviderFilterBean() {
		super();
	}
	public ProviderFilterBean(String maxDischarges, String minDischarges, String maxAvgCoveredCharges,
			String minAvgCoveredCharges, String maxAvgMedicarePayments, String minAvgMedicarePayments, String state,
			String[] fields) {
		super();
		this.maxDischarges = maxDischarges;
		this.minDischarges = minDischarges;
		this.maxAvgCoveredCharges = maxAvgCoveredCharges;
		this.minAvgCoveredCharges = minAvgCoveredCharges;
		this.maxAvgMedicarePayments = maxAvgMedicarePayments;
		this.minAvgMedicarePayments = minAvgMedicarePayments;
		this.state = state;
		this.fields = fields;
	}
	public String getMaxDischarges() {
		return maxDischarges;
	}
	public void setMaxDischarges(String maxDischarges) {
		this.maxDischarges = maxDischarges;
	}
	public String getMinDischarges() {
		return minDischarges;
	}
	public void setMinDischarges(String minDischarges) {
		this.minDischarges = minDischarges;
	}
	public String getMaxAvgCoveredCharges() {
		return maxAvgCoveredCharges;
	}
	public void setMaxAvgCoveredCharges(String maxAvgCoveredCharges) {
		this.maxAvgCoveredCharges = maxAvgCoveredCharges;
	}
	public String getMinAvgCoveredCharges() {
		return minAvgCoveredCharges;
	}
	public void setMinAvgCoveredCharges(String minAvgCoveredCharges) {
		this.minAvgCoveredCharges = minAvgCoveredCharges;
	}
	public String getMaxAvgMedicarePayments() {
		return maxAvgMedicarePayments;
	}
	public void setMaxAvgMedicarePayments(String maxAvgMedicarePayments) {
		this.maxAvgMedicarePayments = maxAvgMedicarePayments;
	}
	public String getMinAvgMedicarePayments() {
		return minAvgMedicarePayments;
	}
	public void setMinAvgMedicarePayments(String minAvgMedicarePayments) {
		this.minAvgMedicarePayments = minAvgMedicarePayments;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}

	private String maxDischarges;
	private String minDischarges;
	private String maxAvgCoveredCharges;
	private String minAvgCoveredCharges;
	private String maxAvgMedicarePayments;
	private String minAvgMedicarePayments;
	private String state;
	private String[] fields;

	
}
