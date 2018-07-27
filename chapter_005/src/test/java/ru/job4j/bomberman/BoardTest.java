package ru.job4j.bomberman;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardTest {

    @Test
    public void emulationOfThreeBeastAndBomberman() {
        Board board = new Board(5, 3);
        board.startGame();
        List<String> stepsOfBomberman = new ArrayList<>(Arrays.asList("RIGHT", "DOWN", "DOWN", "RIGHT"));
        Bomberman bomberman = new Bomberman(board, stepsOfBomberman);
        bomberman.setName("Bomberman ");
        bomberman.start();
        try {
            bomberman.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
