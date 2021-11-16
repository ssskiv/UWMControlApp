package com.morkov.uwmcontrol;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.protobuf.UninitializedMessageException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStand;

    private BluetoothController bluetoothController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonAdv = findViewById(R.id.button_advanced);
        buttonStand = findViewById(R.id.button_standard);
        Log.d("DEBUG", "MainActivity.onCreate: Entered");
        buttonAdv.setOnClickListener(this);
        buttonStand.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        try {


        if (view.getId() == buttonStand.getId()) {
            Log.d("DEBUG", "MainActivity.onClick: Entered");
            Intent intent = new Intent(this, StandardModeActivity.class);
            intent.putExtra("device",bluetoothController.getDevice());
            startActivity(intent);
        } else {
            //Intent intent = new Intent(this, ***);
            //startActivity(intent);
        }}catch (NullPointerException e){
            e.printStackTrace();
            Toast.makeText(
                    this,
                    "Connect to device",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
            return;
        else {
            BluetoothDevice device = data.getParcelableExtra("device");
            try {
                bluetoothController = new BluetoothController(device);
                try {
                    bluetoothController.getSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("DEBUG", "Error occurred when connecting to device", e);
                Toast.makeText(
                        this,
                        "Error occurred when connecting to device",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }
            /*try {
                socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                socket.connect();
                outStream = socket.getOutputStream();
            } catch (IOException e) {
                Log.e("DEBUG", "Error occurred when connecting to device", e);

                // Send a failure message back to the activity.
                Toast.makeText(
                        this,
                        "Error occurred when connecting to device",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }
            Log.d("DEBUG", "Connected to " + device.getName() + " with type " + device.getType());
            Log.d("CONTROL", ("Connected to " + device.getName()));
            writeBTString("connected");*/
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_exit:
                System.exit(-1);
                break;
            case R.id.menu_connect:
                /*val intentOpenBluetoothSettings = Intent()
                intentOpenBluetoothSettings.action = Settings.ACTION_BLUETOOTH_SETTINGS
                startActivity(intentOpenBluetoothSettings)*/
                DeviceChooseActivity dca = new DeviceChooseActivity();
                Intent intent = new Intent(this, dca.getClass());
                startActivityForResult(intent, 1);
                break;
        }
        return true;
    }
}