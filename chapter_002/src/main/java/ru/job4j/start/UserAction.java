package ru.job4j.start;

import ru.job4j.start.Input;
import ru.job4j.start.Tracker;

public interface UserAction{
    int key(); //ключ, по которому пользоватль запрашивает действие
    void execute(Input input, Tracker tracker);//выполнение основного метода, выбранного по ключу
    String info();//оообщает пользователю, что делает данный метод
}