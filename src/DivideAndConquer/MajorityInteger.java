package DivideAndConquer;

//Problem: https://www.geeksforgeeks.org/check-for-majority-element-in-a-sorted-array/

public class MajorityInteger {
    public static void main(String[] args) {
        int[] arr1 = new int[] {1,2,3,3,3,3,10};
        int[] arr2 = new int[] {1,1,2,4,4,4,6,6};
        int[] arr3 = new int[] {1,1,1,2,2};

        System.out.println(CheckMajority(arr1, 3));
        System.out.println(CheckMajority(arr2, 4));
        System.out.println(CheckMajority(arr3, 1));
        System.out.println(CheckMajority(arr3, 5));
    }

    public static boolean CheckMajority(int[] arr, int x) {
        int n = arr.length-1;
        int firstOccurrence = FindFirstOccurrence(arr, 0, n, x);

        if (firstOccurrence == -1) return false;

        int index = firstOccurrence + n/2;

        if (index <= n && arr[index] == x) return true;
        return false;
    }

    public static int FindFirstOccurrence(int[] arr, int low, int high, int x) {
        if (low >= high) {
            if (arr[low] == x) return low;
            return -1;
        }

        int middle = (low+high)/2;

        if (arr[middle] == x) {
            if (middle-1 >= low && arr[middle-1] == x) {
                return FindFirstOccurrence(arr, low, middle, x);
            }
            return middle;
        } else {
            if (arr[middle] > x) {
                return FindFirstOccurrence(arr, low, middle, x);
            }
            return FindFirstOccurrence(arr, middle+1, high, x);
        }
    }
}
