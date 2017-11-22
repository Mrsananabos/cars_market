package ru.job4j.CheessBoard;

class FigureNotFoundException extends RuntimeException{

    public FigureNotFoundException(String ffe){
        super(ffe);
    }
}