package ru.job4j.abuseWords;

import java.io.*;

public class Dropping {
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out))) {
            bufferedReader
                    .lines()
                    .map(line -> {
                        for (String word : abuse) {
                            line = line.replaceAll(word, "");
                        }
                        return line + "\r\n";
                    })
                    .forEach(line -> {
                        try {
                            bufferedWriter.write(line);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
