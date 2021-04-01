package DynamicProgramming;

public class SpaceEfficientKnapsack {

    public static void main(String[] args) {
        int n = 6;
        int W = 20;

        int[] weights = new int[n+1];
        weights[0] = 0;
        weights[1] = 5;
        weights[2] = 2;
        weights[3] = 7;
        weights[4] = 6;
        weights[5] = 6;
        weights[6] = 15;
        int[] values = new int[n+1];
        values[0] = 0;
        values[1] = 10;
        values[2] = 4;
        values[3] = 3;
        values[4] = 9;
        values[5] = 12;
        values[6] = 13;

        SpaceEfficientKnapsack(n, W, weights, values);
    }

    private static int SpaceEfficientKnapsack(int n, int W, int[] weights, int[] values)
    {
        int[] cur = new int[W+1];
        int[] prev = new int[W+1];

        for (int i=0; i<=W; i++) prev[i] = 0;
        cur[0] = 0;

        for (int i=1; i<=n; i++) {
            for (int w=1; w<=W; w++) {
                if (weights[i] > w) cur[w] = prev[w];
                else {
                    cur[w] = Math.max(prev[w], values[i] + prev[w-weights[i]]);
                }
//                System.out.print(cur[w] + " ");
            }
//            System.out.println();

            for (int k=0; k<=W; k++) prev[k] = cur[k];
        }

        int result = cur[W];
        System.out.println(result);
        return result;
    }
}
