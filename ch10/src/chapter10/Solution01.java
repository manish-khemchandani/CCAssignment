package chapter10;

import java.util.Arrays;

// You are given two sorted arrays, A and B, where A has a large enough buffer at the
// end to hold B. Write a method to merge B into A in sorted order.
public class Solution01 {
    public static void mergeSortedArrays(int[] larger, int[] smaller) {
        if(larger == null || smaller == null) {
            return;
        }
        int smallPointer = smaller.length - 1;
        int largePointer = (larger.length - 1) - smallPointer - 1;
        int resultPointer = larger.length - 1;

        while(smallPointer >= 0 && largePointer >= 0) {
            if(smaller[smallPointer] > larger[largePointer]) {
                larger[resultPointer] = smaller[smallPointer];
                smallPointer--;
            } else {
                larger[resultPointer] = larger[largePointer];
                largePointer--;
            }
            resultPointer--;
        }

        while(smallPointer >= 0) {
            larger[resultPointer] = smaller[smallPointer];
            smallPointer--;
            resultPointer--;
        }
    }

    public static void main(String[] args) {
        int length1 = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the length of the first array: "));
        int length2 = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the length of the second array: "));

        int[] array1 = ArrayUtils.generateRandomIntArray(length1);
        Arrays.sort(array1);
        ArrayUtils.printArrayToConsole(array1);

        int[] array2 = ArrayUtils.generateRandomIntArray(length2);
        Arrays.sort(array2);
        int[] larger = new int[length1 + length2];
        System.arraycopy(array2, 0, larger, 0, array2.length);
        ArrayUtils.printArrayToConsole(larger);

        mergeSortedArrays(larger, array1);
        ArrayUtils.printArrayToConsole(larger);
    }
}
