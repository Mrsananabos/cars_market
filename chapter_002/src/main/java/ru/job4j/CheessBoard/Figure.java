package ru.job4j.CheessBoard;

public abstract class Figure {
    int x;
    int y;
    final int[][] CellPosition = new int[x][y];

    public Figure(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract int[][] way(int[][] dist);




}