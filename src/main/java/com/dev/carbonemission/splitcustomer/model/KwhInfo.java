package com.dev.carbonemission.splitcustomer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class KwhInfo
{
    private String uom;

    private List<ReadingData> reading;

    public void setUOM(String UOM){
        this.uom = UOM;
    }
    public String getUOM(){
        return this.uom;
    }
    public void setReading(List<ReadingData> Reading){
        this.reading = Reading;
    }
    public List<ReadingData> getReading(){
        return this.reading;
    }
	@Override
	public String toString() {
		return "KwhInfo [UOM=" + uom + ", Reading=" + reading + "]";
	}
}