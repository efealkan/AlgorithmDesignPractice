package DynamicProgramming;

//Problem from: http://codeforces.com/problemset/problem/1245/C

public class NumberOfStrings {

    public static void main(String[] args) {
        FindSolution("ouukarinn");
        FindSolution("banana");
        FindSolution("nnn");
        FindSolution("nnnn");
        FindSolution("uuuabnnn");
        FindSolution("uuuabnnnn");
        FindSolution("wabs");
    }

    private static int FindSolution(String s) {
        int n = s.length();

        if (s.contains("w") || s.contains("m")) {
            System.out.println(0);
            return 0;
        }

        int[] M = new int[n+1];
        M[0] = 1;
        M[1] = 1;

        for (int i=2; i<=n; i++) {
            char c = s.charAt(i-1);

            if ((c == 'u' && s.charAt(i-2) == 'u') || (c == 'n' && s.charAt(i-2) == 'n')) {
                M[i] = M[i-1] + M[i-2];
            }
            else M[i] = M[i-1];
        }

        System.out.println(M[n]);
        return M[n];
    }
}
