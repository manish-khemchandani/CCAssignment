package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution09 {
    public static List<LinkedList<Integer>> getBSTArrays(Node<Integer> node) {
        List<LinkedList<Integer>> BSTArrays = new ArrayList<>();

        if(node == null) {
            BSTArrays.add(new LinkedList<Integer>());
            return BSTArrays;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);

        List<LinkedList<Integer>> leftBSTArrays = getBSTArrays(node.left);
        List<LinkedList<Integer>> rightBSTArrays = getBSTArrays(node.right);

        for(LinkedList<Integer> leftBSTArray : leftBSTArrays) {
            for(LinkedList<Integer> rightBSTArray : rightBSTArrays) {
                List<LinkedList<Integer>> weavedLists = new ArrayList<>();
                weaveLists(leftBSTArray, rightBSTArray, weavedLists, prefix);
                BSTArrays.addAll(weavedLists);
            }
        }
        return BSTArrays;
    }

    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, List<LinkedList<Integer>> weavedLists, LinkedList<Integer> prefix) {
        if(first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> BSTArray = new LinkedList<>(prefix);
            BSTArray.addAll(first);
            BSTArray.addAll(second);
            weavedLists.add(BSTArray);
            return;
        }

        int headOfFirst = first.removeFirst();
        prefix.addLast(headOfFirst);
        weaveLists(first, second, weavedLists, prefix);
        prefix.removeLast();
        first.addFirst(headOfFirst);

        int headOfSecond = second.removeFirst();
        prefix.addLast(headOfSecond);
        weaveLists(first, second, weavedLists, prefix);
        prefix.removeLast();
        second.addFirst(headOfSecond);
    }

    public static void main(String[] args) {
        int treeHeight = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the tree height: "));
        boolean shouldTreeBeBalanced = Boolean.parseBoolean(CommonUtils.readLineFromConsole("Enter whether the tree should be balanced: "));
        Node<Integer> root = TreeUtils.generateBinaryTree(treeHeight, shouldTreeBeBalanced);
        System.out.println(root);
        List<LinkedList<Integer>> BSTArrays = getBSTArrays(root);
        System.out.println(BSTArrays);
    }
}
