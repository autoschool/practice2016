package ru.qatools.school.api.weather;

import java.util.List;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class WeatherResponse {

    private String city;
    private Daypart daypart;
    private int weathercode;
    private long sunset;
    private long sunrise;
    private long dt;
    private int humidity;
    private double wind;
    private List<Temperature> temperatures;

    public void setWeatherResponse(String city, Daypart daypart, int weathercode,
                                   long sunset, long sunrise, long dt,
                                   int humidity, double wind, List<Temperature> temperatures) {
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

    public String getCity() {
        return city;
    }

    public Daypart getDaypart() {
        return daypart;
    }

    public int getWeathercode() {
        return weathercode;
    }

    public long getSunset() {
        return sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getDt() {
        return dt;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWind() {
        return wind;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

}
