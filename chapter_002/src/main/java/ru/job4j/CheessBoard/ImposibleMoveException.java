package ru.job4j.CheessBoard;

class ImpossibleMoveException extends RuntimeException{

    public ImpossibleMoveException(String ime){
        super(ime);
    }
}