//20250829 SDEV200 Java - Module 1, Programming Assignment 1, Chapter 6 - exercise 6.9

// meter = 0.305 * foot
// foot = 3.279 * meter

public class M1P1 {
    public static void main (String[] args) {

        double startFeet = 1.0;
        double startMeters =3.0;
        
        System.out.println("Feet\tMeters\t|\tMeters\tFeet");
        System.out.println("--------------------------------------");

            for (int i = 0; i < 10; i++) {
                double feet = startFeet + i;          // increment feet by 1 each row
                double meters = footToMeter(feet);    // convert to meters

                double meter = startMeters + i * 5;   // increment meters by 5 each row
                double foot = meterToFoot(meter);     // convert to feet

                // formatted output: tabs for spacing, 2 decimal places
                System.out.printf("%-5.1f\t%-6.2f\t|\t%-6.1f\t%-6.2f%n",
                    feet, meters, meter, foot);
            }    
        }
    public static double footToMeter(double foot) {
        return foot * 0.305;
        
    }
    public static double meterToFoot(double meter) {
        return meter * 3.279;

    }


}

