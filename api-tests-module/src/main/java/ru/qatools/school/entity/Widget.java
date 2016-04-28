package ru.qatools.school.entity;

import lombok.Getter;

import java.util.Date;
import java.util.List;


@Getter
public class Widget {


    private String city;
    private DAYPART daypart;
    private int weathercode;
    private Date sunset;
    private Date sunrise;
    private Date dt;
    private int humidity;
    private double wind;
    private List<Temperature> temperatures;


    public Widget(String city, DAYPART daypart,
                  int weathercode, Date sunset, Date sunrise, Date dt, int humidity,
                  double wind, List<Temperature> temperatures) {
        this.city = city;
        this.daypart = daypart;
        this.weathercode = weathercode;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.dt = dt;
        this.humidity = humidity;
        this.wind = wind;
        this.temperatures = temperatures;
    }
}
