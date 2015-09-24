package chapter4;

public class Solution01 {
    public static boolean isBinaryTreeBalanced(Node root) {
        return getSubtreeHeight(root) != -1;
    }

    private static int getSubtreeHeight(Node node) {
        if(node == null) {
            return 0;
        }
        int leftSubtreeHeight = getSubtreeHeight(node.left);
        if(leftSubtreeHeight == -1) {
            return -1;
        }
        int rightSubtreeHeight = getSubtreeHeight(node.right);
        if(rightSubtreeHeight == -1) {
            return -1;
        }
        if(Math.abs(leftSubtreeHeight - rightSubtreeHeight) <= 1) {
            return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int treeHeight = Integer.parseInt(CommonUtils.readLineFromConsole("Enter the tree height: "));
        boolean shouldTreeBeBalanced = Boolean.parseBoolean(CommonUtils.readLineFromConsole("Enter whether the tree should be balanced: "));
        Node<Integer> root = TreeUtils.generateBinaryTree(treeHeight, shouldTreeBeBalanced);
        if(root != null) {
            System.out.println(root);
            System.out.println("The tree is balanced: " + isBinaryTreeBalanced(root));
        }
    }
}
