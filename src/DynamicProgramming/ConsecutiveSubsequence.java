package DynamicProgramming;

import java.util.LinkedList;

//Problem from: http://codeforces.com/problemset/problem/977/F

public class ConsecutiveSubsequence {

    public static void main(String[] args) {
        int[] arr = {0, 3, 3, 4, 7, 5, 6, 8};
        ApplyMemoziation(7, arr);

        int[] arr2 = {0, 6, 7, 8, 3, 4, 5, 9, 10, 11};
        ApplyMemoziation(9, arr2);
    }

    private static void ApplyMemoziation(int n, int[] arr) {
        int[][] M = new int[n+1][n+1];

        for (int i=0; i<=n; i++) {
            M[i][0] = 0;
            M[0][i] = 0;
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (arr[j] - arr[i] == M[i][j-1]) M[i][j] = M[i][j-1] + 1;
                else M[i][j] = M[i][j-1];
            }
        }

        int max = 0;
        int s_index = 0;
        for (int i=1; i<=n; i++) {
            if (M[i][n] > max) {
                max = M[i][n];
                s_index = i;
            }
        }
        System.out.println("Length of the subsequence: " + max);

        int[] row = new int[n+1];
        for (int i=0; i<=n; i++) row[i] = M[s_index][i];

        FindIndices(n, s_index, row, arr);
    }

    private static void FindIndices(int n, int s_index, int[] M, int[] arr) {
        LinkedList<Integer> indices = new LinkedList<>();

        for (int i=n; i>0; i--) {
            if (arr[i] - arr[s_index] == M[i-1]) indices.addFirst(i);
        }

        System.out.println("Indices: ");
        for (int i=0; i<indices.size(); i++) System.out.print(indices.get(i) + " ");
        System.out.println();
    }
}
