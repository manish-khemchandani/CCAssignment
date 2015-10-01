package chapter10;

import java.util.Arrays;

public class Solution04 {
    public static int searchSortedListy(Listy listy, int element) {
        int index = 1;
        int currentElement;
        while((currentElement = listy.elementAt(index)) != -1) {
            if(currentElement == element) {
                return index;
            } else if(element < currentElement) {
                return binarySearch(element, listy, index / 2, index);
            }
            index *= 2;
        }
        int rightEndPoint = getRightEndPoint(listy, index / 2, index);
        return binarySearch(element, listy, index / 2, rightEndPoint);
    }

    private static int binarySearch(int element, Listy listy, int left, int right) {
        if(left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if(listy.elementAt(mid) == element) {
            return mid;
        } else if(element < listy.elementAt(mid)) {
            return binarySearch(element, listy, left, mid - 1);
        } else {
            return binarySearch(element, listy, mid + 1, right);
        }
    }

    private static int getRightEndPoint(Listy listy, int left, int right) {
        if(left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if(listy.elementAt(mid) != -1 && listy.elementAt(mid + 1) == -1) {
            return mid;
        } else if(listy.elementAt(mid) == -1) {
            return getRightEndPoint(listy, left, mid - 1);
        } else {
            return getRightEndPoint(listy, mid + 1, right);
        }
    }

    public static void main(String[] args) {
        Listy listy = new Listy(6);
        listy.addElement(5, 0);
        listy.addElement(4, 1);
        listy.addElement(7, 2);
        listy.addElement(8, 3);
        listy.addElement(10, 4);
        listy.addElement(12, 5);
        listy.sort();
        int element = 12;
        System.out.println(element + "'s index in listy is: " + searchSortedListy(listy, element));
    }
}

class Listy {
    private int[] elements;

    Listy(int size) {
        elements = new int[size];
    }

    boolean addElement(int element, int index) {
        if(index >= elements.length || element < 0) {
            return false;
        }
        elements[index] = element;
        return true;
    }

    int elementAt(int index) {
        if(index >= elements.length) {
            return -1;
        }
        return elements[index];
    }

    public void sort() {
        Arrays.sort(elements);
    }
}
