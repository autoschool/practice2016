package ru.qatools.school.retrofit;

import java.util.ArrayList;
import java.util.List;

public class Parameters {

    public static Object[][] limitsPositive() {
        return new Object[][]{
                {"1", 1, 200},
                {"0", 0, 200},
                {"273", 272, 200}
        };
    }

    public static Object[][] limitsNegative() {
        return new Object[][] {
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

    public static List<Object[]> cityRegion() {
        List<Object[]> queries
                = new ArrayList<Object[]>();
        ArrayList<String> listCity = new ArrayList<>();
        ArrayList<String> listRegion = new ArrayList<>();

        listCity.add("Moscow");
        listCity.add("");
        listCity.add("Mosc");
        listCity.add("Moscow Saint Petersburg");
        listCity.add("S");
        listCity.add("%20");
        listCity.add(null);

        listRegion.add("ru");
        listRegion.add("");
        listRegion.add("en");
        listRegion.add("eng ru");
        listRegion.add("e");
        listRegion.add("!");
        listRegion.add(null);

        for (String city : listCity) {
            for (String region : listRegion) {
                queries.add(new Object[]{city, region, 200});
            }
        }
        return queries;
    }


}
