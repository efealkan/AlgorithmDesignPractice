package DivideAndConquer;

/* x = xL * 2^(n/2) + xR and y = yL * 2^(n/2) + yR
   xy = 2^n * xL * yL + 2^(n/2) * (xL * yR + xR * yL) + xR * yR
   Above equation is 4T(n/4) + O(n) which is O(n^2). Not good enough!
   So modify the equation;
   xy = 2^n * xL * yL + 2^(n/2) * ((xL + xR)*(yL + yR) - xLyL - xRyR) + xR * yR
   Above becomes 3T(n/2) + O(n) which is O(n^1.59)
 */

public class KaratsubaMultiplication {
    public static void main(String[] args) {
        long x = 1112;
        long y = 2021;
        System.out.println(Karatsuba(x, y));
    }

    public static long Karatsuba(long x, long y) {
        if (x<10 && y<10) return x*y;

        int n = Math.max(Long.valueOf(x).toString().length(), Long.valueOf(y).toString().length());
        int middle = n/2;

        long x0 = x / (long) Math.pow(10, Math.round(middle));
        long x1 = x % (long) Math.pow(10, Math.round(middle));
        long y0 = y / (long) Math.pow(10, Math.round(middle));
        long y1 = y % (long) Math.pow(10, Math.round(middle));

        long x0y0 = Karatsuba(x0, y0);
        long x1y1 = Karatsuba(x1, y1);
        long p = Karatsuba((x0+x1), (y0+y1));

        return x0y0 * (long) Math.pow(10, n) + x1y1 + (p - x1y1 - x0y0)
                * (long) Math.pow(10, Math.round(middle));
    }
}
