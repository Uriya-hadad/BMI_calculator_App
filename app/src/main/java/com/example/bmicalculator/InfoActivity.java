package com.example.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InfoActivity extends Activity implements View.OnClickListener {
    Button next;
    RadioButton male, female;
    EditText firstName, lastName, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_data);
        //Links the right elements to the right elements in XML
        next = findViewById(R.id.next);
        male = findViewById(R.id.maleButton);
        female = findViewById(R.id.femaleButton);
        firstName = findViewById(R.id.FirstNameTxt);
        lastName = findViewById(R.id.LastNameTxt);
        age = findViewById(R.id.ageTxt);
        //setting click listener to the next button
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //in case the user didn't put any data in the text fields
        if (firstName.getText().toString().equals("") ||
                lastName.getText().toString().equals("") ||
                age.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.emptyMessage),
                    Toast.LENGTH_SHORT).show();
        } else {
            //Defining intent element to switch to MainInformation.
            Intent intent = new Intent(this, MainInformation.class);
            //pass the age field to the MainInformation activity.
            intent.putExtra("age", Integer.parseInt(age.getText().toString()));
            startActivity(intent);
            //closing the current activity
            finish();
        }
    }
}
