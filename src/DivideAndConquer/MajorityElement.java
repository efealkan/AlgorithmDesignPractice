package DivideAndConquer;

public class MajorityElement {

    public static void main(String[] args) {
        int[] arr1 = new int[] {2, 2, 1, 1, 1, 2, 2, 2};
        System.out.println(MajorityElement(arr1));
    }

    public static int MajorityElement(int[] nums) {
        return DC(nums, 0, nums.length-1);
    }

    public static int DC(int[] nums, int low, int high) {
        if (low >= high) return nums[low];

        int middle = (low+high)/2;

        int left = DC(nums, low, middle);
        int right = DC(nums, middle+1, high);

        int countL = 0;
        int countR = 0;

        if (left == right) return left;

        if (left != -1) {
            countL = Count(nums, low, high, left);
        }
        if (right != -1) {
            countR = Count(nums, low, high, right);
        }

        if (countL > (high-low)/2) {
            return left;
        } else if (countR > (high-low)/2) {
            return right;
        }
        return -1;
    }

    public static int Count(int[] nums, int low, int high, int x) {
        int c = 0;

        for (int i=low; i<=high; i++) if (nums[i] == x) c++;
        return c;
    }
}
