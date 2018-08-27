package ru.job4j.bomberman;

class ArrayIndexOutOfBoardException extends RuntimeException {
    public ArrayIndexOutOfBoardException(String iob) {
        super(iob);
    }
}