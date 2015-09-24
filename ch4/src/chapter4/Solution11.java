package chapter4;

import java.util.Random;

public class Solution11 {
    TreeNode root;

    public void insertInOrder(int data) {
        root = insertInOrder(data, root);
    }

    private TreeNode insertInOrder(int data, TreeNode node) {
        if(node == null) {
            return new TreeNode(data);
        }
        if(data <= node.data) {
            node.left = insertInOrder(data, node.left);
        } else {
            node.right = insertInOrder(data, node.right);
        }
        node.size++;
        return node;
    }

    public TreeNode getRandomNode() {
        if(root == null) {
            return null;
        }
        Random RNG = new Random();
        int randomNumber = RNG.nextInt(root.size);
        return getRandomNode(root, randomNumber);
    }

    private TreeNode getRandomNode(TreeNode node, int nodeNumber) {
        int leftSubtreeSize = 0;
        if(node.left != null) {
            leftSubtreeSize = node.left.size;
        }
        if(nodeNumber == leftSubtreeSize) {
            return node;
        } else if(nodeNumber < leftSubtreeSize) {
            return getRandomNode(node.left, nodeNumber);
        } else {
            return getRandomNode(node.right, nodeNumber - (leftSubtreeSize + 1));
        }
    }

    public static void main(String[] args) {
        Solution11 BST = new Solution11();
        BST.insertInOrder(20);
        BST.insertInOrder(10);
        BST.insertInOrder(30);
        BST.insertInOrder(5);
        BST.insertInOrder(15);
        BST.insertInOrder(35);
        BST.insertInOrder(3);
        BST.insertInOrder(7);
        BST.insertInOrder(17);

        System.out.println(BST.root);

        System.out.println("Getting Random chapter4.Node: " + BST.getRandomNode().data);
        System.out.println("Getting Random chapter4.Node: " + BST.getRandomNode().data);
        System.out.println("Getting Random chapter4.Node: " + BST.getRandomNode().data);
        System.out.println("Getting Random chapter4.Node: " + BST.getRandomNode().data);
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    int size;

    TreeNode(int data) {
        this.data = data;
        size = 1;
    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }

    private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder builder) {
        if(right != null) {
            right.toString(new StringBuilder().append(prefix).append(isTail ? " |   " : "     "), false, builder);
        }
        builder.append(prefix).append(" |--- ").append(data).append("\n");
        if(left != null) {
            left.toString(new StringBuilder().append(prefix).append(isTail ? "     " : " |   "), true, builder);
        }
        return builder;
    }
}