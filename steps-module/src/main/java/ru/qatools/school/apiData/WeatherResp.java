package ru.qatools.school.apiData;

import java.util.List;

/**
 * @author onegines (Eugene Kirienko)
 */
public class WeatherResp {

    public String city;

    public List<Temperature> temperatures;

    public String getCity() {
        return city;
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }
}
