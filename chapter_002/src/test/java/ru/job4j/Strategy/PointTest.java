package ru.job4j.Strategy;


import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void DrawTriangle() {
        Paint paint = new Paint();
        Triangle triangle = new Triangle();
        paint.draw(triangle);
    }
    @Test
    public void DrawSquare() {
        Paint paint = new Paint();
        Square square = new Square();
        paint.draw(square);
    }


}