package DivideAndConquer;

//Problem from divide and conquer peer odd assignment

public class MedianOfTwoArrays {
    //O(logn)

    public static void main(String[] args) {
        int[] A = new int[] {1, 3, 4, 7};
        int[] B = new int[] {2, 5, 6, 10};

        int n = A.length-1;

        System.out.println(FindMedian(A, B, n));
//        System.out.println(FindMedian2(A, B, 0, n, 0, n));

        A = new int[] {2, 5, 10, 12, 15};
        B = new int[] {1, 6, 7, 10, 14};
        n = A.length-1;

        System.out.println(FindMedian(A, B, n));
//        System.out.println(FindMedian2(A, B, 0, n, 0, n));
    }

    public static int FindMedian(int[] A, int[] B, int n) {
        int a = 0;
        int b = 0;

        while (n!=1) {
            if (A[a+n/2] < B[b+n/2]) {
                a += n/2;
            } else {
                b += n/2;
            }
            n = n/2;
        }

        return Math.min(A[a+1], B[b+1]);
    }

    public static int FindMedian2(int[] A, int[] B, int lowA, int highA, int lowB, int highB) {
        if (lowA >= highA) {
            return Math.min(A[lowA], B[lowB+1]);
        } else if (lowB >= highB) {
            return Math.min(A[lowA+1], B[lowB]);
        }
        int midA = (lowA + highA)/2;
        int midB = (lowB + highB)/2;

        if (A[midA] < B[midB]) {
            return FindMedian2(A, B, midA+1, highA, lowB, highB);
        }
        return FindMedian2(A, B, lowA, highA, midB+1, highB);
    }
}
