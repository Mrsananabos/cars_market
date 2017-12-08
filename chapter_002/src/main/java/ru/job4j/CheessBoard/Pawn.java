package ru.job4j.CheessBoard;

public class Pawn extends Figure {
    public Pawn(int x, int y){
        super(x,y);
    }


    public boolean way(Cell source, Cell dest, Figure[][] figures) {
        if (source.getY() == 1) {
            if (dest.getY() > source.getY() + 2) {
                throw new ImpossibleMoveException("ime");
            }
        } else {
            if (dest.getY() > source.getY() + 1) {
                throw new ImpossibleMoveException("ime");
            }
        }
        if (dest.getY()-source.getY()>2) {
            throw new OccupiedWayException("owe");
        } else {
            if (dest.getY()-source.getY()==2 || source.getY()==1){
                if (figures[source.getX()+1][source.getY()+1] != null || figures[source.getX()+2][source.getY()+2] != null ) {
                    throw new OccupiedWayException("owe");
                }
            } else {
                if (figures[source.getX()][source.getY()+1] != null) {
                    throw new OccupiedWayException("owe");
                }
            }

        }
       return true;
    }

    Figure clone(Cell dest) {
        return new Pawn(dest.getX(), dest.getY());

    }
}