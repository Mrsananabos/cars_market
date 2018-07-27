package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;

public class Player extends Thread  {

    final protected Board board;
    protected Cell source;
    protected Cell dest;

    public Player(Board board) {
        this.board = board;
        this.source = board.generateCell();
        this.dest = board.generateCell();
    }


}
