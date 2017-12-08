package ru.job4j.CheessBoard;
import ru.job4j.CheessBoard.ImpossibleMoveException;
public class Board {


    Figure[][] figures = new Figure[8][8];


    public void fill(Figure figure, int x, int y) {
        figures[x][y] = figure;
    }

    public void BorderCheck(Cell cell) throws ArrayIndexOutOfBoardException {
        if (cell.getX()>=figures.length || cell.getY()>=figures[figures.length-1].length) {
            throw new ArrayIndexOutOfBoardException("iob");
        }

    }





    boolean move(Cell source, Cell dest, Figure figure) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException, ArrayIndexOutOfBoardException {
        if (figures[source.getX()][source.getY()] == null) {
            throw new FigureNotFoundException("ffe");
        }

        if (figure.way(source, dest, figures)) {

            figures[source.getX()][source.getY()] = null;
            figures[dest.getX()][dest.getY()] = figure.clone(dest);
            System.out.println("Ход совершён");


        }
        return true;

    }
}



