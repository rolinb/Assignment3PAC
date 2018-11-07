package com.example.android.assignment3_pac.assn2.part1.devices;

public class Temperature {

  public enum Unit {
    CELSIUS, FAHRENHEIT
  }

  private Unit unit = Unit.CELSIUS;
  private double temperature = 0.0;

  public Temperature(double temperature, Unit unit) throws TemperatureOutofBoundsException {
    if (temperature > 1000) {
      throw new TemperatureOutofBoundsException("Absurd temperature!");
    }

    this.temperature = temperature;
    this.unit = unit;
  }

  public double getTemperature() {
    return temperature;
  }

  public Unit getUnit() {
    return unit;
  }

  public class TemperatureOutofBoundsException extends Exception {
    public TemperatureOutofBoundsException(String s) {
      super(s);
    }
  }
}
