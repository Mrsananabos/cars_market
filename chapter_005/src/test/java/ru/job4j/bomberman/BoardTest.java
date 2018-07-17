package ru.job4j.bomberman;

import org.junit.Test;

public class BoardTest {

    @Test
    public void emulationOfThreeBeastAndBomberman() {
        Board board = new Board(5, 3);
        board.startGame();
        Thread bomberman = new Thread(new Runnable() {
            @Override
            public void run() {
                Bomberman b = new Bomberman(board);
                b.move(b.source, b.nextStep("RIGHT"));
                b.move(b.source, b.nextStep("DOWN"));
                b.move(b.source, b.nextStep("DOWN"));
                b.move(b.source, b.nextStep("RIGHT"));
            }
        });
        bomberman.setName("Bomberman ");
        bomberman.start();
        try {
            bomberman.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < board.numberOfBeast; i++) {
            try {
                board.getArrayBeast()[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
