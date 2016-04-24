package ru.qatools.school.data;

/**
 * Created by @totallynotkate (Kate Kocijevska).
 */
public enum RegexPattern {
    TIME_AND_DATE("\\d{1,2} (A|P)M, \\d{1,2} (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{2} " +
            "\\((00:00|(\\+|\\-)\\d{2}:00)\\)"),
    CELSIUS("\\-?\\d+\\.\\d °C"),
    KELVIN("\\d{3}\\.\\d °K"),
    FAHRENHEIT("\\-?\\d{1,3}\\.\\d °F"),
    KAIF("\\d{2}\\.\\d °Kaif"),
    SUNRISE("Sunrise\\s\\d{2}:\\d{2}"),
    SUNSET("Sunset\\s\\d{2}:\\d{2}"),
    WIND("Wind\\s\\d{1,3}(\\.\\d{2})? m\\/s"),
    HUMIDITY("Humidity\\s\\d{1,2} %");

    private final String expression;

    RegexPattern(String expression) {
        this.expression = expression;
    }

    public String getRegexPattern() {
        return expression;
    }

    @Override
    public String toString() {
        return expression;
    }
}
