package ru.job4j.Strategy;

import ru.job4j.start.Input;
import ru.job4j.start.Tracker;

public interface UserAction{
    int key();
    void execute(Input input, Tracker tracker);
    String info();
}