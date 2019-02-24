package ru.job4j.socket.fileManager.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerImpl {
    private final static Settings SETTINGS = Settings.getInstance();
    private final Socket socket;

    public ServerImpl(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            StubInput stubInput = new StubInput(in);
            StubOutput stubOutput = new StubOutput(out);
            MenuAction menuAction = new MenuAction(stubInput, stubOutput, new Managing());
            int key;
            menuAction.show();
            do {
                key = stubInput.askKey();
                menuAction.select(key);
            } while (key != 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = Integer.valueOf(SETTINGS.value("port"));
        try (Socket socket = new ServerSocket(port).accept()) {
            ServerImpl server = new ServerImpl(socket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
