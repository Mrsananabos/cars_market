package ru.job4j.cheessboard;

public class Knight extends Figure {
    public Knight(int x, int y) {
        super(x, y);
    }


    public boolean way(Cell source, Cell dest, Figure[][] figures) {
        if (((dest.getX() == source.getX()) || (Math.abs(dest.getX() - source.getX()) > 2)) ||
                (Math.abs(dest.getX() - source.getX()) == 2 && Math.abs(dest.getY() - source.getY()) != 1) || (Math.abs(dest.getX() - source.getX()) == 1 && Math.abs(dest.getY() - source.getY()) != 2)) {
            throw new ImposibleMoveException("ime");
        }

        return true;
    }

    Figure clone(Cell dest) {
        return new Knight(dest.getX(), dest.getY());

    }
}