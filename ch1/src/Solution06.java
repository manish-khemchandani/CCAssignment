import java.util.Scanner;

// Implement a method to perform basic string compression using the counts
// of repeated characters. For example, the string aabcccccaaa would become
// a2blc5a3. If the "compressed" string would not become smaller than the original
// string, return the original string.
public class Solution06 {
    public static String getLineWithCharacterCount(String line) {
        // Strings with 1 or 2 characters can not be compressed, so return the original Strings.
        if(line.length() < 3) {
            return line;
        }

        char[] lineChars = line.toCharArray();
        StringBuilder charCountBuilder = new StringBuilder();
        int previousCharCount = 1;
        // Iterate from the second to the last character of the character array representing the String.
        // If that character is equal to the previous character, increase the counter indicating the number of times the
        // character has been encountered. If it is different, append the previous character and its count to the
        // StringBuilder, and start maintaining the count for the current character.
        for(int charCounter = 1; charCounter < lineChars.length; charCounter++) {
            previousCharCount = buildCharCounts(charCountBuilder, lineChars[charCounter],
                    lineChars[charCounter - 1], previousCharCount);
        }
        // So far, only the count of the last character has been maintained. Append the last character and its count to
        // the StringBuilder as well.
        charCountBuilder.append(lineChars[lineChars.length - 1]);
        charCountBuilder.append(previousCharCount);
        // Return the shorter one of the original String and the newly formed String with the character counts.
        if(charCountBuilder.length() >= line.length()) {
            return line;
        } else {
            return charCountBuilder.toString();
        }
    }

    // Method which compares each character with its previous character. If that character is equal to the previous
    // character, the counter indicating the number of times the character has been encountered is increased.
    // If it is different, the previous character and its count are appended to the StringBuilder, and the count for
    // the current character is set to 1.
    private static int buildCharCounts(StringBuilder charCountBuilder,
                                       char currentChar,
                                       char previousChar,
                                       int previousCharCount) {
        if(currentChar == previousChar) {
            previousCharCount++;
        } else {
            charCountBuilder.append(previousChar);
            charCountBuilder.append(previousCharCount);
            previousCharCount = 1;
        }
        return previousCharCount;
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String line = readLineFromConsole("Enter a String: ");
        System.out.println("Line with Character Counts: " + getLineWithCharacterCount(line));
    }
}
