package chapter5;

// Given a positive integer, print the next smallest and the next largest number
// that have the same number of 1 bits in their binary representation.
public class Solution04 {
    public static int getNextNumber(int number) {
        int numberLength = 32;
        int startOf1Block = -1;
        int endOf1Block;
        for(int bitIndex = 0; bitIndex < numberLength; bitIndex++) {
            if(isBitSet(number, bitIndex)) {
                if(startOf1Block == -1) {
                    startOf1Block = bitIndex;
                }
            } else {
                if(startOf1Block != -1) {
                    endOf1Block = bitIndex - 1;
                    number = setBit(number, bitIndex);
                    int bitsToSetTo1 = endOf1Block - startOf1Block;
                    for(bitIndex = 0; bitIndex < bitsToSetTo1; bitIndex++) {
                        number = setBit(number, bitIndex);
                    }
                    while(bitIndex <= endOf1Block) {
                        number = resetBit(number, bitIndex);
                        bitIndex++;
                    }
                    break;
                }
            }
        }
        return number;
    }

    private static boolean isBitSet(int number, int bitIndex) {
        int mask = 1 << bitIndex;
        return (number & mask) != 0;
    }

    private static int setBit(int number, int bitIndex) {
        int mask = 1 << bitIndex;
        return number | mask;
    }

    private static int resetBit(int number, int bitIndex) {
        int mask = ~(1 << bitIndex);
        return number & mask;
    }

    public static void main(String[] args) {
        int number = 0B10111100000;
        System.out.println("Given number is " + Integer.toBinaryString(number));
        int nextNumber = getNextNumber(number);
        System.out.println("Next number is " + Integer.toBinaryString(nextNumber));
    }
}
