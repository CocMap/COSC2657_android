package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Float.parseFloat;

public class MainActivity extends AppCompatActivity {

    private Float currentResult = new Float(0);
    private Float operand = new Float(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickButtonPlus(View view) {
        //get handle on the number (operand whose id="number")
        EditText editText = (EditText) findViewById(R.id.name);

        //get the number's string and convert it to a float object
        operand = parseFloat(editText.getText().toString());
        currentResult += operand;

        //get a handle on "result"
        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText( currentResult.toString() );
        Toast.makeText(this, "Result is " + currentResult.toString(),
        Toast.LENGTH_LONG).show();

    }


}