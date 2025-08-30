//20250829 SDEV200 Java - Module 1, Programming Assignment 2, Chapter 6 - exercise 6.31

/* Steps to validate a card number:
 * double every second digit from right to left, (if doubling a digit makes it 2 digits - add those 2 to get 1 digit)
 * add (sum) all single digits from first step
 * add all digits in the odd places (ones that weren't doubled)
 * combine those sums - if the result is divisible by 10, the card number is valid */

public class M1P2 {
    static long cardnumber = 1234567899874561L;
    public static void main (String[] args) {
        


       /* System.out.println ("Length: " + length);
        System.out.println ("Prefix Valid? "+ validPrefix);
        System.out.println ("Sum of double even place: " + sumEven); */
    }

    // Return true if the card number is valid
    public static boolean isValid (long number) {
        //length check
        int length = getSize(cardnumber);
        if (length >=13 && length <= 16) {
            return true;
        } else {
            return false;
        }
    }

    //get result from step 2
    public static int sumOfDoubleEvenPlace (long number) {
        return 1;
    }

    //return this number if it is a single digit, otherwise return the sum of the 2 digits
    public static int getDigit (int number) {
        return 1;
    }

    //return the sum of the odd-place digits in number
    public static int sumOfOddPlace (long number) {
        return 1;
    }

    //return true if the number d is a prefix for number
    public static boolean prefixMatched (long number, int d) {
        return true;
    }

    // return the number of digits in d
    public static int getSize (long d) {
        int length = Long.toString(d).length();
        return length;
    }

    //return the first k number of digits from number. if the number of digits is less than k, return number. 
    public static long getPrefix (long number, int k) {
        return 1;
    }




}
