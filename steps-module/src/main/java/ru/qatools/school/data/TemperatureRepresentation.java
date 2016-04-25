package ru.qatools.school.data;

/**
 * @author raipc
 */
public enum TemperatureRepresentation {
    DIGITS_PATTERN("-?\\d{1,3}\\.\\d"),
    HUMAN_READABLE_DIGITS_PATTERN("{может быть минус}{1-3 цифры}.{один десятичный знак}"),
    CELSIUS("°C"),
    KELVIN("°K"),
    FAHRENHEIT("°F"),
    KAIF("°Kaif");

    private final String data;

    TemperatureRepresentation(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
