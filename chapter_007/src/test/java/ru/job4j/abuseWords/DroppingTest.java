package ru.job4j.abuseWords;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class DroppingTest {

    @Test
    public void dropAbuseTest() throws IOException {
        Dropping dropping = new Dropping();
        final String fileInname = dropping.getClass().getClassLoader().getResource("abuseWord/sourceAbuses.txt").getPath();
        final String fileOutname = dropping.getClass().getClassLoader().getResource("abuseWord/targetAbuses.txt").getPath();
        String[] abuse = {"Snow", "one", "too"};
        try (InputStream in = new FileInputStream(new File(fileInname).getAbsolutePath());
             OutputStream out = new FileOutputStream(new File(fileOutname).getAbsolutePath());
             InputStream resultStream = new FileInputStream(new File(fileOutname).getAbsolutePath())) {
            StringBuilder resultStringBuilder = new StringBuilder();
            int resultReadInt;
            String actualTrue = "flakes falling"
                    + "One by ,"
                    + "Time to play,"
                    + "And have some fun."
                    + "Build a snowman"
                    + "balls, ,"
                    + "Come and see what you can do.";
            dropping.dropAbuses(in, out, abuse);
            try {
                while ((resultReadInt = resultStream.read()) != -1) {
                    resultStringBuilder.append((char) resultReadInt);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            assertThat(resultStringBuilder.toString(), is(actualTrue));
        }


    }

}