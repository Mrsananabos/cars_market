package ru.job4j.max;

public class Max {
    public int max(int first, int second) {
            return first > second ? first : second;
    }
    public int maxof3(int first, int second, int third) {
        Max m = new Max();
        int maximum = m.max(first, second);
        maximum = m.max(maximum, third);
        return maximum;
    }
}
