package com.morkov.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.protobuf.UninitializedMessageException;
import com.google.type.Date;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAdv;
    private Button buttonStand;

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null)
            return;
        else {
device =data.getParcelableExtra("device");

            try {
                socket=device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
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
            try {
                writeBTString("connected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void writeBT(byte[] bytes) throws IOException {
        try {
            outStream.write(bytes);
        } catch (IOException e ) {
            Log.e("DEBUG", "Error occurred when sending data", e);

            // Send a failure message back to the activity.
            Toast.makeText(this, "Error occurred when sending data", Toast.LENGTH_LONG).show();
            return;
        } catch (UninitializedMessageException e ) {
            Toast.makeText(this, "Connect to device", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeBTString(String text ) throws IOException {
        String txt  = text + '\n';
        writeBT(txt.getBytes());
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