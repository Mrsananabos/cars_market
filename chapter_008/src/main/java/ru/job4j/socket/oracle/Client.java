package ru.job4j.socket.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private boolean working = true;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            String ask;
            String answer;
            do {
                ask = console.nextLine();
                out.println(ask);
                answer = in.readLine();
                while (!answer.isEmpty()) {
                    System.out.println(answer);
                    answer = in.readLine();
                }
                if (ask.equals("exit")) {
                    this.working = false;
                }
            } while (this.working);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 5000)) {
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
