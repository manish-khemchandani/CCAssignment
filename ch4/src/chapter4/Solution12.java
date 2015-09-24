package chapter4;

import java.util.HashMap;
import java.util.Map;

public class Solution12 {
    public static int getNumberOfPathsWithSum(Node<Integer> root, int targetSum) {
        Map<Integer, Integer> pathCounts = new HashMap<>();
        incrementPathCount(pathCounts, 0);
        return getNumberOfPathsWithSum(root, 0, targetSum, pathCounts);
    }

    private static int getNumberOfPathsWithSum(Node<Integer> node, int runningTotal,
                                               int targetSum, Map<Integer, Integer> pathCounts) {
        if(node == null) {
            return 0;
        }
        runningTotal += node.data;
        incrementPathCount(pathCounts, runningTotal);
        int sum = runningTotal - targetSum;
        int totalPaths = 0;
        if(pathCounts.containsKey(sum)) {
            totalPaths += pathCounts.get(sum);
        }
        totalPaths += getNumberOfPathsWithSum(node.left, runningTotal, targetSum, pathCounts);
        totalPaths += getNumberOfPathsWithSum(node.right, runningTotal, targetSum, pathCounts);
        decrementPathCount(pathCounts, runningTotal);
        return totalPaths;
    }

    private static void incrementPathCount(Map<Integer, Integer> pathCounts, int runningTotal) {
        Integer pathCount = pathCounts.get(runningTotal);
        if(pathCount != null) {
            pathCounts.put(runningTotal, pathCount + 1);
        } else {
            pathCounts.put(runningTotal, 1);
        }
    }

    private static void decrementPathCount(Map<Integer, Integer> pathCounts, int runningTotal) {
        Integer pathCount = pathCounts.get(runningTotal);
        if(pathCount != null) {
            if(pathCount == 1) {
                pathCounts.remove(runningTotal);
            } else {
                pathCounts.put(runningTotal, pathCount - 1);
            }
        }
    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(5);
        root.right = new Node<>(-3);
        root.left.left = new Node<>(3);
        root.left.right = new Node<>(2);
        root.right.right = new Node<>(11);
        root.left.left.left = new Node<>(3);
        root.left.left.right = new Node<>(-2);
        root.left.right.right = new Node<>(1);

        System.out.println(root);
        int targetSum = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the target sum: "));
        System.out.println("The number of paths with the target sum are: " + getNumberOfPathsWithSum(root, targetSum));
    }
}
