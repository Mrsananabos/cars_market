package ru.job4j.parsing;

import java.sql.Timestamp;

public class Vacancy {
    private String name;
    private String text;
    private Timestamp datePublished;

    public Vacancy(String name, String text, Timestamp datePublished) {
        this.name = name;
        this.text = text;
        this.datePublished = datePublished;
    }

    public String getName() {
        return name;
    }


    public String getText() {
        return text;
    }


    public Timestamp getDatePublished() {
        return datePublished;
    }

}


