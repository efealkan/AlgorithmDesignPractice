package DivideAndConquer;

//Problem from: https://www.techiedelight.com/find-square-root-using-binary-search-algorithm/

public class SquareRoot {
    public static void main(String[] args) {
        for (int i=1; i<=25; i++) {
            int res = FindSquareRoot(i, 0, i);
            System.out.println("Sqrt(" + i + ") = " + res);
        }
    }

    public static int FindSquareRoot(int x, int low, int high) {
        if (low >= high) {
            if (low*low == x) return low;
            return low-1;
        }

        int middle = (high+low)/2;
        int square = middle*middle;

        if (square == x) return middle;
        if (square < x) return FindSquareRoot(x, middle+1, high);
        return FindSquareRoot(x, low, middle);
    }
}
