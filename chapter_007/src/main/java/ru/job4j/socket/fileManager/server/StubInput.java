package ru.job4j.socket.fileManager.server;

import java.io.BufferedReader;
import java.io.IOException;

public class StubInput implements Input {
    private final BufferedReader reader;

    public StubInput(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public int askKey() {
        int rsl = -1;
        String key = null;
        try {
          key = this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (key != null) {
            rsl = Integer.valueOf(key);
        }
        return rsl;
    }

    @Override
    public String askText() {
        String result = "";
        try {
            result =  this.reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
