package ru.qatools.school.steps.websteps;

public enum EXPECT_STRING {
    CELCIUM("°C"), KELVIN("°K"), FARINGEIT("°F"), KAIF("°Kaif"),
    SUNRISE_TIME("^[0-2][0-9]:[0-5][0-9]$"),
    SUNSET_TIME("^[0-2][0-9]:[0-5][0-9]$"),
    WIND_SPEED("^\\d+.?\\d+ m/s"),
    HUMIDITY_VALUE("^[0-9]+ %");

    private String value;

    EXPECT_STRING(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
