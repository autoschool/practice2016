package ru.qatools.school.api.suggest;

import javax.persistence.Column;

/**
 * @author gladnik (Nikolai Gladkov)
 */
public class SuggestCity {

    @Column(name = "id")
    private long id;
    @Column(name = "uid")
    private long uid;
    @Column(name = "name")
    private String name;
    @Column(name = "country")
    private String country;

    public SuggestCity(long id, long uid, String name, String country) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.country = country;
    }

    public SuggestCity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuggestCity)) return false;

        SuggestCity that = (SuggestCity) o;

        if (id != that.id) return false;
        if (uid != that.uid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;
    }

    public long getId() {
        return id;
    }

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

}
