package ru.job4j.cheessboard;

class OccupiedWayException extends RuntimeException {

    public OccupiedWayException(String owe) {
        super(owe);
    }
}