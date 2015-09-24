package chapter4;

public class Solution08 {
    public static Node getCommonAncestor(Node subTreeRoot, Node first, Node second) {
        if(subTreeRoot == null) {
            return null;
        }
        if(subTreeRoot == first || subTreeRoot == second) {
            return subTreeRoot;
        }

        Node left = getCommonAncestor(subTreeRoot.left, first, second);
        Node right = getCommonAncestor(subTreeRoot.right, first, second);

        if(left != null && right != null) {
            return subTreeRoot;
        }

        if(left != null) {
            return left;
        } else {
            return right;
        }
    }

    public static void main(String[] args) {
        int treeHeight = 5;
        boolean shouldTreeBeBalanced = true;
        Node<Integer> root = TreeUtils.generateBinaryTree(treeHeight, shouldTreeBeBalanced);
        if(root != null) {
            System.out.println(root);
            Node first = root.right.left.left.right;
            Node second = root.right.right.right;
            System.out.println("Finding the common ancestor of " + first.data + " and " + second.data + "...");
            System.out.println("The common ancestor is: " + getCommonAncestor(root, first, second).data);
            System.out.println("");

            first = root.left.left.left.right;
            second = root.right.right.right;
            System.out.println("Finding the common ancestor of " + first.data + " and " + second.data + "...");
            System.out.println("The common ancestor is: " + getCommonAncestor(root, first, second).data);
            System.out.println("");

            first = root.left;
            second = root.left;
            System.out.println("Finding the common ancestor of " + first.data + " and " + second.data + "...");
            System.out.println("The common ancestor is: " + getCommonAncestor(root, first, second).data);
        }
    }
}
