package DynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AsynchronousBellmanFord {

    public static void main(String[] args) {
        List<BelmannFord.Edge> edges = new ArrayList<>();

        int n = 7;
        edges.add(new BelmannFord.Edge(1, 2, 2));
        edges.add(new BelmannFord.Edge(2, 3, 1));
        edges.add(new BelmannFord.Edge(3, 4, 1));
        edges.add(new BelmannFord.Edge(1, 5, 3));
        edges.add(new BelmannFord.Edge(3, 5, 2));
        edges.add(new BelmannFord.Edge(5, 7, 3));
        edges.add(new BelmannFord.Edge(7, 4, 5));
        edges.add(new BelmannFord.Edge(5, 6, 4));
        edges.add(new BelmannFord.Edge(6, 4, -10));

        AsynchronousBellmanFord(n, 1, 4, edges);
    }

    private static int AsynchronousBellmanFord(int n, int s, int t, List<BelmannFord.Edge> edges) {
        int[] M = new int[n+1];

        for (int i=0; i<=n; i++) {
            if (i == s) M[s] = 0;
            else M[i] = Integer.MAX_VALUE/2;
        }

        Queue<Integer> active = new LinkedList<>();
        active.add(s);

        while (!active.isEmpty()) {
            int current = active.remove();

            for (BelmannFord.Edge e : edges) {
                if (e.from != current) continue;
                int to = e.to;
                int cost = e.cost;

                if (M[to] > M[current] + cost) {
                    M[to] = M[current] + cost;
                    active.add(to);
                }
            }
        }

        System.out.println(M[t]);
        return M[t];
    }
}
