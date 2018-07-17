package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;

public class Player {

    final protected Board board;
    protected Cell source;
    protected Cell dest;

    public Player(Board board) {
        this.board = board;
        this.source = board.generateCell();
        this.dest = board.generateCell();
    }

    public boolean move(Cell source, Cell dest) {
        boolean result = false;
        try {
            result = dest.getLock().tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result) {
            try {
                System.out.println(Thread.currentThread().getName() + " locked " + dest.getX() + " " + dest.getY());
                source.getLock().unlock();
                System.out.println(Thread.currentThread().getName() + " unlocked " + source.getX() + " " + source.getY());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.source = dest;
        } else {
            System.out.println(Thread.currentThread().getName() + " fail to locked " + dest.getX() + " " + dest.getY());
        }
        return result;
    }


}
