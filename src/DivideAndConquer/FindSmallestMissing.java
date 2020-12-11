package DivideAndConquer;

//Problem from: https://www.techiedelight.com/find-smallest-missing-element-sorted-array/

public class FindSmallestMissing {
    public static void main(String[] args) {
        int[] arr = new int[] {0,1,2,6,9,11,15};
        System.out.println(FindSmallestMissing(arr, 0, arr.length-1));

        arr = new int[] {1,2,3,4,6,9,11,15};
        System.out.println(FindSmallestMissing(arr, 0, arr.length-1));

        arr = new int[] {0,1,2,3,4,5,6};
        System.out.println(FindSmallestMissing(arr, 0, arr.length-1));
    }

    public static int FindSmallestMissing(int[] arr, int low, int high) {
        if (low >= high) {
            if (low != arr[low]) return low;
            if (low == 0) {
                if (arr[low+1] != low+1) return low+1;
                return low-1;
            }
            if (low == arr.length-1) {
                if (arr[low-1] != low-1) return low-1;
                return low+1;
            }
            if (arr[low+1] != low+1) return low+1;
            return low-1;
        }

        int middle = (low+high)/2;

        if (arr[middle] == middle) {
            return FindSmallestMissing(arr, middle+1, high);
        }
        return FindSmallestMissing(arr, low, middle);
    }
}
