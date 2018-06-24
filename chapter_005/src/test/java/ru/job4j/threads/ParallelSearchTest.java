package ru.job4j.threads;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParallelSearchTest {

    @Test
    public void whenFindPathByText() {
        String root = "C:\\projects\\ashveytser\\chapter_005\\src\\main\\java\\ru\\job4j\\files";
        String text = "LOVE";
        List<String> ext = new ArrayList<>();
        ext.add(".txt");
        ParallelSearch parallelSearch = new ParallelSearch(root, text, ext);

        Thread search = new Thread() {
            @Override
            public void run() {
                parallelSearch.searchInDirectories(root, ext);
                parallelSearch.finish = true;
            }
        };


        Thread read = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (parallelSearch.finish) {
                        parallelSearch.findPathes(text, parallelSearch.files);
                        System.out.println(parallelSearch.result());
                        break;
                    }
                }

            }
        };

        search.start();
        read.start();

        try {
            search.join();
            read.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}