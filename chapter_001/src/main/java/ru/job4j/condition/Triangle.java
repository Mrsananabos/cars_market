package ru.job4j.condition;

public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double distance(Point left, Point right) {
        return Math.sqrt(Math.pow((left.getX() - right.getX()),2) + Math.pow((left.getY() - right.getY()),2));
    }
    public double period(double ab, double ac, double bc){
        return  (ab + ac + bc) / 2;
    }
    public double area() {
        double rsl = -1;
        double ab = this.distance(this.a, this.b);
        double ac = this.distance(this.a, this.c);
        double bc = this.distance(this.b, this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p *(p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    public boolean exist(double ab, double ac, double bc) {
        return (ab+ac> bc) && (ab +bc > ac) && (ac + bc > ab);
    }

}
