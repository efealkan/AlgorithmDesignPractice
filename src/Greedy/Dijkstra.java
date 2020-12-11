package Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    private static class DirectedEdge {
        int from;
        int to;
        int cost;

        public DirectedEdge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static class Node implements Comparable<Node> {
        int id;
        int cost;

        public Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        int n= 8;
        List<DirectedEdge> edges = new ArrayList<>();
        edges.add(new DirectedEdge(1,5,2));
        edges.add(new DirectedEdge(1,2,3));
        edges.add(new DirectedEdge(5,4,2));
        edges.add(new DirectedEdge(5,6,4));
        edges.add(new DirectedEdge(4,6,3));
        edges.add(new DirectedEdge(4,7,1));
        edges.add(new DirectedEdge(4,3,5));
        edges.add(new DirectedEdge(2,3,5));
        edges.add(new DirectedEdge(3,4,5));
        edges.add(new DirectedEdge(3,8,8));
        edges.add(new DirectedEdge(6,8,7));

        List<HashMap<Integer, Integer>> graph = new ArrayList<>();

        for (int i=0; i<=n; i++) graph.add(new HashMap<>());

        for (int i=0; i<edges.size(); i++) {
            DirectedEdge edge = edges.get(i);
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            graph.get(from).put(to, cost);
        }

        int cost = Dijkstra(graph, 1, 8);
        System.out.println(cost);
    }

    public static int Dijkstra(List<HashMap<Integer, Integer>> graph, int s, int t) {
        PriorityQueue<Node> explored = new PriorityQueue<>();
        HashSet<Integer> visited = new HashSet<>();
        int[] distances = new int[graph.size()];

        for (int i=0; i<distances.length; i++) distances[i] = Integer.MAX_VALUE;

        explored.add(new Node(s, 0));
        distances[s] = 0;

        while (!explored.isEmpty()) {
            Node node = explored.remove();
            if (visited.contains(node.id)) continue;
            visited.add(node.id);

            if (node.id == t) break;

            HashMap<Integer, Integer> neighbours = graph.get(node.id);

            for (Map.Entry<Integer, Integer> neighbour : neighbours.entrySet()) {
                if (visited.contains(neighbour.getKey())) continue;
                int cost = node.cost + neighbour.getValue();
                explored.add(new Node(neighbour.getKey(), cost));

                if (distances[neighbour.getKey()] > cost) {
                    distances[neighbour.getKey()] = cost;
                }
            }
        }

        if (!visited.contains(t)) return -1;
        return distances[t];
    }
}
