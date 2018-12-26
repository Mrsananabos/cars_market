package ru.job4j.labmda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Calculator {
    public List<Double> function(int start, int end, Function<Double, Double> func) {
        List<Double> results = new ArrayList<>();
        for (double index = start; index != end; index++) {
            results.add(func.apply(index));
        }
        return results;
    }
}
