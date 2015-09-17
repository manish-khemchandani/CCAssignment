import java.util.Arrays;
import java.util.Scanner;

// Implement an algorithm to determine if a string has all unique characters.
// What if you cannot use additional data structures?
public class Solution01 {
    // To avoid using an additional data structure, we sort the character array representing the String and check if
    // adjacent characters are unique.
    public static boolean areStringCharsUnique(String sentence) {
        char[] sentenceChars = sentence.toCharArray();
        Arrays.sort(sentenceChars);
        for(int charCounter = 1; charCounter < sentenceChars.length; charCounter++) {
            if(sentenceChars[charCounter] == sentenceChars[charCounter - 1]) {
                return false;
            }
        }
        return true;
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String sentence = readLineFromConsole("Enter the sentence that you want checked for uniqueness:");
        System.out.println("Sentence characters are unique: " + areStringCharsUnique(sentence));
    }
}
