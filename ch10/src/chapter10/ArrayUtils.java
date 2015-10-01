package chapter10;

import java.util.Random;

public class ArrayUtils {
    public static int[] generateRandomIntArray(int length) {
        int[] array = new int[length];
        Random RNG = new Random();
        for(int arrayPosition = 0; arrayPosition < length; arrayPosition++) {
            array[arrayPosition] = RNG.nextInt(length * 20);
        }
        return array;
    }

    public static void printArrayToConsole(int[] elements) {
        for(int element : elements) {
            System.out.print(element + " ");
        }
        System.out.println("");
    }

    public static void printArrayToConsole(String[] elements) {
        for(String element : elements) {
            System.out.print(element + " ");
        }
        System.out.println("");
    }

    public static int[][] generateRandomIntArray(int rowSize, int columnSize) {
        boolean wasZeroInserted = false;
        int[][] matrix = new int[rowSize][columnSize];
        Random RNG = new Random();
        for(int rowCounter = 0; rowCounter < matrix.length; rowCounter++) {
            for(int columnCounter = 0; columnCounter < matrix[0].length; columnCounter++) {
                // Higher probability of inserting at least one zero.
                if(!wasZeroInserted) {
                    int coinToss = RNG.nextInt(2);
                    if(coinToss == 0) {
                        matrix[rowCounter][columnCounter] = 0;
                        wasZeroInserted = true;
                        continue;
                    }
                }
                matrix[rowCounter][columnCounter] = RNG.nextInt(rowSize * columnSize);
            }
        }
        return matrix;
    }

    public static void printArrayToConsole(int[][] matrix) {
        int rowLength = matrix.length;
        int columnLength = matrix[0].length;
        for(int rowCounter = 0; rowCounter < rowLength; rowCounter++) {
            for(int columnCounter = 0; columnCounter < columnLength; columnCounter++) {
                System.out.print(matrix[rowCounter][columnCounter] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
