package com.dev.carbonemission.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties.Strategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.carbonemission.model.CustomerConsumptionInfoModel;
import com.dev.carbonemission.services.CarbonService;
import com.dev.carbonemission.splitcustomer.model.RootData;
import com.dev.carbonemission.splitcustomer.model.RootResponse;
import com.google.gson.Gson;

@RestController
@RequestMapping("/")

public class CarbonController {

	@Autowired
	CarbonService carbonService;

	@GetMapping("/customers/{customerId}")
	@ResponseBody()
	public ResponseEntity<?> halfHourlyConsumptionDetails(@PathVariable String customerId) {

		JSONParser jsonParser = new JSONParser();
		Object obj = null;
		String result = null;
		CustomerConsumptionInfoModel customerConsumptionInfoModel = new CustomerConsumptionInfoModel();
		try (FileReader fileReader = new FileReader("CustomerConsumptionDetailsMock1.json")) {
			obj = jsonParser.parse(fileReader);
			String jsonInput = obj.toString();
			Gson gson = new Gson();
			customerConsumptionInfoModel = gson.fromJson(jsonInput, CustomerConsumptionInfoModel.class);
			System.out.println("fuisrt dtauitsiug---->" + customerConsumptionInfoModel);
			result = carbonService.getConsumptionDetails(customerConsumptionInfoModel);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (result != null) {
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(result, HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/split/customers/{customerId}")
	@ResponseBody()
	public ResponseEntity<String> splitCustomer(@PathVariable String customerId) throws FileNotFoundException {

		JSONParser jsonParser = new JSONParser();
		Object obj = null;
		List<FileReader> fileList = new ArrayList<FileReader>();
		double averageConsumptionValue = 0;
		double consumptionValueInRegion = 0;
		int noOfCustomers = 0;
		int averageConsumptionValueInRegion = 0;
		long startTime = -19800000;
		long endTime = -10800000;
		FileReader fileReader = new FileReader("CustomerConsumptionDetailsResponse.json");
		FileReader fileReader2 = new FileReader("CustomerConsumptionDetailsResponse2.json");
		FileReader fileReader3 = new FileReader("CustomerConsumptionDetailsResponse3.json");
		FileReader fileReader4 = new FileReader("CustomerConsumptionDetailsResponse4.json");
		FileReader fileReader5 = new FileReader("CustomerConsumptionDetailsResponse5.json");
		FileReader fileReader6 = new FileReader("CustomerConsumptionDetailsResponse6.json");

		fileList.add(fileReader);
		fileList.add(fileReader2);
		fileList.add(fileReader3);
		fileList.add(fileReader4);
		fileList.add(fileReader5);
		fileList.add(fileReader6);

		int NumberOfCustomers = fileList.size();
		for (int i = 0; i < NumberOfCustomers; i++) {
			FileReader file = fileList.get(i);
			try {
				System.out.println("file------->" + file);
				obj = jsonParser.parse(file);
				String jsonInput = obj.toString();
				System.out.println("jsonoutput------->" + jsonInput);
				Gson gson = new Gson();
				RootResponse rootResponse = new RootResponse();
				rootResponse = gson.fromJson(jsonInput, RootResponse.class);
				averageConsumptionValue = carbonService.calculateAverageConsumption(startTime, endTime, rootResponse);
				consumptionValueInRegion = consumptionValueInRegion + averageConsumptionValue;
				noOfCustomers++;
				System.out.println(averageConsumptionValue);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		averageConsumptionValueInRegion = (int) consumptionValueInRegion;
		int res = averageConsumptionValueInRegion / noOfCustomers;
		// this method is to find whether the user is consuming high or low carbon
		FileReader fileReaderResult = new FileReader("CustomerConsumptionDetailsResponse.json");
		Object obj1 = null;
		double singleCustomerAverageConsumptionValue = 0;
		String index = null;

		try {
			obj1 = jsonParser.parse(fileReaderResult);
			String jsonInput1 = obj1.toString();
			System.out.println("jsonoutput------->" + jsonInput1);
			Gson gson1 = new Gson();
			RootResponse rootResponse1 = new RootResponse();
			rootResponse1 = gson1.fromJson(jsonInput1, RootResponse.class);
			singleCustomerAverageConsumptionValue = carbonService.calculateAverageConsumption(startTime, endTime,
					rootResponse1);

			if (singleCustomerAverageConsumptionValue > averageConsumptionValueInRegion) {
				index = "high";
				System.out.println("CustomerUsingHighIntensity");
			} else {
				index = "low";
				System.out.println("CustomerUsingLowIntensity");
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

//		String starttime = convertMillisecondsToHour(startTime);
//		String endtime =  convertMillisecondsToHour(endTime);
		String starttime= "2020-03-12T00:00Z";
		String endtime = "2020-03-12T02:30Z";
		String customerPreviousUsageResponse = carbonService.previousCustomerUsageResponse(index,
				singleCustomerAverageConsumptionValue, starttime, endtime);
		System.out.println("Csutomer repsonse ----->" + customerPreviousUsageResponse);
		if (customerPreviousUsageResponse != null) {
			return new ResponseEntity<String>(customerPreviousUsageResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(customerPreviousUsageResponse, HttpStatus.CONFLICT);
		}
	}

//	private String convertMillisecondsToHour(long millis) { 
//		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
//            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
//            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
//		return hms;
//	}
}
