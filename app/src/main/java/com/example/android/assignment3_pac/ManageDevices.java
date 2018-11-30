package com.example.android.assignment3_pac;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.Status;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;
import com.example.android.assignment3_pac.assn2.part1.devices.SmartPlug;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;

public class ManageDevices extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manage_devices);

    createView();

    //setContentView(layout);
  }

  @Override
  protected void onResume() {
    super.onResume();
    createView();
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(this, DeleteDevice.class);
    intent.putExtra("UUID", ((Device) v.getTag()).getIdentifier().toString());
    startActivity(intent);
  }

  public void goToAddDevice(View v) {
    Intent intent = new Intent(this, AddDevice.class);
    startActivity(intent);
  }

  private void createView() {
    TableLayout layout = findViewById(R.id.deviceTable);

    if (((Hub) mainController.controller.getHub()).getDevices().values() != null) {
      layout.removeAllViews();

      int i = 0;

      for (Device d : ((Hub) mainController.controller.getHub()).getDevices().values()) {
        TableRow row = new TableRow(this);
        TextView DeviceInfo = new TextView(this);
        DeviceInfo.setText(d.toString());
        DeviceInfo.setMaxLines(3);
        DeviceInfo.setHorizontallyScrolling(false);
        DeviceInfo.setLayoutParams(new TableRow.LayoutParams(800, 250));
        if (d.getStatus() == Status.OFF || d.getStatus() == Status.NOT_AVAILABLE) {
          DeviceInfo.setTextColor(Color.RED);
        } else if (d.getStatus() == Status.NORMAL) {
          DeviceInfo.setTextColor(Color.GREEN);
        } else {
          DeviceInfo.setTextColor(Color.YELLOW);
        }
        ImageButton deviceButton = new ImageButton(this);
        if (d instanceof Camera) {
          deviceButton.setImageResource(R.drawable.ic_camera);

        } else if (d instanceof Lightbulb) {
          deviceButton.setImageResource(R.drawable.ic_lightbulb_on);

        } else if (d instanceof Thermostat) {
          deviceButton.setImageResource(R.drawable.ic_thermostat);

        } else if (d instanceof SmartPlug) {
          deviceButton.setImageResource(R.drawable.ic_smartplug);

        }
        deviceButton.setMinimumWidth(250);
        deviceButton.setTag(d);
        deviceButton.setContentDescription("Button " + i);
        deviceButton.setOnClickListener(this);
        row.addView(deviceButton);
        row.addView(DeviceInfo);
        row.setHorizontalScrollBarEnabled(false);
        layout.addView(row);
        i++;
      }

    } else {
      TextView mytext = new TextView(this);
      mytext.setText("No Devices available");
      layout.addView(mytext);
    }
  }
}
