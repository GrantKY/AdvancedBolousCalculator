/**
 * Created by gy185013 on 25/04/2015.
 */
package com.example.calculator.advancedboluscalculator;
public class StringCalculator {

    private String message = "";

    public String getMessage()
    {
        return message;
    }

    double Result(String currentString)
    {
        String newstring = currentString;

        try {
            newstring = currentString.replaceAll("\\s+$", "");
            if (newstring.contains("+")) {

                newstring = newstring.replace("+", " ");
            }
            String[] split = newstring.split("\\s+");

            double sum = 0.0;

            for (String s : split) {

                sum += Double.parseDouble(s);
            }

            return sum;
        }
        catch(NumberFormatException e )
        {
            message = "Invalid format";
        }
        return 0;
    }
}
