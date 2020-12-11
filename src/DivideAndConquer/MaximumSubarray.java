package DivideAndConquer;

public class MaximumSubarray {

    public static void main(String[] args) {
        int[] arr = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(FindMax(arr, 0, arr.length-1));
    }

    public static int FindMax(int[] arr, int low, int high) {
        if (low >= high) return arr[low];

        int middle = (low + high)/2;

        int left = FindMax(arr, low, middle);
        int right = FindMax(arr, middle+1, high);

        int maxLeft = Integer.MIN_VALUE;
        int maxRight = Integer.MIN_VALUE;
        int sumLeft = 0;
        int sumRight = 0;

        for (int i=middle; i>=low; i--) {
            sumLeft += arr[i];
            maxLeft = Math.max(sumLeft, maxLeft);
        }
        for (int i=middle+1; i<=high; i++) {
            sumRight += arr[i];
            maxRight = Math.max(sumRight, maxRight);
        }

        int mid = maxLeft + maxRight;

        return Math.max(mid, Math.max(left, right));
    }
}
