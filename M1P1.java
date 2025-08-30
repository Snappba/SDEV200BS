//20250829 SDEV200 Java - Module 1, Programming Assignment 1, Chapter 6 - exercise 6.9

// meter = 0.305 * foot
// foot = 3.279 * meter

public class M1P1 {
    public static void main (String[] args) {
        System.out.println("call a method with its measurement to continue");
    }
    public static double footToMeter(double foot) {
        return foot * 0.305;
        
    }
    public static double meterToFoot(double meter) {
        return meter * 3.279;
    }


}

