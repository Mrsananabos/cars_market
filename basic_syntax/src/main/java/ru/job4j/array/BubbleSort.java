package ru.job4j.array;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.Arrays;

public class BubbleSort {
    public int[] sort(int[] array) {
        int length = array.length;
        for (int l = array.length - 1; l > 0; l--) {
            for (int i = 0; i < l; i++) {
                if (array[i] > array[i + 1]) {
                    int num = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = num;
                }
            }
        }
        return array;
    }
}

