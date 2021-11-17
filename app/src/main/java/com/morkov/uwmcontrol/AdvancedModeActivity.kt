package com.morkov.uwmcontrol

import android.bluetooth.BluetoothDevice
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.io.IOException
import java.lang.NullPointerException

class AdvancedModeActivity : AppCompatActivity(), BluetoothController.isWriting {

    private var bluetoothController: BluetoothController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_mode)
        try {
            bluetoothController = BluetoothController(intent.getParcelableExtra("device"))
        } catch (e: IOException) {
            e.printStackTrace()
            return
        } catch (e: NullPointerException) {
            e.printStackTrace()
            return
        }
        writeString("standardMode")
    }
    override fun writeString(text: String?) {
        try {
            bluetoothController!!.writeBTString(text)
        } catch (e: IOException) {
            Log.e("DEBUG", "Error occurred when sending data to device", e)
            Toast.makeText(
                this,
                resources.getText(R.string.err_send_data),
                Toast.LENGTH_SHORT
            ).show()
            return
        }
    }
}