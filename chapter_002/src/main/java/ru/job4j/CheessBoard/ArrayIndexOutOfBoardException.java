package ru.job4j.cheessboard;

class ArrayIndexOutOfBoardException extends RuntimeException {

    public ArrayIndexOutOfBoardException(String iob) {
        super(iob);
    }
}