package com.example.android.assignment3_pac.assn2.part1.devices;

import com.example.android.assignment3_pac.assn2.part1.Status;

import java.util.UUID;

public abstract class Device {

  private final UUID aUuid = UUID.randomUUID();
  private Status aStatus; // This can't be NULL!

  public UUID getIdentifier() {
    return aUuid;
  }

  public Status getStatus() {
    // Since the status can't be NULL, then check IF NULL and IF return dummy
    // status.
    return aStatus == null ? Status.NOT_AVAILABLE : aStatus;
  }

  public void setStatus(Status status) {
    this.aStatus = status;
  }

  @Override
  public String toString() {
    return aUuid.toString();
  }

}
