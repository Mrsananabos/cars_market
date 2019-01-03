package ru.job4j;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.io.*;
import java.util.Arrays;

public class Dropping {

    private StringBuilder stringBuilder = new StringBuilder();

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        int charInt;
        char ch;
        try (InputStreamReader inputStreamReader = new InputStreamReader(in);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out)) {
            while ((charInt = inputStreamReader.read()) != -1) {
                ch = (char) charInt;
                stringBuilder.append(ch);
                for (String abusesWord : abuse) {
                    int start = stringBuilder.indexOf(abusesWord);
                    if (start != -1) {
                        stringBuilder.delete(start, start + abusesWord.length());
                    }
                }
            }

            outputStreamWriter.write(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
