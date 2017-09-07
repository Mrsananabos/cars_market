package ru.job4j.array;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.Arrays;

public class Turn {

    public int[] back(int[] array) {
        int length = array.length ;
        for (int i = array.length-1; i >= (length / 2); i--) {
            int num = array[i];
            array[i] = array[length-i-1];
            array[length-i-1]=num;
        }
        return array;
    }
}

