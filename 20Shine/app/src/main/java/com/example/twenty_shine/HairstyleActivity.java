package com.example.twenty_shine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by Coc Map on 07/11/2017.
 */

public class HairstyleActivity extends AppCompatActivity {
    private static final String TAG = "HairstyleActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hairstyle_layout);
        Log.d(TAG, "onCreate: Started.");

    }
}
