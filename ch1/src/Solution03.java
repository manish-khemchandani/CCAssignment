import java.util.Scanner;

// Write a method to replace all spaces in a string with'%20'. You may assume that
// the string has sufficient space at the end of the string to hold the additional
// characters, and that you are given the "true" length of the string. (Note: if implementing
// in Java, please use a character array so that you can perform this operation
// in place.)
public class Solution03 {
    // In the first approach, we parse the String to get the number of spaces in the String.
    // As a result, we can declare a StringBuilder of the appropriate length. The benefit is
    // that StringBuilder will not have to be resized internally.
    public static String replaceSpaces(String line) {
        char[] lineChars = line.toCharArray();
        int numberOfSpaces = getNumberOfSpaces(lineChars);
        int newStringLength = getSizeOfNewString(line, numberOfSpaces);
        StringBuilder lineBuilder = new StringBuilder(newStringLength);
        // We iterate over each character in the line and append it to lineBuilder.
        // Each time we encounter a ' ', we append '%20' to the lineBuilder instead of the ' '.
        for(char lineChar : lineChars) {
            if(lineChar == ' ') {
                lineBuilder.append("%20");
            } else {
                lineBuilder.append(lineChar);
            }
        }
        return lineBuilder.toString();
    }

    // Method which takes a character Array as input and returns the
    // number of spaces (' ' characters).
    private static int getNumberOfSpaces(char[] lineChars) {
        int numberOfSpaces = 0;
        for(char lineChar : lineChars) {
            if(lineChar == ' ') {
                numberOfSpaces++;
            }
        }
        return numberOfSpaces;
    }

    // Method which takes a String and the number of spaces in that String as the input
    // and returns the length of the String which would result from replacing each ' ' by '%20'.
    private static int getSizeOfNewString(String line, int numberOfSpaces) {
        return line.length() + (2 * numberOfSpaces);
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String line = readLineFromConsole("Enter the sentence: ");
        System.out.println("String with spaces replaced: ");
        System.out.println(replaceSpaces(line));
    }
}
