package ru.job4j.Strategy;

public class Square implements Shape{
    public String pic() {
        StringBuilder pic = new StringBuilder();
        pic.append("++++++\n");
        pic.append("+    +\n");
        pic.append("+    +\n");
        pic.append("++++++\n");
        return pic.toString();
    }

}




