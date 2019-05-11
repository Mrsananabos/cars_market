package ru.job4j.carMarket.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "transmission")
public class Transmission {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Transmission() {
    }

    public Transmission(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
