package DynamicProgramming;

//Unbounded knapsack problem from: https://www.geeksforgeeks.org/cutting-a-rod-dp-13/

public class UnboundedKnapsack {
    public static void main(String[] args) {
        int[] values = {0, 1, 5, 8, 9, 10, 17, 17, 20};
        ApplyMemoization(8, 8, values);

        int[] values2 = {0, 3, 5, 8, 9, 10, 17, 17, 20};
        ApplyMemoization(8, 8, values2);
    }

    private static int ApplyMemoization(int W, int n, int[] values) {
        int[] M = new int[W+1];
        M[0] = 0;

        for (int w=1; w<=W; w++) {
            int max = 0;
            for (int i=1; i<=n; i++) {
                if (i <= w) {
                    max = Math.max(max, M[w-i] + values[i]);
                }
            }
            M[w] = max;
        }

        System.out.println(M[W]);
        return M[W];
    }
}
