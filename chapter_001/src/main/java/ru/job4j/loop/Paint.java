package ru.job4j.loop;

public class Paint {
    public String piramid(int height) {
        int WidthOfPyramid = 2 *  height - 1;
        StringBuilder str = new StringBuilder();
        for (int heightOfLine = 1; height>=heightOfLine; heightOfLine++) {
            int NumOfPyromid = (2*heightOfLine - 1);
            int NumOfSpace = (WidthOfPyramid - NumOfPyromid)/2;

            for (int i = 0; NumOfSpace>i; i++) {
                str.append(" ");
            }
            for (int i = 0; NumOfPyromid>i; i++) {
                str.append("^");
            }
            for (int i = 0; NumOfSpace>i; i++) {
                str.append(" ");
            }
            str.append("\r\n");
        }
        return str.toString();
    }
}
