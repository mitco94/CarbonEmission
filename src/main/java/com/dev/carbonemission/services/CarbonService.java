package com.dev.carbonemission.services;

import com.dev.carbonemission.model.CustomerConsumptionInfoModel;
import com.dev.carbonemission.splitcustomer.model.RootResponse;

public interface CarbonService {

	public String getConsumptionDetails(CustomerConsumptionInfoModel customerConsumptionInfoModel);

	public double calculateAverageConsumption(long startTime, long endTime, RootResponse rootResponse);


	public String previousCustomerUsageResponse(String index, double singleCustomerAverageConsumptionValue,
			String starttime, String endtime);
}

