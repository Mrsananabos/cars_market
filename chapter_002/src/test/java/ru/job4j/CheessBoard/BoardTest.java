package ru.job4j.CheessBoard;


import org.hamcrest.Matcher;
import org.junit.Test;

import ru.job4j.CheessBoard.Board;
import ru.job4j.CheessBoard.Figure;
import ru.job4j.CheessBoard.Cell;


import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BoardTest {


    @Test
    public void whenMoveSlonThenChangeCell() {
        Board board = new Board();
        Cell SourceOfSlon = new Cell(2, 3);// Начальная позиция фигуры
        Cell DestOfSlon = new Cell(5, 6);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfSlon);//Проверка не выхода ячейки за пределы доски
        board.BorderCheck(DestOfSlon);
        Slon slon = new Slon(SourceOfSlon.getX(), SourceOfSlon.getY());
        board.fill(slon, SourceOfSlon.getX(), SourceOfSlon.getY());
        board.move(SourceOfSlon, DestOfSlon, slon);
        assertNull(board.figures[SourceOfSlon.getX()][SourceOfSlon.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[DestOfSlon.getX()][DestOfSlon.getY()]);//Выбраная для хода ячейка должна содержать объект
    }


    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveSlonThenMoveImpossible() throws CloneNotSupportedException {
        Board board = new Board();
        Cell SourceOfSlon = new Cell(5, 4);// Начальная позиция фигуры
        Cell DestOfSlon = new Cell(7, 4);
        board.BorderCheck(SourceOfSlon);//Проверка не выхода ячейки за пределы доски
        board.BorderCheck(DestOfSlon);
        Slon slon = new Slon(SourceOfSlon.getX(), SourceOfSlon.getY());
        board.fill(slon, SourceOfSlon.getX(), SourceOfSlon.getY());
        board.move(SourceOfSlon, DestOfSlon, slon);
    }


    @Test(expected = OccupiedWayException.class)
    public void whenMoveSlonAndPawnOccupiedWay()  {
        Board board = new Board();
        Cell SourceOfSlon = new Cell(3, 2);// Начальная позиция фигуры
        Cell DestOfSlon = new Cell(7, 6);
        board.BorderCheck(SourceOfSlon);//Проверка не выхода ячейки за пределы доски
        board.BorderCheck(DestOfSlon);
        Slon slon = new Slon(SourceOfSlon.getX(), SourceOfSlon.getY());
        board.fill(slon, SourceOfSlon.getX(), SourceOfSlon.getY());
        Cell SourceOfPawn = new Cell(5, 4);// Пешка стоит на пути Слона
        Pawn pawn = new Pawn(SourceOfPawn.getX(), SourceOfPawn.getY());
        board.fill(pawn, SourceOfPawn.getX(), SourceOfPawn.getY());
        board.move(SourceOfSlon, DestOfSlon, slon);
    }



    @Test
    public void whenPawnDoTwoStep()
    {
        Board board = new Board();
        Cell SourceOfPawn = new Cell(3, 1);// Начальная позиция фигуры
        Cell DestOfPawn = new Cell(3, 3);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfPawn);//Проверка не выхода ячейки за пределы доски
        board.BorderCheck(DestOfPawn);
        Pawn pawn = new Pawn(SourceOfPawn.getX(), SourceOfPawn.getY());
        board.fill(pawn, SourceOfPawn.getX(), SourceOfPawn.getY());
        board.move(SourceOfPawn, DestOfPawn, pawn);
        assertNull(board.figures[SourceOfPawn.getX()][SourceOfPawn.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[DestOfPawn.getX()][DestOfPawn.getY()]);
    }


    @Test(expected = ImpossibleMoveException.class)
    public void whenPawnCantDoTwoStep()
    {
        Board board = new Board();
        Cell SourceOfPawn = new Cell(3, 4);// Начальная позиция фигуры
        Cell DestOfPawn = new Cell(3, 6);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfPawn);//Проверка не выхода ячейки за пределы доски
        board.BorderCheck(DestOfPawn);
        Pawn pawn = new Pawn(SourceOfPawn.getX(), SourceOfPawn.getY());
        board.fill(pawn, SourceOfPawn.getX(), SourceOfPawn.getY());
        board.move(SourceOfPawn, DestOfPawn, pawn);
    }


    @Test
    public void whenPawnDoOneStep()
    {
        Board board = new Board();
        Cell SourceOfPawn = new Cell(3, 1);// Начальная позиция фигуры
        Cell DestOfPawn = new Cell(3, 2);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfPawn);//Проверка не выхода ячейки за пределы доски
        board.BorderCheck(DestOfPawn);
        Pawn pawn = new Pawn(SourceOfPawn.getX(), SourceOfPawn.getY());
        board.fill(pawn, SourceOfPawn.getX(), SourceOfPawn.getY());
        board.move(SourceOfPawn, DestOfPawn, pawn);
        assertNull(board.figures[SourceOfPawn.getX()][SourceOfPawn.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[DestOfPawn.getX()][DestOfPawn.getY()]);
    }




    @Test(expected = OccupiedWayException.class)
    public void whenMovePawnAndSlonOccupiedWay() {
        Board board = new Board();
        Cell SourceOfPawn = new Cell(6, 4);// Начальная позиция фигуры
        Cell DestOfPawn = new Cell(6, 5);//Позиция следующего хода фигуры
        Pawn pawn = new Pawn(SourceOfPawn.getX(), SourceOfPawn.getY());
        board.fill(pawn, SourceOfPawn.getX(), SourceOfPawn.getY());
        Cell SourceOfSlon = new Cell(6, 5);
        Slon slon = new Slon(SourceOfSlon.getX(), SourceOfSlon.getY());
        board.fill(slon, SourceOfSlon.x, SourceOfSlon.getY());
        board.move(SourceOfPawn, DestOfPawn, pawn);
    }







    @Test
    public void whenMoveRookThenChangeCell() {
        Board board = new Board();
        Cell SourceOfRook = new Cell(2, 3);// Начальная позиция фигуры
        Cell DestOfRoock = new Cell(6, 3);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfRook);
        board.BorderCheck(DestOfRoock);
        Rook rook = new Rook(SourceOfRook.getX(), SourceOfRook.getY());
        board.fill(rook, SourceOfRook.getX(), SourceOfRook.getY());
        board.move(SourceOfRook, DestOfRoock, rook);
        assertNull(board.figures[SourceOfRook.getX()][SourceOfRook.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[6][3]);
    }



    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveRookThenMoveImpossible() {
        Board board = new Board();
        Cell SourceOfRook = new Cell(2, 3);// Начальная позиция фигуры
        Cell DestOfRook = new Cell(6, 7);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfRook);
        board.BorderCheck(DestOfRook);
        Rook rook = new Rook(SourceOfRook.getX(), SourceOfRook.getY());
        board.fill(rook, SourceOfRook.getX(), SourceOfRook.getY());
        board.move(SourceOfRook, DestOfRook, rook);
    }



    @Test(expected = OccupiedWayException.class)
    public void whenMoveRookThenPawnOccupiedWay() {
        Board board = new Board();
        Cell SourceOfRook = new Cell(2, 3);// Начальная позиция фигуры
        Cell DestOfRoock = new Cell(2, 6);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfRook);
        board.BorderCheck(DestOfRoock);
        Rook rook = new Rook(SourceOfRook.getX(), SourceOfRook.getY());
        board.fill(rook, SourceOfRook.getX(), SourceOfRook.getY());
        Cell SourceOfPawn = new Cell(2,5);
        board.BorderCheck(SourceOfPawn);
        Pawn pawn = new Pawn(SourceOfPawn.getX(), SourceOfPawn.getY());
        board.fill(pawn, SourceOfPawn.getX(),SourceOfPawn.getY());
        board.move(SourceOfRook, DestOfRoock, rook);
    }



    @Test
    public void whenMoveKnightThenChangeCell() {
        Board board = new Board();
        Cell SourceOfKnight = new Cell(3, 3);// Начальная позиция фигуры
        Cell DestOfKnight = new Cell(2, 1);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfKnight);
        board.BorderCheck(DestOfKnight);
        Knight knight = new Knight(DestOfKnight.getX(), DestOfKnight.getY());
        board.fill(knight, SourceOfKnight.getX(), SourceOfKnight.getY());
        board.move(SourceOfKnight, DestOfKnight, knight);
        assertNull(board.figures[SourceOfKnight.getX()][SourceOfKnight.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[DestOfKnight.getX()][DestOfKnight.getY()]);
    }



    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveKnightImpossible() {
        Board board = new Board();
        Cell SourceOfKnight = new Cell(3, 3);// Начальная позиция фигуры
        Cell DestOfKnight = new Cell(6, 4);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfKnight);
        board.BorderCheck(DestOfKnight);
        Knight knight = new Knight(DestOfKnight.getX(), DestOfKnight.getY());
        board.fill(knight, SourceOfKnight.getX(), SourceOfKnight.getY());
        board.move(SourceOfKnight, DestOfKnight, knight);
    }




    @Test
    public void whenMoveKingThenChangeCell()
    {
        Board board = new Board();
        Cell SourceOfKing = new Cell(3, 3);// Начальная позиция фигуры
        Cell DestOfKing = new Cell(4, 4);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfKing);
        board.BorderCheck(DestOfKing);
        King king = new King(SourceOfKing.getX(), SourceOfKing.getY());
        board.fill(king, SourceOfKing.getX(), SourceOfKing.getY());
        board.move(SourceOfKing, DestOfKing, king);
        assertNull(board.figures[SourceOfKing.getX()][SourceOfKing.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[DestOfKing.getX()][DestOfKing.getY()]);
    }


    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveKingImpossible() {
        Board board = new Board();
        Cell SourceOfKing = new Cell(3, 3);// Начальная позиция фигуры
        Cell DestOfKing = new Cell(5, 4);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfKing);
        board.BorderCheck(DestOfKing);
        King king = new King(SourceOfKing.getX(), SourceOfKing.getY());
        board.fill(king, SourceOfKing.getX(), SourceOfKing.getY());
        board.move(SourceOfKing, DestOfKing, king);
    }


    @Test(expected = OccupiedWayException.class)
    public void whenMoveKingAndQeenOccupiedWay() {
        Board board = new Board();
        Cell SourceOfKing = new Cell(3, 3);// Начальная позиция фигуры
        Cell DestOfKing = new Cell(4, 4);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfKing);
        board.BorderCheck(DestOfKing);
        King king = new King(SourceOfKing.getX(), SourceOfKing.getY());
        board.fill(king, SourceOfKing.getX(), SourceOfKing.getY());
        Cell SourceOfQueen = new Cell(4, 4);// Начальная позиция фигуры
        board.BorderCheck(SourceOfQueen);
        Queen queen = new Queen(SourceOfQueen.getX(), SourceOfQueen.getY());
        board.fill(queen, SourceOfQueen.getX(), SourceOfQueen.getY());
        board.move(SourceOfKing, DestOfKing, king);
    }



    @Test
    public void whenMoveQueenThenChangeCell() {
        Board board = new Board();
        Cell SourceOfQueen = new Cell(7, 7);// Начальная позиция фигуры
        Cell DestOfQueen = new Cell(7, 2);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfQueen);
        board.BorderCheck(DestOfQueen);
        Queen queen = new Queen(SourceOfQueen.getX(), SourceOfQueen.getY());
        board.fill(queen, SourceOfQueen.getX(), SourceOfQueen.getY());
        board.move(SourceOfQueen, DestOfQueen, queen);
        assertNull(board.figures[SourceOfQueen.getX()][SourceOfQueen.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[DestOfQueen.getX()][DestOfQueen.getY()]);
    }


    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveQueenThenMoveImpossible()
    {
        Board board = new Board();
        Cell SourceOfQueen = new Cell(3, 2);// Начальная позиция фигуры
        Cell DestOfQueen = new Cell(5, 3);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfQueen);
        board.BorderCheck(DestOfQueen);
        Queen queen = new Queen(SourceOfQueen.getX(), SourceOfQueen.getY());
        board.fill(queen, SourceOfQueen.getX(), SourceOfQueen.getY());
        board.move(SourceOfQueen, DestOfQueen, queen);
        assertNull(board.figures[SourceOfQueen.getX()][SourceOfQueen.getY()]);//начальная позиция не должна содержать фигуры
        assertNotNull(board.figures[DestOfQueen.getX()][DestOfQueen.getY()]);
    }





    @Test(expected = OccupiedWayException.class)
    public void whenMoveQueenAndKingOccupiedWay()
    {
        Board board = new Board();
        Cell SourceOfQueen = new Cell(3, 2);// Начальная позиция фигуры
        Cell DestOfQueen = new Cell(0, 2);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfQueen);
        board.BorderCheck(DestOfQueen);
        Queen queen = new Queen(SourceOfQueen.getX(), SourceOfQueen.getY());
        board.fill(queen, SourceOfQueen.getX(), SourceOfQueen.getY());
        Cell SourceOfKing = new Cell(1, 2);// Король стоит на пути Королевы
        board.BorderCheck(SourceOfKing);
        King king = new King(SourceOfKing.getX(), SourceOfKing.getY());
        board.fill(king, SourceOfKing.getX(), SourceOfKing.getY());
        board.move(SourceOfQueen, DestOfQueen, queen);
    }

    @Test(expected = ArrayIndexOutOfBoardException.class)
    public void whenDestOfCellOutOgBoard() {
        Board board = new Board();
        Cell SourceOfQueen = new Cell(3, 2);// Начальная позиция фигуры
        Cell DestOfQueen = new Cell(8, 2);//Позиция следующего хода фигуры
        board.BorderCheck(SourceOfQueen);
        board.BorderCheck(DestOfQueen);
        Queen queen = new Queen(SourceOfQueen.getX(), SourceOfQueen.getY());
        board.fill(queen, SourceOfQueen.getX(), SourceOfQueen.getY());
        board.move(SourceOfQueen, DestOfQueen, queen);
    }


}

