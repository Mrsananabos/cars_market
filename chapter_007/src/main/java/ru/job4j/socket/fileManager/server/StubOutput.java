package ru.job4j.socket.fileManager.server;

import java.io.PrintWriter;

public class StubOutput implements Output {
    private final PrintWriter writer;

    public StubOutput(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void answer(String answer) {
        this.writer.println(answer);
    }
}
