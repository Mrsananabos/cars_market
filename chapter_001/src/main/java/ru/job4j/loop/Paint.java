package ru.job4j.loop;

public class Paint {

    public String piramid(int height) {
        int widthOfPyramid = 2 * height - 1;
        StringBuilder str = new StringBuilder();
        for (int heightOfLine = 1; height >= heightOfLine; heightOfLine++) {
            int numOfPyromid = (2 * heightOfLine - 1);
            int numOfSpace = (widthOfPyramid - numOfPyromid) / 2;

            for (int i = 0; numOfSpace > i; i++) {
                str.append(" ");
            }
            for (int i = 0; numOfPyromid > i; i++) {
                str.append("^");
            }
            for (int i = 0; numOfSpace > i; i++) {
                str.append(" ");
            }
            str.append("\r\n");
        }
        return str.toString();
    }
}
