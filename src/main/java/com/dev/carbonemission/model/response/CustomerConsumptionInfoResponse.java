package com.dev.carbonemission.model.response;

public class CustomerConsumptionInfoResponse
{
    private String GUID;

    private String MSN;

    private ConsumptionInformationResponse Channel;

    public void setGUID(String GUID){
        this.GUID = GUID;
    }
    public String getGUID(){
        return this.GUID;
    }
    public void setMSN(String MSN){
        this.MSN = MSN;
    }
    public String getMSN(){
        return this.MSN;
    }
    public void setChannel(ConsumptionInformationResponse Channel){
        this.Channel = Channel;
    }
    public ConsumptionInformationResponse getChannel(){
        return this.Channel;
    }
}