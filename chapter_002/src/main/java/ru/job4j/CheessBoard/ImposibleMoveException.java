package ru.job4j.cheessboard;

class ImposibleMoveException extends RuntimeException {

    public ImposibleMoveException(String ime) {

        super(ime);
    }
}