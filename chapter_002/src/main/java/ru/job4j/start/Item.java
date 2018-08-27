package ru.job4j.start;


public class Item {

    private String id;
    private String name;
    private String desc;
    private String created;
    private String comments;


    public Item(String name, String desc, String created, String comments) {
        this.name = name;
        this.desc = desc;
        this.created = created;
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

    public String getCreated() {
        return this.created;
    }

    public String getComment() {
        return this.comments;
    }
}

   
