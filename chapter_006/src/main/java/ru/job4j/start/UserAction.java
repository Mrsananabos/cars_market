package ru.job4j.start;

import ru.job4j.start.Input;
import ru.job4j.start.Tracker;

public interface UserAction{
    int key();
    void execute(Input input, Tracker tracker);
    String inform();

}