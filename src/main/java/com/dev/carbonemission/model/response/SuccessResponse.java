package com.dev.carbonemission.model.response;

public class SuccessResponse
{
    private CustomerConsumptionInfoResponse Data;

    public void setData(CustomerConsumptionInfoResponse Data){
        this.Data = Data;
    }
    public CustomerConsumptionInfoResponse getData(){
        return this.Data;
    }
	@Override
	public String toString() {
		return "Root [Data=" + Data + "]";
	}
    
}
