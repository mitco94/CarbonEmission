package com.dev.carbonemission.splitcustomer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PreviousUsageData
{
    private String index;

    private double consumptionValue;
    
    private String startTime;
    
    private String endTime;

    public void setIndex(String index){
        this.index = index;
    }
    public String getIndex(){
        return this.index;
    }
    public void setConsumptionValue(double consumptionValue){
        this.consumptionValue = consumptionValue;
    }
    public double getConsumptionValue(){
        return this.consumptionValue;
    }
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
