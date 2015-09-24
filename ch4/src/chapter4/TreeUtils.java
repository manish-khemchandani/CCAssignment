package chapter4;

import java.util.Arrays;

public class TreeUtils {
    public static Node<Integer> generateBinaryTree(int treeHeight, boolean shouldTreeBeBalanced) {
        if(treeHeight == 0) {
            return null;
        }
        if(shouldTreeBeBalanced) {
            return generateBalancedNode(treeHeight);
        } else {
            return generateUnbalancedNode(treeHeight);
        }
    }

    private static Node<Integer> generateBalancedNode(int treeHeight) {
        if(treeHeight == 0) {
            return null;
        }
        Node<Integer> node = new Node<>((int) (Math.random() * treeHeight * 20));
        treeHeight--;
        node.left = generateBalancedNode(treeHeight);
        node.right = generateBalancedNode(treeHeight);
        return node;
    }

    private static Node<Integer> generateUnbalancedNode(int treeHeight) {
        if(treeHeight <= 0) {
            return null;
        }
        Node<Integer> node = new Node<>((int) (Math.random() * treeHeight * 20));
        treeHeight--;
        node.left = generateUnbalancedNode(treeHeight);
        node.right = generateUnbalancedNode(treeHeight - 2);
        return node;
    }

    public static Node<Integer> generateBST(int treeHeight) {
        int numberOfElements = (int) Math.pow(2, treeHeight) - 1;
        int[] integers = ArrayUtils.generateRandomIntArray(numberOfElements);
        Arrays.sort(integers);
        return Solution03.createBSTFromArray(integers);
    }

//    public static Node<Integer> generateBinaryTreeWithParentLinks(int treeHeight, boolean shouldTreeBeBalanced) {
//        if(treeHeight == 0) {
//            return null;
//        }
//        if(shouldTreeBeBalanced) {
//            return generateBalancedNode(treeHeight);
//        } else {
//            return generateUnbalancedNode(treeHeight);
//        }
//    }
//
//    private static Node<Integer> generateBalancedNode(int treeHeight) {
//        if(treeHeight == 0) {
//            return null;
//        }
//        Node<Integer> node = new Node<>((int) (Math.random() * treeHeight * 20));
//        treeHeight--;
//        node.left = generateBalancedNode(treeHeight);
//        node.right = generateBalancedNode(treeHeight);
//        return node;
//    }

    public static void traverseTreeInOrder(Node node) {
        if(node == null) {
            return;
        }
        traverseTreeInOrder(node.left);
        System.out.print(node.data + " ");
        traverseTreeInOrder(node.right);
    }
}
