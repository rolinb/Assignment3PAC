package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AdminHome extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_home);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


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

}
