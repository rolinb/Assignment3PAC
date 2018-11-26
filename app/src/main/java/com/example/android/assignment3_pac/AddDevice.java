package com.example.android.assignment3_pac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;
import com.example.android.assignment3_pac.assn2.part1.devices.SmartPlug;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;

public class AddDevice extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_device);
  }

  public void addCamera(View v){
    Camera c = new Camera(mainController.controller.getHub());
    returnToManageDevices(v);
  }

  public void addThermostat(View v){
    Thermostat c = new Thermostat(mainController.controller.getHub());
    returnToManageDevices(v);
  }

  public void addSmartplug(View v){
    SmartPlug c = new SmartPlug(mainController.controller.getHub());
    returnToManageDevices(v);
  }

  public void addLightbulb(View v){
    Lightbulb c = new Lightbulb(mainController.controller.getHub());
    returnToManageDevices(v);
  }

  private void returnToManageDevices(View v){
    Intent intent = new Intent(this, ManageDevices.class);
    startActivity(intent);
  }

}
