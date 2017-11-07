package com.example.moneyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Float.parseFloat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonPlus(View view) {
        //get handle on the number (operand whose id="number")
        EditText editText = (EditText) findViewById(R.id.name);

        //get the number's string and convert it to a float object
         = parseFloat(editText.getText().toString());
        currentResult += operand;

        //get a handle on "result"
        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText( currentResult.toString() );
        Toast.makeText(this, "Result is " + currentResult.toString(),
        Toast.LENGTH_LONG).show();

    }

    //clicking on the button "Money converter" will execute this method
    //executing this will start a new activity MoneyActivity.jva whose layout is this
    public void onClickButtonMoneyConverter(View view){
        Intent intent = new Intent(this, MoneyActivity.class);
        startActivity(intent);
    }

    public void onClickButtonMeasurementConverter(View view){
        Intent intent = new Intent(this, MeasurementActivity.class);
        startActivity(intent);

    }
}

