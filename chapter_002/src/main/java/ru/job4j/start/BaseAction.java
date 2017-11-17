package ru.job4j.start;

public abstract class BaseAction implements UserAction {

    public String name;
    public int key;

    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    };


    public String inform() {   //переопределяем метод inform
        return String.format("%s. %s", this.key, this.name);
    }


}