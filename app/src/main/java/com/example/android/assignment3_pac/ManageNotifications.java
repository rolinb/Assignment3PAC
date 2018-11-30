package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import com.example.android.assignment3_pac.assn2.mainController;

public class ManageNotifications extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_manage_notifications);


  }

  public void confirmButton(View view) {
    RadioButton userButton = findViewById(R.id.allButton);
    RadioButton adminButton = findViewById(R.id.adminButton);
    RadioButton noneButton = findViewById(R.id.noneButton);

    if (userButton.isChecked()) {
      mainController.controller.setNotificationLevel(2);
    } else if (adminButton.isChecked()) {
      mainController.controller.setNotificationLevel(1);

    } else if (noneButton.isChecked()) {
      mainController.controller.setNotificationLevel(0);
    }

    Intent intent = new Intent(this, AdminHome.class);
    startActivity(intent);
  }
}
