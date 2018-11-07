package com.example.android.assignment3_pac.assn2.part1;

import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part2.Client;


public interface Mediator {

  public void unregister(Device device) throws HubRegistrationException;

  public void unregister(Client client) throws HubRegistrationException;

  //not in spec, do not test
  public void register(Device pDevice) throws HubRegistrationException;

  public void register(Client pClient) throws HubRegistrationException;

  public void alert(Device pDevice, String pMessage);
}
