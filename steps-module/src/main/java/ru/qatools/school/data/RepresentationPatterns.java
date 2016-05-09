package ru.qatools.school.data;

/**
 * @author raipc
 */
public enum RepresentationPatterns {
    DATE_PATTERN("(\\d|1[012]) [AP]M, ([012]\\d|3[01]) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{2} \\([+-]?\\d{2}:[0-5]\\d\\)"),
    HUMAN_READABLE_DATE_PATTERN("{час по 12-часовому времени} {AM или PM} " +
            "{2 цифры даты} {3 лат. буквы месяца} {2 последние цифры года} " +
            "{часовой пояс в формате {+ или -}{часы}:{минуты}}"),
    TEMPERATURE_DIGITS_PATTERN("-?\\d{1,3}\\.\\d"),
    HUMAN_READABLE_DIGITS_PATTERN("{может быть минус}{1-3 цифры}.{один десятичный знак}"),
    CELSIUS("°C"),
    KELVIN("°K"),
    FAHRENHEIT("°F"),
    KAIF("°Kaif");

    private final String data;

    RepresentationPatterns(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
