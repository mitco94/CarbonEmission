package com.dev.carbonemission.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SuccessResponse
{
    private Channel Channel;

    private String GUID;

    private String MSN;

    public Channel getChannel ()
    {
        return Channel;
    }

    public void setChannel (Channel Channel)
    {
        this.Channel = Channel;
    }

    public String getGUID ()
    {
        return GUID;
    }

    public void setGUID (String GUID)
    {
        this.GUID = GUID;
    }

    public String getMSN ()
    {
        return MSN;
    }

    public void setMSN (String MSN)
    {
        this.MSN = MSN;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Channel = "+Channel+", GUID = "+GUID+", MSN = "+MSN+"]";
    }
}
