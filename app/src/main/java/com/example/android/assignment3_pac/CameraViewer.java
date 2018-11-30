package com.example.android.assignment3_pac;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;
import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Status;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.CameraFullException;
import java.util.Random;
import java.util.UUID;

public class CameraViewer extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_camera_viewer);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Intent intent = getIntent();
    String uuid = intent.getStringExtra("UUID");
    final Camera camera = (Camera) mainController.controller.getDevice(UUID.fromString(uuid));

    final TextView deviceInfo = findViewById(R.id.DeviceInformation);
    updateDeviceInfo(deviceInfo, camera);

    final ToggleButton toggle = findViewById(R.id.Record);
    final ToggleButton onOff = findViewById(R.id.cameraOnOff);

    if (camera.getStatus() == Status.NORMAL) {
      onOff.setChecked(true);

    }

    VideoView changeImage = (VideoView) findViewById(R.id.cameraSees);
    if (onOff.isChecked()) {
      if (camera.getIsRecording()) {
        toggle.setChecked(true);
      }
      toggle.setEnabled(true);
      boolean person = new Random().nextBoolean(); //determines lights
      if (person) {
        changeImage.setVideoURI(Uri.parse(
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"));//.setImageResource(R.drawable.ic_person_added);
        changeImage.requestFocus();
        changeImage.start();
        mainController.controller.cameraChangeLights(person);
      } else {
        mainController.controller.cameraChangeLights(person);
        changeImage.setVideoURI(Uri.parse(
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"));//.setImageResource(R.drawable.ic_empty_person);
        changeImage.requestFocus();
        changeImage.start();
      }

    } else {
      toggle.setEnabled(false);
      changeImage.suspend();//.setImageResource(R.color.colorBlack);

    }

    onOff.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        VideoView changeImage = (VideoView) findViewById(R.id.cameraSees);

        if (onOff.isChecked()) {
          toggle.setEnabled(true);
          camera.setStatus(Status.NORMAL);
          updateDeviceInfo(deviceInfo, camera);

          boolean person = new Random().nextBoolean();
          if (person) {
            changeImage.setVideoURI(Uri.parse(
                "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"));//.setImageResource(R.drawable.ic_person_added);
            changeImage.requestFocus();
            changeImage.start();
            mainController.controller.cameraChangeLights(person);
          } else {
            mainController.controller.cameraChangeLights(person);
            changeImage.setVideoURI(Uri.parse(
                "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"));//.setImageResource(R.drawable.ic_empty_person);
            changeImage.requestFocus();
            changeImage.start();
          }

        } else {
          camera.setStatus(Status.OFF);
          changeImage.stopPlayback();//.setImageResource(R.color.colorBlack);
          toggle.setEnabled(false);
          updateDeviceInfo(deviceInfo, camera);
        }
      }
    });

    toggle.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (camera.getStatus() != Status.OFF) {

          try {
            camera.record();
            VideoView changeImage = (VideoView) findViewById(R.id.cameraSees);
            boolean person = new Random().nextBoolean();
            if (person) {
              changeImage.setVideoURI(Uri.parse(
                  "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"));//.setImageResource(R.drawable.ic_person_added);
              changeImage.requestFocus();
              changeImage.start();
              mainController.controller.cameraChangeLights(person);
            } else {
              mainController.controller.cameraChangeLights(person);
              changeImage.setVideoURI(Uri.parse(
                  "https://devstreaming-cdn.apple.com/videos/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"));//.setImageResource(R.drawable.ic_empty_person);
              changeImage.requestFocus();
              changeImage.start();
            }


          } catch (CameraFullException e) {
            if ((mainController.controller.getIsAdmin()
                && mainController.controller.getNotificationLevel() > 0) ||
                mainController.controller.getNotificationLevel() == 2) {
              Toast.makeText(CameraViewer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
          }
        }

      }
    });

  }

  public void updateDeviceInfo(TextView deviceInfo, Camera camera) {

    deviceInfo.setText(camera.toString() + "\n");
    deviceInfo.append("Status: " + camera.getStatus());
  }


}
