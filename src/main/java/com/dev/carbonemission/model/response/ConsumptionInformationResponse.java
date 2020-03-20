package com.dev.carbonemission.model.response;

import java.util.List;
public class ConsumptionInformationResponse
{
    private String UOM;

    private List<ResponseReading> Reading;

    public void setUOM(String UOM){
        this.UOM = UOM;
    }
    public String getUOM(){
        return this.UOM;
    }
    public void setReading(List<ResponseReading> Reading){
        this.Reading = Reading;
    }
    public List<ResponseReading> getReading(){
        return this.Reading;
    }
}