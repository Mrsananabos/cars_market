package ru.job4j.bomberman;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void emulationOfTwoHeroes() {
        Board board = new Board(5, 5);
        Player work1 = new Player(board, board.getBoard()[0][0]);
        Player work2 = new Player(board, board.getBoard()[1][1]);
        Thread player1 = new Thread(work1);
        Thread player2 = new Thread(work2);
        player1.setName("Player 1 ");
        player2.setName("Player 2 ");
        player1.start();
        player2.start();
        try {
            player1.join();
            player2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
