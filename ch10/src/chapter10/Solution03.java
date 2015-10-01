package chapter10;

import java.util.Arrays;
import java.util.Random;

// Given a sorted array of n integers that has been rotated an unknown number of
// times, write code to find an element in the array. You may assume that the array was
// originally sorted in increasing order.
public class Solution03 {
    public static int getIndex(int element, int[] rotatedArray) {
        if(rotatedArray == null) {
            return -1;
        }
        return getIndex(element, rotatedArray, 0, rotatedArray.length - 1);
    }

    private static int getIndex(int element, int[] rotatedArray, int left, int right) {
        if(left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if(rotatedArray[mid] == element) {
            return mid;
        }
        if(rotatedArray[left] < rotatedArray[mid]) {
            if(rotatedArray[left] <= element && element < rotatedArray[mid]) {
                return getIndex(element, rotatedArray, left, mid - 1);
            } else {
                return getIndex(element, rotatedArray, mid + 1, right);
            }
        } else if(rotatedArray[left] > rotatedArray[mid]) {
            if(rotatedArray[mid] < element && element <= rotatedArray[right]) {
                return getIndex(element, rotatedArray, mid + 1, right);
            } else {
                return getIndex(element, rotatedArray, left, mid - 1);
            }
        } else {
            // If the right-most element is different from the left and middle elements,
            // it is enough to search just the right half of the array.
            // For example, assume we are trying to find 3 in the array 5 5 5 5 5 3 4.
            // However, if the same array is rotated differently so that the left, the right
            // and the middle element are identical, we need to search both halves of the array.
            // For example, if the array is 5 3 4 5 5 5 5, and we are trying to find 3,
            // we need to search both halves.
            if(rotatedArray[mid] == rotatedArray[right]) {
                int index = getIndex(element, rotatedArray, left, mid - 1);
                if(index == -1) {
                    index = getIndex(element, rotatedArray, mid + 1, right);
                }
                return index;
            } else {
                return getIndex(element, rotatedArray, mid + 1, right);
            }
        }
    }

    private static int[] getRotatedArray(int[] array) {
        Arrays.sort(array);
        Random RNG = new Random();
        int rotationIndex = RNG.nextInt(array.length);
        int[] rotatedArray = new int[array.length];
        System.arraycopy(array, 0, rotatedArray, array.length - rotationIndex - 1, rotationIndex + 1);
        System.arraycopy(array, rotationIndex + 1, rotatedArray, 0, array.length - rotationIndex - 1);
        return rotatedArray;
    }

    public static void main(String[] args) {
        int length = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the length of the array: "));
        int[] array = ArrayUtils.generateRandomIntArray(length);
        System.out.print("The array is: ");
        ArrayUtils.printArrayToConsole(array);
        int[] rotatedArray = getRotatedArray(array);
//        int[] rotatedArray = {5, 5, 5, 5, 5, 3, 4};
//        int[] rotatedArray = {5, 5, 5, 5, 3, 4, 5};
        System.out.print("The sorted and rotated array is: ");
        ArrayUtils.printArrayToConsole(rotatedArray);
        int element = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the element to find: "));
        System.out.println("The element is at position: " + getIndex(element, rotatedArray));
    }
}
