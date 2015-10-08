package chapter5;

// Write a function to determine the number of bits required to convert integer A
// to integer B.
// EXAMPLE
// Input: 31,14
// Output: 2
public class Solution06 {
    // We iterate over the bits of the 2 numbers simultaneously, and count the number of
    // bits that are flipped between the 2 numbers. That gives us the number of bits
    // that would have to be flipped to convert one number to another.
    public static int getAwayBits(int number1, int number2) {
        int numberOfAwayBits = 0;
        for(int bitIndex = 0; bitIndex < 32; bitIndex++) {
            if(!areBitsEqual(number1, number2, bitIndex)) {
                numberOfAwayBits++;
            }
        }
        return numberOfAwayBits;
    }

    private static boolean areBitsEqual(int number1, int number2, int bitIndex) {
        int mask = 1 << bitIndex;
        return (mask & number1) == (mask & number2);
    }

    public static void main(String[] args) {
        String binaryString1 = CommonUtils.readLineFromConsole("Enter binary number 1: ");
        String binaryString2 = CommonUtils.readLineFromConsole("Enter binary number 2: ");
        try {
            int binary1 = Integer.parseInt(binaryString1, 2);
            int binary2 = Integer.parseInt(binaryString2, 2);
            System.out.println("Number of bits required to be flipped to convert the 1st number to the 2nd are: " + getAwayBits(binary1, binary2));
        } catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("At least one input is not a binary number.");
        }
    }
}
