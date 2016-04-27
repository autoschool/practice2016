import java.util.List;

/**
 * Created by onegines (Eugene Kirienko)
 */
public class WeatherResp {
    public String city;

    public String getCity() {
        return city;
    }

    public List<Temperature> temperatures;

    public List<Temperature> getTemperatures() {
        return temperatures;
    }
}
