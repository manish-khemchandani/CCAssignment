package chapter4;

import java.util.Arrays;

public class Solution03 {
    public static Node<Integer> createBSTFromArray(int[] sortedElements) {
        return getNode(sortedElements, 0, sortedElements.length - 1);
    }

    private static Node<Integer> getNode(int[] sortedElements, int startPosition, int endPosition) {
        if(endPosition < startPosition) {
            return null;
        }
        int midPoint = startPosition + (endPosition - startPosition) / 2;
        int data = sortedElements[midPoint];
        Node<Integer> node = new Node<>(data);
        node.left = getNode(sortedElements, startPosition, midPoint - 1);
        node.right = getNode(sortedElements, midPoint + 1, endPosition);
        return node;
    }

    public static void main(String[] args) {
        int length = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the array length: "));
        int[] array = ArrayUtils.generateRandomIntArray(length);
        Arrays.sort(array);
        ArrayUtils.printArrayToConsole(array);
        System.out.println("");
        Node<Integer> root = createBSTFromArray(array);
        System.out.println(root);
    }
}
