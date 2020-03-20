package com.dev.carbonemission.splitcustomer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerKwhInfo
{
    private String guid;

    private String msn;

    private KwhInfo channel;

    public void setGUID(String GUID){
        this.guid = GUID;
    }
    public String getGUID(){
        return this.guid;
    }
    @Override
	public String toString() {
		return "CustomerKwhInfo [GUID=" + guid + ", MSN=" + msn + ", Channel=" + channel + "]";
	}
	public void setMSN(String MSN){
        this.msn = MSN;
    }
    public String getMSN(){
        return this.msn;
    }
    public void setChannel(KwhInfo Channel){
        this.channel = Channel;
    }
    public KwhInfo getChannel(){
        return this.channel;
    }
}