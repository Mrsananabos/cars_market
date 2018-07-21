package ru.job4j.bomberman;

public class Bomberman extends Player {

    public Bomberman(Board board) {
        super(board);
        super.source = super.board.getBoard()[0][0];
        super.source.getLock().lock();
        super.dest = null;
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
            default: System.out.println("The direcion is incorrect");
        }
        if ((newX > (board.getWeight() - 1) || (newX < 0) || (newY > (board.getHeight() - 1)) || (newY < 0))) {
            throw new ArrayIndexOutOfBoardException("Bomberman went out of bonds");
        }
        return board.getBoard()[newY][newX];
    }


}