package com.example.calculator.advancedboluscalculator;
import junit.framework.TestCase;

public class BolusCalculatorTest extends TestCase {

    double Delta = 0.0001;

    public void testCalculateCarbBolus() {

        BolusCalculator calculator = new BolusCalculator(1,1,1, 1);

        assertEquals(1.0, calculator.CalculateCarbBolus(1,1));
        assertEquals(6.0, calculator.CalculateCarbBolus(12,2));
        assertEquals(0.5, calculator.CalculateCarbBolus(6,12));
    }
    public void testGetResults() throws Exception {

        BolusCalculator calculator = new BolusCalculator(25,21,16, 22);

        BolusResults results = calculator.GetResults();

        assertEquals(2.2863, results.getTotalInsulin(), Delta);
        assertEquals("Yes", results.getComboRequired());
        assertEquals("4 Hours", results.getSuggestedDuration());
        assertEquals(49.701789, results.getImmediateInsulinPercentage(), Delta);
        assertEquals(50.2982, results.getDelayedInsulin(), Delta);

    }

    public void testCalculatePrecentage() throws Exception {

        BolusCalculator calculator = new BolusCalculator(1,1,1, 1);
        assertEquals(50.0, calculator.CalculatePrecentage(5,10));
        assertEquals(5.0, calculator.CalculatePrecentage(5,100));
    }

    public void testComboRequired() throws Exception {

        BolusCalculator calculator = new BolusCalculator(1,1,1, 1);
        assertEquals("No", calculator.ComboRequired(50.0));
        assertEquals("No", calculator.ComboRequired(100.0));
        assertEquals("Yes", calculator.ComboRequired(100.1));
    }

    public void testCalculateProteinBolus() throws Exception {

        BolusCalculator calculator = new BolusCalculator(1,1,1, 1);
        assertEquals(0.21818, calculator.CalculateProteinBolus(12,22), Delta);
        assertEquals(0.48, calculator.CalculateProteinBolus(12,10), Delta);
    }

    public void testCalculateFatBolus() throws Exception {

        BolusCalculator calculator = new BolusCalculator(1,1,1, 1);
        assertEquals(0.4909, calculator.CalculateFatBolus(12,22), Delta);
    }
    public void testSuggestedDuration() throws Exception{

        BolusCalculator calculator = new BolusCalculator(1,1,1, 1);

        assertEquals("No Combo", calculator.SuggestedDuration(2));
        assertEquals("No Combo", calculator.SuggestedDuration(99));
        assertEquals("3 Hours", calculator.SuggestedDuration(199));
        assertEquals("4 Hours", calculator.SuggestedDuration(299));
        assertEquals("5 Hours", calculator.SuggestedDuration(399));
        assertEquals("8 Hours", calculator.SuggestedDuration(500));


    }
}