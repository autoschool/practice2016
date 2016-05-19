package ru.qatools.school.retrofit;

import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parameters {

    public static Object[][] limitsPositive() {
        return new Object[][]{
                {"1", 1, 200},
                {"0", 0, 200},
                {"273", 272, 200}
        };
    }

    public static Object[][] limitsNegative() {
        return new Object[][]{
                {"null", 404},
                {"-3", 404},
                {"%20", 404},
                {"city", 404}
        };
    }

    public static Object[][] queryString() {
        return new Object[][]{
                {"Moscow", "Moscow", 200},
                {"", null, 200},
                {"Mosc", "Moscow", 200},
                {"Moscow Saint Petersburg", null, 200},
                {"S", null, 200},
                {"%20", null, 200},
                {null, null, 200}
        };
    }

    public static List<List<Object>> cityRegionPositive() {
        List<List<Object>> queries
                = new ArrayList<>();
        HashMap<String, Boolean> listCity = new HashMap<>();
        HashMap<String, Boolean> listRegion = new HashMap<>();

        listCity.put("Moscow", true);
        listCity.put("", true);
        listCity.put("Mosc", true);
        listCity.put("Moscow Saint Petersburg", false);
        listCity.put("S", true);
        listCity.put("%20", false);
        listCity.put(null, false);

        listRegion.put("ru", true);
        listRegion.put("", true);
        listRegion.put("en", true);
        listRegion.put("eng ru", false);
        listRegion.put("e", true);
        listRegion.put("!", false);
        listRegion.put(null, false);

        for (Map.Entry<String, Boolean> cityEntry : listCity.entrySet()) {
            for (Map.Entry<String, Boolean> regionEntry : listRegion.entrySet()) {
                ArrayList<Object> list = new ArrayList<>();
                list.add(cityEntry.getKey());
                list.add(regionEntry.getKey());
                if (cityEntry.getValue() && regionEntry.getValue()) {
                    list.add(HttpStatus.SC_OK);
                } else {
                    list.add(HttpStatus.SC_BAD_REQUEST);
                }
                    queries.add(list);
            }
        }
        return queries;
    }


}
