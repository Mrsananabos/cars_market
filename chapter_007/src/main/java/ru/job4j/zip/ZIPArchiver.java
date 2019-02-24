package ru.job4j.zip;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIPArchiver {
    private List<String> extensions = new ArrayList<>(Arrays.asList("txt"));
    private String targetZip = "C:/projects/ashveytser";


    public void searchFiles(String path) {
        File nFile = new File(targetZip + "file.zip");
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            try (FileOutputStream fos = new FileOutputStream(nFile);
                 ZipOutputStream zous = new ZipOutputStream(fos)) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        searchFiles(file.getPath());
                        System.out.println("Это директория " + file.getPath());
                    } else {
                        if (checkTemplate(file.getName())) {
                            System.out.println("! " + file.getAbsolutePath());
                            zipFile(file, zous);
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void zipFile(File file, ZipOutputStream zous) {
        try (FileInputStream fis = new FileInputStream(file)) {
            zous.putNextEntry(new ZipEntry(file.getPath()));
            byte[] buffer = new byte[4048];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zous.write(buffer, 0, length);
            }
            zous.closeEntry();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkTemplate(String name) {
        boolean valid = false;
        for (String temp : extensions) {
            if (name.contains(temp)) {
                valid = true;
                break;
            }
        }
        return valid;
    }


    public static void main(String[] args) {
        ZIPArchiver zipArchiver = new ZIPArchiver();
        String path = "C:/projects/ashveytser";
        zipArchiver.searchFiles(path);
    }
}

