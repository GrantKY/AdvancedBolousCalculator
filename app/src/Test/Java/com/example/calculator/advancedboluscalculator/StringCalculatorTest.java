package com.example.calculator.advancedboluscalculator;

import junit.framework.TestCase;

public class StringCalculatorTest extends TestCase {

    public void testResult() {

        StringCalculator calculator = new StringCalculator();

        assertEquals(0.0, calculator.Result("0 0 0 "));
        assertEquals(6.0, calculator.Result("1 2 3"));
        assertEquals(6.0, calculator.Result("1+2+3"));
        assertEquals(6.6, calculator.Result("1.2 + 2.3 + 3.1 "));
    }

    public void testResult_InvalidValue()
    {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0.0, calculator.Result("1.2 + 2.....3 + 3.1 "));

        assertEquals("Invalid format", calculator.getMessage());

    }
}