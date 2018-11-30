package com.example.android.assignment3_pac.assn2.part1;

import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;

/**
 * A method object for making testing easier Messaging not a core responsibility for Hub
 */
public class JSONMessaging {

  private Device pDevice;
  private String pMessage;

  public JSONMessaging(Device pDevice, String pMessage) {
    this.pDevice = pDevice;
    this.pMessage = pMessage;
  }

  public JSONObject invoke() {
    HashMap<String, String> jsonMap = new HashMap<String, String>();
    jsonMap.put("msg_id", "unique id");
    jsonMap.put("node_id", pDevice.getIdentifier().toString());
    jsonMap.put("status", pDevice.getStatus().name());
    jsonMap.put("payload", pMessage);
    jsonMap.put("created_at", new Date().toString());
    return new JSONObject(jsonMap);
  }
}

