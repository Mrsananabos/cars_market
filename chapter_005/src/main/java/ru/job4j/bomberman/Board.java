package ru.job4j.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final Cell[][] board;
    private int height;
    private int weight;

    public Board(int height, int width) {
        this.height = height;
        this.weight = width;
        this.board = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.board[y][x] = new Cell(x, y, new ReentrantLock());
            }
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell generateCell() {
        int x = ((int) (Math.random() * weight));
        int y = (int) (Math.random() * height);
        Cell newCell = board[y][x];
        return newCell;
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
        } else {
            System.out.println(Thread.currentThread().getName() + " fail to locked " + dest.getX() + " " + dest.getY());
        }
        return result;
    }


    public static void main(String[] args) {
        Board board = new Board(10, 10);


        Thread hero1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Cell sorce = board.getBoard()[0][0];
                Cell dest = board.generateCell();
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
        });


        Thread hero2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Cell sorce1 = board.getBoard()[1][1];
                Cell dest1 = board.generateCell();
                sorce1.getLock().lock();
                while (true) {
                    if (board.move(sorce1, dest1)) {
                        sorce1 = dest1;
                        dest1 = board.generateCell();
                    } else {
                        dest1 = board.generateCell();
                    }

                }
            }
        });

        hero1.setName("Hero 1 ");
        hero2.setName("Hero 2 ");
        hero1.start();
        hero2.start();
    }
}





