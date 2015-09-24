package chapter4;

public class Solution05 {
    public static boolean isBST(Node<Integer> root) {
        return isNodeValid(root, Integer.MIN_VALUE);
    }

    private static boolean isNodeValid(Node<Integer> node, int previousNodeData) {
        if(node == null) {
            return true;
        }
        if(!isNodeValid(node.left, previousNodeData)) {
            return false;
        }
        if(node.data < previousNodeData) {
            return false;
        }
        previousNodeData = node.data;
        if(!isNodeValid(node.right, previousNodeData)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int treeHeight = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the tree height: "));
        boolean shouldBeBST = Boolean.parseBoolean(CommonUtils.readLineFromConsole("Enter whether the binary tree should be a BST: "));
        Node<Integer> root;
        if(shouldBeBST) {
            root = TreeUtils.generateBST(treeHeight);
        } else {
            root = TreeUtils.generateBinaryTree(treeHeight, true);
        }
        System.out.println(root);
        TreeUtils.traverseTreeInOrder(root);
        System.out.println("");
        System.out.println("The binary tree is a BST: " + isBST(root));
    }
}
