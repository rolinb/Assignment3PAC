package com.example.android.assignment3_pac.assn2.part1.devices;

import com.example.android.assignment3_pac.assn2.part1.HubRegistrationException;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import com.example.android.assignment3_pac.assn2.part1.Status;

public class Camera extends Device {

  private boolean isRecording;
  private int diskSize;

  private final Mediator aMed;

  public Camera(Mediator med) {
    super();
    aMed = med;
    diskSize = 999;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      // in future, log this
    }
  }

  public String startup() {
    isRecording = false;
    return "started";
  }

  public void record() throws CameraFullException {
    isRecording = !isRecording;
    aMed.alert(this, "Started recording");
    if(Math.random()*1000 > diskSize) {
      throw new CameraFullException("Camera Full");
    }
  }

  @Override
  public Status getStatus() {
    return Status.ERROR;
  }

  public boolean getIsRecording(){
    return isRecording;
  }

  @Override
  public String toString() {
    return "Camera id " + super.getIdentifier().toString();
  }
}

