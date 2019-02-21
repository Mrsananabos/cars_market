package ru.job4j.socket.fileManager.server;

public interface UserAction{
    int key();
    String execute(Input input, Managing managing);
    String inform();
}