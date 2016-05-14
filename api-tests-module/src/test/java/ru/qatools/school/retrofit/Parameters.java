package ru.qatools.school.retrofit;

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
        //listCity.add("");
        //listCity.add("Mosc");
        listCity.put("Moscow Saint Petersburg", false);
        //listCity.add("S");
        //listCity.add("%20");
        //listCity.add(null);

        listRegion.put("ru", true);
        listRegion.put("", true);
        //listRegion.add("en");
        //listRegion.add("eng ru");
        //listRegion.add("e");
        //listRegion.add("!");
        //listRegion.add(null);

        for (Map.Entry<String, Boolean> cityEntry : listCity.entrySet()) {
            for (Map.Entry<String, Boolean> regionEntry : listRegion.entrySet()) {
                ArrayList<Object> list = new ArrayList<>();
                list.add(cityEntry.getKey());
                list.add(regionEntry);
                if (cityEntry.getValue() && regionEntry.getValue()) {
                    list.add(200);
                } else {
                    list.add(400);
                }
                    queries.add(list);
            }
        }
        return queries;
    }


}
