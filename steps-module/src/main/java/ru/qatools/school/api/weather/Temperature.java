package ru.qatools.school.api.weather;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class Temperature {

    private String unit;
    private double value;

    public void setTemperature(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

}
