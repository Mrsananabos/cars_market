package ru.job4j.cheessboard;

public abstract class Figure {
    int x;
    int y;
    final int[][] cellPosition = new int[x][y];

    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract boolean way(Cell source, Cell dest, Figure[][] figures);

    abstract Figure clone(Cell dest);




}