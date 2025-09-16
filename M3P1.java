//202509015 SDEV200 Java - Module 3, Programming Assignment 1, Chapter 13 - exercise 13.9
import java.util.Date;

// Base class
class GeometricObject {
    private String color = "white";
    private boolean filled;
    private Date dateCreated;

    public GeometricObject() {
        dateCreated = new Date();
    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
        dateCreated = new Date();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    @Override
    public String toString() {
        return "Created on " + dateCreated + ", color: " + color + ", filled: " + filled;
    }
}

// Circle class
class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;

    public Circle() {
        this.radius = 0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getDiameter() {
        return 2 * radius;
    }

    public void printCircle() {
        System.out.println("The circle is created " + this.getDateCreated() +
                           " and the radius is " + radius);
    }

    @Override
    public int compareTo(Circle o) {
        return Double.compare(this.radius, o.radius);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle other = (Circle) obj;
        return Double.compare(radius, other.radius) == 0;
    }
}

// Main class named for file
public class M3P1 {
    // Standard main method
    public static void main(String[] args) {
        Circle c1 = new Circle(5.0);
        Circle c2 = new Circle(7.0);
        Circle c3 = new Circle(5.0);

        c1.printCircle();
        c2.printCircle();
        c3.printCircle();

        System.out.println("c1 equals c2? " + c1.equals(c2));
        System.out.println("c1 equals c3? " + c1.equals(c3));

        System.out.println("Comparing c1 to c2: " + c1.compareTo(c2));
        System.out.println("Comparing c2 to c1: " + c2.compareTo(c1));
        System.out.println("Comparing c1 to c3: " + c1.compareTo(c3));
    }
}
