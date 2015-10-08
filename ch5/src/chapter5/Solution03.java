package chapter5;

// Write a method to find the longest sequence of 1s possible in a number
// if you were allowed to flip one 0 to a 1.
public class Solution03 {
    // We check the bits of the number one by one. If we encounter a 0, we
    // know that we could flip it to create a sequence of 1s. However, the 1s
    // encountered after the 0 could contribute to the next longest sequence
    // as well. For example, consider 11111101111011. While moving onwards from
    // the units place, we encounter 2 1s and then a 0. The 1s we encounter could
    // contribute to two sequences 1111011 or 11111101111. Hence, whenever we
    // encounter a 0, we add the number 1s before it to the length of the current
    // sequence.
    public static int getLongestSequence(int number) {
        int numberLength = 32;
        int last0index = -1;
        int currentSequence = 0;
        int longestSequence = 0;
        int previousSequenceOf1s = 0;
        for(int bitIndex = 0; bitIndex < numberLength; bitIndex++) {
            if(isBitSet(number, bitIndex)) {
                if(last0index != -1) {
                    previousSequenceOf1s++;
                }
            } else {
                if(last0index == -1) {
                    last0index = bitIndex;
                } else {
                    if(longestSequence < currentSequence) {
                        longestSequence = currentSequence;
                    }
                    currentSequence = previousSequenceOf1s;
                    previousSequenceOf1s = 0;
                }
            }
            currentSequence++;
        }
        if(longestSequence < currentSequence) {
            longestSequence = currentSequence;
        }
        return longestSequence;
    }

    private static boolean isBitSet(int number, int bitIndex) {
        int mask = 1 << bitIndex;
        return (number & mask) != 0;
    }

    public static void main(String[] args) {
        String binaryString = CommonUtils.readLineFromConsole("Enter a binary number: ");
        try {
            int binary = Integer.parseInt(binaryString, 2);
            System.out.println("Longest sequence possible by flipping a 0 to a 1 is: " + getLongestSequence(binary));
        } catch(Exception e) {
            System.out.println(e.toString());
            System.out.println("Input is not a binary number.");
        }
    }
}
