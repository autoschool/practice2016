package ru.qatools.school.utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import retrofit2.Response;
import ru.qatools.school.apiData.CitiesJSON;

import java.util.List;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class HttpCodeMatcher extends TypeSafeMatcher<Response<List<CitiesJSON>>> {

    private final int status;

    private HttpCodeMatcher(int status){
        this.status = status;
    }

    public static HttpCodeMatcher responseHasHttpStatus(int status){
        return new HttpCodeMatcher(status);
    }

    @Override
    protected boolean matchesSafely(Response<List<CitiesJSON>> response) {
        return response.code() == status;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Response should have status ").appendValue(status);
    }

    @Override
    public void describeMismatchSafely(Response<List<CitiesJSON>> response, Description description){
        description.appendText("was ").appendValue(response.code());
    }
}
