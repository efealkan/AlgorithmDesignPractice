package DivideAndConquer;

//Strassen's Matrix multiplication algorithm

public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] A = new int[2][2];
        int[][] B = new int[2][2];

        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                A[i][j] = 2;
                B[i][j] = 2;
            }
        }

        int[][] res = Multiply(A, B);

        for (int i=0; i<res.length; i++) {
            for (int j=0; j<res.length; j++) {
                System.out.print(res[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] Multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] res = new int[n][n];

        if (n == 1) {
            res[0][0] = A[0][0] * B[0][0];
            return res;
        }

        @SuppressWarnings(value = "Ignore Duplicates")
        //Divide First Matrix
        int[][] a = DivideMatrix(A, 0, 0, n);
        int[][] b = DivideMatrix(A, 0, n/2, n);
        int[][] c = DivideMatrix(A, n/2, 0, n);
        int[][] d = DivideMatrix(A, n/2, n/2, n);

        //Divide Second Matrix
        int[][] e = DivideMatrix(B, 0, 0, n);
        int[][] f = DivideMatrix(B, 0, n/2, n);
        int[][] g = DivideMatrix(B, n/2, 0, n);
        int[][] h = DivideMatrix(B, n/2, n/2, n);

        //From formulas
        int[][] p1 = Multiply(a, Sub(f, h));
        int[][] p2 = Multiply(Add(a, b), h);
        int[][] p3 = Multiply(Add(c, d), e);
        int[][] p4 = Multiply(d, Sub(g, e));
        int[][] p5 = Multiply(Add(a, d), Add(e, h));
        int[][] p6 = Multiply(Sub(b, d), Add(g, h));
        int[][] p7 = Multiply(Sub(a, c), Add(e, f));

        //Merge
        int[][] res1 = Sub(Add(p5, p4), Add(p2, p6));
        int[][] res2 = Add(p1, p2);
        int[][] res3 = Add(p3, p4);
        int[][] res4 = Sub(Add(p1, p5), Sub(p3, p7));

        res = MergeMatrix(res1, res2, res3, res4);
        return res;
    }

    public static int[][] Add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] res = new int[n][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                res[i][j] = A[i][j] + B[i][j];
            }
        }
        return res;
    }

    public static int[][] Sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] res = new int[n][n];

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                res[i][j] = A[i][j] - B[i][j];
            }
        }
        return res;
    }

    public static int[][] DivideMatrix(int[][] A, int x, int y, int n) {
        int[][] res = new int[n/2][n/2];

        for (int i=0, iA=x; i<n/2; i++, iA++) {
            for (int j=0, jA=y; j<n/2; j++, jA++) {
                res[i][j] = A[iA][jA];
            }
        }
        return res;
    }

    public static int[][] MergeMatrix(int[][] res1, int[][] res2, int[][] res3, int[][] res4) {
        int n = res1.length * 2;
        int[][] res = new int[n][n];

        for (int i=0; i<n/2; i++) {
            for (int j=0; j<n/2; j++) {
                res[i][j] = res1[i][j];
            }
        }

        for (int i=0; i<n/2; i++) {
            for (int j=n/2, j2=0; j<n; j++, j2++) {
                res[i][j] = res1[i][j2];
            }
        }

        for (int i=n/2, i2=0; i<n; i++, i2++) {
            for (int j=0; j<n/2; j++) {
                res[i][j] = res1[i2][j];
            }
        }

        for (int i=n/2, i2=0; i<n; i++, i2++) {
            for (int j=n/2, j2=0; j<n; j++, j2++) {
                res[i][j] = res1[i2][j2];
            }
        }

        return res;
    }
}
