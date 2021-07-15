package com.example.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {
    int age;
    double weight, bmiValue, idealWeight, height, slimness;
    String WeightStatus;
    TextView bmiLab, statusLab, idealLab, MessageDetails;
    Button mainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        //Links the right elements to the right elements in XML
        bmiLab = findViewById(R.id.BmiLab);
        idealLab = findViewById(R.id.idealWeightLab);
        statusLab = findViewById(R.id.weightStatusLab);
        MessageDetails = findViewById(R.id.messageDetails);
        mainPage = findViewById(R.id.mainPageButt);
        //getting all the data from the intent.
        age = getIntent().getIntExtra("age", 0);
        slimness = getIntent().getDoubleExtra("slimness", 0);
        weight = getIntent().getDoubleExtra("weight", 0);
        height = (getIntent().getIntExtra("height", 0)) / 100.0;
        //calculation the BMI.
        bmiValue = weight / (height * height);
        //calculation the ideal weight.
        idealWeight = ((height * 100) - 100 + (age / 10.0)) * 0.9 * slimness;
        //changing the bmi text view.
        bmiLab.setText(getResources().getString(R.string.BmiLab, bmiValue));
        //changing the ideal weight text view.
        idealLab.setText(getResources().getString(R.string.idealLab, idealWeight));
        //calculation and changing the weight status text view.
        WeightStatusUpdate(bmiValue);
        //writing a message to the user that compares the ideal weight to the current weight
        updateMessage(weight,idealWeight);
        //setting click listener to the 'mainPage' button
        mainPage.setOnClickListener(v ->
        {
            //switching to the MainActivity.
            startActivity(new Intent(this, MainActivity.class));
            //closing the current activity
            finish();
        });



    }

    private void updateMessage(double weight, double idealWeight) {
        //updating the message text view
        MessageDetails.setText(getResources().getString(R.string.messageDetails,weight,idealWeight));
    }

    private void WeightStatusUpdate(double bmiValue) {
        //calculation the weight status based on the bmi.
        if (bmiValue < 15)
            WeightStatus = "Anorexic";
        else if (15 <= bmiValue && bmiValue < 18.5)
            WeightStatus = "Underweight";
        else if (18.5 <= bmiValue && bmiValue < 25)
            WeightStatus = "Normal";
        else if (25 <= bmiValue && bmiValue < 30)
            WeightStatus = "Overweight";
        else if (30 <= bmiValue && bmiValue < 35)
            WeightStatus = "Obese";
        else if (35 <= bmiValue)
            WeightStatus = "Extreme Obese";
        //changing the weight status text view.
        statusLab.setText(getResources().getString(R.string.statusLab,WeightStatus));
    }
}
