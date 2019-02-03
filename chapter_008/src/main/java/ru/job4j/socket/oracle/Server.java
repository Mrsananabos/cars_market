package ru.job4j.socket.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private final Socket socket;
    private Map<String, String> answers = new HashMap<>();
    private boolean working = true;

    public Server(Socket socket) {
        this.socket = socket;
        answers.put("hello", "Hello, dear friend, I'm a oracle.");
        answers.put("exit", "Bye, good luck!");
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("exit".equals(ask)) {
                    this.working = false;
                }
                out.println(this.chooseAnswer(ask));
                out.println();
            } while (this.working);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String chooseAnswer(String ask) {
        String result = this.answers.get(ask);
        if (result == null) {
            result = "Oracle's answer";
        }
        return result;
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(5000).accept()) {
            Server server = new Server(socket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
