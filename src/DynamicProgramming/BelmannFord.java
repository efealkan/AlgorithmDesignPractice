package DynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BelmannFord {

    public static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static class Pair {
        int cost;
        int[] pre;

        public Pair(int cost, int[] pre) {
            this.cost = cost;
            this.pre = pre;
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();

        int n = 7;
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(2, 3, 1));
        edges.add(new Edge(3, 4, 1));
        edges.add(new Edge(1, 5, 3));
        edges.add(new Edge(3, 5, 2));
        edges.add(new Edge(5, 7, 3));
        edges.add(new Edge(7, 4, 5));
        edges.add(new Edge(5, 6, 4));
        edges.add(new Edge(6, 4, -10));

        Pair pair = Belmann_Ford(n, edges, 1, 4);

        System.out.println("cost of the shortest path: " + pair.cost);

        List<Integer> path = Find_ShortestPath(pair.pre, 1, 4);

        for (int i=0; i<path.size(); i++) System.out.println(i + "th node in path: " + path.get(i));
    }

    public static Pair Belmann_Ford(int n, List<Edge> edges, int s, int t) {
        int[] M = new int[n+1];
        int[] pre = new int[n+1];

        for (int i=0; i<=n; i++) {
            if (i == t) M[i] = 0;
            else M[i] = Integer.MAX_VALUE/2;
            pre[i] = 0;
        }

        for (int i = 0; i<=n-1; i++) {
            int k = 0;
            for (int j=0; j<edges.size(); j++) {
                int from = edges.get(j).from;
                int to = edges.get(j).to;
                int cost = edges.get(j).cost;

                if (M[from] > M[to] + cost) {
                    M[from] = M[to] + cost;
                    pre[from] = to;
                    k++;
                }
            }
            if (k == 0) break; //Early termination!
        }

        return new Pair(M[s], pre);
    }

    public static List<Integer> Find_ShortestPath(int[] pre, int s, int t) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(pre[s]);

        List<Integer> path = new ArrayList<>();
        path.add(s);

        while (!queue.isEmpty()) {
            int current = queue.remove();
            path.add(current);
            if (current == t) break;
            queue.add(pre[current]);
        }
        return path;
    }
}
