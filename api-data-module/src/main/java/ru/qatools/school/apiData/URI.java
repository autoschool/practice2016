package ru.qatools.school.apiData;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public enum URI {
    BASE_URI("http://weather.lanwen.ru/"),
    BASE_PATH("api"),
    LIMIT_PARAMETER("limit"),
    CITIES_RESOURCE("cities");

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
