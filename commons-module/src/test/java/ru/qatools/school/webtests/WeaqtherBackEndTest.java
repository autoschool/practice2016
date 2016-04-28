package ru.qatools.school.webtests;


import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by thebaldsoprano on 28.04.16.
 */
public class WeaqtherBackEndTest {
    @Test
    public void urlIsOK() throws Exception{
        given().baseUri("http://weather.lanwen.ru")
                .get().then().assertThat().statusCode(HttpStatus.SC_OK);
    }
}
