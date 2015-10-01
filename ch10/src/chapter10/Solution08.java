package chapter10;

public class Solution08 {
    public static void printDuplicates(int[] numbers) {
        BitVector bitVector = new BitVector(32000);
        for(int number : numbers) {
            int adjustedNumber = number - 1;
            if(bitVector.isBitSet(adjustedNumber)) {
                System.out.println(number);
            } else {
                bitVector.setBit(adjustedNumber);
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 2, 3, 4, 4, 5, 7, 7, 7};
        System.out.println("The array is: ");
        ArrayUtils.printArrayToConsole(numbers);
        System.out.println();
        System.out.println("Duplicates in the array are: ");
        printDuplicates(numbers);
    }
}

class BitVector {
    int[] bitVector;

    public BitVector(int size) {
        bitVector = new int[(size / 32) + 1]; // Division can be done using bit shifts (size / 32 = size >> 5).
    }

    boolean isBitSet(int position) {
        int wordNumber = position / 32;
        int bitNumber = (position & 0x1F);
        return (bitVector[wordNumber] & (1 << bitNumber)) != 0;
    }

    void setBit(int position) {
        int wordNumber = (position >> 5);
        int bitNumber = (position & 0x1F);
        bitVector[wordNumber] |= 1 << bitNumber;
    }
}
