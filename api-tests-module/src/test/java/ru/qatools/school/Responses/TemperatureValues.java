package ru.qatools.school.Responses;

/**
 * @author ava1on
 */
public class TemperatureValues {
    private String unit;
    private double value;

    public TemperatureValues(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
