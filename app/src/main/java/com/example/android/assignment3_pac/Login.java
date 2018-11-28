package com.example.android.assignment3_pac;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
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

    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
        PackageManager.PERMISSION_GRANTED) {

      configureLog4j();

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

    } else {
      requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
          0);


    }
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

  public void configureLog4j() {

    // set file name
    String fileName = Environment.getExternalStorageDirectory() + "/"
        + "log4j.log";
    System.out.println("filname is : " + fileName);
    // set log line pattern
    String filePattern = "%d - [%c] - %p : %m%n";
    // set max. number of backed up log files
    int maxBackupSize = 1;
    // set max. size of log file
    long maxFileSize = 1024 * 1024;

    // configure
    Log4JHelper.Configure(fileName, filePattern, maxBackupSize, maxFileSize);
  }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
      if (requestCode == 0) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
        }
      }
    }


}
