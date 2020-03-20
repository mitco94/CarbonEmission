package com.dev.carbonemission.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

	public long convertTimeToMillisecondsFromCarbonIntensity(String dateAndTime) {
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'", Locale.ENGLISH);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
		LocalTime time = LocalTime.parse("2018-05-15T23:30Z", inputFormatter);
		String formattedTime = outputFormatter.format(time);
		System.out.println(formattedTime);
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = null;
		try {
			date1 = format.parse(formattedTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date1.getTime());
		return date1.getTime();
	}
	
	public long convertTimeToMillisecondsFromConsumption(String dateAndTime) {
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);
		LocalTime time = LocalTime.parse(dateAndTime, inputFormatter);
		String formattedTime = outputFormatter.format(time);
		System.out.println(formattedTime);
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = null;
		try {
			date1 = format.parse(formattedTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date1.getTime());
		return date1.getTime();
	}
	
}
