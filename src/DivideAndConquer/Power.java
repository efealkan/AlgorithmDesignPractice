package DivideAndConquer;

public class Power {
    public static void main(String[] args) {
        System.out.println(Power(2, 3));
        System.out.println(Power(7, 2));
        System.out.println(Power(2, 5));
        System.out.println(Power(3, 3));
    }

    public static int Power(int num, int power) {
        if (power == 1) {
            return num;
        }
        int extra = 1;
        if (power % 2 != 0) {
            power -= 1;
            extra = num;
        }

        int rec = Power(num, power/2);
        return rec * rec * extra;
    }
}
