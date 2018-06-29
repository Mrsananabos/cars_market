package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void emulationOfTwoHeroes() {
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
