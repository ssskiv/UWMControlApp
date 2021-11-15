package com.morkov.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAdv;
    private Button buttonStand;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdv = findViewById(R.id.button_advanced);
        buttonStand = findViewById(R.id.button_standard);
        Log.d("DEBUG", "MainActivity.onCreate: Entered");
        buttonAdv.setOnClickListener(this);
        buttonStand.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == buttonStand.getId()) {
            Log.d("DEBUG", "MainActivity.onClick: Entered");
            Intent intent = new Intent(this, StandardModeActivity.class);
            startActivity(intent);
        } else {
            //Intent intent = new Intent(this, ***);
            //startActivity(intent);
        }
    }
}