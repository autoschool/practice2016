package ru.qatools.school.apiData;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public enum URI {
    BASE_URI("http://weather.lanwen.ru/"),
    BASE_PATH("api"),
    LIMIT_PARAM("limit"),
    QUERY_PARAM("query"),
    CITY_PARAM("city"),
    REGION_PARAM("region"),
    CITIES_RESOURCE("cities"),
    INIT_RESOURCE("init"),
    SUGGEST_RESOURCE("suggest"),
    WEATHER_RESOURCE("weather");

    private final String URIValue;

    URI (String URIValue){
        this.URIValue = URIValue;
    }

    public String getValue(){
        return URIValue;
    }

    @Override
    public String toString(){
        return URIValue;
    }
}
