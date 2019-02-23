package ru.job4j.bomberman;

import ru.job4j.threads.ThreadPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private final Cell[][] board;
    private int height;
    private int weight;
    protected int numberOfBeast;
    private int numberOfBlocks;
    private Thread[] arrayBeast;


    public Board(int height, int width) {
        this.height = height;
        this.weight = width;
        this.board = new Cell[height][width];
        this.numberOfBeast = height * width > 7 ? (int) Math.sqrt(this.height * this.weight) : 1;
        this.numberOfBlocks = this.numberOfBeast - 1;
        arrayBeast = new Thread[this.numberOfBeast];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.board[y][x] = new Cell(x, y, new ReentrantLock());
            }
        }

    }


    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell generateCell() {
        int x = (int) (Math.random() * (this.weight - 1) + 1);
        int y = (int) (Math.random() * (this.height - 1) + 1);
        return board[y][x];
    }

    public void generateBlocks() {
        for (int i = 0; i < this.numberOfBlocks; ) {
            Cell newCell = generateCell();
            if (!newCell.getLock().isLocked()) {
                newCell.getLock().lock();
                System.out.println("Block is created by coordinates " + newCell.getX() + " " + newCell.getY());
                i++;
            }
        }
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
                if (source.getLock().isHeldByCurrentThread()) {
                    source.getLock().unlock();
                }

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

    public void startGame() {
        generateBlocks();
        for (int i = 0; i < this.numberOfBeast; i++) {
            Thread beast = new Thread(new Beast(this));
            beast.setName("Beast " + i);
            beast.setDaemon(true);
            arrayBeast[i] = beast;
            beast.start();
        }

    }
}





