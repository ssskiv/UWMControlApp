package com.morkov.uwmcontrol;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.google.protobuf.UninitializedMessageException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.UUID;

public class BluetoothController {
    public BluetoothSocket getSocket() {
        return socket;
    }

    private BluetoothSocket socket;
    private OutputStream outStream;

    private BluetoothDevice device;

    BluetoothController(BluetoothDevice device) throws IOException {
        this.device = device;
        try {socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        } catch (IOException e) {e.printStackTrace();return;
        }
        socket.connect();
        outStream = socket.getOutputStream();
    }

    protected void writeBT(byte[] bytes) throws IOException {
        outStream.write(bytes);
    }

    protected void writeBTString(String text) throws IOException {
        String txt = text + '\n';
        writeBT(txt.getBytes());
    }

    public BluetoothDevice getDevice() {
        return device;
    }
    public interface isWriting{
        void writeString(String text);
    }
}
