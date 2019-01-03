package ru.job4j;

import java.io.*;


public class NumberReader {

    public boolean numberIsEven(InputStream in) {
        boolean result = false;
        try (InputStreamReader inputStreamReader = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            String c = br.readLine();
            try {
                Integer i = Integer.valueOf(c);
                result = i % 2 == 0;
            } catch (NumberFormatException e) {
                System.out.println("You entered not a number");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        NumberReader reader = new NumberReader();
        try (InputStream inputStream = new BufferedInputStream(System.in)) {
            boolean result = reader.numberIsEven(inputStream);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
