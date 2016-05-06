package ru.qatools.school.Responses;

/**
 * @author ava1on
 */
public class WeatherResponse {
    private TemperatureValues[] temperatures;

    public WeatherResponse(TemperatureValues[] temperatures) {
        this.temperatures = temperatures;
    }

    public TemperatureValues[] getTemperatures() {
        return temperatures;
    }

    public Double recalculateCelsiusToFahrenheit(){
        return (this.getTemperatures()[0].getValue()*9/5+32);
    }
}
