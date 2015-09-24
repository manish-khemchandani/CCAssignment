package chapter4;

public class Solution10 {
    public static boolean isSubtree(Node largerTreeRoot, Node smallerTreeRoot) {
        if(smallerTreeRoot == null) {
            return true;
        }
        return containsTree(largerTreeRoot, smallerTreeRoot);
    }

    private static boolean containsTree(Node largerTreeNode, Node smallerTreeRoot) {
        if(largerTreeNode == null) {
            return false;
        }
        if(largerTreeNode.data == smallerTreeRoot.data) {
            if(doTreesMatch(largerTreeNode, smallerTreeRoot)) {
                return true;
            }
        }
        return containsTree(largerTreeNode.left, smallerTreeRoot) || containsTree(largerTreeNode.right, smallerTreeRoot);
    }

    private static boolean doTreesMatch(Node largerTreeNode, Node smallerTreeNode) {
        if(largerTreeNode == null && smallerTreeNode == null) {
            return true;
        }
        if(largerTreeNode == null || smallerTreeNode == null) {
            return false;
        }
        if(largerTreeNode.data == smallerTreeNode.data) {
            return doTreesMatch(largerTreeNode.left, smallerTreeNode.left)
                    && doTreesMatch(largerTreeNode.right, smallerTreeNode.right);
        } else {
            return false;
        }
    }

    private static Node<Integer> generateSubtree(Node<Integer> largerTreeRoot) {
        Node<Integer> node = largerTreeRoot.right.left;
        Node<Integer> smallerTreeRoot = new Node<>(node.data);
        smallerTreeRoot.left = new Node<>(node.left.data);
        smallerTreeRoot.right = new Node<>(node.right.data);
        smallerTreeRoot.left.left = new Node<>(node.left.left.data);
        smallerTreeRoot.left.right = new Node<>(node.left.right.data);
        smallerTreeRoot.right.left = new Node<>(node.right.left.data);
        smallerTreeRoot.right.right = new Node<>(node.right.right.data);
        return smallerTreeRoot;
    }

    public static void main(String[] args) {
        Node<Integer> largerTreeRoot = TreeUtils.generateBinaryTree(5, true);
        Node<Integer> smallerTreeRoot = generateSubtree(largerTreeRoot);
        System.out.println(largerTreeRoot);
        System.out.println(smallerTreeRoot);
        System.out.println("The larger tree contains the smaller tree: " + isSubtree(largerTreeRoot, smallerTreeRoot));

        largerTreeRoot = TreeUtils.generateBinaryTree(5, true);
        smallerTreeRoot = TreeUtils.generateBinaryTree(3, true);
        System.out.println(largerTreeRoot);
        System.out.println(smallerTreeRoot);
        if(smallerTreeRoot != null) {
            smallerTreeRoot.data = 37;
            System.out.println("The larger tree contains the smaller tree: " + isSubtree(largerTreeRoot, smallerTreeRoot));
        }
    }
}
