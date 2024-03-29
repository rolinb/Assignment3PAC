package com.example.android.assignment3_pac.assn2.part1;

import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;
import com.example.android.assignment3_pac.assn2.part1.devices.SmartPlug;
import com.example.android.assignment3_pac.assn2.part1.devices.SwitchableDevice;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;

public class Driver {

  public Driver() {
    Mediator med = new Hub();
    Thermostat t = new Thermostat(med);
    Lightbulb l = new Lightbulb(med);
    Camera c = new Camera(med);
    SwitchableDevice s = new SmartPlug(med);
    // ideally, we would load these in from a config file
  }

  public static void main(String[] args) {
    new Driver();
  }
}
