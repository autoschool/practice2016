package ru.qatools.school.apitests.data;

/**
 * @author Gavrilov_IS
 */
public class Temperature {
    private String unit;
    private double value;

    public String getUnit(){
        return unit;
    }

    public double getValue(){
        return value;
    }

    @Override
    public String toString(){
        return "unit = " + unit + "; digit = " + value + "; \r\n";
    }


}
