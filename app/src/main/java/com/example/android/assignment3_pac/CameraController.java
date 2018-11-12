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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        TableLayout layout = new TableLayout(this);

        int mapper = 0;

        if(mainController.controller.getCameras() != null) {

            int i = 0;

            for (Camera c : mainController.controller.getCameras()) {
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
                cameraButton.setContentDescription("Button " + i);
                cameraButton.setOnClickListener(this);
                row.addView(cameraButton);
                row.addView(DeviceInfo);
                row.setHorizontalScrollBarEnabled(false);
                layout.addView(row);
                i++;
            }

        }
        else{
            TextView mytext = new TextView(this);
            mytext.setText("No Cameras available speak to an Administrator");
            layout.addView(mytext);
        }
        setContentView(layout);


    }

    public void onClick(View view){

        Intent intent = new Intent(this, cameraViewer.class);
        intent.putExtra("UUID", ((Device) view.getTag()).getIdentifier().toString());
        startActivity(intent);
    }


}