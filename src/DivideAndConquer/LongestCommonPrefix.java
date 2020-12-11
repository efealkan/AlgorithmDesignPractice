package DivideAndConquer;

//Problem: https://www.geeksforgeeks.org/longest-common-prefix-using-divide-and-conquer-algorithm/

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String arr[] = {"chare", "chars", "characters"};

        String result = FindLongestCommonPrefix(arr, 0, arr.length-1);
        System.out.println(result);
    }

    public static String FindLongestCommonPrefix(String[] arr, int low, int high) {
        if (low >= high) return arr[low];
        int middle = (low+high)/2;

        StringBuilder common = new StringBuilder();

        String left = FindLongestCommonPrefix(arr, low, middle);
        String right = FindLongestCommonPrefix(arr, middle+1, high);

        int length = Math.min(left.length(), right.length());

        for (int i=0; i<length; i++) {
            if (left.charAt(i) == right.charAt(i)) {
                common.append(left.charAt(i));
            } else break;
        }
        return common.toString();
    }
}
