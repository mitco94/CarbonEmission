package com.dev.carbonemission.model;

public class ConsumptionInformation {
	private SuccessResponse SuccessResponse;

	public SuccessResponse getSuccessResponse() {
		return SuccessResponse;
	}

	public void setSuccessResponse(SuccessResponse SuccessResponse) {
		this.SuccessResponse = SuccessResponse;
	}

	@Override
	public String toString() {
		return "ClassPojo [SuccessResponse = " + SuccessResponse + "]";
	}
}