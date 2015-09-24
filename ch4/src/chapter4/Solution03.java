package chapter4;

import java.util.ArrayList;
import java.util.List;

public class Solution03 {
    public static List<SinglyLinkedList<Integer>> createLevelOrderLL(Node<Integer> root) {
        if(root == null) {
            return null;
        }
        List<SinglyLinkedList<Integer>> treeLevelData = new ArrayList<>();
        SinglyLinkedList<Integer> rootData = new SinglyLinkedList<>();
        rootData.addLast(root.data);
        treeLevelData.add(rootData);

        SinglyLinkedList<Node<Integer>> rootNode = new SinglyLinkedList<>();
        rootNode.addLast(root);

        createNextLevelLL(rootNode, treeLevelData);
        return treeLevelData;
    }

    private static void createNextLevelLL(SinglyLinkedList<Node<Integer>> lastLevelNodes, List<SinglyLinkedList<Integer>> treeLevelData) {
        SinglyLinkedList<Node<Integer>> currentLevelNodes = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> currentLevelData = new SinglyLinkedList<>();

        for(Node<Integer> lastLevelNode : lastLevelNodes) {
            if(lastLevelNode.left != null) {
                currentLevelNodes.addLast(lastLevelNode.left);
                currentLevelData.addLast(lastLevelNode.left.data);
            }
            if(lastLevelNode.right != null) {
                currentLevelNodes.addLast(lastLevelNode.right);
                currentLevelData.addLast(lastLevelNode.right.data);
            }
        }
        if(currentLevelNodes.isEmpty()) {
            return;
        }
        treeLevelData.add(currentLevelData);
        createNextLevelLL(currentLevelNodes, treeLevelData);
    }

    public static void main(String[] args) {
        int treeHeight = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the tree height: "));
        boolean shouldTreeBeBalanced = Boolean.parseBoolean(CommonUtils.readLineFromConsole("Enter whether the tree should be balanced: "));
        Node<Integer> root = TreeUtils.generateBinaryTree(treeHeight, shouldTreeBeBalanced);
        System.out.println(root);
        List<SinglyLinkedList<Integer>> treeLevelData = createLevelOrderLL(root);
        if(treeLevelData != null) {
            for(SinglyLinkedList<Integer> treeLevel : treeLevelData) {
                SinglyLinkedList.Node curr = treeLevel.first;
                while(curr != treeLevel.last) {
                    System.out.print(curr.data + " -> ");
                    curr = curr.next;
                }
                System.out.println(curr.data);
            }
        }
    }
}
