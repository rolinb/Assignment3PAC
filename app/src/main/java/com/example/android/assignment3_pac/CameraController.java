package com.example.android.assignment3_pac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;

public class CameraController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Mediator hub = new Hub();
        int a = (int) Math.round(Math.random()*10);
        for (int i=0; i< a; i++){
            Device c = new Camera(hub);
        }
        LinearLayout layout = new LinearLayout(this);
        for ( Device d : ((Hub) hub).getDevices().values()){
            if (d instanceof Camera){

                TextView mytext = new TextView(this);
                mytext.setText("OMG ITS A CAMERA");
                layout.addView(mytext);
            }
        }
        setContentView(layout);


        //ImageView imageView = new ImageView(findViewById())
    }


}
