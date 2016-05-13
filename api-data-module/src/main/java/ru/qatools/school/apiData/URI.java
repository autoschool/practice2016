package ru.qatools.school.apiData;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public enum URI {
    BASE_URI("http://weather.lanwen.ru/"),
    BASE_PATH("api"),

    CITIES_RESOURCE("cities"),
    LIMIT_PARAMETER("limit"),

    SUGGEST_RESOURCE("suggest"),
    QUERY_PARAMETER("query");

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
