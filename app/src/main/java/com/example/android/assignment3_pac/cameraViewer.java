package com.example.android.assignment3_pac;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.android.assignment3_pac.R;

import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Status;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.CameraFullException;

import java.util.Random;
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
        final Camera camera = (Camera) mainController.controller.getDevice(UUID.fromString(uuid));



        final TextView deviceInfo = findViewById(R.id.DeviceInformation);
        updateDeviceInfo(deviceInfo, camera);

        final ToggleButton toggle = findViewById(R.id.Record);
        final ToggleButton onOff = findViewById(R.id.cameraOnOff);

        if(camera.getStatus() == Status.NORMAL){
            onOff.setChecked(true);

        }

        ImageView changeImage = findViewById(R.id.cameraSees);
        if(onOff.isChecked()) {
            if(camera.getIsRecording()){
                toggle.setChecked(true);
            }
            toggle.setEnabled(true);
            boolean person = new Random().nextBoolean(); //determines lights
            if(person) {
                changeImage.setImageResource(R.drawable.ic_person_added);
                mainController.controller.cameraChangeLights(person);
            }
            else{
                mainController.controller.cameraChangeLights(person);
                changeImage.setImageResource(R.drawable.ic_empty_person);
            }
        }
        else{
            toggle.setEnabled(false);
            changeImage.setImageResource(R.color.colorBlack);

        }

        onOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView changeImage = findViewById(R.id.cameraSees);

                if (onOff.isChecked()){
                    toggle.setEnabled(true);
                    camera.setStatus(Status.NORMAL);
                    updateDeviceInfo(deviceInfo, camera);

                    boolean person = new Random().nextBoolean();
                    if (person) {
                        changeImage.setImageResource(R.drawable.ic_person_added);
                        mainController.controller.cameraChangeLights(person);
                    } else {
                        mainController.controller.cameraChangeLights(person);
                        changeImage.setImageResource(R.drawable.ic_empty_person);
                    }

                }
                else{
                    camera.setStatus(Status.OFF);
                    changeImage.setImageResource(R.color.colorBlack);
                    toggle.setEnabled(false);
                    updateDeviceInfo(deviceInfo, camera);
                }
            }
        });

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camera.getStatus() != Status.OFF) {


                    try {
                        camera.record();
                        ImageView changeImage = findViewById(R.id.cameraSees);
                            boolean person = new Random().nextBoolean();
                            if (person) {
                                changeImage.setImageResource(R.drawable.ic_person_added);
                                mainController.controller.cameraChangeLights(person);
                            } else {
                                mainController.controller.cameraChangeLights(person);
                                changeImage.setImageResource(R.drawable.ic_empty_person);
                            }


                    } catch (CameraFullException e) {
                        Toast.makeText(cameraViewer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void updateDeviceInfo(TextView deviceInfo, Camera camera){

        deviceInfo.setText(camera.toString() + "\n");
        deviceInfo.append("Status: " + camera.getStatus());
    }





}
