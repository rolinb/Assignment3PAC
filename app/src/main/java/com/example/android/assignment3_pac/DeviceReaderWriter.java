package com.example.android.assignment3_pac;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;
import com.example.android.assignment3_pac.assn2.part1.devices.SmartPlug;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeviceReaderWriter {

  SharedPreferences sp;
  Context mContext;


  public DeviceReaderWriter(Context c) {
    mContext = c;
  }

  public void addDevice(Device d) {
    sp = mContext.getSharedPreferences("DEVICES", MODE_PRIVATE);
    Editor editor = sp.edit();
    if (d instanceof Camera) {
      editor.putString(d.getIdentifier().toString(), "CAMERA");
    } else if (d instanceof Thermostat) {
      editor.putString(d.getIdentifier().toString(), "THERMOSTAT");
    } else if (d instanceof Lightbulb) {
      editor.putString(d.getIdentifier().toString(), "LIGHTBULB");
    } else {
      editor.putString(d.getIdentifier().toString(), "SMARTPLUG");
    }
    editor.apply();
  }

  public void createDevices() {
    sp = mContext.getSharedPreferences("DEVICES", MODE_PRIVATE);

    HashMap<String, String> map = (HashMap<String, String>) sp.getAll();
    for (Map.Entry<String, String> e : map.entrySet()) {
      if (e.getValue().equals("CAMERA")) {
        Camera c = new Camera(mainController.controller.getHub(), UUID.fromString(e.getKey()));
      } else if (e.getValue().equals("THERMOSTAT")) {
        Thermostat t = new Thermostat(mainController.controller.getHub(),
            UUID.fromString(e.getKey()));
      } else if (e.getValue().equals("LIGHTBULB")) {
        Lightbulb l = new Lightbulb(mainController.controller.getHub(),
            UUID.fromString(e.getKey()));
      } else {
        SmartPlug s = new SmartPlug(mainController.controller.getHub(),
            UUID.fromString(e.getKey()));
      }
    }

  }

  public void deleteDevice(Device d) {
    sp = mContext.getSharedPreferences("DEVICES", MODE_PRIVATE);
    Editor editor = sp.edit();
    editor.remove(d.getIdentifier().toString()).apply();
  }

  public void addUser(String username, boolean isAdmin, String pass) {
    sp = mContext.getSharedPreferences("USERS", MODE_PRIVATE);
    Editor editor = sp.edit();
    editor.putString(username, isAdmin + "-" + pass);
    editor.apply();
  }


}
