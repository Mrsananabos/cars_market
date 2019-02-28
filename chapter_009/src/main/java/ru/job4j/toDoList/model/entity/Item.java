package ru.job4j.toDoList.model.entity;

import java.sql.Timestamp;

public class Item {
    private int id;
    private String desc;
    private Timestamp created;
    private boolean done;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (done != item.done) return false;
        if (desc != null ? !desc.equals(item.desc) : item.desc != null) return false;
        return created != null ? created.equals(item.created) : item.created == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", created=" + created +
                ", done=" + done +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}
