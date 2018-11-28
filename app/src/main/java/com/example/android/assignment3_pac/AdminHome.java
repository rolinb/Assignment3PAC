package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
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

  public void goToUserView(View v){
    Intent intent = new Intent(this, UserHome.class);
    startActivity(intent);
  }

  public void goToManageUsers(View v){
    Intent intent = new Intent(this, ManageUsers.class);
    startActivity(intent);
  }

  public void goToManageDevices(View v){
    Intent intent = new Intent(this, ManageDevices.class);
    startActivity(intent);
  }

  public void readLog(){
    String filename = Environment.getExternalStorageDirectory()+"/log4j.log";
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

    }
    catch (IOException eatIt) {
      tv.setText("ISSUE WITH FILE");
      tv.append(eatIt.toString());
    }
    tv.setMovementMethod(new ScrollingMovementMethod());



  }
}
