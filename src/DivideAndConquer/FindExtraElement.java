package DivideAndConquer;

public class FindExtraElement {
    //Problem: https://www.geeksforgeeks.org/find-index-of-an-extra-element-present-in-one-sorted-array/

    public static void main(String[] args) {
        int[] arr1 = new int[] {2, 4, 6, 8, 9, 10, 12};
        int[] arr2 = new int[] {2, 4, 6, 8, 10, 12};

        System.out.println(FindExtra(arr1, arr2, 0, arr1.length-1));
        arr1 = new int[] {2, 4, 5, 6, 8, 10, 12};
        System.out.println(FindExtra(arr1, arr2, 0, arr1.length-1));
    }

    public static int FindExtra(int[] arr1, int[] arr2, int low, int high) {
        if (low>=high) return arr1[low];

        int middle = (low+high)/2;

        if (arr1[middle] == arr2[middle]) {
            return FindExtra(arr1, arr2, middle+1, high);
        }
        return FindExtra(arr1, arr2, low, middle);
    }
}
