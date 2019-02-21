package ru.job4j.start;


public class Item {

    private String id;
    private String name;
    private String desc;
    private String comments;

    public Item(String name, String desc, String comments) {
        this.name = name;
        this.desc = desc;
        this.comments = comments;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getComment() {
        return this.comments;
    }

    @Override
    public String toString() {
        String rsl = "Id = " + this.id + " name = " + this.name + " descroption = " + this.desc + " comment = " + this.comments + "\r\n";
        return rsl;
    }
}

   
