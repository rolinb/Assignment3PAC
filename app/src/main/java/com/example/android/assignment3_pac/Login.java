package com.example.android.assignment3_pac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import com.example.android.assignment3_pac.assn2.mainController;

public class Login extends AppCompatActivity {

  private EditText name;
  private EditText password;
  private Button loginButton;
  private TextView failedInfo;




  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    mainController.controller.initialise(this);


    name = findViewById(R.id.nameField);
    password = findViewById(R.id.passwordField);
    loginButton = findViewById(R.id.loginButton);

    loginButton.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {
                                       signIn(name.getText().toString(), password.getText().toString());
                                     }
                                   }
    );


  }

  private void signIn(String name, String pass){
    if(name.equals("admin") && pass.equals("hunter2")){
      Intent intent = new Intent(this, AdminHome.class);
      mainController.controller.setIsAdmin(true);
      startActivity(intent);    }
    else if(name.equals("user") && pass.equals("pass")){
      Intent intent = new Intent(this, UserHome.class);
      mainController.controller.setIsAdmin(false);
      startActivity(intent);
    }
  }




}
