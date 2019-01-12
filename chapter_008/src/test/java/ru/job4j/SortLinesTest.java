package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SortLinesTest {

    @Test
    public void dropAbuseTest() throws IOException {
        SortLines sortLines = new SortLines(100);
        File fileIn = new File("C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/source.txt");
        File fileOut = new File("C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/dest.txt");
        File fileExpect = new File("C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/expect.txt");
        List<String> result = new ArrayList<>();
        List<String> expect = new ArrayList<>();
        sortLines.sortByLineLength(fileIn, fileOut);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileOut))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileExpect))) {
            String line;
            while ((line = reader.readLine()) != null) {
                expect.add(line);
            }
        }
        Assert.assertEquals(result, expect);
}

}