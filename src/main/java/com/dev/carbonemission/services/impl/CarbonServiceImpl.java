package com.dev.carbonemission.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.carbonemission.model.CustomerConsumptionInfoModel;
import com.dev.carbonemission.model.Reading;
import com.dev.carbonemission.model.response.ConsumptionInformationResponse;
import com.dev.carbonemission.model.response.CustomerConsumptionInfoResponse;
import com.dev.carbonemission.model.response.ResponseReading;
import com.dev.carbonemission.model.response.SuccessResponse;
import com.dev.carbonemission.services.CarbonService;
import com.dev.carbonemission.splitcustomer.model.PreviousUsageData;
import com.dev.carbonemission.splitcustomer.model.ReadingData;
import com.dev.carbonemission.splitcustomer.model.RootData;
import com.dev.carbonemission.splitcustomer.model.RootResponse;
import com.dev.carbonemission.utility.UtilityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarbonServiceImpl implements CarbonService {

	@Autowired
	UtilityService utiltiService;

	SuccessResponse root = new SuccessResponse();
	RootData rootData = new RootData();
	CustomerConsumptionInfoResponse data = new CustomerConsumptionInfoResponse();
	ConsumptionInformationResponse channel;
	ResponseReading[] ResponseReading;
	PreviousUsageData previousUsageData;

	@Override
	public String getConsumptionDetails(CustomerConsumptionInfoModel customerConsumptionInfoModel) {

		List<ResponseReading> responseReadingList = new ArrayList<ResponseReading>();
		Reading[] requestReadingList = customerConsumptionInfoModel.getConsumptionInformation().getSuccessResponse()
				.getChannel().getReading();
		LinkedHashMap<String, String> hmap = new LinkedHashMap<String, String>();
		for (int i = 0; i < requestReadingList.length - 1; i++) {
			Reading reading = requestReadingList[i];
			String halfAndHourActualConsumption = calculateHalfAndHourConsumption(requestReadingList, i);
			hmap.put(reading.getReadingTimestamp(), halfAndHourActualConsumption);
		}
		map(hmap, responseReadingList);
		String result = createResponse(customerConsumptionInfoModel, responseReadingList);

		return result;

	}

	private String createResponse(CustomerConsumptionInfoModel customerConsumptionInfoModel,
			List<com.dev.carbonemission.model.response.ResponseReading> responseReadingList) {
		String guid = customerConsumptionInfoModel.getConsumptionInformation().getSuccessResponse().getGUID();
		String msnValue = customerConsumptionInfoModel.getConsumptionInformation().getSuccessResponse().getMSN();
		String uomValue = customerConsumptionInfoModel.getConsumptionInformation().getSuccessResponse().getChannel()
				.getUOM();
		channel = new ConsumptionInformationResponse();
		channel.setReading(responseReadingList);
		channel.setUOM(uomValue);
		data.setGUID(guid);
		data.setMSN(msnValue);
		data.setChannel(channel);
		root.setData(data);
		String resultJson = writeJsonObject(root);
		return resultJson;
	}

	private String writeJsonObject(SuccessResponse root) {
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = null;
		try {
			jsonStr = Obj.writeValueAsString(root);
			System.out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	private void map(HashMap<String, String> hmap, List<ResponseReading> responseReadingList) {
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
			ResponseReading responseReading = new ResponseReading();
			responseReading.setReadingTimestamp(entry.getKey());
			responseReading.setReadingValue(entry.getValue());
			responseReadingList.add(responseReading);
		}
	}

	private String calculateHalfAndHourConsumption(Reading[] list, int i) {
		Reading firstValue = list[i];
		Reading secondValue = list[i + 1];
		String first = firstValue.getReadingValue();
		String second = secondValue.getReadingValue();
		Double firstNumber = Double.parseDouble(first);
		Double secondNumber = Double.parseDouble(second);
		Double difference = secondNumber - firstNumber;
		String result = difference.toString();
		return result;
	}

	@Override
	public double calculateAverageConsumption(long startTime, long endTime, RootResponse rootResponse) {
		List<ReadingData> reading = rootResponse.getData().getChannel().getReading();
		double totalConsumptionValue = 0;
		double avgConsumptionValue = 0;
		int count = 0;
		int i;
		boolean flag = false;
		for (i = 0; i < reading.size(); i++) {
//			avgConsumptionValue =0;
//			count =0;
//			totalConsumptionValue =0;
			ReadingData firstReading = reading.get(i);
			String firstTimeFromConsumption = firstReading.getReadingTimestamp();
			long millisecFromConsumption = utiltiService
					.convertTimeToMillisecondsFromConsumption(firstTimeFromConsumption);

			System.out.println("start time----->" + startTime);
			System.out.println("End time------->" + endTime);
			System.out.println("mmillisecFromConsumption------>" + millisecFromConsumption);
			if (millisecFromConsumption == startTime) {
				for (int j = i; j < reading.size() - i; j++) {
					ReadingData lastReading = reading.get(j);
					String LastTimeFromConsumption = lastReading.getReadingTimestamp();
					long LastMillisecFromConsumption = utiltiService
							.convertTimeToMillisecondsFromConsumption(LastTimeFromConsumption);
					System.out.println("LastMillisecFromConsumption------>" + LastMillisecFromConsumption);
					String consumptionValue = lastReading.getReadingValue();
					double intConsumptionValue = Double.parseDouble(consumptionValue);
					totalConsumptionValue = totalConsumptionValue + intConsumptionValue;
					count++;
					if (LastMillisecFromConsumption == endTime) {
						i = j;
						flag = true;
						break;
					}
				}

			}
			if (flag) {
				break;
			}
		}
		try {
			avgConsumptionValue = (totalConsumptionValue / count);
		} catch (Exception e) {

		}

		System.out.println("Average consumption value ------->" + avgConsumptionValue);
		return avgConsumptionValue;
	}


	@Override
	public String previousCustomerUsageResponse(String index, double singleCustomerAverageConsumptionValue,
			String starttime, String endtime) {
		System.out.println("Entered previousCustomerUsageResponse block");
		previousUsageData = new PreviousUsageData();
		previousUsageData.setConsumptionValue(singleCustomerAverageConsumptionValue);
		previousUsageData.setIndex(index);
		previousUsageData.setStartTime(starttime);
		previousUsageData.setEndTime(endtime);
		List<PreviousUsageData> previousUsageList = new ArrayList<PreviousUsageData>();
		previousUsageList.add(previousUsageData);
		rootData.setPreviousUsageData(previousUsageList);
		ObjectMapper Obj = new ObjectMapper();
		String resultJson = null;
		try {
			resultJson = Obj.writeValueAsString(rootData);
			System.out.println(resultJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resultJson;
	}

}
