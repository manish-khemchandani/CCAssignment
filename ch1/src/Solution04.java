import java.util.Scanner;

// Check if a given String is a permutation of a palindrome or not.
public class Solution04 {
    private static final int aIntValue = Character.getNumericValue('a');
    private static final int zIntValue = Character.getNumericValue('z');
    private static final int AIntValue = Character.getNumericValue('A');
    private static final int ZIntValue = Character.getNumericValue('Z');

    public static boolean isPalindromePermutation(String string) {
        int[] charCounts = new int[26]; // A character array to store the counts of each of
        // the 26 letters of the alphabet (case insensitive).
        int oddCounter = 0; // A counter which keeps track of the characters
        // in the input string with an odd count.

        char[] stringChars = string.toCharArray();
        // For each character in the input, increment its count in the charCounts array.
        // If the count of that character becomes odd upon incrementing it, add 1 to the
        // oddCounter, else subtract 1 from it.
        for(Character stringChar : stringChars) {
            int charIntValue = getIntValue(stringChar);
            // A value of -1 indicates the character is not a letter, so ignore it.
            if(charIntValue != -1) {
                charCounts[charIntValue] += 1;
                if(charCounts[charIntValue] % 2 != 0) {
                    oddCounter++;
                } else {
                    oddCounter--;
                }
            }
        }
        // Return true if oddCount has a value of 1 or 0,
        // since palindromes may have at most 1 odd number of characters.
        return oddCounter < 2;
    }

    // Method which returns a value from 1 to 26 for each
    // letter of the alphabet (from a to z - case insensitive).
    private static int getIntValue(Character character) {
        int characterIntValue = Character.getNumericValue(character);
        if(characterIntValue >= aIntValue && characterIntValue <= zIntValue) {
            return characterIntValue - aIntValue;
        } else if(characterIntValue >= AIntValue && characterIntValue <= ZIntValue) {
            return characterIntValue - AIntValue;
        } else {
            return -1;  // -1 indicates the character is not a letter.
        }
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String string = readLineFromConsole("Enter a String: ");
        System.out.println("The String is a palindrome permutation: " + isPalindromePermutation(string));
    }
}
