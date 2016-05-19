package ru.qatools.school.entity;


import lombok.Getter;

@Getter
public class Temperature {

    public Temperature(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }

    private String unit;

    private double value;
}
