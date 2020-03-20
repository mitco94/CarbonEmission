package com.dev.carbonemission.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Channel
{
    private String UOM;

    private Reading[] Reading;

    private String Name;

    public String getUOM ()
    {
        return UOM;
    }

    public void setUOM (String UOM)
    {
        this.UOM = UOM;
    }

    public Reading[] getReading ()
    {
        return Reading;
    }

    public void setReading (Reading[] Reading)
    {
        this.Reading = Reading;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UOM = "+UOM+", Reading = "+Reading+", Name = "+Name+"]";
    }
}