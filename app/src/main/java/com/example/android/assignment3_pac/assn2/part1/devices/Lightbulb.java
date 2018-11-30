package com.example.android.assignment3_pac.assn2.part1.devices;

import com.example.android.assignment3_pac.assn2.part1.HubRegistrationException;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import java.util.UUID;

public class Lightbulb extends Device implements SwitchableDevice {

  private boolean isOn = false;
  private final Mediator aMed;

  public Lightbulb(Mediator pMed) {
    super();
    aMed = pMed;
    isOn = false;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  public Lightbulb(Mediator pMed, UUID uuid) {
    super();
    setIdentifier(uuid);
    aMed = pMed;
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
    String status = "lightbulb is now ";
    aMed.alert(this, status + isOn);
  }

  public boolean getCondition() {
    return isOn;
  }

  @Override
  public String toString() {
    return "Lightbulb id " + super.getIdentifier().toString();
  }
}
