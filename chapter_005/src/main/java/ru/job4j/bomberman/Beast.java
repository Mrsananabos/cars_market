package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;

public class Beast extends Player implements Runnable {
    private boolean isLockFirstSorce = false;

    public Beast(Board board) {
        super(board);
    }


    @Override
    public void run() {
        while (!this.isLockFirstSorce) {
            super.source = board.generateCell();
            try {
                this.isLockFirstSorce = super.source.getLock().tryLock(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            boolean locked = false;
            locked = super.board.move(super.source, super.dest);
            if (locked) {
                super.source = super.dest;
            }
            super.dest = board.generateCell();
        }
    }
}

