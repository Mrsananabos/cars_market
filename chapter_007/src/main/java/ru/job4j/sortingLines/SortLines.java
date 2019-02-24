package ru.job4j.sortingLines;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortLines {
    private static final String PATH_TEMP_FILE = "C:/projects/ashveytser/chapter_007/src/main/resources";
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
        String lineWithoutSpace;
        try (RandomAccessFile sourceFile = new RandomAccessFile(source, "r")) {
            long writingCursor = 0;
            while (sourceFile.length() > writingCursor) {
                File currentFile = File.createTempFile("tmp", ".txt", new File(PATH_TEMP_FILE));
                files.add(currentFile);
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(currentFile))) {
                    while (writingCursor < lastNumberOfByte && ((line = sourceFile.readLine()) != null)) {
                        lineWithoutSpace = line.replaceAll("\\s+$", "");
                        bufferedWriter.write(lineWithoutSpace);
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
        splitFiles.forEach(x -> {
            List<String> lines = readFile(x);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(x))) {
                lines.stream()
                        .filter(line -> line.length() > 0)
                        .sorted(Comparator.comparingInt(String::length))
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
        File fileForMerge = File.createTempFile("tmp", ".txt", new File(PATH_TEMP_FILE));
        if (sortedFiles.size() > 1) {
            merge(sortedFiles.get(0), sortedFiles.get(1), fileForMerge);
            for (int index = 2; index < sortedFiles.size(); index++) {
                File partOfSortedAndMergedFile = File.createTempFile("tmp", ".txt", new File(PATH_TEMP_FILE));
                merge(sortedFiles.get(index), fileForMerge, partOfSortedAndMergedFile);
                fileForMerge = partOfSortedAndMergedFile;
            }
            rewrite(fileForMerge, dest);
        } else {
            rewrite(sortedFiles.get(0), dest);
            sortedFiles.get(0).deleteOnExit();
        }
        fileForMerge.deleteOnExit();
    }


    private void rewrite(File in, File out) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out));
             BufferedReader reader = new BufferedReader(new FileReader(in))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                writer.write(currentLine);
                writer.write("\r\n");
            }
        }
    }


    private void merge(File file1, File file2, File dest) throws FileNotFoundException {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(dest))) {
            String lineLeft = reader1.readLine();
            String lineRight = reader2.readLine();
            boolean doAction = true;
            while (doAction) {
                if (lineLeft != null && lineRight != null) {
                    if (lineLeft.length() < lineRight.length()) {
                        writer.write(lineLeft);
                        writer.write("\r\n");
                        lineLeft = reader1.readLine();
                    } else {
                        writer.write(lineRight);
                        writer.write("\r\n");
                        lineRight = reader2.readLine();
                    }
                }
                if (lineLeft == null && lineRight != null) {
                    writer.write(lineRight);
                    writer.write("\r\n");
                    lineRight = reader2.readLine();
                }
                if (lineRight == null && lineLeft != null) {
                    writer.write(lineLeft);
                    writer.write("\r\n");
                    lineLeft = reader1.readLine();
                }
                if (lineLeft == null && lineRight == null) {
                    doAction = false;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        file1.deleteOnExit();
        file2.deleteOnExit();
    }

}
