package com.example.android.assignment3_pac;

import android.app.Application;
import android.os.Environment;
import com.example.android.assignment3_pac.Log4JHelper;

public class LogCreator extends Application {

  @Override
  public void onCreate() {
    super.onCreate();


    // configure log4j
    configureLog4j();
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

}
