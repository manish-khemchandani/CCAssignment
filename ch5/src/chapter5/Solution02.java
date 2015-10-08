package chapter5;

import java.util.Scanner;

// Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
// print the binary representation. If the number cannot be represented accurately
// in binary with at most 32 characters, print "ERROR.
public class Solution02 {
    // In the decimal system, we would multiply a fractional number by 10 and then check
    // whether it is greater than zero to verify if the first digit after the decimal
    // place is 0 or non-zero. Similarly, here, we repeatedly multiply the decimal number
    // by 2 to check whether the first digit after the decimal place is 0 or 1.
    public static String convertBinaryToString(double number) {
        String error = "ERROR";
        if(number <= 0 || number >= 1) {
            return error;
        }
        StringBuilder binary = new StringBuilder("0.");
        while(number > 0) {
            if(binary.length() > 32) {
                return error;
            }
            double binaryDouble = number * 2;
            if(binaryDouble >= 1) {
                binary.append("1");
                number = binaryDouble - 1;
            } else {
                binary.append("0");
                number = binaryDouble;
            }
        }
        return binary.toString();
    }

    public static void main(String[] args) {
        System.out.println("Enter a number between 0 and 1: ");
        Scanner in = new Scanner(System.in);
        System.out.println("Binary representation of the number is: " + convertBinaryToString(in.nextDouble()));
    }
}
