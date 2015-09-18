import java.util.Scanner;

// Write a program to rotate an array by 90 degrees clockwise.
public class Solution07 {
    public static void rotateArray(int[][] array) {
        int dimension = array.length;
        // We rotate the array in reducing concentric spirals. We copy the top-left element of the current spiral into
        // a temporary variable, and start copying elements from the corners into their rotated positions, one by one.
        // For an N x N array, we do this rotation N/2 number of times. This is because by this time we have rotated
        // the outer spiral and have reached the innermost spiral.
        for(int rowCounter = 0; rowCounter < dimension / 2; rowCounter++) {
            for(int columnCounter = rowCounter; columnCounter < dimension - rowCounter - 1; columnCounter++) {
                int temp = array[rowCounter][columnCounter];
                array[rowCounter][columnCounter] = array[dimension - columnCounter - 1][rowCounter];
                array[dimension - columnCounter - 1][rowCounter] = array[dimension - rowCounter - 1][dimension - columnCounter - 1];
                array[dimension - rowCounter - 1][dimension - columnCounter - 1] = array[columnCounter][dimension - rowCounter - 1];
                array[columnCounter][dimension - rowCounter - 1] = temp;
            }
        }
    }

    private static String readLineFromConsole(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void printArrayToConsole(int[][] matrix) {
        int arrayDimension = matrix.length;
        for(int rowCounter = 0; rowCounter < arrayDimension; rowCounter++) {
            for(int columnCounter = 0; columnCounter < arrayDimension; columnCounter++) {
                System.out.print(matrix[rowCounter][columnCounter] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int arrayDimension = Integer.parseInt(readLineFromConsole("Enter the array size: "));
        int[][] array = new int[arrayDimension][arrayDimension];
        int counter = 0;
        for(int rowCounter = 0; rowCounter < arrayDimension; rowCounter++) {
            for(int columnCounter = 0; columnCounter < arrayDimension; columnCounter++) {
                array[rowCounter][columnCounter] = counter++;
            }
        }
        printArrayToConsole(array);
        rotateArray(array);
        printArrayToConsole(array);
    }
}
