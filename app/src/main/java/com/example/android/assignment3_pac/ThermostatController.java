package com.example.android.assignment3_pac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.devices.Camera;
import com.example.android.assignment3_pac.assn2.part1.devices.Device;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;

public class ThermostatController extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat_controller);

        TableLayout layout = new TableLayout(this);


        if(mainController.controller.getThermostats() != null) {

            int i = 0;

            for (Thermostat t : mainController.controller.getThermostats()) {
                TableRow row = new TableRow(this);
                TextView DeviceInfo = new TextView(this);
                DeviceInfo.setText(t.toString());
                DeviceInfo.setMaxLines(3);
                DeviceInfo.setHorizontallyScrolling(false);
                DeviceInfo.setLayoutParams(new TableRow.LayoutParams(800,250) );
                ImageButton thermostatButton = new ImageButton(this);
                thermostatButton.setImageResource(R.drawable.ic_thermostat);
                thermostatButton.setMinimumWidth(250);
                thermostatButton.setTag(t);
                thermostatButton.setContentDescription("Button " + i);
                thermostatButton.setOnClickListener(this);
                row.addView(thermostatButton);
                row.addView(DeviceInfo);
                row.setHorizontalScrollBarEnabled(false);
                layout.addView(row);
                i++;
            }

        }
        else{
            TextView mytext = new TextView(this);
            mytext.setText("No Thermostats available speak to an Administrator");
            layout.addView(mytext);
        }
        setContentView(layout);
    }

    public void onClick(View view){

        Intent intent = new Intent(this, ThermostatViewer.class);
        intent.putExtra("UUID", ((Device) view.getTag()).getIdentifier().toString());
        startActivity(intent);
    }
}
