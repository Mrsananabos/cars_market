package ru.job4j;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.Arrays;

public class CoincidenceOfWords {
    boolean contains(String origin, String sub) {
        char[] ArrayOrigin = origin.toCharArray();
        char[] ArraySub = sub.toCharArray();
        int length = ArraySub.length;
        int in = 0;
        for (int out = 0; out < ArrayOrigin.length; out++) {
            if (ArraySub[in] == ArrayOrigin[out]) {
                int m = out + 1;
                int i = in + 1;
                length--;
                for (; length > 0; m++, i++) {
                    if (ArraySub[i] == ArrayOrigin[m]) {
                        length--;
                    } else {
                        length = ArraySub.length;
                        break;
                    }
                }
                if (length == 0) break;
            }
        }
        return length == 0;
    }
}