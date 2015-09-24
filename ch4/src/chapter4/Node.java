package chapter4;

public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> parent;
    public Node<T> left;
    public Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public boolean isLeaf() {
        return left == null && right == null;
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

