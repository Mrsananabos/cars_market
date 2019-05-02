package ru.job4j.carMarket.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "mark")
    private String mark;
    @Column(name = "model")
    private String model;
    @Column(name = "transmission")
    private String transmission;
    @Column(name = "body_type")
    private String bodyType;
    @Column(name = "year_issue")
    private int yearIssue;
    @Column(name = "price")
    private int price;
    @Column(name = "pathImage")
    private String pathImage;
    @Column(name = "is_sold")
    private boolean isSold;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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

    public boolean getIsSold() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (yearIssue != car.yearIssue) return false;
        if (price != car.price) return false;
        if (isSold != car.isSold) return false;
        if (!mark.equals(car.mark)) return false;
        if (!model.equals(car.model)) return false;
        if (!transmission.equals(car.transmission)) return false;
        if (!bodyType.equals(car.bodyType)) return false;
        return pathImage.equals(car.pathImage);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mark.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + transmission.hashCode();
        result = 31 * result + bodyType.hashCode();
        result = 31 * result + yearIssue;
        result = 31 * result + price;
        result = 31 * result + pathImage.hashCode();
        result = 31 * result + (isSold ? 1 : 0);
        result = 31 * result + user.hashCode();
        return result;
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
