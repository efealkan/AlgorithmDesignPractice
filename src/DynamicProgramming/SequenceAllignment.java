package DynamicProgramming;

public class SequenceAllignment {

    public static void main(String[] args) {
        System.out.println(AllignmentCost("kitten", "sitting", 1, 1));
        System.out.println(AllignmentCost("cat", "case", 1, 2));
        System.out.println(AllignmentCost("abcbdab", "bdcaba", 1, 1));
    }

    private static int AllignmentCost(String s1, String s2, int mismatch_cost, int penalty) {
        int n = s1.length();
        int m = s2.length();

        int[][] M = new int[n+1][m+1];

        for (int i=0; i<=n; i++) M[i][0] = i * mismatch_cost;
        for (int j=0; j<=m; j++) M[0][j] = j * mismatch_cost;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                int min;
                //Not matched case
                min = mismatch_cost + Math.min(M[i-1][j], M[i][j-1]);

                //Matched case
                int a = (s1.charAt(i-1) == s2.charAt(j-1)) ? 0 : penalty;
                min = Math.min(min, a + M[i-1][j-1]);

                M[i][j] = min;
            }
        }

        return M[n][m];
    }
}
