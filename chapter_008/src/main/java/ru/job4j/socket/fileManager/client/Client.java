package ru.job4j.socket.fileManager.client;

import ru.job4j.socket.fileManager.server.Settings;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final static Settings SETTINGS = Settings.getInstance();
    private final Socket socket;
    private boolean working = true;

    public Client(Socket socket) {
        this.socket = socket;
        this.showInstruction();
    }

    private void showInstruction() {
        String preview = "Hello, user.\n" +
                "Enter \"goHome\" to see the list of files in the main directory\n" +
                "Enter \"goTo\" and then the directory to see the list of files\n" +
                "Enter \"download\" and then name of file with extension to see contents\n" +
                "Enter \"push\" and then name with extension and text of new file to create";
        System.out.println(preview);
    }
    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             Scanner console = new Scanner(System.in)) {
            String action;
            String outString;
            String answer;
            do {
                action = console.nextLine();
                out.println(action);
                switch (action) {
                    case("goTo"): {
                        System.out.println("Enter name of directory");
                        outString = console.nextLine();
                        out.println(outString);
                        while (!(answer = in.readLine()).isEmpty()) {
                            System.out.println(answer);
                        }
                        break;
                    }
                    case("download"): {
                        System.out.println("Enter name of file for download");
                        outString = console.nextLine();
                        out.println(outString);
                        while (!(answer = in.readLine()).isEmpty()) {
                            System.out.println(answer);
                        }
                        break;
                    }
                    case("push"): {
                        System.out.println("Enter name of new file");
                        outString = console.nextLine();
                        out.println(outString);
                        System.out.println("Enter text");
                        outString = console.nextLine();
                        out.println(outString);
                        while (!(answer = in.readLine()).isEmpty()) {
                            System.out.println(answer);
                        }
                        break;
                    }
                    case ("goHome"): {
                        while (!(answer = in.readLine()).isEmpty()) {
                            System.out.println(answer);
                        }
                        break;
                    }
                    case ("exit"): {
                        this.working = false;
                        break;
                    }
                    default: {
                        System.out.println("this action is not exist");
                        break;
                    }
                }
            } while (this.working);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = Integer.valueOf(SETTINGS.value("port"));
        try (Socket socket = new Socket(InetAddress.getByName(SETTINGS.value("ip")), port)) {
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
