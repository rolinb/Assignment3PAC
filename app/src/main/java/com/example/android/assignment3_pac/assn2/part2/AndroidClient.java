package com.example.android.assignment3_pac.assn2.part2;

import com.example.android.assignment3_pac.assn2.part1.HubRegistrationException;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import org.json.JSONObject;

public class AndroidClient extends Client {

  private final Mediator aMed;
  private JSONObject aJsonObj;

  public AndroidClient(Mediator pMed) {
    aMed = pMed;
    try {
      aMed.register(this);
    } catch (HubRegistrationException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void notify(JSONObject json) {
    super.notify(json);
    this.aJsonObj = json;
    display();
  }

  private void display() {
    try {
      System.out.println("AndroidClient is displaying alert from : " + aJsonObj.getString("node_id"));
    }
    catch(Exception eatIt){

    }
  }

  @Override
  public String toString() {
    return "AndroidClient: " + getIdentifier().toString();
  }
}
