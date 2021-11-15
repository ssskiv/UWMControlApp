package com.morkov.myapplication;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Set;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceHolder> {
    public DeviceAdapter(Set<BluetoothDevice> devices, CellClickListener cellClickListener) {
        this.devices = devices;
        this.cellClickListener = cellClickListener;
    }

    Set<BluetoothDevice> devices;
    CellClickListener cellClickListener;

    @NonNull
    @Override
    public DeviceAdapter.DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device, parent, false);
        return new DeviceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position) {
        BluetoothDevice device = (BluetoothDevice) devices.toArray()[position];

        holder.name.setText(device.getName());
        holder.addr.setText(device.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cellClickListener.onCellClickListener(device);
            }
        }/*(View.OnClickListener) cellClickListener.onCellClickListener(device)*/);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public static class DeviceHolder extends RecyclerView.ViewHolder {

        TextView addr = itemView.findViewById(R.id.device_addr);
        TextView name = itemView.findViewById(R.id.device_name);

        public DeviceHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
