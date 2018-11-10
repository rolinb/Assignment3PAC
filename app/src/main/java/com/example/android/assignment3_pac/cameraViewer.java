package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;

import java.util.UUID;

public class cameraViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String uuid = intent.getStringExtra("UUID");
        Camera camera = (Camera) mainController.controller.getDevice(UUID.fromString(uuid));

        TextView deviceInfo = findViewById(R.id.DeviceInformation);
        deviceInfo.append(camera.toString() + "\n");
        deviceInfo.append("Status: " + camera.getStatus());



    }



}
