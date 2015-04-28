package com.example.calculator.advancedboluscalculator;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetCarbRatioTextBox();
    }


    private void SetCarbRatioTextBox()
    {
        WriteToTextBox(R.id.txtCarbRatio , GetCarbRatio(), 1, "#" );
    }
    private double GetCarbRatio() {

        return 22;
    }

    private boolean UpdateContents(TextView v) {

        com.example.calculator.advancedboluscalculator.StringCalculator calculator = new com.example.calculator.advancedboluscalculator.StringCalculator();
        String currentstring = v.getText().toString();
        double result = calculator.Result(currentstring);
        String newstring = Double.toString(result);
        v.setText(newstring);

        if(calculator.getMessage().length() > 0) {

            Toast.makeText(this,calculator.getMessage(),Toast.LENGTH_SHORT).show();
            v.requestFocus();
            return false;
        }
        return true;
    }
    private void ResetAllResults()
    {
        WriteToTextBox(R.id.txtTotalInsulin, "N/A" );
        WriteToTextBox(R.id.txtComboRequired,"N/A" );
        WriteToTextBox(R.id.txtImmediateInsulinPercentage , "N/A"  );
        WriteToTextBox(R.id.txtDelayedInsulinPercentage ,"N/A"  );
        WriteToTextBox(R.id.txtSuggestedDuration, "N/A");

    }
    private void SetScrolling(TextView v)
    {
        // v.setMovementMethod(new ScrollingMovementMethod());
    }
    public void butCalculate(View v) {

        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);


        Double protein, carbs, fat, TotalFatAndProtein;

        TextView txtCarbs = (TextView)findViewById(R.id.txtCarbs);
        TextView txtprotein = (TextView)findViewById(R.id.txtProtein);
        TextView txtFat = (TextView)findViewById(R.id.txtFat);
        EditText txtCarbRatio = (EditText)findViewById(R.id.txtCarbRatio);
        //  EditText txtresult = (EditText)findViewById(R.id.txtResult);

        // Update Contents
        if(!UpdateContents(txtCarbs)) {
            ResetAllResults();
            return;
        }
        if(!UpdateContents(txtprotein)){
            ResetAllResults();
            return;
        }

        if(!UpdateContents(txtFat)){
            ResetAllResults();
            return;
        }

        protein= ConvertTextViewToDouble(txtprotein);
        carbs= ConvertTextViewToDouble(txtCarbs);
        fat= ConvertTextViewToDouble(txtFat);

        double carbratio = ConvertTextViewToDouble((txtCarbRatio));

        BolusCalculator calculator = new BolusCalculator(carbs,fat, protein, carbratio);
        BolusResults results = calculator.GetResults();

        // Write Data
        //  EditText txtTotalInsulin = (EditText)findViewById(R.id.txtTotalInsulin);
        WriteToTextBox(R.id.txtTotalInsulin, results.getTotalInsulin(), 0.05, "#.00" );
        WriteToTextBox(R.id.txtComboRequired, results.getComboRequired() );
        WriteToTextBox(R.id.txtImmediateInsulinPercentage , results.getImmediateInsulinPercentage(), 5, "#", "%" );
        WriteToTextBox(R.id.txtDelayedInsulinPercentage , results.getDelayedInsulin(), 5, "#", "%" );
        WriteToTextBox(R.id.txtSuggestedDuration, results.getSuggestedDuration());
    }
    private void WriteToTextBox(int id, double valuetowrite, double rounding, String DecimaFormat, String Suffix) {

        TextView edittext = (TextView)findViewById(id);
        DecimalFormat df = new DecimalFormat(DecimaFormat);
        double roundingresult = Math.round((valuetowrite / rounding))*rounding;
        String s = df.format(roundingresult) + Suffix;
        WriteToTextBox(id, s);

    }
    private void WriteToTextBox(int id, double valuetowrite, double rounding, String DecimaFormat) {

        TextView edittext = (TextView)findViewById(id);
        DecimalFormat df = new DecimalFormat(DecimaFormat);
        double roundingresult = Math.round((valuetowrite / rounding))*rounding;
        String s = df.format(roundingresult);
        WriteToTextBox(id, s);

    }
    private void WriteToTextBox(int id, double valuetowrite)
    {
        TextView edittext = (TextView)findViewById(id);
        DecimalFormat df = new DecimalFormat("#.00");
        String s = df.format(valuetowrite);
        WriteToTextBox(id, s);
        //  edittext.setText(Double.toString(valuetowrite));
    }

    private void WriteToTextBox(int id, String valuetowrite)
    {
        TextView edittext = (TextView)findViewById(id);
        edittext.setText(valuetowrite);
    }

    private double ConvertTextViewToDouble(TextView v)
    {
        String currentstring = v.getText().toString();
        return Double.valueOf(currentstring).doubleValue();
    }
    private double ConvertTextViewToDouble(EditText v)
    {
        String currentstring = v.getText().toString();
        return Double.valueOf(currentstring).doubleValue();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

