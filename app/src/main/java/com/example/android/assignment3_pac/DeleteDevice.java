package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import java.util.UUID;

public class DeleteDevice extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_delete_device);

    Intent intent = getIntent();
    String uuid = intent.getStringExtra("UUID");
    final Device d = mainController.controller.getDevice(UUID.fromString(uuid));

    final TextView deviceInfo = findViewById(R.id.DeviceInfoTV);
    updateDeviceInfo(deviceInfo, d);

    Button delete = findViewById(R.id.DeleteButton);
    delete.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        deleteDevice(d);
        finish();

      }
    });

    Button back = findViewById(R.id.BackButton);
    back.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  private void deleteDevice(Device device) {
    try {
      mainController.controller.getHub().unregister(device);
      new DeviceReaderWriter(this).deleteDevice(device);
    } catch (Exception eatIt) {
    }

  }

  private void updateDeviceInfo(TextView deviceInfo, Device device) {

    deviceInfo.setText(device.toString() + "\n");
    deviceInfo.append("Status: " + device.getStatus());
  }


}
