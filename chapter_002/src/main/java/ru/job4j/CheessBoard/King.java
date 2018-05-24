package ru.job4j.cheessboard;

public class King extends Figure {
    public King(int x, int y) {
        super(x, y);
    }


    public boolean way(Cell source, Cell dest, Figure[][] figures) {
        if ((Math.abs(dest.getX() - source.getX()) > 1 && Math.abs(dest.getY() - source.getY()) > 1) || (Math.abs(dest.getX() - source.getX()) > 1 || Math.abs(dest.getY() - source.getY()) > 1)) {
            throw new ImposibleMoveException("ime");
        }
        if (figures[dest.getX()][dest.getY()] != null) {
            throw new OccupiedWayException("owe");
        }


        return true;
    }

    Figure clone(Cell dest) {
        return new King(dest.getX(), dest.getY());

    }
}