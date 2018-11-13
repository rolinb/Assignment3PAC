package com.example.android.assignment3_pac.assn2;

import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;
import com.example.android.assignment3_pac.assn2.part1.devices.SmartPlug;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;

import org.json.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public  class mainController {


    public static class controller{
        private static Mediator hub;

        //Todo: set this up to read from file static works for now
        public static void initialise(){
            hub = new Hub();
            Camera c = new Camera(hub);
            c = new Camera(hub);
            Lightbulb l = new Lightbulb(hub);
            l = new Lightbulb(hub);
            l = new Lightbulb(hub);
            SmartPlug s = new SmartPlug(hub);
            Thermostat t = new Thermostat(hub);


        }

        public static Mediator getHub(){
            return hub;
        }

        public static ArrayList<Camera> getCameras(){
            ArrayList<Camera> cameras = new ArrayList<Camera>();
            for (Device d : ((Hub) hub).getDevices().values()){
                if(d instanceof Camera){
                    cameras.add( (Camera) d);
                }
            }
            if(cameras.isEmpty()){
                return null;
            }
            return cameras;
        }

        public static ArrayList<Lightbulb> getLightbulbs(){
            ArrayList<Lightbulb> lightbulbs = new ArrayList<Lightbulb>();
            for (Device d : ((Hub) hub).getDevices().values()){
                if(d instanceof Lightbulb){
                    lightbulbs.add( (Lightbulb) d);
                }
            }
            if(lightbulbs.isEmpty()){
                return null;
            }
            return lightbulbs;
        }

        public static ArrayList<SmartPlug> getSmartPlugs(){
            ArrayList<SmartPlug> smartPlugs = new ArrayList<SmartPlug>();
            for (Device d : ((Hub) hub).getDevices().values()){
                if(d instanceof SmartPlug){
                    smartPlugs.add( (SmartPlug) d);
                }
            }
            if(smartPlugs.isEmpty()){
                return null;
            }
            return smartPlugs;
        }

        public static ArrayList<Thermostat> getThermostats(){
            ArrayList<Thermostat> thermostats = new ArrayList<Thermostat>();
            for (Device d : ((Hub) hub).getDevices().values()){
                if(d instanceof Thermostat){
                    thermostats.add( (Thermostat) d);
                }
            }
            if(thermostats.isEmpty()){
                return null;
            }
            return thermostats;
        }



        public static Device getDevice(UUID uuid){
            Map<UUID, Device> devices = ((Hub) hub).getDevices();
            return devices.get(uuid);
        }

        public static void cameraChangeLights(boolean bool){
            for(Lightbulb l : getLightbulbs()){
                hub.alert(l, "Status changed by camera");
                if(l.getCondition() != bool){
                    l.toggle();
                }
            }
        }






    }

}
