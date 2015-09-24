package chapter4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution01 {
    public static boolean doesRouteExist(DirectedGraph graph, Vertex origin, Vertex destination) {
        Queue<Vertex> unvisitedNodes = new LinkedList<>();
        Set<Vertex> visitedNodes = new HashSet<>();

        unvisitedNodes.add(origin);
        visitedNodes.add(origin);
        System.out.print(origin);
        while(!unvisitedNodes.isEmpty()) {
            Vertex node = unvisitedNodes.poll();
            unvisitedNodes.addAll(getUnvisitedNeighbours(graph, node, visitedNodes));
            if(unvisitedNodes.contains(destination)) {
                return true;
            }
        }
        return false;
    }

    private static Set<Vertex> getUnvisitedNeighbours(DirectedGraph graph, Vertex node, Set<Vertex> visitedNodes) {
        Set<Vertex> unvisitedNeighbours = new HashSet<>();
        Set<Vertex> neighbours = graph.getNeighbours(node);
        for(Vertex neighbour : neighbours) {
            if(!visitedNodes.contains(neighbour)) {
                unvisitedNeighbours.add(neighbour);
                visitedNodes.add(neighbour);
            }
        }
        return unvisitedNeighbours;
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge("a", "b");
        graph.addVertex("c");
        graph.addEdge("c", "d");
        graph.addEdge("b", "c");
        graph.addEdge("b", "e");
        graph.addEdge("c", "e");
        graph.addEdge("e", "f");
        graph.addEdge("b", "g");
        graph.addEdge("g", "j");
        graph.addEdge("g", "h");
        graph.addEdge("i", "h");
        graph.addEdge("h", "k");
        Vertex b = graph.getVertex("b");
        Vertex c = graph.getVertex("c");
        Vertex f = graph.getVertex("f");
        Vertex i = graph.getVertex("i");
        System.out.println(graph);
        System.out.println("A route exists between vertices c and f: " + doesRouteExist(graph, c, f));
        System.out.println("A route exists between vertices b and i: " + doesRouteExist(graph, b, i));
    }
}
