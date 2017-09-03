package ru.job4j.loop;

public class Board {
    public String paint(int width, int height) {
        StringBuilder str = new StringBuilder();
        for (int height2 = 1; height >= height2; height2 ++) {
            for (int width2 = width; width2 > 0; width2 = width2 - 2) {
                if (height2 % 2 != 0) {
                    if (width2 == 1) {
                        str.append("x");
                    } else {
                        str.append("x ");
                    }

                } else{
                        if (width2 == 1) {
                            str.append(" ");
                        } else {
                            str.append(" x");
                        }
                    }
                }
            str.append("\r\n");
            }
       return str.toString();
        }
  }
