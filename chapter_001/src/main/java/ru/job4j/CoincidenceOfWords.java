package ru.job4j;


public class CoincidenceOfWords {

    boolean contains(String origin, String sub) {
        char[] arrayOrigin = origin.toCharArray();
        char[] arraySub = sub.toCharArray();
        int length = arraySub.length;
        int in = 0;
        for (int out = 0; out < arrayOrigin.length; out++) {
            if (arraySub[in] == arrayOrigin[out]) {
                int m = out + 1;
                int i = in + 1;
                length--;
                for (; length > 0; m++, i++) {
                    if (arraySub[i] == arrayOrigin[m]) {
                        length--;
                    } else {
                        length = arraySub.length;
                        break;
                    }
                }
                if (length == 0) {
                    break;
                }
            }
        }
        return length == 0;
    }
}