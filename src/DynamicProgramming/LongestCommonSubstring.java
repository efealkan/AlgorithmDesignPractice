package DynamicProgramming;

//Problem from: https://www.geeksforgeeks.org/longest-common-substring-dp-29/

public class LongestCommonSubstring {
    public static void main(String[] args) {
        ApplyMemoization("abcdxyz", "xyzabcd");
        ApplyMemoization("mine", "mikertmins");
    }

    private static int ApplyMemoization(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] M = new int[n+1][m+1];

        for (int i=0; i<=n; i++) M[i][0] = 0;
        for (int j=0; j<=m; j++) M[0][j] = 0;

        int max = 0;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {

                    M[i][j] = Math.max(M[i-1][j-1] + 1, M[i-1][j]);
                    max = Math.max(max, M[i][j]);

                } else {
                    M[i][j] = 0;
                }
            }
        }

        System.out.println("Length of longest common substring: " + max);

        RetrieveSolution(s1, s2, M, max);

        return max;
    }

    private static String RetrieveSolution(String s1, String s2, int[][] M, int value) {
        int n = s1.length();
        int m = s2.length();

        int v = value;

        StringBuilder substring = new StringBuilder();

        for (int i=n; i>0; i--) {
            for (int j=m; j>0; j--) {
                if (s1.charAt(i-1) == s2.charAt(j-1) && M[i][j] == v) {
                    substring.append(s1.charAt(i-1));
                    v -= 1;
                }
            }
        }

        substring.reverse();

        System.out.println("Longest Common Substring: " + substring.toString());
        System.out.println();
        return substring.toString();
    }
}
