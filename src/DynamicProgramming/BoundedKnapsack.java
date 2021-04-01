package DynamicProgramming;

public class BoundedKnapsack {
    public static void main(String[] args) {
        int[] values = {0, 1, 7, 6};
        int[] weights = {0, 2, 4, 3};
        int[] amounts = {0, 3, 1, 2};
        BoundedKnapsack(3, 6, values, weights, amounts);

        int[] values2 = {0, 1, 7, 6, 15};
        int[] weights2 = {0, 2, 4, 3, 2};
        int[] amounts2 = {0, 3, 1, 2, 1};
        BoundedKnapsack(4, 6, values2, weights2, amounts2);
    }

    private static int BoundedKnapsack(int n, int W, int[] values, int[] weights, int[] amounts) {
        int[][] opt = new int[n+1][W+1];

        for (int i=0; i<=n; i++) opt[i][0] = 0;
        for (int w=0; w<=W; w++) opt[0][w] = 0;

        for (int i=1; i<=n; i++) {
            for (int w=1; w<=W; w++) {
                int max = opt[i-1][w];
                for (int a=1; a<=amounts[i]; a++) {
                    if (weights[i]*a <= w) {
                        max = Math.max(max, opt[i-1][w-weights[i]*a] + a*values[i]);
                    }
                }
                opt[i][w] = max;
            }
        }

        System.out.println(opt[n][W]);
        return opt[n][W];
    }
}
