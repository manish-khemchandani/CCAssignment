package chapter10;

// Given a sorted array of strings which is interspersed with empty strings, write a
// method to find the location of a given string.
public class Solution05 {
    public static int getIndex(String[] strings, String searchString) {
        return getIndex(strings, searchString, 0, strings.length - 1);
    }

    private static int getIndex(String[] strings, String searchString, int left, int right) {
        if(left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if(strings[mid].length() == 0) {
            int leftOfMid = mid - 1;
            int rightOfMid = mid + 1;

            while(true) {
                if(leftOfMid < left && rightOfMid > right) {
                    return -1;
                } else if(leftOfMid >= left && strings[leftOfMid].length() != 0) {
                    mid = leftOfMid;
                    break;
                } else if(rightOfMid <= right && strings[rightOfMid].length() != 0) {
                    mid = rightOfMid;
                    break;
                }
                leftOfMid--;
                rightOfMid++;
            }
        }

        if(strings[mid].equals(searchString)) {
            return mid;
        } else if(strings[mid].compareTo(searchString) < 0) {
            return getIndex(strings, searchString, mid + 1, right);
        } else {
            return getIndex(strings, searchString, left, mid - 1);
        }
    }

    public static void main(String[] args) {
        String[] strings = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        ArrayUtils.printArrayToConsole(strings);
        String searchString = "car";
        System.out.println("Index of " + searchString + " in the array is: " + getIndex(strings, searchString));
    }
}
