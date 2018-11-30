package ru.job4j.servlets.model;

public class GeoPoint {

    private String id;
    private String name;

    public GeoPoint(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeoPoint point = (GeoPoint) o;

        if (id != null ? !id.equals(point.id) : point.id != null) return false;
        return name != null ? name.equals(point.name) : point.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
