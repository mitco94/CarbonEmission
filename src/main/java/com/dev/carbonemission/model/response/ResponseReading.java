package com.dev.carbonemission.model.response;

public class ResponseReading
{
    private String ReadingTimestamp;

    private String ReadingValue;

    public void setReadingTimestamp(String ReadingTimestamp){
        this.ReadingTimestamp = ReadingTimestamp;
    }
    public String getReadingTimestamp(){
        return this.ReadingTimestamp;
    }
    public void setReadingValue(String ReadingValue){
        this.ReadingValue = ReadingValue;
    }
    public String getReadingValue(){
        return this.ReadingValue;
    }
}
