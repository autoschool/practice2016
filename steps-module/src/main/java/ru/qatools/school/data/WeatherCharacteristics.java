package ru.qatools.school.data;

/**
 * @author raipc
 */
public enum WeatherCharacteristics {
    SUNRISE("Sunrise"),
    SUNSET("Sunset"),
    WIND("Wind"),
    HUMIDITY("Humidity");

    private final String data;

    WeatherCharacteristics(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
