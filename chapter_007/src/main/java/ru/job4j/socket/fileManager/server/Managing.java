package ru.job4j.socket.fileManager.server;

import java.io.*;
import java.util.stream.Stream;

public class Managing {
    private final static Settings SETTINGS = Settings.getInstance();
    private final String homePath;
    private String currentDirectory;

    public Managing() {
        this.homePath = SETTINGS.value("homeDirectory");
        this.currentDirectory = homePath;
    }

    private String getList(String path) {
        StringBuilder string = new StringBuilder();
        File file = new File(path);
        String[] files = file.list();

        if (files != null) {
            if (files.length == 0) {
                string.append("Directory is empty!\r\n");
            } else {
                Stream.of(files).forEach(nameFile -> {
                    string.append(nameFile);
                    string.append(System.getProperty("line.separator"));
                });
            }
        } else {
            string.append("Error!\r\n");
        }
        return string.toString();
    }

    String goHome() {
        String homeFiles = this.getList(this.homePath);
        this.currentDirectory = this.homePath;
        return homeFiles;
    }

    String goTo(String path) {
        String rsl;
        String toDirect = this.currentDirectory + "/" + path;
        File directory = new File(toDirect);
        if (directory.isDirectory()) {
            this.currentDirectory = toDirect;
            rsl = getList(this.currentDirectory);
        } else {
            rsl = path + " is not directory!\r\n";
        }
        return rsl;
    }

    String downloadFile(String name) {
        StringBuilder result = new StringBuilder();
        File file = new File(this.currentDirectory + "/" + name);
        if (!file.isDirectory()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                    result.append(System.getProperty("line.separator"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result.append("Loading error\r\n");
        }
        return result.toString();
    }

    String pushFile(String name, String text) {
        File file = new File(this.currentDirectory + "/" + name);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success\r\n";
    }
}
