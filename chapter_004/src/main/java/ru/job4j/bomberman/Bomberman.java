package ru.job4j.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Bomberman extends Player implements Runnable  {

    List<String> steps = new ArrayList<>();

    public Bomberman(Board board, List<String> steps) {
        super(board);
        super.source = super.board.getBoard()[0][0];
        super.source.getLock().lock();
        super.dest = null;
        this.steps = steps;
    }


    public Cell nextStep(String direction) {
        int newX = super.source.getX();
        int newY = super.source.getY();
        switch (direction) {
            case "STRAIGHT":
                newY--;
                break;
            case "DOWN":
                newY++;
                break;
            case "LEFT":
                newX--;
                break;
            case "RIGHT":
                newX++;
                break;
            default: System.out.println("The direction is incorrect");
        }
        if ((newX > (board.getWeight() - 1) || (newX < 0) || (newY > (board.getHeight() - 1)) || (newY < 0))) {
            throw new ArrayIndexOutOfBoardException("Bomberman went out of bonds");
        }
        super.dest = board.getBoard()[newY][newX];
        return super.dest;
    }


    @Override
    public void run() {
      int numSteps = this.steps.size();
      for (int i = 0; i < numSteps; i++) {
          Cell nextStep = this.nextStep(this.steps.get(i));
          boolean result = super.board.move(super.source, nextStep);
          if (result) {
              super.source = super.dest;
          }
      }

    }


}
