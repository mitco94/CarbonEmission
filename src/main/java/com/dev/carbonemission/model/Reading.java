package com.dev.carbonemission.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Reading
{
    private String ReadingTimestamp;

    private String RegisterId;

    private String ReadingValue;

    public String getReadingTimestamp ()
    {
        return ReadingTimestamp;
    }

    public void setReadingTimestamp (String ReadingTimestamp)
    {
        this.ReadingTimestamp = ReadingTimestamp;
    }

    public String getRegisterId ()
    {
        return RegisterId;
    }

    public void setRegisterId (String RegisterId)
    {
        this.RegisterId = RegisterId;
    }

    public String getReadingValue ()
    {
        return ReadingValue;
    }

    public void setReadingValue (String ReadingValue)
    {
        this.ReadingValue = ReadingValue;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ReadingTimestamp = "+ReadingTimestamp+", RegisterId = "+RegisterId+", ReadingValue = "+ReadingValue+"]";
    }
}