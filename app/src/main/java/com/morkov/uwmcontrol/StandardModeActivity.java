package com.morkov.uwmcontrol;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class StandardModeActivity extends AppCompatActivity implements ControlPadView.PadListener {

    public float x;
    public float y;
    private BluetoothController bluetoothController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_mode);
        //BluetoothDevice device = getIntent().getParcelableExtra("device");
        try {
            bluetoothController = new BluetoothController(getIntent().getParcelableExtra("device"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }catch (NullPointerException e){
            e.printStackTrace();

            return;
        }
        writeString("standardMode");
    }

    private void writeString(String text) {
        try {
            bluetoothController.writeBTString(text);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("DEBUG", "Error occurred when sending data to device", e);
            Toast.makeText(
                    this,
                    "Error occurred when sending data to device",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }
    }

    @Override
    public void onPadMoved(float xPercent, float yPercent, int id) {
        x = xPercent;
        y = yPercent;
        Log.d("DEBUG", "onPadMoved: " + x + " " + y);
        writeString("move$" + x + "$" + y);
    }
}