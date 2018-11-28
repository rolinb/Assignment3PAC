package com.example.android.assignment3_pac;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;
import com.example.android.assignment3_pac.assn2.part1.devices.SmartPlug;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;
import java.io.File;
import java.security.KeyStore;
import java.sql.SQLClientInfoException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class DeviceReaderWriter {

  SharedPreferences sp;
  Context mContext;


  public DeviceReaderWriter(Context c){
    mContext = c;
  }

  public void addDevice(Device d){
    sp = mContext.getSharedPreferences("DEVICES", MODE_PRIVATE);
    Editor editor = sp.edit();
    if(d instanceof Camera){
      editor.putString(d.getIdentifier().toString(), "CAMERA");
    }
    else if(d instanceof Thermostat){
      editor.putString(d.getIdentifier().toString(), "THERMOSTAT");
    }
    else if(d instanceof Lightbulb){
      editor.putString(d.getIdentifier().toString(), "LIGHTBULB");
    }
    else{
      editor.putString(d.getIdentifier().toString(), "SMARTPLUG");
    }
    editor.apply();
  }

  public void createDevices(){
    sp =  mContext.getSharedPreferences("DEVICES", MODE_PRIVATE);
    HashMap<String, String> map = (HashMap<String, String>) sp.getAll();
    for(Map.Entry<String, String> e : map.entrySet()){
      if(e.getValue().equals("CAMERA")){
        Camera c = new Camera(mainController.controller.getHub());
      }
      else if(e.getValue().equals("THERMOSTAT")){
        Thermostat t = new Thermostat(mainController.controller.getHub());
      }
      else if(e.getValue().equals("LIGHTBULB")){
        Lightbulb l = new Lightbulb(mainController.controller.getHub());
      }
      else{
        SmartPlug s = new SmartPlug(mainController.controller.getHub());
      }
    }

  }

  public void deleteDevice(Device d){
    sp =  mContext.getSharedPreferences("DEVICES", MODE_PRIVATE);
    Editor editor = sp.edit();
    editor.remove(d.getIdentifier().toString());
  }




  /*
  private final Context mContext;

  private String col1 = "DEVICE_ID";
  private String col2 = "DEVICE_TYPE";
  private String TABLE_NAME = "DEVICES";



  private DeviceReaderWriter(Context mContext) {
    super(mContext, "databases", null, 1);
    this.mContext = mContext;
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ("
        + "_ID INTEGER PRIMARY KEY,"
        + col1 + " TEXT,"
        + col2 + " TEXT)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

  }

  public void addDevice(Device d){
    SQLiteDatabase db = getWritableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(col1, d.getIdentifier().toString());
    if(d instanceof Camera){
      cv.put(col2, "CAMERA");
    }
    else if(d instanceof Thermostat){
      cv.put(col2, "THERMOSTAT");
    }
    else if(d instanceof Lightbulb){
      cv.put(col2, "LIGHTBULB");
    }
    else {
      cv.put(col2, "SMARTPLUG");
    }

    long newRowID =db.insert(TABLE_NAME, null, cv);
  }

  */
}
