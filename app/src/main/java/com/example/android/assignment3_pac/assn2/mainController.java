package com.example.android.assignment3_pac.assn2;

import android.content.Context;
import com.example.android.assignment3_pac.DeviceReaderWriter;
import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;
import com.example.android.assignment3_pac.assn2.part1.devices.SmartPlug;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public class mainController {


  public static class controller {

    private static Mediator hub;
    private static boolean isAdmin = false;
    private static int notificationLevel = 2;

    //Todo: set this up to read from file static works for now
    public static void initialise(Context c) {
      hub = new Hub();

      new DeviceReaderWriter(c).createDevices();


       /*
        Camera c = new Camera(hub);
        c.setStatus(Status.OFF);
        c = new Camera(hub);
        c.setStatus(Status.NORMAL);
        Lightbulb l = new Lightbulb(hub);
        l = new Lightbulb(hub);
        l = new Lightbulb(hub);
        SmartPlug s = new SmartPlug(hub);
        Thermostat t = new Thermostat(hub);*/

    }

    public static Mediator getHub() {
      return hub;
    }

    public static ArrayList<Camera> getCameras() {
      ArrayList<Camera> cameras = new ArrayList<Camera>();
      for (Device d : ((Hub) hub).getDevices().values()) {
        if (d instanceof Camera) {
          cameras.add((Camera) d);
        }
      }
      if (cameras.isEmpty()) {
        return null;
      }
      return cameras;
    }

    public static void setNotificationLevel(int level) {
      notificationLevel = level;
    }

    public static int getNotificationLevel() {
      return notificationLevel;
    }

    public static ArrayList<Lightbulb> getLightbulbs() {
      ArrayList<Lightbulb> lightbulbs = new ArrayList<Lightbulb>();
      for (Device d : ((Hub) hub).getDevices().values()) {
        if (d instanceof Lightbulb) {
          lightbulbs.add((Lightbulb) d);
        }
      }
      if (lightbulbs.isEmpty()) {
        return null;
      }
      return lightbulbs;
    }

    public static ArrayList<SmartPlug> getSmartPlugs() {
      ArrayList<SmartPlug> smartPlugs = new ArrayList<SmartPlug>();
      for (Device d : ((Hub) hub).getDevices().values()) {
        if (d instanceof SmartPlug) {
          smartPlugs.add((SmartPlug) d);
        }
      }
      if (smartPlugs.isEmpty()) {
        return null;
      }
      return smartPlugs;
    }

    public static ArrayList<Thermostat> getThermostats() {
      ArrayList<Thermostat> thermostats = new ArrayList<Thermostat>();
      for (Device d : ((Hub) hub).getDevices().values()) {
        if (d instanceof Thermostat) {
          thermostats.add((Thermostat) d);
        }
      }
      if (thermostats.isEmpty()) {
        return null;
      }
      return thermostats;
    }


    public static Device getDevice(UUID uuid) {
      Map<UUID, Device> devices = ((Hub) hub).getDevices();
      return devices.get(uuid);
    }

    public static void cameraChangeLights(boolean bool) {
      if (getLightbulbs() != null) {
        for (Lightbulb l : getLightbulbs()) {
          hub.alert(l, "Status changed by camera");
          if (l.getCondition() != bool) {
            l.toggle();
          }
        }
      }
    }

    public static boolean getIsAdmin() {
      return isAdmin;
    }

    public static void setIsAdmin(boolean b) {
      isAdmin = b;
    }


  }

}
