package chapter10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Solution07 {
    public static int findMissingInteger(String fileName) throws FileNotFoundException {
        long numberOfPositiveIntegers = ((long) Integer.MAX_VALUE) + 1;
        byte[] bitVector = new byte[(int) (numberOfPositiveIntegers / 8)];
        readFileIntoBitVector(fileName, bitVector);
        return getMissingNumber(bitVector);
    }

    private static void readFileIntoBitVector(String fileName, byte[] bitVector) throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader(fileName));
        while(in.hasNextInt()) {
            int number = in.nextInt();
            int byteNumber = number / 8;
            int positionInByte = number % 8;
            bitVector[byteNumber] |= (1 << positionInByte);
        }
    }

    private static int getMissingNumber(byte[] bitVector) {
        int byteSize = 8;
        for(int byteCounter = 0; byteCounter < bitVector.length; byteCounter++) {
            byte currentByte = bitVector[byteCounter];
            for(int bit = 0; bit < byteSize; bit++) {
                if((currentByte & (1 << bit)) == 0) {
                    return (byteCounter * 8 + bit);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            System.out.println("The first missing integer is: " + findMissingInteger("numbers.txt"));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
