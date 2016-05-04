package ru.qatools.school.Responses;

import java.util.List;
import java.util.Map;

/**
 * @author ava1on
 */
public class WeatherResponse {
    private String city;
    private String daypart;
    private long dt;
    private int humidity;
    private long sunset;
    private long sunrise;
    private TemperatureValues[] temperatures;
    private int weatherCode;
    private double wind;

    public WeatherResponse(String city, String daypart, long dt, int humidity, long sunset, long sunrise,
                           TemperatureValues[] temperatures, int weatherCode, double wind) {
        this.city = city;
        this.daypart = daypart;
        this.dt = dt;
        this.humidity = humidity;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.temperatures = temperatures;
        this.weatherCode = weatherCode;
        this.wind = wind;
    }

    public String getCity() {
        return city;
    }

    public String getDaypart() {
        return daypart;
    }

    public long getDt() {
        return dt;
    }

    public int getHumidity() {
        return humidity;
    }

    public long getSunset() {
        return sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    public TemperatureValues[] getTemperatures() {
        return temperatures;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public double getWind() {
        return wind;
    }

    public Double recalculateCelsiusToFahrenheit(){
        return (this.getTemperatures()[0].getValue()*9/5+32);
    }
}
