package com.example.android.assignment3_pac.assn2.part2;

import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part1.devices.Temperature;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;
import java.util.UUID;

public class Driver {

  public static void main(String[] args) {
    Hub hub = new Hub();
//    Camera camera = new Camera(hub);
//    camera.setStatus(Status.NORMAL);
//    Client ac = new AndroidClient(hub);
//    try {
//      camera.record();
//    } catch (CameraFullException e) {
//      e.printStackTrace();
//    }

    Client wc = new WebClient(hub);
    Client wc2 = new WebClient(hub);
    Device therm = new Thermostat(hub);
    UUID thermIdentifier = therm.getIdentifier();
    try {
      ((Thermostat) therm).setTemp(new Temperature(42.0, Temperature.Unit.FAHRENHEIT));
    } catch (Temperature.TemperatureOutofBoundsException e) {
      e.printStackTrace();
    }
  }
}

