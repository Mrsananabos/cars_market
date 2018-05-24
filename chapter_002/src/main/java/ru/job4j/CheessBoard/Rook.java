package ru.job4j.cheessboard;

public class Rook extends Figure {
    public Rook(int x, int y) {
        super(x, y);
    }


    public boolean way(Cell source, Cell dest, Figure[][] figures) {
        if (((source.getX() != dest.getX()) && (source.getY() != dest.getY()))) {
            throw new ImposibleMoveException("ime");

        }


        if (dest.getY() > source.getY()) {
            for (int i = 1; i <= Math.abs(dest.getY() - source.getY()); i++) {
                if (figures[source.getX()][source.getY() + i] != null) {
                    throw new OccupiedWayException("owe");
                }
            }
        } else {
            if (dest.getY() < source.getY()) {
                for (int i = 1; i <= Math.abs(dest.getY() - source.getY()); i++) {
                    if (figures[source.getX()][source.getY() - i] != null) {
                        throw new OccupiedWayException("owe");
                    }
                }
            } else {
                if (dest.getX() > source.getX()) {
                    for (int i = 1; i <= Math.abs(dest.getX() - source.getX()); i++) {
                        if (figures[source.getX() + 1][source.getY()] != null) {
                            throw new OccupiedWayException("owe");
                        }
                    }
                } else {
                    for (int i = 1; i <= Math.abs(dest.getX() - source.getX()); i++) {
                        if (figures[source.getX() - 1][source.getY()] != null) {
                            throw new OccupiedWayException("owe");
                        }
                    }
                }
            }
        }
        return true;
    }

    Figure clone(Cell dest) {
        return new Rook(dest.getX(), dest.getY());

    }
}
