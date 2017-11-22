package ru.job4j.CheessBoard;
import ru.job4j.CheessBoard.ImpossibleMoveException;
public class Board {

    private int[][] SizeOfBoard = new int[8][8];
    Figure[][] figures = new Figure[8][8];



    public void fillBoard(){
        figures[7][2] = new Slon(7, 2);
    }


    private class Slon extends Figure {


        public Slon(int x, int y) {
            super(x, y);
        }


        int[][] way(int[][] dist) {
            if (Math.abs(((dist.length ) - (CellPosition.length))) == Math.abs((dist[dist.length - 1].length) - (CellPosition[CellPosition.length - 1].length))) {

                return dist;
            } else {
                throw new ImpossibleMoveException("ime");
            }
        }


        boolean move(int[][] source, int[][] dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException, CloneNotSupportedException {


            if (figures[source.length][figures[source.length - 1].length] == null) {
                throw new FigureNotFoundException("ffe");
            }
            way(dist);
            if (dist.length > source.length || dist[dist.length - 1].length > source[source.length - 1].length) {
                for (int i = 1; i <= Math.abs(dist.length - source.length); i++) {
                    if (figures[source.length + i][source[source.length - 1].length + i] != null) {
                        throw new OccupiedWayException("owe");
                    }
                }
            } else {
                if (dist.length > source.length || dist[dist.length - 1].length < source[source.length - 1].length) {
                    for (int i = 1; i <= Math.abs(dist.length - source.length); i++) {
                        if (figures[source.length + i][source[source.length - 1].length - i] != null) {
                            throw new OccupiedWayException("owe");
                        }
                    }
                } else {
                    if (dist.length < source.length || dist[dist.length - 1].length > source[source.length - 1].length) {
                        for (int i = 1; i <= Math.abs(dist.length - source.length); i++) {
                            if (figures[source.length - i][source[source.length - 1].length + i] != null) {
                                throw new OccupiedWayException("owe");
                            }
                        }

                    } else {
                        for (int i = 1; i <= Math.abs(dist.length - source.length); i++) {
                            if (figures[source.length - i][source[source.length - 1].length - i] != null) {
                                throw new OccupiedWayException("owe");
                            }
                        }
                    }
                }
            }
            Figure clone() {
                return new Slon(dist.length, dist[dist.length-1].length )};

            return true;
        }

        ;
    }


    }


