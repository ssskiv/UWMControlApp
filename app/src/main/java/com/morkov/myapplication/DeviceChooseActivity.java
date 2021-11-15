package com.morkov.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

public class DeviceChooseActivity extends AppCompatActivity implements CellClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_choose);

        Log.d("DEBUG", "DeviceChooseActivity onCreate: entered");

        RecyclerView recyclerView = findViewById(R.id.rv_devices);
        BluetoothController bluetoothController = new BluetoothController();


    }

    @Override
    public void onCellClickListener(BluetoothDevice data) {
        Intent intent = new Intent();
        intent.putExtra("device", data);
        setResult(RESULT_OK, intent);
        finish();
    }

    private static class BluetoothController {

        String message;
        BluetoothDevice device;

        private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        BluetoothController() {
            pairedDevices.forEach(device -> {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress();
            });
        }
    }
}

interface CellClickListener {
    void onCellClickListener(BluetoothDevice data);
}