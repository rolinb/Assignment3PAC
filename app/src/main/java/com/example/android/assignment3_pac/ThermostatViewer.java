package com.example.android.assignment3_pac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.android.assignment3_pac.assn2.mainController;
import com.example.android.assignment3_pac.assn2.part1.Status;
import com.example.android.assignment3_pac.assn2.part1.devices.Temperature;
import com.example.android.assignment3_pac.assn2.part1.devices.Thermostat;

import java.util.Random;
import java.util.UUID;

public class ThermostatViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat_viewer);

        Random random = new Random();

        int outsideTrigger = random.nextInt(10) + 1;


        Intent intent = getIntent();
        String uuid = intent.getStringExtra("UUID");

        final Thermostat thermostat = (Thermostat) mainController.controller.getDevice(UUID.fromString(uuid));

        //1 in 10 chance to trigger outside temp auto change temperature
        if (outsideTrigger == 1 && thermostat.getStatus() != Status.OFF){
            outsideTempTrigger(thermostat);
        }



        TextView textview = findViewById(R.id.thermostatInfo);
        textview.setText(thermostat.toString());

        final NumberPicker numberPicker = findViewById(R.id.temperatureInput);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setValue((int) thermostat.getTemp().getTemperature());

        final Temperature.Unit unit = thermostat.getTemp().getUnit();
        final RadioGroup units = findViewById(R.id.temperatureUnits);
        final RadioButton celsiusButton = findViewById(R.id.celsiusButton);
        final RadioButton fahrenheitButton = findViewById(R.id.fahrenheitButton);

        if(unit == Temperature.Unit.CELSIUS){
            units.check(R.id.celsiusButton);
        }
        else{
            units.check(R.id.fahrenheitButton);
        }

        final ToggleButton onOff = findViewById(R.id.onOffToggle);

        if(thermostat.getStatus() == Status.OFF){
            numberPicker.setEnabled(false);
            units.setEnabled(false);
            celsiusButton.setEnabled(false);
            fahrenheitButton.setEnabled(false);
        }
        else{
            onOff.setChecked(true);
        }

        onOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onOff.isChecked()){
                    numberPicker.setEnabled(true);
                    units.setEnabled(true);
                    celsiusButton.setEnabled(true);
                    fahrenheitButton.setEnabled(true);
                    thermostat.setStatus(Status.NORMAL);
                }else{
                    numberPicker.setEnabled(false);
                    units.setEnabled(false);
                    celsiusButton.setEnabled(false);
                    fahrenheitButton.setEnabled(false);
                    thermostat.setStatus(Status.OFF);
                }
            }
        });

        celsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tmp = thermostat.getTemp().getTemperature();
                tmp = (tmp - 32) * 5.0/9.0;
                try {
                    thermostat.setTemp(new Temperature(tmp, Temperature.Unit.CELSIUS));
                    numberPicker.setValue((int) tmp);
                }catch(Temperature.TemperatureOutofBoundsException e){
                    Toast.makeText(ThermostatViewer.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        fahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tmp = thermostat.getTemp().getTemperature();
                tmp = (tmp * 9.0 / 5.0) + 32;
                try {
                    thermostat.setTemp(new Temperature(tmp, Temperature.Unit.FAHRENHEIT));
                    numberPicker.setValue((int) tmp);
                }catch(Temperature.TemperatureOutofBoundsException e){
                    Toast.makeText(ThermostatViewer.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                try {
                    int tmp = numberPicker.getValue();

                    thermostat.setTemp(new Temperature(tmp, unit));
                    numberPicker.setValue(tmp);
                }catch(Temperature.TemperatureOutofBoundsException e){
                    Toast.makeText(ThermostatViewer.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void outsideTempTrigger(Thermostat t){
        try {
            t.setTemp(new Temperature(72, Temperature.Unit.FAHRENHEIT));
            mainController.controller.getHub().alert(t, "Temp change triggered by outside");
        }catch(Temperature.TemperatureOutofBoundsException e){
            Toast.makeText(ThermostatViewer.this, e.toString(), Toast.LENGTH_SHORT).show();

        }
    }


}
