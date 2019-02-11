package ru.job4j.socket.fileManager.server;

public interface Server {
    /*
    Start of the main cycle for work with the client
     */
    void start();

    /*
    Returns the list of files from the given path
     */
    String goTo(String path);

    /*
    Prints text from file
     */
    String downloadFile(String path);

    /*
    Create new file
     */
    String pushFile(String name, String text);
}
