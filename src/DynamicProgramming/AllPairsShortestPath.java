package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class AllPairsShortestPath {
    public static void main(String[] args) {
        List<BelmannFord.Edge> edges = new ArrayList<>();

        int n = 4;
        edges.add(new BelmannFord.Edge(2, 1, 4));
        edges.add(new BelmannFord.Edge(1, 3, -2));
        edges.add(new BelmannFord.Edge(3, 4, 2));
        edges.add(new BelmannFord.Edge(4, 2, -1));
        edges.add(new BelmannFord.Edge(2, 3, 3));

        CreateMatrix(edges, n);
    }

    private static void CreateMatrix(List<BelmannFord.Edge> edges, int n) {
        int[][] M = new int[n+1][n+1];

        for (int i=0; i<=n; i++) {
            M[0][i] = 0;
            M[i][0] = 0;
        }

        //Apply BellmanFord to every node as a starting node
        for (int i=1; i<=n; i++) {
            int[] mem = FindShortestPath(edges, n, i);

            for (int j=1; j<=n; j++) {
                M[i][j] = mem[j];
            }
        }

        PrintMatrix(M, n);
    }

    //BellmanFord
    private static int[] FindShortestPath(List<BelmannFord.Edge> edges, int n, int s) {
        int[] M = new int[n+1];

        for (int i=0; i<=n; i++) {
            if (i == s) M[i] = 0;
            else M[i] = Integer.MAX_VALUE/2;
        }

        for (int i=0; i<n; i++) {
            for (BelmannFord.Edge e : edges) {
                int from = e.from;
                int to = e.to;
                int cost = e.cost;

                if (M[to] > M[from] + cost) {
                    M[to] = M[from] + cost;
                }
            }
        }

        return M;
    }

    private static void PrintMatrix(int[][] M, int n) {
        for (int i=0; i<=n; i++) {
            for (int j=0; j<=n; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
}
