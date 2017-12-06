package com.example.twenty_shine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Coc Map on 07/11/2017.
 */
public class MenuActivity extends AppCompatActivity{
    //vars
    private static final String TAG = "MenuActivity";

    ImageButton btnHairstyle, btnAppointment, btnShopping, btnBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        Log.d(TAG, "onCreate: Started");

        btnHairstyle  = (ImageButton) findViewById(R.id.hairstyleButton);
        btnAppointment  = (ImageButton) findViewById(R.id.appointmentButton);
        btnShopping  = (ImageButton) findViewById(R.id.shoppingButton);
        btnBranch  = (ImageButton) findViewById(R.id.branchButton);

        btnHairstyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ******Clicked btnToHairstyle.");

                Intent intent = new Intent(MenuActivity.this, HairstyleActivity.class);
                startActivity(intent);
            }
        });

        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToBookAppointment.");

                Intent intent = new Intent(MenuActivity.this, BookAppointment.class);
                startActivity(intent);
            }
        });

        btnShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToProduct.");

                Intent intent = new Intent(MenuActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        btnBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked btnToMap.");

                Intent intent = new Intent(MenuActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
