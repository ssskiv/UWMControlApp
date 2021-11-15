package com.morkov.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

public class StandardModeActivity extends AppCompatActivity implements ControlPadView.PadListener {

    private ControlPadView pad;
    public float x;
    public float y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_mode);
        //if()
    }

    @Override
    public void onPadMoved(float xPercent, float yPercent, int id) {
        x = xPercent;
        y = yPercent;
        Log.d("DEBUG", "onPadMoved: "+x+" "+y);
    }
}