package ru.qatools.school.data;

/**
 * Created by sergey.petrov on 25.04.2016.
 */
public enum DataPatterns {
    CELSIUS("^-?\\d{1,2}\\.\\d °C$"),
    KELVIN("^[1-3]\\d{2}\\.\\d °K$"),
    FAHRENHEIT("^-?[1]?\\d{1,2}\\.\\d °F$"),
    KAIF("^-?\\d{1,2}\\.\\d °Kaif$"),
    SUNRISE("^Sunrise\\s[0-2][0-9]:[0-5][0-9]$"),
    SUNSET("^Sunset\\s[0-2][0-9]:[0-5][0-9]$"),
    WIND("^Wind\\s\\d{1,} m\\/s$"),
    HUMIDITY("^Humidity\\s[0-9]{1,} %$");

    private final String pattern;

    DataPatterns(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern(){
        return pattern.toString();
    }

    @Override
    public String toString(){
        return pattern;
    }

}
