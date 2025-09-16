//202509015 SDEV200 Java - Module 3, Programming Assignment 2, Chapter 13 - exercise 13.15

import java.math.*;
import java.util.Scanner;

public class M3P2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter rational r1 with numerator and denominator separated by a space: ");
        String n1 = input.next();
        String d1 = input.next();

        System.out.print("Enter rational r2 with numerator and denominator separated by a space: ");
        String n2 = input.next();
        String d2 = input.next();

        RationalUsingBigInteger r1 = new RationalUsingBigInteger(
            new BigInteger(n1), new BigInteger(d1));
        RationalUsingBigInteger r2 = new RationalUsingBigInteger(
            new BigInteger(n2), new BigInteger(d2));

        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " is " + r2.doubleValue());

        input.close();
    }
}

class RationalUsingBigInteger extends Number implements Comparable<RationalUsingBigInteger> {
    private BigInteger numerator;
    private BigInteger denominator;

    // Constructors
    public RationalUsingBigInteger() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    public RationalUsingBigInteger(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO))
            throw new ArithmeticException("Denominator cannot be zero");

        // Normalize the sign
        if (denominator.signum() < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        // Reduce to lowest terms
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    // Accessors
    public BigInteger getNumerator() { return numerator; }
    public BigInteger getDenominator() { return denominator; }

    // Arithmetic operations
    public RationalUsingBigInteger add(RationalUsingBigInteger r) {
        BigInteger num = numerator.multiply(r.denominator).add(r.numerator.multiply(denominator));
        BigInteger den = denominator.multiply(r.denominator);
        return new RationalUsingBigInteger(num, den);
    }

    public RationalUsingBigInteger subtract(RationalUsingBigInteger r) {
        BigInteger num = numerator.multiply(r.denominator).subtract(r.numerator.multiply(denominator));
        BigInteger den = denominator.multiply(r.denominator);
        return new RationalUsingBigInteger(num, den);
    }

    public RationalUsingBigInteger multiply(RationalUsingBigInteger r) {
        BigInteger num = numerator.multiply(r.numerator);
        BigInteger den = denominator.multiply(r.denominator);
        return new RationalUsingBigInteger(num, den);
    }

    public RationalUsingBigInteger divide(RationalUsingBigInteger r) {
        if (r.numerator.equals(BigInteger.ZERO))
            throw new ArithmeticException("Division by zero");
        BigInteger num = numerator.multiply(r.denominator);
        BigInteger den = denominator.multiply(r.numerator);
        return new RationalUsingBigInteger(num, den);
    }

    // Implement abstract methods from Number
    @Override
    public int intValue() { return (int) doubleValue(); }

    @Override
    public long longValue() { return (long) doubleValue(); }

    @Override
    public float floatValue() { return (float) doubleValue(); }

    @Override
    public double doubleValue() {
        return new BigDecimal(numerator).divide(new BigDecimal(denominator), 10, RoundingMode.HALF_UP).doubleValue();
    }

    // Comparable interface
    @Override
    public int compareTo(RationalUsingBigInteger r) {
        BigInteger left = numerator.multiply(r.denominator);
        BigInteger right = r.numerator.multiply(denominator);
        return left.compareTo(right);
    }

    // Equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RationalUsingBigInteger)) return false;
        RationalUsingBigInteger r = (RationalUsingBigInteger) obj;
        return numerator.equals(r.numerator) && denominator.equals(r.denominator);
    }

    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator.toString();
        else
            return numerator + "/" + denominator;
    }
}
