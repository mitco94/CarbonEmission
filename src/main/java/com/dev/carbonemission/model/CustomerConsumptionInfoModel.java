package com.dev.carbonemission.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerConsumptionInfoModel {
	private ConsumptionInformation ConsumptionInformation;

	public ConsumptionInformation getConsumptionInformation() {
		return ConsumptionInformation;
	}

	public void setConsumptionInformation(ConsumptionInformation ConsumptionInformation) {
		this.ConsumptionInformation = ConsumptionInformation;
	}

	@Override
	public String toString() {
		return "ClassPojo [ConsumptionInformation = " + ConsumptionInformation + "]";
	}
}
