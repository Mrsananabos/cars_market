package ru.job4j.array;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.Arrays;

public class RotateArray {
    public int[][] rotate(int[][] array) {
        int bin = array.length / 2;
        for (int i = 0; i < bin; i++) {
            for (int k = i; k < array.length - i - 1; k++) {
                if (array.length == 2) {
                    bin = 0;
                }

                int tmp1 = array[i][k];
                array[i][k] = array[bin - k + 1][i];
                array[bin - k + 1][i] = array[bin - i + 1][bin - k + 1];
                array[bin - i + 1][bin - k + 1] = array[k][bin - i + 1];
                array[k][bin - i + 1] = tmp1;

            }

        }
        return array;
    }
}

