package DivideAndConquer;

//Problem from: https://www.techiedelight.com/count-occurrences-number-sorted-array-duplicates/

public class CountOccurrences {
    public static void main(String[] args) {
        int[] arr = new int[] {2,5,5,5,6,6,8,9,9,9,9,9,10};

        System.out.println(FindOccurrences(arr, 5));
        System.out.println(FindOccurrences(arr, 9));
        System.out.println(FindOccurrences(arr, 10));
    }

    public static int FindOccurrences(int[] arr, int x) {
        int first = FirstOccurrence(arr, x, 0, arr.length-1);
        int last = LastOccurrence(arr, x, 0, arr.length-1);

        return last - first + 1;
    }

    public static int FirstOccurrence(int[] arr, int x, int low, int high) {
        if (low >= high) return low;

        int middle = (low+high)/2;

        if (arr[middle] == x) {
            if (middle-1 >= low && arr[middle-1] == x) return FirstOccurrence(arr, x, low, middle);
            return middle;
        }
        if (arr[middle] < x) return FirstOccurrence(arr, x, middle+1, high);
        return FirstOccurrence(arr, x, low, middle);
    }

    public static int LastOccurrence(int[] arr, int x, int low, int high) {
        if (low >= high) return low;

        int middle = (low+high)/2;

        if (arr[middle] == x) {
            if (middle+1 <= high && arr[middle+1] == x) return LastOccurrence(arr, x, middle+1, high);
            return middle;
        }
        if (arr[middle] < x) return LastOccurrence(arr, x, middle+1, high);
        return LastOccurrence(arr, x, low, middle);
    }
}
