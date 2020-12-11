package DivideAndConquer;

//Problem from: https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/

public class RotationCount {
    public static void main(String[] args) {
        int[] arr = new int[] {15, 18, 2, 3, 6, 12};
        System.out.println(FindMinIndex(arr, 0, arr.length-1));

        arr = new int[] {7, 9, 11, 12, 5};
        System.out.println(FindMinIndex(arr, 0, arr.length-1));

        arr = new int[] {7, 9, 11, 12, 15};
        System.out.println(FindMinIndex(arr, 0, arr.length-1));
    }

    public static int FindMinIndex(int arr[], int low, int high) {
        if (low >= high) return low;

        int middle = (low + high)/2;

        if (middle-1 >= low && arr[middle] < arr[middle-1]) return middle;

        if (arr[low] > arr[high]) return FindMinIndex(arr, middle+1, high);
        return FindMinIndex(arr, low, middle);
    }
}
