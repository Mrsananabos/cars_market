package ru.job4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortLines {

    private int maxLengthBytesInFile;


    public SortLines(int maxLengthBytesInFile) {
        this.maxLengthBytesInFile = maxLengthBytesInFile;
    }

    public SortLines() {
        this.maxLengthBytesInFile = 3000;
    }

    public void sortByLineLength(File source, File dest) throws IOException {
        List<File> splitFiles = splitFile(source);
        sortedFiles(splitFiles);
        mergeSortedFiles(splitFiles, dest);
    }

    private List<File> splitFile(File source) throws IOException {
        List<File> files = new ArrayList<>();
        long lastNumberOfByte = this.maxLengthBytesInFile;
        String line;
        try (RandomAccessFile sourceFile = new RandomAccessFile(source, "r")) {
            long writingCursor = 0;
            while (sourceFile.length() > writingCursor) {
                File currentFile = File.createTempFile("tmptttt", ".txt", new File("C:\\projects\\ashveytser\\chapter_008"));
                files.add(currentFile);
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentFile))) {
                    while (writingCursor < lastNumberOfByte && ((line = sourceFile.readLine()) != null)) {
                        bufferedWriter.write(line);
                        bufferedWriter.write("\n");
                        writingCursor = sourceFile.getFilePointer();
                    }
                }
                lastNumberOfByte = this.maxLengthBytesInFile + writingCursor;
            }
        }

        return files;
    }

    private void sortedFiles(List<File> splitFiles) {
        Comparator<String> f = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                System.out.println("Слово 1 " + o1 + " длина = " + o1.length());
                System.out.println("Слово 2 " + o2 + " длина = " + o2.length());
                return o1.length() - o2.length();
            }
        };



        splitFiles.forEach(x -> {
            List<String> lines = readFile(x);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(x))) {
                lines.stream()
                        .filter(line -> line.length() > 0)
                        .sorted(f)
                        .forEach(line -> {
                            try {
                                writer.write(line);
                                writer.write("\r\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private void mergeSortedFiles(List<File> sortedFiles, File dest) throws IOException {
        File fileForMerge = File.createTempFile("tmp11", ".txt", new File("C:\\projects\\ashveytser\\chapter_008"));
        if (sortedFiles.size() > 1) {
            merge(sortedFiles.get(0), sortedFiles.get(1), fileForMerge);
            for (int index = 2; index < sortedFiles.size(); index++) {
                File partOfSortedAndMergedFile = File.createTempFile("tmp22", ".txt", new File("C:\\projects\\ashveytser\\chapter_008"));
                merge(sortedFiles.get(index), fileForMerge, partOfSortedAndMergedFile);
                fileForMerge = partOfSortedAndMergedFile;
            }
            rewrite(fileForMerge, dest);

        } else {
            rewrite(sortedFiles.get(0), dest);
            sortedFiles.get(0).deleteOnExit();
            fileForMerge.deleteOnExit();
        }


        }


        private void rewrite(File in, File out) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out));
             BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                writer.write(currentLine);
                writer.write("\r\n");
                System.out.println(currentLine);
            }

        }
        }



    public void merge(File file1, File file2, File dest) throws FileNotFoundException {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dest));) {
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            boolean doAction = true;
            while (doAction) {
                if (line1 != null && line2 != null) {
                    if (line1.length() < line2.length()) {
                        writer.write(line1);
                        writer.write("\r\n");
                        line1 = reader1.readLine();
                    } else {
                        writer.write(line2);
                        writer.write("\r\n");
                        line2 = reader2.readLine();
                    }
                }
                if (line1 == null && line2 != null) {
                    writer.write(line2);
                    writer.write("\r\n");
                    line2 = reader2.readLine();
                }
                if (line2 == null && line1 != null) {
                    writer.write(line1);
                    writer.write("\r\n");
                    line1 = reader1.readLine();
                }
                if (line1 == null && line2 == null) {
                    doAction = false;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        file1.deleteOnExit();
        file2.deleteOnExit();
    }


    public static void main(String[] args) throws IOException {
        SortLines sortLines = new SortLines(400);
        // sortLines.sort(new File("C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/sortLinesSource.txt"), new File("C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/1.txt"));

        File sd1 = new File("C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/source.txt");

        File sd = new File("C:/projects/ashveytser/chapter_008/src/main/java/ru/job4j/2.txt");
        ArrayList<File> files = new ArrayList<>();
        files.add(sd);
        files.add(sd1);
        sortLines.sortByLineLength(sd, sd1);


    }
}
