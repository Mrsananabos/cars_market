package ru.job4j.CheessBoard;

class ArrayIndexOutOfBoardException extends RuntimeException{

    public ArrayIndexOutOfBoardException(String iob){
        super(iob);
    }
}