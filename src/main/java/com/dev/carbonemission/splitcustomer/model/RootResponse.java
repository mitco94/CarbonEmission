package com.dev.carbonemission.splitcustomer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)

public class RootResponse
{
    private CustomerKwhInfo data;

    public void setData(CustomerKwhInfo Data){
        this.data = Data;
    }
    public CustomerKwhInfo getData(){
        return this.data;
    }
	@Override
	public String toString() {
		return "RootResponse [Data=" + data + "]";
	}
    
}
