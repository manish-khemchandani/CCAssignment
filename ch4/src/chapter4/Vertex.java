package chapter4;

public class Vertex implements Comparable<Vertex> {
    public String name;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Vertex other) {
        return name.compareTo(other.getName());
    }
}
