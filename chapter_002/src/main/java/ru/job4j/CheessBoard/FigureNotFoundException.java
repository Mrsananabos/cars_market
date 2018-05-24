package ru.job4j.cheessboard;

class FigureNotFoundException extends RuntimeException {

    public FigureNotFoundException(String ffe) {

        super(ffe);
    }
}