package chapter4;

public class Solution06 {
    public static Node getNextNode(Node node) {
        if(node == null) {
            return null;
        }
        if(node.right != null) {
            return getLeftMostChild(node.right);
        } else {
            Node child = node;
            Node parent = child.parent;
            while(parent != null && parent.left != child) {
                child = parent;
                parent = child.parent;
            }
            return parent;
        }
    }

    private static Node getLeftMostChild(Node node) {
        if(node == null) {
            return null;
        }
        Node leftChild = getLeftMostChild(node.left);
        if(leftChild == null) {
            return node;
        } else {
            return leftChild;
        }
    }

    static class Tree {
        Node<Integer> root;

        public void insertInOrder(int data) {
            root = insertInOrder(data, root, null);
        }

        private Node<Integer> insertInOrder(int data, Node<Integer> node, Node<Integer> parent) {
            if(node == null) {
                node = new Node<>(data);
                node.parent = parent;
                return node;
            }
            if(data <= node.data) {
                node.left = insertInOrder(data, node.left, node);
            } else {
                node.right = insertInOrder(data, node.right, node);
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Tree BST = new Tree();
        BST.insertInOrder(20);
        BST.insertInOrder(10);
        BST.insertInOrder(30);
        BST.insertInOrder(5);
        BST.insertInOrder(15);
        BST.insertInOrder(3);
        BST.insertInOrder(7);
        BST.insertInOrder(14);
        BST.insertInOrder(17);
        BST.insertInOrder(13);

        System.out.println(BST.root);

        Node node = BST.root.left.left.left;
        System.out.println("Inorder successor of " + node.data + " is: " + getNextNode(node).data);
        node = BST.root.left.left;
        System.out.println("Inorder successor of " + node.data + " is: " + getNextNode(node).data);
        node = BST.root.left;
        System.out.println("Inorder successor of " + node.data + " is: " + getNextNode(node).data);
        node = BST.root.left.right.right;
        System.out.println("Inorder successor of " + node.data + " is: " + getNextNode(node).data);
        node = BST.root.right;
        System.out.println("Inorder successor of " + node.data + " is: " + getNextNode(node));
    }
}
