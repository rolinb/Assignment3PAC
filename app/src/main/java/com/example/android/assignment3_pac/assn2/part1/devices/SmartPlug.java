package com.example.android.assignment3_pac.assn2.part1.devices;

import com.example.android.assignment3_pac.assn2.part1.HubRegistrationException;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import java.util.UUID;

public class SmartPlug extends Device implements SwitchableDevice {

  private final Mediator aMed;
  private boolean isOn = false;

  public SmartPlug(Mediator med) {
    super();
    aMed = med;
    isOn = false;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  public SmartPlug(Mediator med, UUID uuid) {
    super();
    setIdentifier(uuid);
    aMed = med;
    isOn = false;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void toggle() {
    isOn = !isOn;
    String status = "plug is now ";
    aMed.alert(this, status + isOn);
  }

  public boolean getCondition() {
    return isOn;
  }


  @Override
  public String toString() {
    return "Smartplug id " + super.getIdentifier().toString();
  }
}
