package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdminHome extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_home);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    readLog();

  }

  public void goToUserView(View v) {
    Intent intent = new Intent(this, UserHome.class);
    startActivity(intent);
  }

  public void goToManageUsers(View v) {
    Intent intent = new Intent(this, ManageUsers.class);
    startActivity(intent);
  }

  public void goToManageDevices(View v) {
    Intent intent = new Intent(this, ManageDevices.class);
    startActivity(intent);
  }

  public void goToManageNotifations(View v) {
    Intent intent = new Intent(this, ManageNotifications.class);
    startActivity(intent);
  }

  public void shutdownButton(View v) {
    ((Hub) mainController.controller.getHub()).shutdown();
    if ((mainController.controller.getIsAdmin()
        && mainController.controller.getNotificationLevel() > 0) ||
        mainController.controller.getNotificationLevel() == 2) {
      Toast.makeText(this, "Devices shutdown", Toast.LENGTH_LONG).show();
    }
  }

  public void getStatus(View v) {
    AsyncTask.execute(new Runnable() {
      @Override
      public void run() {
        ;
        for (Device x : ((Hub) mainController.controller.getHub()).getDevices().values()) {
          ((Hub) mainController.controller.getHub()).log(x.toString() + x.getStatus().toString());
        }
      }
    });
  }

  public void readLog() {
    String filename = Environment.getExternalStorageDirectory() + "/log4j.log";
    StringBuilder sb = new StringBuilder();

    TextView tv = findViewById(R.id.logTextView);

    try {
      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line;

      while ((line = br.readLine()) != null) {
        sb.append(line);
        sb.append('\n');
      }
      br.close();
      tv.setText(sb);

    } catch (IOException eatIt) {
      tv.setText("ISSUE WITH FILE");
      tv.append(eatIt.toString());
    }
    tv.setMovementMethod(new ScrollingMovementMethod());


  }
}
