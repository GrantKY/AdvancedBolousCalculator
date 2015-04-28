/**
 * Created by gy185013 on 22/04/2015.
 */
package com.example.calculator.advancedboluscalculator;

public class BolusCalculator {

    private double Carbs;
    private double Fat;
    private double Protein;
    private double CarbRatio;
    public BolusCalculator(double carbs, double fat, double protein, double carbratio)
    {
        Carbs  = carbs;
        Fat = fat;
        Protein = protein;
        CarbRatio = carbratio;
    }

    public BolusResults GetResults()
    {
         // Carb Bolus
        double Carb_Bolus = CalculateCarbBolus(Carbs, CarbRatio);

        // Protein Bolus
        double Protein_Bolus = CalculateProteinBolus(Protein, CarbRatio);

        // Fat Bolus
        double Fat_Bolus =  CalculateFatBolus(Fat, CarbRatio);

        // Total Fat and protein calories
        double Total_Fat_Protein = TotalFatAndProtein(Protein, Fat);

        // Total Insulin
        double Total_Insulin = this.TotalInsulin(Carb_Bolus,Protein_Bolus, Fat_Bolus);

        // Immediate insulin
        double immediateinsulinpercentage = this.CalculatePrecentage(Carb_Bolus,Total_Insulin);

        // Delayed  Insulin
        double delayedinsulinpercentage = this.CalculatePrecentage((Protein_Bolus + Fat_Bolus),Total_Insulin);

        // Set Values
        BolusResults result =  new BolusResults();
        result.setSuggestedDuration(this.SuggestedDuration((Total_Fat_Protein)));
        result.setComboRequired(this.ComboRequired(Total_Fat_Protein)) ;
        result.setTotalInsulin(Total_Insulin);
        if(this.ComboRequired(Total_Fat_Protein).toLowerCase().contains("no"))
        {
            immediateinsulinpercentage = 100;
            delayedinsulinpercentage = 0;
        }
        result.setImmediateInsulinPercentage(immediateinsulinpercentage);
        result.setDelayedInsulinPercentage(delayedinsulinpercentage);

        return result;
    }

    protected double CalculatePrecentage(double value, double total) {

        return value/total * 100;
    }

    protected String ComboRequired(double total_fat_protein)
    {
        return (total_fat_protein <=100.00) ?  "No": "Yes";
    }
    protected double CalculateCarbBolus(double carbs, double carbratio)
    {
        return  carbs/ carbratio;
    }
    protected double CalculateProteinBolus(double protein, double carbratio)
    {
        return (protein * 4)/ 100 * (10/carbratio);
    }
    protected double CalculateFatBolus(double fat, double carbratio)
    {
        return (fat * 9)/100 *(10/ carbratio);
    }

    private double TotalInsulin(double carb_bolus, double protein_bolus, double fat_bolus)
    {
        return carb_bolus + protein_bolus +  fat_bolus;
    }

    private double TotalFatAndProtein(double Protein, double Fat)
    {
        return  Protein * 4 + Fat* 9;
    }

    protected String SuggestedDuration(double Total_Fat_Protein)
    {
        if (Total_Fat_Protein <= 99) {
            return "No Combo";
        } else if (Total_Fat_Protein <= 199) {
            return "3 Hours";
        } else if (Total_Fat_Protein <= 299) {
            return "4 Hours";
        } else if (Total_Fat_Protein <= 399) {
            return "5 Hours";
        } else {
            return "8 Hours";
        }
    }

}
