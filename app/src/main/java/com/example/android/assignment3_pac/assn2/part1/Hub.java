package com.example.android.assignment3_pac.assn2.part1;

import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part2.Client;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.json.JSONObject;


public class Hub extends Device implements Mediator {

  private HashMap<UUID, Device> aDevices = new HashMap<UUID, Device>();
  private HashMap<UUID, Client> aClients = new HashMap<UUID, Client>();
  private Logger logger = Logger.getLogger("Hub");

  public void startup() {
    // some logic about sending init messages or somethng.
  }

  public void shutdown() {
    if (!aDevices.isEmpty()) {
      for (Device d : aDevices.values()) {
        d.setStatus(Status.OFF);
      }
    }
    log("Notification: System has been shutdown, all devices OFF. ");
  }

  @Override
  public void register(Device pDevice) throws HubRegistrationException {
    if (!aDevices.containsKey(pDevice.getIdentifier())) {
      aDevices.put(pDevice.getIdentifier(), pDevice);
    } else {
      throw new HubRegistrationException(pDevice + " was already registered");
    }
  }

  @Override
  public void register(Client pClient) throws HubRegistrationException {
    if (!aClients.containsKey(pClient.getIdentifier())) {
      aClients.put(pClient.getIdentifier(), pClient);
    } else {
      throw new HubRegistrationException(pClient + " was already registered");
    }
  }

  @Override
  public void unregister(Device device) throws HubRegistrationException {
    if (!aDevices.containsKey(device.getIdentifier())) {
      log("Unknown Device unregister");
      throw new HubRegistrationException("Device does not exists!");
    }
    aDevices.remove(device.getIdentifier());
    log("Device removed " + device);
  }

  @Override
  public void unregister(Client client) throws HubRegistrationException {
    if (!aClients.containsKey(client.getIdentifier())) {
      throw new HubRegistrationException("Client does not exists!");
    }
    aClients.remove(client.getIdentifier());
  }

  /**
   * Logging. Use SLF4J to write all message traffic to the log file.
   */
  public void log(String logMsg) {
    logger.info(logMsg);
  }

  /**
   * Alerts are events that happen at the Device level. They send the alert to the hUb, which
   * redistributes to the clients
   */
  @Override
  public void alert(Device pDevice, String pMessage) {

    // initialize the map
    JSONObject jsonMessage = new JSONMessaging(pDevice, pMessage).invoke();

    // ordinarily, we would have logic for which clients to notify
    notifyClients(jsonMessage);
    log("ALERT msg: " + pMessage + " - from Device " + pDevice.toString());
  }

  private void notifyClients(JSONObject pMsg) {
    for (Client c : aClients.values()) {
      c.notify(pMsg);
      log("Notified: " + c.toString());
    }
  }

  public Map<UUID, Device> getDevices() {
    return new HashMap<UUID, Device>(aDevices);
  }
}