package ru.qatools.school;

/**
 * Created by vananos on 4/20/16.
 */
public enum RepresentationPatterns {

    DATE_TIME("\\d{1,2} (P|A)M, \\d{1,2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{2} \\((?!((\\+|\\-)00:00))(00:00|(\\+|\\-)\\d{2}:\\d{2})\\)"),
    CELSIUS("\\-?\\d{1,2}\\.\\d °C"),
    FAHRENHEIT("\\-?\\d{1,3}\\.\\d °F"),
    KELVIN("\\d{3}\\.\\d °K"),
    KAIF("\\d{2}\\.\\d °Kaif"),
    SUNRISE("Sunrise\\s\\d{2}:\\d{2}"),
    SUNSET("Sunset\\s\\d{2}:\\d{2}"),
    WIND("Wind\\s\\d{1,3}(\\.\\d{2})? m\\/s"),
    HUMIDITY("Humidity\\s\\d{1,2} %");


    private final String pattern;

    RepresentationPatterns(String s) {
        pattern = s;
    }

    public String getPattern() {
        return pattern;
    }

    public String toString() {
        return pattern.toString();
    }
}
