import java.util.Scanner;

// 3 edits that can be made on a String are adding, deleting or replacing a character.
// Given two Strings, find if they are 0 or 1 edit away.
public class Solution05 {
    public static boolean areStringsOneAway(String string1, String string2) {
        if(string1.equals(string2)) {   // If the Strings are equal, they are 0 edits away; so, we return true.
            return true;
        }
        // If the Strings have the same length, they have some or all characters replaced by others.
        if(string1.length() == string2.length()) {
            return isCharacterReplaced(string1, string2);
        }
        if(string1.length() == string2.length() + 1) {
            return isCharacterExtra(string1, string2);
        } else if(string2.length() == string1.length() + 1) {
            return isCharacterExtra(string2, string1);
        } else {
            return false;
        }
    }

    // Method which outputs whether two Strings are identical except for one character.
    private static boolean isCharacterReplaced(String string1, String string2) {
        char[] string1Chars = string1.toCharArray();
        char[] string2Chars = string2.toCharArray();
        boolean isCharDifferent = false;
        // Iterate over the characters of the String and find out if one or more characters are different.
        for(int charCounter = 0; charCounter < string1Chars.length; charCounter++) {
            // If we find a second character difference between the 2 Strings, return false.
            if(string1Chars[charCounter] != string2Chars[charCounter]) {
                if(isCharDifferent) {
                    return false;
                } else {
                    // Set a flag to indicate that we have found a character in one
                    // String which is different from the other String.
                    isCharDifferent = true;
                }
            }
        }
        return true;    // A true return value indicates that at most 1 character was different between the 2 Strings.
    }

    // Method which outputs whether 2 Strings are equal except for an extra character.
    private static boolean isCharacterExtra(String longer, String shorter) {
        char[] longerChars = longer.toCharArray();
        char[] shorterChars = shorter.toCharArray();
        boolean isCharMissing = false;
        for(int shorterCounter = 0, longerCounter = 0; shorterCounter < shorterChars.length; shorterCounter++, longerCounter++) {
            if(shorterChars[shorterCounter] != longerChars[longerCounter]) {
                if(isCharMissing) {
                    return false;
                } else {
                    isCharMissing = true;
                    longerCounter++;
                    if(shorterChars[shorterCounter] != longerChars[longerCounter]) {
                        return false;
                    }
                }
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
        String string1 = readLineFromConsole("Enter String1: ");
        String string2 = readLineFromConsole("Enter String2: ");
        System.out.println("Strings are one (or zero) away: " + areStringsOneAway(string1, string2));
    }
}
