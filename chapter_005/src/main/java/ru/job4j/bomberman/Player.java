package ru.job4j.bomberman;

public class Player implements Runnable {

    final private Board board;
    private Cell sorce;
    private Cell dest;

    public Player(Board board, Cell source) {
        this.board = board;
        this.sorce = source;
        this.dest = board.generateCell();

    }


    @Override
    public void run() {
        sorce.getLock().lock();
        while (true) {
            if (board.move(sorce, dest)) {
                sorce = dest;
                dest = board.generateCell();
            } else {
                dest = board.generateCell();
            }

        }
    }
}
