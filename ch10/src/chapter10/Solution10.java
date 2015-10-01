package chapter10;

import java.util.Random;

// Imagine you are reading in a stream of integers. Periodically, you wish to be able to
// look up the rank of a number x (the number of values less than or equal to x). Implement
// the data structures and algorithms to support these operations. That is, implement
// the method track(int x), which is called when each number is generated,
// and the method getRankOf'Number (int x), which returns the number of values
// less than or equal to x (not including x itself).
public class Solution10 {
    private BinarySearchTree tree;

    Solution10() {
        tree = new BinarySearchTree();
    }

    public void track(int data) {
        tree.insert(data);
    }

    public int getRankOfNumber(int data) {
        return tree.getRank(data);
    }

    public void printTree() {
        System.out.println(tree.getTreeString());
    }

    public static void main(String[] args) {
        Solution10 rankFinder = new Solution10();
        int numberCount = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the number of numbers in the stream: "));
        Random RNG = new Random();
        for(int i = 0; i < numberCount; i++) {
            int number = RNG.nextInt(100);
            System.out.print(number + " ");
            rankFinder.track(number);
        }
        System.out.println();
        System.out.println();
        rankFinder.printTree();
        System.out.println();
        while(true) {
            int data = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the number whose rank you wish to find and -1 to exit: "));
            if(data == -1) {
                break;
            } else {
                System.out.println("The rank of " + data + " is: " + rankFinder.getRankOfNumber(data));
            }
            System.out.println();
        }
    }
}

class BinarySearchTree {
    private Node root;

    class Node {
        int data;
        Node left;
        Node right;
        int leftSubtreeSize;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
        }

        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder builder) {
            if(right != null) {
                right.toString(new StringBuilder().append(prefix).append(isTail ? " |   " : "     "), false, builder);
            }
            builder.append(prefix).append(" |--- ").append(data).append("(").append(leftSubtreeSize).append(")").append("\n");
            if(left != null) {
                left.toString(new StringBuilder().append(prefix).append(isTail ? "     " : " |   "), true, builder);
            }
            return builder;
        }
    }

    public void insert(int data) {
        root = insert(data, root);
    }

    private Node insert(int data, Node node) {
        if(node == null) {
            return new Node(data);
        }
        if(data <= node.data) {
            node.left = insert(data, node.left);
            node.leftSubtreeSize += 1;
        } else {
            node.right = insert(data, node.right);
        }
        return node;
    }

    public int getRank(int data) {
        return getRank(data, root, 0);
    }

    private int getRank(int data, Node node, int rank) {
        if(node == null) {
            return -1;
        }
        if(data == node.data) {
            return node.leftSubtreeSize;
        } else if(data < node.data) {
            rank = getRank(data, node.left, rank);
        } else {
            // rank = node.leftSubtreeSize + 1;
            rank = getRank(data, node.right, rank);
            if(rank != -1) {
                rank += 1 + node.leftSubtreeSize;
            }
        }
        return rank;
    }

    public String getTreeString() {
        return root.toString();
    }
}
