package chapter10;

// Given an MX N matrix in which each row and each column is sorted in ascending
// order, write a method to find an element.
public class Solution09 {
    public static int[] searchSortedMatrix(int[][] matrix, int element) {
        if(matrix == null) {
            return null;
        }
        return searchSortedMatrix(matrix, element, 0, matrix.length - 1);
    }

    private static int[] searchSortedMatrix(int[][] matrix, int element, int row, int column) {
        if(column < 0 || row >= matrix.length) {
            return null;
        }
        if(matrix[row][column] == element) {
            return new int[]{row, column};
        } else if(element < matrix[row][column]) {
            return searchSortedMatrix(matrix, element, row, column - 1);
        } else {
            return searchSortedMatrix(matrix, element, row + 1, column);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{15, 20, 40, 85}, {20, 35, 70, 95}, {30, 55, 95, 105}, {40, 80, 100, 120}};
        ArrayUtils.printArrayToConsole(matrix);
        int element = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the element to find: "));
        int[] position = searchSortedMatrix(matrix, element);
        if(position != null) {
            System.out.print("The element is at: ");
            ArrayUtils.printArrayToConsole(position);
        } else {
            System.out.println("The element was not found.");
        }
    }
}
