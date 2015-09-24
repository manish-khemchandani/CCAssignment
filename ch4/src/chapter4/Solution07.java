package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution07 {
    public static DirectedGraph createGraph(String projectString, String dependencyString) {
        DirectedGraph graph = new DirectedGraph();
        String[] projects = projectString.split(" ");
        for(String project : projects) {
            graph.addVertex(project);
        }
        String[] dependencies = dependencyString.split(" ");
        for(int i = 0; i < dependencies.length; i = i + 2) {
            graph.addEdge(dependencies[i + 1], dependencies[i]);
        }
        return graph;
    }

    public static List<Vertex> getBuildOrder(DirectedGraph graph) {
        LinkedList<Vertex> buildOrder = new LinkedList<>();
        List<Vertex> visitedNodes = new ArrayList<>();
        for(Vertex vertex : graph.getVertices()) {
            if(!buildOrder.contains(vertex)) {
                getBuildOrder(vertex, graph, visitedNodes, buildOrder);
            }
        }
        return buildOrder;
    }

    private static boolean getBuildOrder(Vertex vertex, DirectedGraph graph, List<Vertex> visitedNodes, LinkedList<Vertex> buildOrder) {
        if(visitedNodes.contains(vertex)) {
            return false;
        }
        visitedNodes.add(vertex);
        Set<Vertex> neighbours = graph.getNeighbours(vertex);
        if(neighbours != null) {
            for(Vertex neighbour : neighbours) {
                if(!buildOrder.contains(neighbour)) {
                    if(!getBuildOrder(neighbour, graph, visitedNodes, buildOrder)) {
                        return false;
                    }
                }
            }
        }
        buildOrder.addFirst(vertex);
        return true;
    }

    public static void main(String[] args) {
        String projects = "a b c d e f g";
        String dependencies = "c f a f b f a c a b e a e b g d";
        // String dependencies = "d a b f d b a f c d";
        DirectedGraph graph = createGraph(projects, dependencies);
        System.out.println(graph);
        List<Vertex> buildOrder = getBuildOrder(graph);
        System.out.println(buildOrder);
    }
}
