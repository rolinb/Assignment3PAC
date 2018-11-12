package com.example.android.assignment3_pac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.devices.Lightbulb;

public class LightbulbController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lightbulb_controller);
        TableLayout layout = new TableLayout(this);


        if(mainController.controller.getLightbulbs() != null){

            for(Lightbulb l : mainController.controller.getLightbulbs()){
                final Lightbulb innerL = l; //need for inner class
                TableRow r = new TableRow(this);
                TextView DeviceInfo = new TextView(this);
                DeviceInfo.setText(l.toString());
                DeviceInfo.setMaxLines(3);
                DeviceInfo.setHorizontallyScrolling(false);
                DeviceInfo.setLayoutParams(new TableRow.LayoutParams(800,250) );
                ToggleButton onOff = new ToggleButton(this);
                if(l.getCondition()){
                    onOff.setChecked(true);
                }
                onOff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        innerL.toggle();
                    }
                });
                r.addView(DeviceInfo);
                r.addView(onOff);
                layout.addView(r);
            }
        }
        else{
            TextView mytext = new TextView(this);
            mytext.setText("No lightbulbs available speak to an Administrator");
            layout.addView(mytext);
        }
        setContentView(layout);

    }
}
