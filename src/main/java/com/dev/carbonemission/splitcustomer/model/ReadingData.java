package com.dev.carbonemission.splitcustomer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ReadingData
{
    private String readingTimestamp;

    private String readingValue;

    @Override
	public String toString() {
		return "ReadingData [ReadingTimestamp=" + readingTimestamp + ", ReadingValue=" + readingValue + "]";
	}
	public void setReadingTimestamp(String ReadingTimestamp){
        this.readingTimestamp = ReadingTimestamp;
    }
    public String getReadingTimestamp(){
        return this.readingTimestamp;
    }
    public void setReadingValue(String ReadingValue){
        this.readingValue = ReadingValue;
    }
    public String getReadingValue(){
        return this.readingValue;
    }
}
