package com.dev.carbonemission.splitcustomer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class RootData
{
    private List<PreviousUsageData> previousUsageData;

    public void setPreviousUsageData(List<PreviousUsageData> previousUsageData){
        this.previousUsageData = previousUsageData;
    }
    public List<PreviousUsageData> getPreviousUsageData(){
        return this.previousUsageData;
    }
	
}
