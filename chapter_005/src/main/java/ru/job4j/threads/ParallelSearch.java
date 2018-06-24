package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@ThreadSafe
public class ParallelSearch {
    private final String root;
    private final String text;
    private final List<String> exts;
    protected volatile boolean finish = false;

    @GuardedBy("this")
    final Queue<String> files = new LinkedList<>();

    @GuardedBy("this")
    private final List<String> paths = new ArrayList<>();


    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }



    public void searchInDirectories(String directory, List<String> ext) {
        synchronized (this.files) {
            File file = new File(directory);
            for (File element : file.listFiles()) {
                for (String name : ext) {
                    if (element.getName().endsWith(name)) {
                        this.files.offer(element.getAbsolutePath());
                    }
                }
            }
        }
    }

    public void findPathes(final String text, final Queue<String> files) {
        synchronized (this.paths) {
            for (String path : files) {
                if (searchTextInFile(text, path)) {
                    this.paths.add(path);
                }
            }
        }
    }


    private boolean searchTextInFile(final String text, final String path) {
        boolean flag = false;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            while (bufferedReader.ready()) {
                String currentLine = bufferedReader.readLine();
                if (currentLine.contains(text)) {
                    flag = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    synchronized List<String>  result() {
        return this.paths;
    }



    public static void main(String[] args) {
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



