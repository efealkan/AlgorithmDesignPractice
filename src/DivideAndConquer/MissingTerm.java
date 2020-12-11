package DivideAndConquer;

//Problem from https://www.techiedelight.com/find-missing-term-sequence-ologn-time/

public class MissingTerm {
    public static void main(String[] args) {
        int[] arr = new int[] {5,7,9,11,15};
        System.out.println(FindMissing(arr, (arr[arr.length-1] - arr[0])/arr.length, 0, arr.length-1));

        arr = new int[] {1,4,7,13,16};
        System.out.println(FindMissing(arr, (arr[arr.length-1] - arr[0])/arr.length, 0, arr.length-1));

        arr = new int[] {5,9,11,13,15, 17};
        System.out.println(FindMissing(arr, (arr[arr.length-1] - arr[0])/arr.length, 0, arr.length-1));

        arr = new int[] {1,4,7,13};
        System.out.println(FindMissing(arr, (arr[arr.length-1] - arr[0])/arr.length, 0, arr.length-1));
    }

    public static int FindMissing(int[] arr, int c, int low, int high) {
        int middle = (low+high)/2;

        if (middle == low) {
            if (middle == 0) {
                if (arr[middle] + c == arr[middle+1]) return -1;
                return arr[middle] + c;
            }
            if (middle == arr.length-1) {
                if (arr[middle] - c == arr[middle-1]) return -1;
                return arr[middle] - c;
            }
            if (arr[middle] + c != arr[middle+1]) return arr[middle]+c;
            if (arr[middle] - c != arr[middle-1]) return arr[middle]-c;
            return -1;
        }

        int diff = arr[middle] - arr[low];
        int count = diff/c;

        if (count == middle-low) {
            return FindMissing(arr, c, middle+1, high);
        }
        return FindMissing(arr, c, low, middle);
    }
}
