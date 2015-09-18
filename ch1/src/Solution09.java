import java.util.Scanner;

// Given two Strings, check if one is a rotation of the other.
public class Solution09 {
    // If String A is a rotation of String B, then it will be a substring of String B concatenated with itself.
    // For example, if A is cappuccino and B is cinocappuc, then A will be a substring of B + B = cinocappuccinocappuc.
    public static boolean isRotation(String string1, String string2) {
        if(string1 != null && string2!= null && string1.length() == string2.length()) {
            String combinedString = string1 + string1;
            return isSubstring(combinedString, string2);
        }
        return false;
    }

    // Method which checks whether one String is a substring of another String.
    private static boolean isSubstring(String longer, String shorter) {
        return longer.contains(shorter);
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String string1 = readLineFromConsole("Enter String1: ");
        String string2 = readLineFromConsole("Enter String2: ");
        System.out.println("String2 is a rotation of String1: " + isRotation(string1, string2));
    }
}
