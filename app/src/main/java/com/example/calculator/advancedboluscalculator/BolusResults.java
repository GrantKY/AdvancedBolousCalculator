package com.example.calculator.advancedboluscalculator;

/**
 * Created by gy185013 on 22/04/2015.
 */
public class BolusResults {

    private double TotalInsulin;
    private String ComboRequired;
    private String SuggestedDuration;
    private double ImmediateInsulinPercentage;
    private double DelayedInsulinPercentage;

//    public BolusResults(double totalinsulin, String comborequired, String suggestedduration,
//                       double immediateinsulin, double delayedinsulin)
//    {
//        TotalInsulin = totalinsulin;
//        ComboRequired = comborequired;
//        SuggestedDuration = suggestedduration;
//        ImmediateInsulin = immediateinsulin;
//        DelayedInsulin = delayedinsulin;
//    }
    public void setTotalInsulin(double value)
    {
        this.TotalInsulin = value;
    }
    public double getTotalInsulin()
    {
        return this.TotalInsulin;
    }

    public String getComboRequired()
    {
        return this.ComboRequired;
    }

    public void setComboRequired(String value)
    {
        this.ComboRequired = value;
    }

    public String getSuggestedDuration()
    {
        return this.SuggestedDuration;
    }
    public void setSuggestedDuration(String value)
    {
        this.SuggestedDuration = value;
    }

    public double getImmediateInsulinPercentage()
    {
        return this.ImmediateInsulinPercentage;
    }

    public void setImmediateInsulinPercentage(double value)
    {
        this.ImmediateInsulinPercentage = value;
    }
    public double getDelayedInsulin()
    {
        return this.DelayedInsulinPercentage;
    }
    public void setDelayedInsulinPercentage(double value)
    {
        this.DelayedInsulinPercentage = value;
    }
}
