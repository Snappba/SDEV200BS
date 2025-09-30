//20250829 SDEV200 Java - Module 1, Programming Assignment 4, Chapter 9 - exercise 9.9

public class M1P4 {
//M1P4 should be replaced with "RegularPolygon in every instance"
    private int n;
    private double side;
    private double x;
    private double y;

    public static void main(String[] args) {
        M1P4 polygon0 = new M1P4();
        M1P4 polygon1 = new M1P4(6,4);
        M1P4 polygon2 = new M1P4(10,4,5.6,7.8);
        System.out.println("Polygon 0 (no arg) perimeter: " + polygon0.getPerimeter() + ", Area: " + polygon0.getArea());
        System.out.println("Polygon 1 perimeter: " + polygon1.getPerimeter() + ", Area: " + polygon1.getArea());
        System.out.println("Polygon 2 perimeter: " + polygon2.getPerimeter() + ", Area: " + polygon2.getArea());
    }

    // No-arg constructor
    public M1P4() {
        this.n = 3;
        this.side = 1;
        this.x = 0;
        this.y = 0;
    }

    // Constructor with n and side, center at (0,0)
    public M1P4(int n, double side) {
        this.n = n;
        this.side = side;
        this.x = 0;
        this.y = 0;
    }

    // Constructor with n, side, x, y
    public M1P4(int n, double side, double x, double y) {
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    // Accessors
    public int getN() { return n; }
    public double getSide() { return side; }
    public double getX() { return x; }
    public double getY() { return y; }

    // Mutators
    public void setN(int n) { this.n = n; }
    public void setSide(double side) { this.side = side; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    // Perimeter
    public double getPerimeter() {
        return n * side;
    }

    // Area
    public double getArea() {
        return (n * side * side) / (4 * Math.tan(Math.PI / n));
    }
}
