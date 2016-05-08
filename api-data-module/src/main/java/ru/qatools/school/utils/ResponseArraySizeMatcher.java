package ru.qatools.school.utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import retrofit2.Response;
import ru.qatools.school.apiData.CityJSON;

import java.util.List;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class ResponseArraySizeMatcher extends TypeSafeMatcher<Response<List<CityJSON>>>{

    private int size;

    private ResponseArraySizeMatcher(int size){
        this.size = size;
    }

    public static ResponseArraySizeMatcher responseHasArraySize(int size){
        return new ResponseArraySizeMatcher(size);
    }

    @Override
    protected boolean matchesSafely(Response<List<CityJSON>> response) {
        return response.body().size() == size;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Response should consist of a JSON array with size ").appendValue(size);
    }

    @Override
    public void describeMismatchSafely(Response<List<CityJSON>> response, Description description){
        description.appendText("array size was ").appendValue(response.body().size());
    }
}
