package com.example.android.assignment3_pac.assn2;

import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;

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

        public static Device getDevice(UUID uuid){
            Map<UUID, Device> devices = ((Hub) hub).getDevices();
            return devices.get(uuid);
        }
    }

}
