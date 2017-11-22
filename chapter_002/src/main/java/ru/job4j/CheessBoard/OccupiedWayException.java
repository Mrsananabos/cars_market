package ru.job4j.CheessBoard;

class OccupiedWayException extends RuntimeException{

    public OccupiedWayException(String owe){
        super(owe);
    }
}