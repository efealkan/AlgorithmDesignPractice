package Greedy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ReverseDelete {
    private static class Edge implements Comparable<Edge> {
        int nodeX;
        int nodeY;
        int cost;
        boolean deleted;

        public Edge(int x, int y, int length) {
            this.nodeX = x;
            this.nodeY = y;
            this.cost = length;
            this.deleted = false;
        }

        @Override
        public int compareTo(Edge other) {
            return -Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        int n = 12;

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(2, 3, 10));
        edges.add(new Edge(3, 4, 8));
        edges.add(new Edge(4, 5, 1));
        edges.add(new Edge(5, 6, 2));
        edges.add(new Edge(3, 6, 2));
        edges.add(new Edge(6, 7, 4));
        edges.add(new Edge(7, 8, 5));
        edges.add(new Edge(8, 9, 6));
        edges.add(new Edge(5, 9, 20));
        edges.add(new Edge(4, 10, 4));
        edges.add(new Edge(10, 11, 5));
        edges.add(new Edge(11, 12, 1));
        edges.add(new Edge(7, 11, 30));

//        edges.add(new Edge(1, 2, 4));
//        edges.add(new Edge(2, 3, 1));
//        edges.add(new Edge(3, 4, 8));
//        edges.add(new Edge(1, 4, 2));
//        edges.add(new Edge(1, 3, 10));

        List<HashMap<Integer, Integer>> graph = new ArrayList<>();

        for (int i=0; i<=n; i++) graph.add(new HashMap<>());

        for (int i=0; i<edges.size(); i++) {
            Edge edge = edges.get(i);
            int nodeX = edge.nodeX;
            int nodeY = edge.nodeY;
            int cost = edge.cost;

            graph.get(nodeX).put(nodeY, cost);
            graph.get(nodeY).put(nodeX, cost);
        }
        List<Edge> mst = ReverseDelete(edges, graph);
        PrintMst(mst);
    }

    public static List<Edge> ReverseDelete(List<Edge> edges, List<HashMap<Integer, Integer>> graph) {
        Collections.sort(edges);

        for (int i=0; i<edges.size(); i++) {
            Edge edge = edges.get(i);
            graph.get(edge.nodeX).remove(edge.nodeY);
            graph.get(edge.nodeY).remove(edge.nodeX);

            if (IsConnected(graph, edge.nodeX, edge.nodeY)) {
                edges.get(i).deleted = true;
            } else {
                graph.get(edge.nodeX).put(edge.nodeY, edge.cost);
                graph.get(edge.nodeY).put(edge.nodeX, edge.cost);
            }
        }

        List<Edge> mst = new ArrayList<>();

        for (int i=0; i<edges.size(); i++) {
            if (!edges.get(i).deleted) mst.add(edges.get(i));
        }
        return mst;
    }

    public static boolean IsConnected(List<HashMap<Integer, Integer>> graph, int s, int t) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        if (s == t) return true;

        queue.add(s);

        while (!queue.isEmpty()) {
            int node = queue.remove();
            if (visited.contains(node)) continue;
            visited.add(node);

            if (node == t) return true;

            HashMap<Integer, Integer> neighbours = graph.get(node);

            for (Map.Entry<Integer, Integer> neighbour : neighbours.entrySet()) {
                if (visited.contains(neighbour.getKey())) continue;
                if (neighbour.getKey() == t) return true;
                queue.add(neighbour.getKey());
            }
        }
        return false;
    }

    public static void PrintMst(List<Edge> mst) {
        int mst_cost = 0;
        for (Edge e : mst) {
            System.out.println(e.nodeX + " --- " + e.nodeY + " cost: " + e.cost);
            mst_cost += e.cost;
        }
        System.out.println();
        System.out.println("Mst Cost: " + mst_cost);
    }
}
