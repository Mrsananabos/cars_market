package ru.job4j.CheessBoard;

public class Queen extends Figure {
    public Queen(int x, int y) {
        super(x, y);
    }


    public boolean way(Cell source, Cell dest, Figure[][] figures) {
        if ((Math.abs((dest.getX() - source.getX())) != Math.abs((dest.getY() - source.getY()))) && (source.getX() != dest.getX() && source.getY()!= dest.getY())) {
            throw new ImpossibleMoveException("ime");
        }

        if (Math.abs((dest.getX() - source.getX())) == Math.abs((dest.getY() - source.getY()))) {

            if (dest.getY() > source.getY() || dest.getX() < source.getX()) {
                for (int i = 1; i <= Math.abs(dest.getX() - source.getX()); i++) {
                    if (figures[source.getX() - i][source.getY() + i] != null) {
                        throw new OccupiedWayException("owe");
                    }
                }
            } else {
                if (dest.getY() < source.getY() || dest.getX() < source.getX()) {
                    for (int i = 1; i <= Math.abs(dest.getX() - source.getX()); i++) {
                        if (figures[source.getX() - i][source.getY() - i] != null) {
                            throw new OccupiedWayException("owe");
                        }
                    }
                } else {
                    for (int i = 1; i <= Math.abs(dest.getX() - source.getX()); i++) {
                        if (figures[source.getX() + i][source.getY() + i] != null) {
                            throw new OccupiedWayException("owe");
                        }
                    }
                }
            }



        } else {
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
                        if (figures[source.getX() + i][source.getY()] != null) {
                            throw new OccupiedWayException("owe");
                        }
                    }
                } else {
                    for (int i = 1; i <= Math.abs(dest.getX() - source.getX()); i++) {
                        if (figures[source.getX() - i][source.getY()] != null) {
                            throw new OccupiedWayException("owe");
                        }
                    }
                }
            }
        }

        }

        return true;
    }







    Figure clone(Cell dest) {
        return new Queen(dest.getX(), dest.getY());
    }
}