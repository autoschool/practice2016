package ru.qatools.school.apidata;

/**
 * @author onegines (Eugene Kirienko)
 */
public class SuggestResp {
    private long id;
    private long uid;
    private String name;
    private String country;

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

    public void setId(long id) {
        this.id = id;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "SuggestResp{" +
                "id=" + id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuggestResp that = (SuggestResp) o;

        if (id != that.id) return false;
        if (uid != that.uid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
