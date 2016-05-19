package ru.qatools.school.apiData;

public enum Country {
    RUSSIA("ru"),
    FRANCE("fr");

    private final String country;

    Country(String country){
        this.country = country;
    }

    @Override
    public String toString(){
        return country;
    }
}
