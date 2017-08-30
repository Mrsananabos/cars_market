package ru.job4j.condition;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class TriangleTest {

    @Test
    public void whenPointOnLineThenTrue() {
        //create of new point.
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        Point c = new Point(0, 2);
        // execute method - is and get result;
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.distance(a, b);
        // assert result by excepted value.
        assertThat(result, is(2D));
    }

    @Test
    public void whenPerimetr() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        Point c = new Point(0, 2);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.period(2, 2, 2);
        assertThat(result, is(3D));
    }

    @Test
    public void whenArea() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        Point c = new Point(0, 2);
        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();
        // assert result by excepted value.
        assertThat(result, closeTo(2D, 0.01));
    }

    @Test
    public void whenExistTrue() {
        Point a = new Point(0, 0);
        Point b = new Point(3, 0);
        Point c = new Point(0, 4);
        Triangle triangle = new Triangle(a, b, c);
        boolean rsl = triangle.exist(3, 4, 5);
        assertThat(rsl, is(true));
    }

    @Test
    public void whenExistFalse() {
        Point a = new Point(0, 0);
        Point b = new Point(2, 0);
        Point c = new Point(0, 2);
        Triangle triangle = new Triangle(a, b, c);
        boolean rsl = triangle.exist(3, 4, 9);
        assertThat(rsl, is(false));
    }
}