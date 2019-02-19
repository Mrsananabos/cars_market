package ru.job4j.strategy;

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




