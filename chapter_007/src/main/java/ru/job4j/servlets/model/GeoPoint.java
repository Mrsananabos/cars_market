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
    public String toString() {
        return "GeoPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
