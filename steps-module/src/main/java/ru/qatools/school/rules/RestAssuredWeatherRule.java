package ru.qatools.school.rules;

import com.jayway.restassured.RestAssured;
import org.junit.rules.ExternalResource;

import static ru.qatools.school.WebsiteData.API_PATH;
import static ru.qatools.school.WebsiteData.MAIN_PAGE;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class RestAssuredWeatherRule extends ExternalResource {

    protected void before() {
        RestAssured.baseURI = MAIN_PAGE;
        RestAssured.basePath = API_PATH;
    }

}
