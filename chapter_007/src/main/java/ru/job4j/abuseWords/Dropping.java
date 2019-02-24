package ru.job4j.abuseWords;

import java.io.*;
import java.util.Arrays;

public class Dropping {
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out))) {
            bufferedReader
                    .lines()
                    .map(line -> Arrays.stream(abuse).reduce(line, (s1, s2) ->  s1.replaceAll(s2, ""))
                    ).forEach(line -> {
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
