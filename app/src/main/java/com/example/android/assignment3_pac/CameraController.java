package com.example.android.assignment3_pac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Hub;
import com.example.android.assignment3_pac.assn2.part1.Mediator;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;

import org.w3c.dom.Text;

import java.util.HashMap;

public class CameraController extends AppCompatActivity implements View.OnClickListener {

    HashMap<Integer, Device> whichCamera = new HashMap<Integer, Device>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        TableLayout layout = new TableLayout(this);

        int mapper = 0;

        if(mainController.controller.getCameras() != null) {


            for (Camera c : mainController.controller.getCameras()) {
                whichCamera.put(mapper, c);
                TableRow row = new TableRow(this);
                TextView DeviceInfo = new TextView(this);
                DeviceInfo.setText(c.toString());
                DeviceInfo.setMaxLines(3);
                DeviceInfo.setHorizontallyScrolling(false);
                DeviceInfo.setLayoutParams(new TableRow.LayoutParams(800,250) );
                ImageButton cameraButton = new ImageButton(this);
                cameraButton.setImageResource(R.drawable.ic_camera);
                cameraButton.setMinimumWidth(250);
                cameraButton.setTag(c);
                cameraButton.setOnClickListener(this);
                row.addView(cameraButton);
                row.addView(DeviceInfo);
                row.setHorizontalScrollBarEnabled(false);
                layout.addView(row);

            }

        }
        else{
            TextView mytext = new TextView(this);
            mytext.setText("No Cameras available speak to an Administrator");
            layout.addView(mytext);
        }
        setContentView(layout);

        /*
        Mediator hub = new Hub();
        int a = (int) Math.round(Math.random()*10);
        for (int i=0; i< a; i++){
            Device c = new Camera(hub);
        }
        for ( Device d : ((Hub) hub).getDevices().values()){
            if (d instanceof Camera){

                TextView mytext = new TextView(this);
                mytext.setText("OMG ITS A CAMERA");
                layout.addView(mytext);
            }
        }
        */


        //ImageView imageView = new ImageView(findViewById())
    }

    public void onClick(View view){

        Intent intent = new Intent(this, cameraViewer.class);
        intent.putExtra("UUID", ((Device) view.getTag()).getIdentifier().toString());
        startActivity(intent);
    }


}
