package com.example.twenty_shine;

import android.content.Intent;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Coc Map on 07/11/2017.
 */
public class MenuActivity extends AppCompatActivity{
    private static final String TAG = "MenuActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        ListView list = (ListView) findViewById(R.id.menu_list);
        Log.d(TAG, "onCreate: Started.");

        ArrayList<String> names = new ArrayList<>();
        names.add("Hair style");
        names.add("Book a date");
        names.add("Hair and nails product");
        names.add("Services");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        list.setAdapter(adapter);

        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
//        Button btn4 = (Button) findViewById(R.id.button4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToHairstyle.");

                Intent intent = new Intent(MenuActivity.this, HairstyleActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToBook.");

                Intent intent = new Intent(MenuActivity.this, BookAppointment.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToProduct.");

                Intent intent = new Intent(MenuActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }
}
