//202509022 SDEV200 Java - Module 4, Programming Assignment 1, Chapter 20 - exercise 20.11

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class M4P1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolsChecker <source-code-file>");
            return;
        }

        String fileName = args[0];
        if (checkGroupingSymbols(fileName)) {
            System.out.println("The file has correct pairs of grouping symbols.");
        } else {
            System.out.println("The file has incorrect pairs of grouping symbols.");
        }
    }

    private static boolean checkGroupingSymbols(String fileName) {
        Stack<Character> stack = new Stack<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (isOpeningSymbol(ch)) {
                        stack.push(ch);
                    } else if (isClosingSymbol(ch)) {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            return false; // Mismatched or unbalanced symbols
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return false;
        }

        return stack.isEmpty(); // Check if all opening symbols are matched
    }

    private static boolean isOpeningSymbol(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    private static boolean isClosingSymbol(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']');
    }
}
