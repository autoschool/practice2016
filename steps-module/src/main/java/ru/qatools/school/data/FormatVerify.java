package ru.qatools.school.data;

import java.util.regex.Pattern;

/**
 * Created by Gavrilov_IS on 26.04.2016.
 */
public class FormatVerify {
    private static String regDate = "(1[012]|[1-9])(\\s)?(AM|PM)(,\\s)([012]?[0-9]|3[01])(\\s)(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)(\\s)\\d{2}(\\s)(\\([+-]\\d{2}:\\d{2}\\))";
    private static String regCelsius = "-?\\d{1,2}\\.(\\d)(\\s)(°C)";
    private static String regKelvin = "\\d{1,3}\\.(\\d)(\\s)(°K)";
    private static String regForenheit = "-?\\d{1,2}\\.(\\d)(\\s)(°F)";
    private static String regKaif = "-?\\d{1,2}\\.(\\d)(\\s)(°Kaif)";
    private static String regSunriseTime = "Sunrise\\s\\d{2}:\\d{2}";
    private static String regSunsetTime = "Sunset\\s\\d{2}:\\d{2}";
    private static String regWind = "Wind\\s\\d{1,2}(\\.\\d{1,2})?\\s(m\\/s)";
    private static String regHumidity = "Humidity\\s\\d{1,2}?\\s(\\%)";


    public String getRegExpForDate(){
        return regDate;
    }

    public String getRegExpForCelsius(){
        return regCelsius;
    }

    public String getRegExpForKelvin(){
        return regKelvin;
    }

    public String getRegExpForFarenheit(){
        return regForenheit;
    }

    public String getRegExpForKaif(){
        return regKaif;
    }

    public String getRegExpForSunriseTime(){
        return regSunriseTime;
    }

    public String getRegExpForSunsetTime(){
        return regSunsetTime;
    }

    public String getRegExpForWind(){
        return regWind;
    }

    public String getRegExpForHumidity(){
        return regHumidity;
    }



}
