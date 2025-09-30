//202509022 SDEV200 Java - Module 4, Programming Assignment 2, Chapter 21 - exercise 21.3

import java.util.*;
import java.io.*;

public class M4P2 {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Please provide a Java source file as a command line argument.");
            return;
        }

        String filename = args[0];
        File file = new File(filename);
        
        if (file.exists()) {
            System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist");
        }
    }

    public static int countKeywords(File file) throws Exception {
        String[] keywordString = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", 
            "class", "const", "continue", "default", "do", "double", "else", "enum", 
            "extends", "for", "final", "finally", "float", "goto", "if", "implements", 
            "import", "instanceof", "int", "interface", "long", "native", "new", 
            "package", "private", "protected", "public", "return", "short", "static", 
            "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", 
            "transient", "try", "void", "volatile", "while", "true", "false", "null"
        };
        
        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0;
        Scanner input = new Scanner(file);
        boolean inString = false;
        boolean inComment = false;

        while (input.hasNextLine()) {
            String line = input.nextLine();
            // Remove single-line comments
            int singleLineCommentIndex = line.indexOf("//");
            if (singleLineCommentIndex != -1) {
                line = line.substring(0, singleLineCommentIndex);
            }

            // Handle multi-line comments
            while (line.contains("/*")) {
                inComment = true;
                int commentStart = line.indexOf("/*");
                int commentEnd = line.indexOf("*/", commentStart + 2);
                if (commentEnd != -1) {
                    line = line.substring(0, commentStart) + line.substring(commentEnd + 2);
                    inComment = false; // End of block comment
                } else {
                    line = line.substring(0, commentStart); // Remove everything after the start of the comment
                    break; // Exit the loop to process the next line
                }
            }

            // Check for strings and process the line for keywords
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '"') {
                    // Check for escaped quotes
                    if (i == 0 || line.charAt(i - 1) != '\\') {
                        inString = !inString; // Toggle inString state
                    }
                }
            }

            // Process the line for keywords only if not in a string or comment
            if (!inString && !inComment) {
                // Split by whitespace and non-word characters
                String[] words = line.split("[\\s(){};,.=]+"); // Adjusted regex to handle various delimiters
                for (String word : words) {
                    if (!word.isEmpty() && keywordSet.contains(word)) { // Test if word is a keyword
                        count++;
                    }
                }
            }
        }
        input.close();
        return count;
    }
}
