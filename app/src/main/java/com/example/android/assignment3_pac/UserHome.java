package com.example.android.assignment3_pac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class UserHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void goToCamera(View v){
        Intent intent = new Intent(this, CameraController.class);
        startActivity(intent);
    }

    public void goToSmartPlug(View v){
        Intent intent = new Intent(this, SmartPlugController.class);
        startActivity(intent);
    }

    public void goToLightbulb(View v){
        Intent intent = new Intent(this, LightbulbController.class);
        startActivity(intent);
    }


}
