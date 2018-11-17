package com.example.android.assignment3_pac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.assignment3_pac.assn2.mainController;

public class Login extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    mainController.controller.initialise();

  }

  public void goToUser(View v) {
    Intent intent = new Intent(this, UserHome.class);
    startActivity(intent);
  }

  public void goToAdmin(View v) {
    Intent intent = new Intent(this, AdminHome.class);
    startActivity(intent);
  }


}
