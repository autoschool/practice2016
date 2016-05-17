package ru.qatools.school.data;

/**
 * @author ava1on
 */
public enum DataPatterns {
    CELSIUS("^-?\\d{1,2}\\.\\d °C$"),
    KELVIN("^[1-3]\\d{2}\\.\\d °K$"),
    FAHRENHEIT("^-?[1]?\\d{1,2}\\.\\d °F$"),
    KAIF("^-?\\d{1,2}\\.\\d °Kaif$"),
    SUNRISE("^Sunrise\\s[0-2][0-9]:[0-5][0-9]$"),
    SUNSET("^Sunset\\s[0-2][0-9]:[0-5][0-9]$"),
    WIND("^Wind\\s\\d{1,}\\.?\\d{0,2}? m\\/s$"),
    HUMIDITY("^Humidity\\s[0-9]{1,} %$"),
    TIME_DATE("^1?\\d [A|P]M, [0-3]\\d \\w{3} \\d{2}");

    private final String pattern;

    DataPatterns(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern(){
        return pattern;
    }

    @Override
    public String toString(){
        return pattern;
    }

}
