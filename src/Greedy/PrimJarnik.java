package Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PrimJarnik {
    private static class UndirectedEdge {
        int node_1;
        int node_2;
        int cost;

        public UndirectedEdge(int from, int to, int cost) {
            this.node_1 = from;
            this.node_2 = to;
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
        List<UndirectedEdge> edges = new ArrayList<>();
        edges.add(new UndirectedEdge(1,5,2));
        edges.add(new UndirectedEdge(1,2,3));
        edges.add(new UndirectedEdge(5,4,2));
        edges.add(new UndirectedEdge(5,6,4));
        edges.add(new UndirectedEdge(4,6,3));
        edges.add(new UndirectedEdge(4,7,1));
        edges.add(new UndirectedEdge(4,3,5));
        edges.add(new UndirectedEdge(2,3,5));
        edges.add(new UndirectedEdge(3,8,8));
        edges.add(new UndirectedEdge(6,8,7));

        List<HashMap<Integer, Integer>> graph = new ArrayList<>();

        for (int i=0; i<=n; i++) graph.add(new HashMap<>());

        for (int i=0; i<edges.size(); i++) {
            UndirectedEdge edge = edges.get(i);
            int node_1 = edge.node_1;
            int node_2 = edge.node_2;
            int cost = edge.cost;
            graph.get(node_1).put(node_2, cost);
            graph.get(node_2).put(node_1, cost);
        }

        int mst = PrimJarnik(graph, 1);
        System.out.println(mst);
    }

    public static int PrimJarnik(List<HashMap<Integer, Integer>> graph, int s) {
        PriorityQueue<Node> explored = new PriorityQueue<>();
        HashSet<Integer> visited = new HashSet<>();
        int[] distances = new int[graph.size()];

        for (int i=0; i<distances.length; i++) distances[i] = Integer.MAX_VALUE;

        explored.add(new Node(s, 0));
        distances[s] = 0;

        int mst = 0;

        while (!explored.isEmpty()) {
            Node node = explored.remove();
            if (visited.contains(node.id)) continue;
            visited.add(node.id);

            mst += node.cost;

            HashMap<Integer, Integer> neighbours = graph.get(node.id);

            for (Map.Entry<Integer, Integer> neighbour : neighbours.entrySet()) {
                if (visited.contains(neighbour.getKey())) continue;
                explored.add(new Node(neighbour.getKey(), neighbour.getValue()));

                if (distances[neighbour.getKey()] > neighbour.getValue()) {
                    distances[neighbour.getKey()] = neighbour.getValue();
                }
            }
        }

        return mst;
    }
}
