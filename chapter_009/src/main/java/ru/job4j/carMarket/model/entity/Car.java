package ru.job4j.carMarket.model.entity;

public class Car {
    private int id;
    private String mark;
    private String model;
    private String transmission;
    private String bodyType;
    private int yearIssue;
    private int price;
    private String pathImage;
    private boolean isSold;
    private User user;

    public Car() {
    }

    public Car(String mark, String model, String transmission, String bodyType, int yearIssue, int price, String pathImage, User user) {
        this.mark = mark;
        this.model = model;
        this.transmission = transmission;
        this.bodyType = bodyType;
        this.yearIssue = yearIssue;
        this.price = price;
        this.pathImage = pathImage;
        this.isSold = false;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getYearIssue() {
        return yearIssue;
    }

    public void setYearIssue(int yearIssue) {
        this.yearIssue = yearIssue;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public boolean isIsSold() {
        return isSold;
    }

    public void setIsSold(boolean sold) {
        this.isSold = sold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", transmission='" + transmission + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", yearIssue=" + yearIssue +
                ", price=" + price +
                ", pathImage='" + pathImage + '\'' +
                ", sold=" + isSold +
                ", user=" + user.getLogin() +
                '}';
    }
}
