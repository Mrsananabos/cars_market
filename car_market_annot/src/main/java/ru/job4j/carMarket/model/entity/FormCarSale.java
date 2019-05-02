package ru.job4j.carMarket.model.entity;

public class FormCarSale {
    private String mark;
    private String model;
    private String transmission;
    private String bodyType;
    private String yearIssue;
    private String price;
    private String pathImage;
    private String author;

    public FormCarSale() {
    }

    public FormCarSale(String mark, String model, String transmission, String bodyType, String yearIssue, String price, String pathImage, String author) {
        this.mark = mark;
        this.model = model;
        this.transmission = transmission;
        this.bodyType = bodyType;
        this.yearIssue = yearIssue;
        this.price = price;
        this.pathImage = pathImage;
        this.author = author;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setYearIssue(String yearIssue) {
        this.yearIssue = yearIssue;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public Car getCar() {
        return new Car(mark, model, transmission, bodyType, Integer.valueOf(yearIssue), Integer.valueOf(price), pathImage, null);
    }

    @Override
    public String toString() {
        return "FormCarSale{" +
                "mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", transmission='" + transmission + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", yearIssue='" + yearIssue + '\'' +
                ", price='" + price + '\'' +
                ", pathImage='" + pathImage + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
