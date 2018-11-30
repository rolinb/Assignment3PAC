package com.example.android.assignment3_pac.assn2.part2;

import com.example.android.assignment3_pac.assn2.part1.Hub;
import java.util.UUID;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Client {

  private final UUID uuid = UUID.randomUUID();
  private Logger logger = LoggerFactory.getLogger(Hub.class);

  public UUID getIdentifier() {
    return uuid;
  }

  public void notify(JSONObject json) {
    logger.info(json.toString());
  }

  @Override
  public String toString() {
    return getIdentifier().toString();
  }
}
