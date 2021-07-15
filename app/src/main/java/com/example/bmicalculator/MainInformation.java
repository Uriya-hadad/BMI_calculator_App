package com.example.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

public class MainInformation extends Activity implements View.OnClickListener {
    Intent intent;
    RadioButton small, medium, large;
    Double slimness;
    EditText weight;
    Slider heightSlider;
    Button submit;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_information);
        //Links the right elements to the right elements in XML
        intent = getIntent();
        small = findViewById(R.id.smallButton);
        medium = findViewById(R.id.mediumButton);
        large = findViewById(R.id.largeButton);
        weight = findViewById(R.id.weightTxt);
        heightSlider = findViewById(R.id.heightSlider);
        submit = findViewById(R.id.submit);
        group = findViewById(R.id.bodyTypeGroup);
        //setting click listener to the submit button
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //in case the user didn't put any data in the text field
        if (weight.getText().toString().equals(""))
            Toast.makeText(this, getResources().getString(R.string.emptyMessage),
                    Toast.LENGTH_SHORT).show();
        else{
            //setting up the 'slimness' variable
            slimness = findSelectedButton(group.getCheckedRadioButtonId());
            //pass the data field to the ResultActivity activity.
            intent.putExtra("height",(int)heightSlider.getValue());
            intent.putExtra("slimness", slimness);
            intent.putExtra("weight",Double.parseDouble(weight.getText().toString()));
            intent.setClass(this,ResultActivity.class);
            startActivity(intent);
            //closing the current activity
            finish();

        }
    }

    private Double findSelectedButton(int checkedRadioButtonId) {
        //getting the button that is benn checked
        if (checkedRadioButtonId == R.id.smallButton) {
            return 0.9;
        } else if (checkedRadioButtonId == R.id.mediumButton) {
            return 1.0;
        } else
            return 1.1;
    }
}
