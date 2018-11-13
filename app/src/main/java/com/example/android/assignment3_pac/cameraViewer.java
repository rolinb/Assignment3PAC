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

        TextView deviceInfo = findViewById(R.id.DeviceInformation);
        deviceInfo.append(camera.toString() + "\n");
        deviceInfo.append("Status: " + camera.getStatus());

        ToggleButton toggle = findViewById(R.id.Record);
        ImageView changeImage = findViewById(R.id.cameraSees);
        if(camera.getIsRecording()) {
            toggle.setChecked(true);
            boolean person = new Random().nextBoolean();
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
            changeImage.setImageResource(R.color.colorBlack);

        }

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    camera.record();
                    ImageView changeImage = findViewById(R.id.cameraSees);
                    if(camera.getIsRecording()){
                        boolean person = new Random().nextBoolean();
                        if(person) {
                            changeImage.setImageResource(R.drawable.ic_person_added);
                            mainController.controller.cameraChangeLights(person);
                        }
                        else{
                            mainController.controller.cameraChangeLights(person);
                            changeImage.setImageResource(R.drawable.ic_empty_person);
                        }
                    }else {
                        changeImage.setImageResource(R.color.colorBlack);
                    }

                }catch(CameraFullException e){
                    Toast.makeText(cameraViewer.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }





}
