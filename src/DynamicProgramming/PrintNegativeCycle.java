package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrintNegativeCycle {

    public static void main(String[] args) {
        List<BelmannFord.Edge> edges = new ArrayList<>();

        int n = 8;
        edges.add(new BelmannFord.Edge(1, 2, 2));
        edges.add(new BelmannFord.Edge(2, 3, 1));
        edges.add(new BelmannFord.Edge(3, 4, 1));
        edges.add(new BelmannFord.Edge(1, 5, 3));
        edges.add(new BelmannFord.Edge(3, 5, 2));
        edges.add(new BelmannFord.Edge(5, 7, 3));
        edges.add(new BelmannFord.Edge(7, 4, 5));
        edges.add(new BelmannFord.Edge(5, 6, 4));
        edges.add(new BelmannFord.Edge(6, 4, -10));
        edges.add(new BelmannFord.Edge(5, 2, -5));

        System.out.println(NegativeCycle(n, edges, 1, 4));
    }

    private static boolean NegativeCycle(int n, List<BelmannFord.Edge> edges, int s, int t) {
        int[] M = new int[n+1];

        //Initialize
        for (int i=0; i<=n; i++) {
            if (i == s) M[i] = 0;
            else M[i] = Integer.MAX_VALUE/2;
        }

        //BellmanFord
        for (int i=0; i<n-1; i++) {
            boolean early_termination = true;
            for (BelmannFord.Edge edge : edges) {
                int from = edge.from;
                int to = edge.to;
                int cost  = edge.cost;

                if (M[to] > M[from] + cost) {
                    M[to] = M[from] + cost;
                    early_termination = false;
                }
            }
            if (early_termination) break;
        }

        //Check for negative cycle
        for (BelmannFord.Edge edge : edges) {
            int from = edge.from;
            int to = edge.to;
            int cost  = edge.cost;

            if (M[to] > M[from] + cost) {
                CostOfNegativeCycle(to, to, edges);
                return true;
            }
        }

        return false;
    }

    private static int CostOfNegativeCycle(int s, int d, List<BelmannFord.Edge> edges) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        List<Integer> costs = new ArrayList<>();
        for (int i=0; i<edges.size(); i++) costs.add(0);

        queue.add(s);

        while (!queue.isEmpty()) {
            int current = queue.remove();
            if (visited.contains(current)) continue;
            visited.add(current);

            for (BelmannFord.Edge edge : edges) {
                int from = edge.from;
                if (from == current) {
                    int to = edge.to;
                    int cost = edge.cost;
                    costs.set(to, costs.get(from) + cost);

                    if (to == d) {
                        break;
                    } else {
                        queue.add(to);
                    }
                }
            }
        }
        System.out.println("Cost of negative cycle is: " + costs.get(d));
        return costs.get(d);
    }
}
