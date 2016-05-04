package ru.qatools.school.Responses;

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

    public TemperatureValues[] getTemperatures() {
        return temperatures;
    }

    public Double recalculateCelsiusToFahrenheit(){
        return (this.getTemperatures()[0].getValue()*9/5+32);
    }
}
