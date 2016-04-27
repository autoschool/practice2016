package ru.qatools.school.Retrofit;

import java.util.List;

/**
 * Created by onodee on 27.04.2016.
 */
public class WeatherResp {
    private String city;
    private Object daypart;
    private List<Temperature> temperatures;
    private double weathercode;
    private double sunset;
    private double sunrise;
    private double dt;
    private double humidity;
    private double wind;

    public String getCity() {
        return city;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public Object getDaypart() {
        return daypart;
    }

    public double getWeathercode() {
        return weathercode;
    }

    public double getSunset() {
        return sunset;
    }

    public double getSunrise() {
        return sunrise;
    }

    public double getDt() {
        return dt;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWind() {
        return wind;
    }
}
