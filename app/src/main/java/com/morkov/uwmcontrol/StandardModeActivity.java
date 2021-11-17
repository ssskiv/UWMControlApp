package com.morkov.uwmcontrol;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class StandardModeActivity extends AppCompatActivity implements ControlPadView.PadListener, BluetoothController.isWriting {

    public float x;
    public float y;
    private BluetoothController bluetoothController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_mode);
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

    public void writeString(String text) {
        try {
            bluetoothController.writeBTString(text);
        } catch (IOException e) {
            Log.e("DEBUG", "Error occurred when sending data to device", e);
            Toast.makeText(
                    this,
                    getResources().getText(R.string.err_send_data),
                    Toast.LENGTH_SHORT
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