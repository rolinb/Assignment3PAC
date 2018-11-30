package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class ManageUsers extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manage_users);
  }

  public void createUser(View v) {
    TextView usernameTV = findViewById(R.id.userNameField);
    String username = usernameTV.getText().toString();
    TextView passwordTV = findViewById(R.id.passwordField);
    String password = passwordTV.getText().toString();
    RadioButton userButton = findViewById(R.id.UserButton);
    RadioButton adminButton = findViewById(R.id.adminButton);
    boolean isAdmin;
    if (userButton.isChecked()) {
      isAdmin = false;
    } else if (userButton.isChecked()) {
      isAdmin = true;
    } else {
      return; //ignores the click
    }

    if (username.isEmpty()) {
      return;
    }
    if (password.isEmpty()) {
      return;
    }

    new DeviceReaderWriter(this).addUser(username, isAdmin, password);
    Intent intent = new Intent(this, AdminHome.class);
    startActivity(intent);
  }
}
