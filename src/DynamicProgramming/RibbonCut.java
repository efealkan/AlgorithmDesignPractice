package DynamicProgramming;

//Problem from: http://codeforces.com/problemset/problem/189/A

public class RibbonCut {

    public static void main(String[] args) {

        int[] arr = {0, 5, 2, 1, 1};
        FindSolution(7, 4, arr);

        int[] arr2 = {0, 5, 5, 3, 2};
        FindSolution(7, 4, arr2);
    }

    private static int FindSolution(int l, int n, int[] arr)
    {
        int[][] M = new int[n+1][l+1];

        for (int i=0; i<=n; i++) M[i][0] = 0;
        for (int i=0; i<=l; i++) M[0][i] = 0;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=l; j++) {
                if (arr[i] > j) M[i][j] = M[i-1][j];
                else M[i][j] = Math.max(M[i-1][j], M[i-1][j-arr[i]] + 1);
            }
        }

        System.out.println(M[n][l]);
        return M[n][l];
    }
}
