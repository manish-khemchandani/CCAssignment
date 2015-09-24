package chapter4;

import java.util.*;

public class DirectedGraph {
    public Map<String, Vertex> vertices;
    public Map<Vertex, Set<Vertex>> neighbours;
    public int numberOfVertices;
    public int numberOfEdges;

    public DirectedGraph() {
        vertices = new HashMap<>();
        neighbours = new HashMap<>();
        numberOfVertices = 0;
        numberOfEdges = 0;
    }

    public Vertex addVertex(String name) {
        if(name == null || vertices.containsKey(name)) {
            return null;
        }
        Vertex vertex = new Vertex(name);
        vertices.put(name, vertex);
        neighbours.put(vertex, new TreeSet<Vertex>());
        numberOfVertices++;
        return vertex;
    }

    public Vertex getVertex(String name) {
        if(name == null) {
            return null;
        }
        return vertices.get(name);
    }

    public boolean addEdge(String from, String to) {
        if(from == null || to == null || hasEdge(from, to)) {
            return false;
        }
        Vertex fromVertex = getVertex(from);
        Vertex toVertex = getVertex(to);
        if(fromVertex == null) {
            fromVertex = addVertex(from);
        }
        if(toVertex == null) {
            toVertex = addVertex(to);
        }
        neighbours.get(fromVertex).add(toVertex);
        numberOfEdges++;
        return true;
    }

    public boolean hasEdge(String from, String to) {
        Vertex fromVertex = getVertex(from);
        Vertex toVertex = getVertex(to);
        if(fromVertex == null || toVertex == null) {
            return false;
        }
        Set<Vertex> fromConnections = neighbours.get(fromVertex);
        return fromConnections.contains(toVertex);
    }

    public Set<Vertex> getNeighbours(Vertex vertex) {
        if(vertex == null) {
            return null;
        }
        return neighbours.get(vertex);
    }

    public Set<Vertex> getVertices() {
        return new HashSet<>(vertices.values());
    }

    public String toString() {
        StringBuilder graphBuilder = new StringBuilder();
        for(Vertex vertex : vertices.values()) {
            graphBuilder.append(vertex).append(" -> ");
            for(Vertex connection : neighbours.get(vertex)) {
                graphBuilder.append(connection).append(". ");
            }
            graphBuilder.append(System.lineSeparator());
        }
        return graphBuilder.toString();
    }
}
