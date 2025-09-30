//20250829 SDEV200 Java - Module 1, Programming Assignment 2, Chapter 6 - exercise 6.31

/* Steps to validate a card number:
 * double every second digit from right to left, (if doubling a digit makes it 2 digits - add those 2 to get 1 digit)
 * add (sum) all single digits from first step
 * add all digits in the odd places (ones that weren't doubled)
 * combine those sums - if the result is divisible by 10, the card number is valid */

public class M1P2 {
    static long cardnumber = 4388576018410707L; // sample invalid Visa number

    public static void main(String[] args) {
        System.out.println("Card Number: " + cardnumber);
        System.out.println("Valid? " + isValid(cardnumber));
    }

    // Return true if the card number is valid
    public static boolean isValid(long number) {
        int length = getSize(number);
        System.out.println(length);

        // Step 1: check length
        if (length < 13 || length > 16) {
            return false;
        }

        // Step 2: check prefix (Visa/Mastercard/Amex/Discover for exercise)
        if (!(prefixMatched(number, 4) || prefixMatched(number, 5) ||
              prefixMatched(number, 37) || prefixMatched(number, 6))) {
            return false;
        }

        // Step 3: Luhn Check
        int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        System.out.println ("Sum of double evens: " + sumOfDoubleEvenPlace(number) + " sum of odd place: " + sumOfOddPlace(number));
        return sum % 10 == 0;
    }

    // Get result from step 1 (double even-place digits, right-to-left)
    public static int sumOfDoubleEvenPlace(long number) {
        String numStr = Long.toString(number);
        int sum = 0;

        for (int i = numStr.length() - 2; i >= 0; i -= 2) { // start at 2nd-to-last digit
            int digit = Character.getNumericValue(numStr.charAt(i));
            sum += getDigit(digit * 2);
        }
        return sum;
    }

    // Return this number if it is a single digit, otherwise return the sum of the two digits
    public static int getDigit(int number) {
        if (number < 10) return number;
        return number / 10 + number % 10; // e.g. 12 → 1+2=3
    }

    // Return the sum of odd-place digits in number (from right-to-left)
    public static int sumOfOddPlace(long number) {
        String numStr = Long.toString(number);
        int sum = 0;

        for (int i = numStr.length() - 1; i >= 0; i -= 2) { // start at last digit
            sum += Character.getNumericValue(numStr.charAt(i));
        }
        return sum;
    }

    // Return true if the number d is a prefix for number
    public static boolean prefixMatched(long number, int d) {
        int prefixSize = getSize(d);
        return getPrefix(number, prefixSize) == d;
    }

    // Return the number of digits in d
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    // Return the first k number of digits from number.
    public static long getPrefix(long number, int k) {
        String numStr = Long.toString(number);
        if (numStr.length() < k) return number;
        return Long.parseLong(numStr.substring(0, k));
    }
}