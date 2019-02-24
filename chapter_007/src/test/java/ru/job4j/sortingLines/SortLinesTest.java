package ru.job4j.sortingLines;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortLinesTest {

    @Test
    public void dropAbuseTest() throws IOException {
        SortLines sortLines = new SortLines(100);
        File fileIn = new File(sortLines.getClass().getClassLoader().getResource("sortingLines/source.txt").getPath());
        File fileOut = new File(sortLines.getClass().getClassLoader().getResource("sortingLines/dest.txt").getPath());
        File fileExpect = new File(sortLines.getClass().getClassLoader().getResource("sortingLines/expect.txt").getPath());
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
        assertEquals(result, expect);
    }

}