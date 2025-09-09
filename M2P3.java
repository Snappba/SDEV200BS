//20250908 SDEV200 Java - Module 2, Programming Assignment 3, Chapter 12 - exercise 12.9

import java.util.Scanner;

// Custom Exception
class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}

public class M2P3 {
    // Method to convert binary string to decimal
    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        // Check for invalid characters
        for (char c : binaryString.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new BinaryFormatException("Not a binary number: " + binaryString);
            }
        }

        // If valid, convert to decimal
        int decimal = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            decimal = decimal * 2 + (binaryString.charAt(i) - '0');
        }
        return decimal;
    }

    // Test program
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a binary number: ");
        String binaryString = input.nextLine();

        try {
            int decimal = bin2Dec(binaryString);
            System.out.println("Decimal value: " + decimal);
        } catch (BinaryFormatException ex) {
            System.out.println(ex.getMessage());
        }

        input.close();
    }
}
