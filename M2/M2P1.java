//20250908 SDEV200 Java - Module 2, Programming Assignment 1, Chapter 10 - exercise 10.14

import java.util.Calendar;
import java.util.GregorianCalendar;

public class M2P1 {
    private int year;
    private int month; // 0-based so add 1 to display properly
    private int day;

    public static void main(String[] args) {
        // Using no-arg constructor (current date)
        M2P1 date1 = new M2P1();
        System.out.println("Date 1: " + date1.getYear() + "-" + date1.getMonth() + "-" + date1.getDay());

        // Using elapsed time constructor
        M2P1 date2 = new M2P1(34355555133101L);
        System.out.println("Date 2: " + date2.getYear() + "-" + date2.getMonth() + "-" + date2.getDay());
    }

    // No-arg constructor: current date
    public M2P1() {
        Calendar cal = new GregorianCalendar();
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH); // 0-based
        this.day = cal.get(Calendar.DAY_OF_MONTH);
    }

    // Constructor with elapsed time in milliseconds
    public M2P1(long elapsedTime) {
        setDate(elapsedTime);
    }

    // Constructor with specified year, month, and day
    public M2P1(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getter methods
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month+1;
        //add 1 since months are 0-based
    }

    public int getDay() {
        return day;
    }

    // Set date using elapsed time in milliseconds
    public void setDate(long elapsedTime) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(elapsedTime);
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DAY_OF_MONTH);
    }
}
