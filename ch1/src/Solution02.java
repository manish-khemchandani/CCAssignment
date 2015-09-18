import java.util.Arrays;
import java.util.Scanner;

// Given two strings, write a method to decide if one is a permutation of the other.
public class Solution02 {
    // A straightforward approach is to sort the two String and see if they are equal.
    // If yes, they are permutations of each other. This method also does not use additional
    // space.
    public static boolean areAnagrams(String string1, String string2) {
        char[] string1Chars = string1.toCharArray();
        char[] string2Chars = string2.toCharArray();
        Arrays.sort(string1Chars);
        Arrays.sort(string2Chars);
        return Arrays.equals(string1Chars, string2Chars);
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String string1 = readLineFromConsole("Enter String1: ");
        String string2 = readLineFromConsole("Enter String2: ");
        System.out.println("Anagrams: " + areAnagrams(string1, string2));
    }
}
