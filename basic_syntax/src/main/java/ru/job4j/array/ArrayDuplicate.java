package ru.job4j.array;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.Arrays;


public class ArrayDuplicate {
    String[] remove(String[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int l = i + 1; l < length; l++) {
                if (array[l].equals(array[i])) {
                    array[l] = array[length - 1];
                    l--;
                    length--;
                }

            }
        }
        return Arrays.copyOf(array, length);

    }
}