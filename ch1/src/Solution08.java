import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Write an algorithm such that if an element in an M x N array is zero,
// its entire row and column are also set to zero.
public class Solution08 {
    // In our approach, rather than converting the row and column to zero when a 0 element is encountered,
    // we keep track of that elements row and column numbers in separate lists. This is to avoid setting rows or columns
    // to 0 multiple times in case of multiple 0 elements.
    public static void zerofyArray(int[][] matrix) {
        // Lists to keep track of the row and column numbers of elements which are zero.
        List<Integer> rowZeroTracker = new ArrayList<>();
        List<Integer> columnZeroTracker = new ArrayList<>();

        // Iterate through the array keeping track of the rows and column numbers of the zero elements.
        for(int rowCounter = 0; rowCounter < matrix.length; rowCounter++) {
            for(int columnCounter = 0; columnCounter < matrix[0].length; columnCounter++) {
                if(matrix[rowCounter][columnCounter] == 0) {
                    rowZeroTracker.add(rowCounter);
                    columnZeroTracker.add(columnCounter);
                }
            }
        }

        // Iterate through the array changing to 0 all elements whose row or column numbers
        // are present in the ArrayList trackers.
        for(int rowCounter = 0; rowCounter < matrix.length; rowCounter++) {
            for(int columnCounter = 0; columnCounter < matrix[0].length; columnCounter++) {
                if(rowZeroTracker.contains(rowCounter) || columnZeroTracker.contains(columnCounter)) {
                    matrix[rowCounter][columnCounter] = 0;
                }
            }
        }
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
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

    private static void printArrayToConsole(int[][] matrix) {
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

    public static void main(String[] args) {
        int rowSize = Integer.parseInt(readLineFromConsole("Enter the array row size: "));
        int columnSize = Integer.parseInt(readLineFromConsole("Enter the array column size: "));

        int[][] matrix = generateRandomIntArray(rowSize, columnSize);
        printArrayToConsole(matrix);
        zerofyArray(matrix);
        printArrayToConsole(matrix);
    }
}
