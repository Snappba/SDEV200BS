//20250908 SDEV200 Java - Module 2, Programming Assignment 2, Chapter 11 - exercise 11.1

import java.util.Scanner;

public class M2P2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Prompt user
        System.out.print("Enter three sides of the triangle: ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();

        System.out.print("Enter the color of the triangle: ");
        String color = input.next();

        System.out.print("Is the triangle filled (true/false)? ");
        boolean filled = input.nextBoolean();

        // Create triangle
        Triangle triangle = new Triangle(side1, side2, side3);
        triangle.setColor(color);
        triangle.setFilled(filled);

        // Display results
        System.out.println(triangle.toString());
        System.out.println("Area: " + triangle.getArea());
        System.out.println("Perimeter: " + triangle.getPerimeter());
        System.out.println("Color: " + triangle.getColor());
        System.out.println("Filled: " + triangle.isFilled());

        input.close();
    }
}

/** Abstract base class GeometricObject */
abstract class GeometricObject {
    private String color = "white";
    private boolean filled;

    /** Default constructor */
    protected GeometricObject() {
    }

    /** Construct a geometric object */
    protected GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    /** Getter for color */
    public String getColor() {
        return color;
    }

    /** Setter for color */
    public void setColor(String color) {
        this.color = color;
    }

    /** Getter for filled */
    public boolean isFilled() {
        return filled;
    }

    /** Setter for filled */
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    /** Abstract methods */
    public abstract double getArea();
    public abstract double getPerimeter();
}

/** Triangle class extending GeometricObject */
class Triangle extends GeometricObject {
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    /** No-arg constructor */
    public Triangle() {
    }

    /** Constructor with specified sides */
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    /** Getters */
    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    /** Compute area using Heron's formula */
    @Override
    public double getArea() {
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    /** Compute perimeter */
    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    /** String description */
    @Override
    public String toString() {
        return "Triangle: side1 = " + side1 +
               " side2 = " + side2 +
               " side3 = " + side3;
    }
}
