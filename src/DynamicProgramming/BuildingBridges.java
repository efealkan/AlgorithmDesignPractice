package DynamicProgramming;

//Problem from: https://www.geeksforgeeks.org/dynamic-programming-building-bridges/

public class BuildingBridges {
    public static void main(String[] args) {
        int n = 8;
        int[] matches = {0, 2, 6, 4, 3, 5, 7, 8, 1};
        ApplyMemoization(n, matches);

        int n2 = 6;
        int[] matches2 = {0, 5, 6, 0, 3, 0, 2};
        ApplyMemoization(n2, matches2);
    }

    private static int ApplyMemoization(int n, int[] matches) {
        int[][] M = new int[n+1][n+1];

        for (int i=0; i<=n; i++) {
            M[0][i] = 0;
            M[i][0] = 0;
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (matches[i] == j) {
                    M[i][j] = Math.max(M[i-1][j], M[i-1][j-1] + 1);
                } else {
                    M[i][j] = Math.max(M[i-1][j], M[i][j-1]);
                }
            }
        }

        System.out.println("Number of bridges that can be built: " + M[n][n] + "\n");
        return M[n][n];
    }
}
