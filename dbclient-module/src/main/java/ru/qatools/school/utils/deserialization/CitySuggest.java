package ru.qatools.school.utils.deserialization;

/**
 * @author totallynotkate (Kate Kocijevska)
 */
public class CitySuggest {
    private int id;
    private String name;
    private String country;
    private int uid;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitySuggest suggest = (CitySuggest) o;

        if (id != suggest.id) return false;
        if (uid != suggest.uid) return false;
        if (name != null ? !name.equals(suggest.name) : suggest.name != null) return false;
        return country != null ? country.equals(suggest.country) : suggest.country == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + uid;
        return result;
    }
}
